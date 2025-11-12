(ns aoc.aoc2023.day03-test
  (:require [aoc.aoc2023.day03 :refer [part1 part2 parse]]
            [clojure.test :refer [deftest testing is]]))

(def test-input
  "467..114..
   ...*......
   ..35..633.
   ......#...
   617*......
   .....+.58.
   ..592.....
   ......755.
   ...$.*....
   .664.598..")

(deftest aoc2023-day03
  (testing "Part 1"
    (is (= (part1 (parse test-input)) 4361)))

  (testing "Part 2"
    (is (= (part2 (parse test-input)) 467835))))
