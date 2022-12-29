(defproject advent-of-code "0.1.0-SNAPSHOT"
  :description "Clerk + Databases"
  :url ""
  :dependencies [[metosin/compojure-api "2.0.0-alpha31"]
                 [org.clojure/clojure "1.10.1"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [io.github.nextjournal/clerk "0.12.707"]
                 [com.github.seancorfield/honeysql "2.4.962"]
                 [org.postgresql/postgresql "42.2.4"]
                 [toucan "1.15.0"]
                 [migratus "1.3.5"]
                 [aero "1.1.6"]
                 [org.clojure/tools.cli "1.0.206"]]
  :ring {:handler server/app
         :init server/init}
  :main ^:skip-aot server
  :uberjar-name "clerk+databases.jar"
  :aliases {"make-docs" ["run" "-m" "make-docs"]
            "migrate" ["run" "-m" "migrate"]}
  :profiles {:dev {:dependencies
                   [[javax.servlet/javax.servlet-api "3.1.0"]]
                   :plugins
                   [[lein-ring "0.12.5"]]}
             :uberjar {:aot :all
                       :omit-source true}}
  :repl-options {;; Defaults to 30000 (30 seconds)
                 :timeout 120000})
