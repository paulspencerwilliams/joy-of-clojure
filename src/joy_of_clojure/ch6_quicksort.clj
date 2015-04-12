(ns joy-of-clojure.ch6-quicksort)

(defn rand-ints [n]
  (take n (repeatedly #(rand-int n))))

(defn sort-parts [work]
  (lazy-seq
    (loop [[part & parts] work]
      (if-let [[pivot & xs] (seq part)]
        (let [smaller? #(< % pivot)]
          (recur (list*
                   (filter smaller? xs)
                   pivot
                   (remove smaller? xs)
                   parts)))
        (when-let [[x & parts] parts]
          (cons x (sort-parts parts)))))))

(defn qsort [xs]
  (sort-parts (list xs)))

(qsort '( 4 2 5 1 1))

(first (qsort (rand-ints 2000000)))
