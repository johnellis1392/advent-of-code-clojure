(ns aoc.runner
  (:require [aoc.util :refer [read-input-for-day]]))

(defn ns-for
  [year day]
  (format "aoc.aoc%d.day%02d" year day))

(defn run
  [year day]
  (require [(symbol (ns-for year day)) :refer '[parse-input part1 part2]] :reload)
  (let [input (read-input-for-day year day)
        input (parse-input input)]
    (println (format "%d, Day %d, Part 1: %s" year day (part1 input)))
    (println (format "%d, Day %d, Part 2: %s" year day (part2 input)))))

(comment
  (run 2023 3))
