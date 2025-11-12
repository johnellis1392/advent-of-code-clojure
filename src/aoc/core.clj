(ns aoc.core
  (:require
   [clojure.string :as string]
   [aoc.util :refer [read-input-for-day years days]]
   [aoc.aoc2015.solutions :as aoc2015]
   [aoc.aoc2023.solutions :as aoc2023]))

(def solutions-by-year
  {"2015" aoc2015/solutions
   "2023" aoc2023/solutions})

;; (defn ns-for
;;   [year day]
;;   (format "aoc.aoc%d.day%02d" year day))

#_(defn run
    [year day]
    (require [(symbol (ns-for year day)) :refer '[parse part1 part2]] :reload)
    (let [input (read-input-for-day year day)
          input (parse input)]
      (println (format "%d, Day %d, Part 1: %s" year day (part1 input)))
      (println (format "%d, Day %d, Part 2: %s" year day (part2 input)))))

(defn run
  [year day]
  (cond
    (not (contains? years year)) (println "Invalid Year: " year)
    (not (contains? days day)) (println "Invalid Day: " day)
    :else (let [{:keys [parse part1 part2]} (-> solutions-by-year (get year) (get day))
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
