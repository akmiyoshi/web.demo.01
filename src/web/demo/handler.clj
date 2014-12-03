(ns web.demo.handler
  (:use [web.demo.views])
  (:use [clojure.pprint :only (pprint pp)])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.content-type :as content-type]
            [ring.util.response :as response]))

(def call-count (atom 0))

(defn- log-request
  [$request]
  (swap! call-count inc)
  (println "[ACCESS] " (:request-method $request) (:uri $request) @call-count "time(s)"
           ;(str $request)
           )
  nil)

(defroutes app-routes
  (ANY "/*" $request (log-request $request) nil)
  (GET "/" [] (str "Hello World こんにちは 世界 " (new java.util.Date)))
  (GET "/*.html" $request
       (println "Accessing /*.html ..." (:uri $request))
       (println (str $request))
       (let [$res (response/resource-response (:uri $request) {:root "public"})]
         (when $res (response/content-type $res "text/html;charset=UTF-8"))))
  (GET "/ih" []
       (println "Accessing /ih ...")
       (response/resource-response "/index.html" {:root "public"}))
  (GET "/ip" [] (-> (response/resource-response "/index.page" {:root "public"})
                  (response/content-type "text/html;charset=UTF-8")
                  ))
  (GET "/cp1" [] (clostache-page-1))
  (GET "/cp2" [] (clostache-page-2))
  (GET "/tp" [] (table-page))
  (GET "/mt" [] (main-template))
  (GET "/sp1" [] (selmer-page-1))
  (GET "/sp2" [] (selmer-page-2))
  (GET "/ep2" [] (enlive-page-2))
  (GET "/video/:id" [id] (println id) (str "id2=" id))
  (GET "/user/:user" [user x] (println user) (str " user=" user " x=" x))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> 
    (handler/site app-routes)
    (content-type/wrap-content-type
      {:mime-types {"page" "text/html;charset=UTF-8"}})))
