(ns user
  "The namespace in which most REPLs start by default."
  (:require
    [clojure.tools.namespace.repl :refer [set-refresh-dirs refresh]]
    [thr.www.main]
    [thr.www.server :as server]))


;; Ensure we only refresh the code we care about upon reload.
(set-refresh-dirs "src")


;; A pace to hold the web server when using the REPL.
(defonce server nil)


(defn start
  "Starts a development server"
  []
  (when-not server
    (alter-var-root #'server
      (constantly (server/run-server!)))))


(defn stop
  "Stops the development server"
  []
  (when server
    (server/stop-server! server)
    (alter-var-root #'server (constantly nil))))


(defn reset []
  "Reloads the code and restarts the dev system."
  (stop)
  (refresh :after 'user/start))


(comment
  server
  (start)
  (stop)
  (reset)
  )

