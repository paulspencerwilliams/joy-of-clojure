(ns joy-of-clojure.joy-of-clojure.ch8.2.dountil)

(defmacro do-until [& clauses]
  (when clauses
    (list 'clojure.core/when (first clauses)
          (if (next clauses)
            (second clauses)
            (throw (IllegalArgumentException.
                     "do-until requires an even number of forms")))
          (cons 'do-until (nnext clauses))))
  )

(macroexpand-1 '(do-until true (prn 1) false (prn 2)))

(require '[clojure.walk :as walk])

(walk/macroexpand-all '(do-until true (prn 1) false (prn 2)))

(do-until true (prn 1) false (prn 2))