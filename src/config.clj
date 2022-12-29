(ns config
  (:require [aero.core :refer [read-config]]))

(def config
  (read-config "./config.edn"))
