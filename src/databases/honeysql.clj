;; ## Introduction to Honey SQL
;; ### Why HoneySQL?
;; This library allows you to write Clojure-like SQL queries

(ns honeysql
  (:require [clojure.java.jdbc :as j]
            [config :refer [config]]
            [honeysql.core :as sql]))

;; Get postgres configuration
(def pg-db (config :postgres))

;; Pass the config to JDBC (shortened to j). Use `query` method to query a table
(j/query pg-db ["select id from pantry"])

;; Querying table using HoneySQL 
(j/query pg-db (-> {:select [:id] :from [:pantry]}
                   (sql/format)))

;; example 2 using Where Clause
;; using JDBC
(j/query pg-db ["select id from pantry where id=2"])

;; same using HoneySQL
(j/query pg-db (-> {:select [:id] :from [:pantry] :where [:= :id 2]}
                   (sql/format)))

;; the above query makes a Prepared Statement that looks like this:
(-> {:select [:id] :from [:pantry] :where [:= :id 2]}
                   (sql/format))