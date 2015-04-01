(ns joy-of-clojure.ch6-persistent-toy)

(def baselist (list :barnabas :adam))

(def lst1 (cons :willie baselist))

(def lst2 (cons :phoenix baselist))

(= (next lst1) (next lst2))

(identical? (next lst1) (next lst2))


{:val 5 :L nil :R nil}

(defn xconj [t v]
  (cond 
    (nil? t) {:val v :L nil :R nil}
    (< v (:val t)) {:val (:val t) :L (xconj (:L t) v)  :R (:R t)}
    :else          {:val (:val t) :L (:L t) :R (xconj (:R t) v)}))  


(xconj nil 5)

(def tree1 (xconj nil 5))

(def tree1 (xconj tree1 3))

(def tree1 (xconj tree1 2))

(def tree2 (xconj tree1 7))