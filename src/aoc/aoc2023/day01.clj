(ns aoc.aoc2023.day01
  (:require [clojure.string :as str]))

(defn part1 [input]
  (->>
   input
   str/split-lines
   (map str/trim)
   (filter not-empty)
   (map #(str/replace % #"[^1-9]" ""))
   (map (fn [line] (str (first line) (last line))))
   (map #(if (empty? %) 0 (Integer/parseInt %)))
   (reduce + 0)))

(defn parse-line [line]
  (if (empty? line)
    line
    (cond
      (str/starts-with? line "one") (str \1 (parse-line (subs line 1)))
      (str/starts-with? line "two") (str \2 (parse-line (subs line 1)))
      (str/starts-with? line "three") (str \3 (parse-line (subs line 1)))
      (str/starts-with? line "four") (str \4 (parse-line (subs line 1)))
      (str/starts-with? line "five") (str \5 (parse-line (subs line 1)))
      (str/starts-with? line "six") (str \6 (parse-line (subs line 1)))
      (str/starts-with? line "seven") (str \7 (parse-line (subs line 1)))
      (str/starts-with? line "eight") (str \8 (parse-line (subs line 1)))
      (str/starts-with? line "nine") (str \9 (parse-line (subs line 1)))
      (contains? #{\1 \2 \3 \4 \5 \6 \7 \8 \9} (first line)) (str (first line) (parse-line (subs line 1)))
      :else (parse-line (subs line 1)))))

(defn part2 [input]
  (->>
   input
   str/split-lines
   (map str/trim)
   (filter not-empty)
   (map parse-line)
   (map (fn [line] (str (first line) (last line))))
   (map #(Integer/parseInt %))
   (reduce + 0)))

(defn -main []
  (let [filename "input.txt"
        input (slurp filename)]
    (println "2023 Day 1, Part 2: " (part2 input))))
