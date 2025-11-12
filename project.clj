(defproject default "0.1.0-SNAPSHOT"
  :description "Advent of Code, in Clojure"
  :url "https://github.com/johnellis1392/advent-of-code-clojure"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [clj-http "3.13.1"]]
  :repl-options {:init-ns aoc.core}
  :main ^:skip-aot aoc.core)
