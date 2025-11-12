(defproject advent-of-code-clojure "0.1.0-SNAPSHOT"
  :description "Advent of Code, in Clojure"
  :url "https://github.com/johnellis1392/advent-of-code-clojure"
  :min-lein-version "2.0.0"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [clj-http "3.13.1"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.5.1"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler aoc.handler/app}
  :repl-options {:init-ns aoc.core}
  :main ^:skip-aot aoc.core
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.2"]]}})
