(ns aoc.util
  (:require [clj-http.client :as client]
            [clojure.string :as s]
            [clojure.java.io :refer [file make-parents]]))

(def years (range 2015 2025))

(def input-dir "input")

(defn input-filename-for
  [day]
  (str "day" day ".input.txt"))

(defn path-for
  [year day]
  (file (str "./" input-dir "/" year "/" (input-filename-for day))))

(def session-id (slurp ".session-id"))

(def base-url "https://adventofcode.com")

(defn url-for
  [year day]
  (str base-url "/" year "/day/" day))

(defn input-url-for
  [year day]
  (str (url-for year day) "/input"))

(defn fetch-input-for-day
  [year day]
  (let [input-url (input-url-for year day)]
    (client/get input-url
                {:headers {"Cookie" (str "session=" session-id)}})))

(defn download-input-for-day
  [year day]
  (let [input-path (path-for year day)]
    (if (not (.exists input-path))
      (do
        (make-parents input-path)
        (->> (fetch-input-for-day year day)
             :body
             (spit  input-path)))
      (println "File " input-path " already exists; skipping."))))

(defn download-input-for-year
  [year]
  (for [day (range 1 25)]
    (download-input-for-day year day)))

(defn download-all []
  (for [year years]
    (download-input-for-year year)))

(defn read-input-for-day
  [year day]
  (download-input-for-day year day)
  (slurp (path-for year day)))
