(ns thr.www.server
  (:require
    [org.httpkit.server :as http-kit]
    [stasis.core :as stasis]
    [thr.www.web :as web]))


(def app (stasis/serve-pages web/get-pages))


(defn run-server!
  []
  (http-kit/run-server app {:port                 8080
                            :legacy-return-value? false}))


(defn stop-server!
  "Stops the server after a maximum of 100 ms"
  [s]
  (http-kit/server-stop! s {:timeout 100}))

