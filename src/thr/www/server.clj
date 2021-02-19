(ns thr.www.server
  "This namespace contains the code for serving the site during development."
  (:require
    [org.httpkit.server :as http-kit]
    [ring.middleware.reload :refer [wrap-reload]]
    [stasis.core :as stasis]
    [thr.www.web :as web]))


;; (stasis/serve-pages ...) returns a handler for Ring compatible web servers
(def app (stasis/serve-pages web/get-pages))


(defn run-server!
  "Starts a development server.

  opts:
    :port - The port on which to serve the content. Default 8080
    :auto-reload? - Whether to continuously reload changed namespaces or not. Default false"
  ([]
   (run-server! {}))
  ([opts]
   (let [port (:port opts 8080)
         auto-reload? (:auto-reload? opts false)
         handler (if auto-reload? (wrap-reload #'app) app)]
     (println "Serving on" (str "http://127.0.0.1:" port))
     (http-kit/run-server handler {:port                 port
                                   :legacy-return-value? false}))))


(defn stop-server!
  "Stops a running server s after a maximum of 100 ms"
  [s]
  (when-let [result (http-kit/server-stop! s {:timeout 100})]
    ;; If we are here result is a promise which is delivered once the server stops
    ;; Wait for it to be delivered
    (deref result)))
