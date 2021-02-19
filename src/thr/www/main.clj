(ns thr.www.main
  "This namespace contains the entry point for command line execution"
  (:require
    [clojure.tools.cli :as tools.cli]
    [thr.www.server :as server]
    [thr.www.export :as export])

  ;; Make sure that this ns is compiled to a proper java class
  (:gen-class))


(defn print-help
  "Prints the cli usage help"
  [opts-summary]
  (println "Usage:")
  (println "  clj -m thr.www.main <cmd> [opts ...]")
  (println "cmd:")
  (println "  export   - generates static artifacts")
  (println "  serve    - Starts a development webserver")
  (when opts-summary
    (println "opts:")
    (println opts-summary)))


;; The cli options in a format clojure.tools.cli understands.
(def cli-options
  [["-o" "--output DIR" "Output directory"
    :default "dist"]
   ["-p" "--port PORT" "Port number for webserver"
    :default 8080
    :parse-fn (fn [x] (Integer/parseInt x))
    :validate [(fn [x] (< 0 x 0x10000)) "Must be a number between 0 and 65536"]]
   ])


;; Similar to "public static void main" entry point in java
(defn -main
  "Parses command line arguments and performs the requested action"
  [& args]
  (let [cli-opts (tools.cli/parse-opts args cli-options)    ;; Parse command line arguments
        cmd (first (:arguments cli-opts))                   ;; Extract command
        opts (:options cli-opts)                            ;; Extract parsed options
        summary (:summary cli-opts)]
    (case cmd
      "serve" (server/run-server! (assoc opts :auto-reload? true))
      "export" (export/export opts)
      ;; Default action:
      (print-help summary))))
