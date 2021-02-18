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


(defn get-pages []
  (merge
    (stasis/slurp-directory "site" #".*\.(html|css|js)$")
    {"/test/" (fn [request] (layout-page))}))
