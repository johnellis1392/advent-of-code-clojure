(ns aoc.aoc2023.solutions
  (:require [aoc.aoc2023.day01 :as day01]
            [aoc.aoc2023.day02 :as day02]
            [aoc.aoc2023.day03 :as day03]))

(def solutions
  {"01" {:parse day01/parse :part1 day01/part1 :part2 day01/part2}
   "02" {:parse day02/parse :part1 day02/part1 :part2 day02/part2}
   "03" {:parse day03/parse :part1 day03/part1 :part2 day03/part2}})
