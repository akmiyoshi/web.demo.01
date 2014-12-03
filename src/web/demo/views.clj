(ns web.demo.views
  (:require 
    [clostache.parser :as mustache]
    [selmer.parser :as selmer]
    [net.cgrand.enlive-html :as html]
    [hiccup.page :as page]
    ))

(defn clostache-page-1 []
  (mustache/render "<body>
  Hello(こんにちは), {{name}}!
</body>" {:name "Felix"})
  )

(defn clostache-page-2 []
  (mustache/render-resource "templates/hello.mustache" {:name "Michael"})
  )

(def records
  [
   {:name "John Doe" :age 34}
   {:name "Peter Simpson" :age 25}
   ])

(defn table-page []
  (page/xhtml
    [:head
     [:title "Hello World こんにちは世界"]
     (page/include-css "/css/style.css")]
    [:body
     [:h1 "Hello World こんにちは世界"]
     [:table#table1 {:border 1}
      [:tr [:td "名前"] [:td "年齢"]]
      (for [$rec records]
        [:tr [:td (:name $rec)] [:td (:age $rec)]]
        )
      ]
     ]))

(html/deftemplate main-template "templates/application.html"
  []
  [:head :title] (html/content "Enlive starter kit"))

(defn selmer-page-1 []
  (selmer/render "Hello {{name}}!" {:name "Yogthos"})
  )

(defn selmer-page-2 []
  (selmer/render "Hello {{name}}!" {:name "Yogthos"})
  (selmer/render-file "templates/selmer.html" {:name "John" :records records})
  )