(ns make-docs
  (:require [nextjournal.clerk :as clerk]
            [clojure.java.io :refer [file]])
  (:gen-class))

(defn files
  []
  (->> (file "src/databases")
       (file-seq)
       (map #(.getPath %))
       (filter #(.endsWith % ".clj"))))

;; (defn files
;;   []
;;   [""])

(defn -main
  []
  (clerk/build! {:paths (files) :out-path "resources/docs"}))
