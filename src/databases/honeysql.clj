(ns honeysql
  (:require 
   [toucan.db :refer [*db-connection*]]
   [toucan.db :as db]
   [honeysql.core :as sql]))