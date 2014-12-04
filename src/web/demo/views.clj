(ns web.demo.views
  (:use [clojure.pprint :only (pprint pp)])
  (:require 
    [clostache.parser :as clostache]
    [net.cgrand.enlive-html :as enlive]
    [hiccup.page :as hiccup]
    [selmer.parser :as selmer]
    ))

(defn clostache-page-1 []
  (clostache/render "<body>
  Hello(こんにちは), {{name}}!
</body>" {:name "Felix"})
  )

(defn clostache-page-2 []
  (clostache/render-resource "templates/hello.mustache" {:name "Michael"})
  )

(def records
  [
   {:name "John Doe" :age 34 :住所 "アメリカ"}
   {:name "Peter Simpson" :age 25 :住所 "フランス"}
   ])

(defn table-page []
  (hiccup/xhtml
    [:head
     [:title "Hello World こんにちは世界"]
     (hiccup/include-css "/css/style.css")]
    [:body
     [:h1 "Hello World こんにちは世界"]
     [:table#table1 {:border 1}
      [:tr [:td "名前"] [:td "年齢"] [:td "住所"]]
      (for [$rec records]
        [:tr [:td (:name $rec)] [:td (:age $rec)] [:td (:住所 $rec)]]
        )
      ]
     ]))

(enlive/deftemplate main-template "templates/application.html"
  []
  [:head :title] (enlive/content "Enlive starter kit"))

(defn selmer-page-1 []
  (selmer/render "Hello {{name}}!" {:name "Yogthos"})
  )

(defn selmer-page-2 []
  (selmer/render "Hello {{name}}!" {:name "Yogthos"})
  (selmer/render-file "templates/selmer.html" {:name "John" :records records})
  )

(enlive/defsnippet snippet-product "templates/product.html" [:div.header]
  [{name :name, desc :desc, price :price maker :maker, url :url}]
  [:h1] (enlive/content name)
  [:h2] (enlive/transform-content(enlive/replace-vars {:PRODUCT_NAME name}))
  [:div.description] (enlive/content desc)
  [:div.price] (enlive/content price)
  [:a.maker] (enlive/content maker)
  [:a.maker] (enlive/set-attr :href url))

;(enlive/deftemplate template-product-list "templates/product.html"
;  [products]
;  [:div.header]
;  (enlive/substitute (map #(snippet-product %) products)))

(enlive/deftemplate template-product-list "templates/product.html"
  [products]
  [:div.header]
  (let [x (map #(snippet-product %) products)]
    (pprint x)
    (enlive/substitute x)))

(def product-list
  [{:name "BOLT"
    :desc "COMBINATION WALL CHARGER AND BATTERY BACKUP"
    :price "$59.99"
    :maker "FLUXMOB"
    :url "http://www.fluxmob.com"}
   {:name "HiddenRadio2"
    :desc "The world's simplest, most advanced wireless multispeaker for iPhone + iPad"
    :price "$149.00"
    :maker "HIDDEN"
    :url "http://www.hiddenradiodesign.com/"}]
  )

(defn enlive-page-2 []
  (template-product-list product-list)
  )

;(println (apply str (template-product-list product-list)))

;(pprint (template-product-list product-list))
