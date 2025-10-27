(ns aoc.aoc2023.day02-test
  (:require [aoc.aoc2023.day02 :refer [part1 part2 read-input]]
            [clojure.test :refer [deftest testing is]]))

(def test-input
  "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")

(deftest day02
  (testing "Part 1"
    (is (= (part1 (read-input test-input)) 8)))

  (testing "Part 2"
    (is (= (part2 (read-input test-input)) 2286))))
