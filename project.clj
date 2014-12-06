(defproject web.demo.01 "2014.12.02"
  :min-lein-version "2.0.0"
;  :java-cmd "C:\\jdk1.8.0_05\\bin\\java.exe"
  :java-source-paths ["src"]
  :javac-opts ["-target" "1.6" "-source" "1.6" "-xlint:-options"]
  :prep-tasks ["javac"]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.2.2"]
                 [de.ubercode.clostache/clostache "1.4.0"]
                 [selmer "0.7.6"]
                 [enlive "1.1.5"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler web.demo.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
