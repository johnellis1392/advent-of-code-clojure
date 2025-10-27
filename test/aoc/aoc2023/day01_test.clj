(ns aoc.aoc2023.day01-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc.aoc2023.day01 :refer [part1 part2]]))

(deftest day03
  (testing "Part 1"
    (let [test-input
          "1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet"]
      (is (= (part1 test-input) 142))))

  (testing "Part 2"
    (let [test-input
          "two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen"]
      (is (= (part2 test-input) 281)))))
