(ns migrate
  (:require [config :refer [config]]
            [migratus.core :as migratus]
            [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(def migratus-config {:store                :database
                      :migration-dir        "migrations/"
                      :init-script          "init.sql"
                      :init-in-transaction? false
                      :migration-table-name "migrations"
                      :db (config :postgres)})

(defn init [dryrun]
  (if dryrun
    (println "run init")
    (migratus/init migratus-config)))

(defn migrate [dryrun]
  (if dryrun
    (println "run migrate")
    (migratus/migrate migratus-config)))

(defn create [v dryrun]
  (if dryrun
    (println (str "run create " v))
    (migratus/create migratus-config v)))

(defn rollback [dryrun]
  (if dryrun
    (println "run rollback")
    (migratus/rollback migratus-config)))

(defn up [version dryrun]
  (if dryrun
    (println (str "run up " version))
    (migratus/up migratus-config version)))

(defn down [version dryrun]
  (if dryrun
    (println (str "run down " version))
    (migratus/down migratus-config version)))

; are repeated. 
;
; --dryrun wherever given will dryrun all commands.
;
; --init, --migrate, --help, --rollback, --dryrun are treated
; idempotent if repeated.
;
; --up and --down are not idempotent. It will execute all versions but not 
; in the order given. All the ups are executed together and all downs are 
; executed together. But the order of up and down depends on what appears last.
; So don't rely on the order. If there is an order requirement just run 
; migration script again for each version.
;
; for example: --init --migrate --down 12312 --up 123141241231 --down 1231231
;  --rollback
; should do:
;  run init
;  run migrate
;  run up 123141241231
;  run down 12312
;  run down 1231231
;  run rollback
(def cli-options
  ;; An option with a required argument
  [["-i" "--init" "Initializes the data base"]
   ["-m" "--migrate" "Migrates database to latest sql (excluding init)"]
   ["-h" "--help" "Prints help"]
   ["-r" "--rollback" "Rolls back the most recent sql timestamp"]
   [nil "--dryrun" "Prints all the commands it is going to run"]
   ["-c" "--create NAME" "Creates a new migration file with given name"
    :default []
    :update-fn conj
    :multi true]
   ["-u" "--up VERSION" "Applies (go up) just this specific sql"
    :default []
    :update-fn conj
    :multi true
    :parse-fn #(Long/parseLong %)]
   ["-d" "--down VERSION" "Undo this specific SQL."
    :default []
    :update-fn conj
    :multi true
    :parse-fn #(Long/parseLong %)]])

(defn -main [& args]
;  https://github.com/clojure/tools.cli
  (clojure.pprint/pprint config)
  (let [parsed (parse-opts args cli-options)
        errors (:errors parsed)
        options (:options parsed)
        summary (:summary parsed)
        dryrun (:dryrun options)]
    (cond
      (seq errors) (do (doseq [err errors] (println err)) 1)
      (:help options) (println summary)
      :else (doseq [[k v] options]
              (case k
                :init (init dryrun)
                :rollback (rollback dryrun)
                :migrate (migrate dryrun)
                :create (doseq [version v]
                          (create version dryrun))
                :up (doseq [version v]
                      (up version dryrun))
                :down (doseq [version v]
                        (down version dryrun))
                nil))))) ; // default ignore case.