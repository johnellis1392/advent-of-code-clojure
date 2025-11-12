(ns aoc.aoc2015.day01-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [aoc.aoc2015.day01 :refer [parse part1 part2]]))

(deftest aoc2015-day01
  (testing "parse"
    (is (= (parse "(((") [1 1 1]))
    (is (= (parse ")))") [-1 -1 -1]))
    (is (= (parse "()()()") [1 -1 1 -1 1 -1]))
    (is (= (parse "((()))") [1 1 1 -1 -1 -1]))
    (is (= (parse "((())") [1 1 1 -1 -1])))

  (testing "part1"
    (let [run #(->> % parse part1)]
      (is (= (run "(((") 3))
      (is (= (run ")))") -3))
      (is (= (run "()()()") 0))
      (is (= (run "((()))") 0))
      (is (= (run "((())") 1))))

  (testing "part2"
    (let [run #(->> % parse part2)]
      (is (= (run "(()))") 5))
      (is (= (run ")") 1))
      (is (= (run "()()())") 7)))))
