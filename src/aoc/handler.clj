(ns aoc.handler
  (:require
   [aoc.solutions :refer [solution-for]]
   [aoc.util :refer [download-input-for-day read-input-for-day]]
   [compojure.core :refer :all]
   [compojure.route :as route]
   [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
   [ring.middleware.json :refer [wrap-json-response]]))

(defn run-part1
  ([year day]
   (let [input (read-input-for-day year day)]
     (run-part1 year day input)))

  ([year day input]
   (let [{:keys [parse part1]} (solution-for year day)
         input (parse input)]
     {:body {:result (part1 input)}})))

(defn run-part2
  ([year day]
   (let [input (read-input-for-day year day)]
     (run-part2 year day input)))

  ([year day input]
   (let [{:keys [parse part2]} (solution-for year day)
         input (parse input)]
     {:body {:result (part2 input)}})))

(defn run-day
  ([year day]
   (let [input (read-input-for-day year day)]
     (run-day year day input)))

  ([year day input]
   (let [{:keys [parse part1 part2]} (solution-for year day)
         input (parse input)]
     {:body
      {:part1 (part1 input)
       :part2 (part2 input)}})))

(defn download-input [year day]
  (download-input-for-day year day)
  {:body {:success true}})

(defn temp [f]
  (println f)
  f)

(defroutes app-routes
  (GET "/" [] "Hello World")
  (context "/years/:year" [year]
    (context "/days/:day" [day]
      (POST "/input" [] (download-input year day))
      (POST "/part1" [] (run-part1 year day))
      (POST "/part2" [] (run-part2 year day))
      (POST "/" [] (run-day year day))))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
      (wrap-json-response)))
