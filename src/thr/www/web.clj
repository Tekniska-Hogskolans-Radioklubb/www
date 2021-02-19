(ns thr.www.web
  (:require
    [stasis.core :as stasis]
    [hiccup.page :refer [html5]]
    ))


(defn layout-page
  []
  (html5
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name    "viewport"
             :content "width=device-width, initial-scale=1.0"}]
     [:title "THR"]
     [:link {:rel "stylesheet" :href "/static/tachyons.min.css"}]]
    [:body
     [:div "THR"]
     ]))


;; This function should - in essence - just return a map of paths => content,
;; where the content may be HTML-strings, file content etc.
;; An optimization Stasis understands is to replace the content with a lambda function
;; which returns the content when called.
;;
;; Simply merging multiple such maps together is convenient way of composing the site,
;; but conflicting paths will be silently overwritten. Stasis provides a helper function
;; which detects and reports such conflicts upon merge - stasis/merge-page-sources.
;; The helper is not currently used.
(defn get-pages []
  (merge
    (stasis/slurp-directory "site/public" #".*\.(html|css|js)$")
    {"/test/" (fn [request] (layout-page))}))
