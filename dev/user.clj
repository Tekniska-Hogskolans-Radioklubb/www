(ns user
  (:require
    [thr.www.main]
    [thr.www.server :as server]))


(defonce server nil)


(defn start
  []
  (when-not server
    (alter-var-root #'server
      (constantly (server/run-server!)))))


(defn stop
  []
  (when server
    (server/stop-server! server)
    (alter-var-root #'server (constantly nil))))

(comment
  (start)
  (stop)
  )

