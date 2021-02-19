(ns thr.www.export
  "This namespace contains code for generating static artifacts"
  (:require
    [stasis.core :as stasis]
    [thr.www.web :as web]))


(defn export
  "Exports a static version of the site.

  opts:
    :output - The directory in which to put the artifacts. Default \"dist\""
  [opts]
  (let [export-dir (:output opts "dist")]
    (println "Exporting to" (str export-dir))
    (stasis/empty-directory! export-dir)
    (stasis/export-pages (web/get-pages) export-dir)
    (println "Done")))
