(ns aoc.aoc2023.day03
  (:require [clojure.string :as str]))


(def adjacent-point-set
  [[-1 -1] [-1 0] [-1 1]
   [0 -1] [0 1]
   [1 -1] [1 0] [1 1]])

(defn parse-input [input]
  (->>
   input
   str/trim
   str/split-lines
   (map str/trim)
   (map #(map str %))
   (map vec)
   vec))

(defn size [grid]
  [(count grid) (count (first grid))])

(defn valid-point [grid r c]
  (let [[rows cols] (size grid)]
    (and
     (>= r 0)
     (>= c 0)
     (< r rows)
     (< c cols))))

(defn add-points [[r1 c1] [r2 c2]]
  [(+ r1 r2) (+ c1 c2)])

(defn adjacents
  ([grid [r c]] (adjacents grid r c))
  ([grid r c]
   (->>
    adjacent-point-set
    (map (partial add-points [r c]))
    (filter (partial apply valid-point grid)))))

(defn grid-get [grid r c]
  (if (valid-point grid r c)
    ((grid r) c)
    nil))

(defn concat-map [f coll]
  (apply concat (map f coll)))

(defn is-digit? [s]
  (cond
    (nil? s) false
    (and (string? s) (re-matches #"[0-9]+" s)) true
    (and (char? s) (contains? #{\0 \1 \2 \3 \4 \5 \6 \7 \8 \9} s)) true
    :else false))

(defn is-symbol? [s]
  (and (not (is-digit? s)) (not (= "." s))))

(defn zip [a b] (map vector a b))
(defn enumerate [coll] (zip (range 0 (count coll)) coll))

(defn grid-get-symbols [grid]
  (->>
   grid
   enumerate
   (concat-map
    (fn [[r row]]
      (->>
       (enumerate row)
       (map
        (fn [[c value]]
          (if (is-symbol? value) [r c] [])))
       (filter seq))))))

(defn split [pred coll]
  (cond
    (empty? coll) []

    (pred (first coll))
    (cons (take-while pred coll) (split pred (drop-while pred coll)))

    :else
    (cons (take-while (comp not pred) coll) (split pred (drop-while (comp not pred) coll)))))

(defn collect-contiguous [pred coll]
  (cond
    (empty? coll) []

    (pred (first coll))
    (cons (take-while pred coll) (collect-contiguous pred (drop-while pred coll)))

    :else
    (collect-contiguous pred (drop-while (comp not pred) coll))))

(defn row-get-numbers [r row]
  (println ", count = " (count row) ", row = " row)
  (cond
    (empty? row) []

    (is-digit? (second (first row)))
    (let [numbers (take-while (comp is-digit? second) row)
          n (Integer/parseInt (reduce str "" (map second numbers)))
          points (map (fn [[c _]] [r c]) numbers)
          rest (drop-while (comp is-digit? second) row)]
      (cons [n points] (row-get-numbers r rest)))

    :else
    (let [rest (drop-while (comp not is-digit? second) row)]
      (row-get-numbers r rest))))

(defn grid-get-numbers [grid]
  (let [rows (enumerate grid)]
    (println rows)
    (map (fn [[r row]]
           (row-get-numbers r row)) rows)))

(defn grid-get-numbers [grid]
  (->>
   grid
   enumerate
   (map
    (fn [[r row]]))))

(defn grid-map
  [f grid]
  (map (fn [row] (map f row)) grid))

(defn grid-map-indexed
  [f grid]
  (map-indexed
   (fn [r row]
     (map-indexed (fn [c v] (f [r c] v)) row)) grid))

(defn grid-map-rows
  [f grid]
  (map (fn [row] (f row)) grid))

(defn grid-filter
  [f grid]
  (->> grid
       (apply concat)
       (filter f)))

(defn with-indices
  [grid]
  (grid-map-indexed (fn [[r c] v] [[r c] v]) grid))

(defn grid-filter-indexed
  [f grid]
  (->> grid
       with-indices
       (grid-filter f)))


(defn find-numbers
  [grid]
  (->> grid
       with-indices
       (grid-map-rows #(partition-by (comp is-digit? second) %))
       (apply concat)
       (filter (comp  is-digit? second first))
       (map
        (fn [coll]
          (reduce
           (fn [[indices s] [p c]] [(conj indices p) (str s c)])
           [[] ""] coll)))
       (map (fn [coll] (update-in coll [1] #(Integer/parseInt %))))))

(defn part1 [grid]
  (let [numbers (find-numbers grid)
        symbols (grid-get-symbols grid)
        symbol-adjacents
        (->> symbols
             (map (partial adjacents grid))
             (apply concat)
             set)]
    (->> numbers
         (filter
          (fn [[indices _]]
            (some (fn [index] (contains? symbol-adjacents index)) indices)))
         (map second)
         (reduce + 0))))

(defn find-gears
  [grid]
  (grid-filter-indexed (comp (partial = "*") second) grid))

(defn part2 [grid]
  (let [numbers (find-numbers grid)
        gears (find-gears grid)
        gear-adjacents
        (->> gears
             (map first)
             (map (partial adjacents grid))
             (map set))]
    (->> gear-adjacents
         (map
          (fn [indices]
            (filter
             (fn [[num-indices _]]
               (some (partial contains? indices) num-indices))
             numbers)))
         (filter #(= 2 (count %)))
         (map (partial map second))
         (map (partial reduce * 1))
         (reduce + 0))))

(defn -main []
  (let [filename "input.txt"
        input (parse-input (slurp filename))]
    (println "2023 Day 3, Part 1: " (part1 input))
    (println "2023 Day 3, Part 2: " (part2 input))))
