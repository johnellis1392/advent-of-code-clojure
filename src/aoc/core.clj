(ns aoc.core
  (:require
   [aoc.solutions :refer [solution-for]]
   [aoc.util :refer [days read-input-for-day years]]
   [clojure.string :as string]))

(defn run
  [year day]
  (cond
    (not (contains? years year)) (println "Invalid Year: " year)
    (not (contains? days day)) (println "Invalid Day: " day)
    :else (let [{:keys [parse part1 part2]} (solution-for year day)
                input (read-input-for-day year day)
                input (parse input)]
            (println year ", Day " day ", Part1: " (part1 input))
            (println year ", Day " day ", Part2: " (part2 input)))))

(defn usage []
  (println "Usage: lein run [year]/day[day]"))

(defn -main [& args]
  (println (first args))
  (cond
    (empty? args) (usage)
    (not (= 1 (count args))) (usage)
    :else
    (let [[_ year day] (re-matches #"^([0-9]{4})/day([0-9]{2})$" (string/trim (first args)))]
      (run year day))))
