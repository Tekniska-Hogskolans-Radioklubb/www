(ns thr.www.main
  (:require [stasis.core :as stasis]
            [thr.www.web :as web]))


(defn hello-world
  []
  (println "Hello, world!"))


(def export-dir "dist")


(defn export []
  (stasis/empty-directory! export-dir)
  (stasis/export-pages (web/get-pages) export-dir))


(comment
  (hello-world)

  (export)
  )