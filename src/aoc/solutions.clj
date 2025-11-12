(ns aoc.solutions
  (:require [aoc.aoc2015.solutions :as aoc2015]
            [aoc.aoc2023.solutions :as aoc2023]))

(def solutions-by-year
  {"2015" aoc2015/solutions
   "2023" aoc2023/solutions})

(defn solution-for [year day]
  (-> solutions-by-year (get year) (get day)))
