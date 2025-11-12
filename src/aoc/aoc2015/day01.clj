(ns aoc.aoc2015.day01
  (:require [clojure.string :as string]))

(defn parse [input]
  (->> input
       string/trim
       vec
       (map #(if (= % \() 1 -1))))

(defn part1 [input]
  (reduce + 0 input))

(defn part2 [input]
  (count (take-while #(>= % 0) (reductions + 0 input))))
