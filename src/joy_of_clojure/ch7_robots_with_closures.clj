(ns joy-of-clojure.ch7-robots-with-closures)

(def bearings [{:x 0 :y 1}                                  ;north
               {:x 1 :y 0}                                  ;east
               {:x 0 :y -1}                                 ;south
               {:x -1 :y 0}])                               ;west

(defn forward [x y bearing-num]  
  [(+ x (:x (bearings bearing-num)))
     (+ y (:y (bearings bearing-num)))])


(forward 5 5 0)
(forward 5 5 1)
(forward 5 5 2)

(defn bot [x y bearing-num] ;https://forums.manning.com/posts/list/34153.page#p93987
  {:coords  [x y]
   :bearing ([:north :east :south :west] bearing-num)
   :forward (fn [] (bot (+ x (:x (bearings bearing-num)))
                        (+ y (:y (bearings bearing-num)))
                        bearing-num))
   :turn-right (fn [] (bot x y (mod (+ bearing-num 1) 4)))
   :turn-left (fn [] (bot x y (mod (- bearing-num 1) 4)))})


(:coords (bot 5 5 0))

(:bearing (bot 5 5 0))

(:coords ((:forward (bot 5 5 0))))

(:bearing ((:forward ((:forward ((:turn-right (bot 5 5 0))))))))

(:bearing ((:forward ((:forward ((:turn-left (bot 5 5 0))))))))

(:coords ((:forward ((:forward ((:turn-right (bot 5 5 0))))))))

(defn mirror-bot [x y bearing-num]
  {:coords  [x y]
   :bearing ([:north :east :south :west] bearing-num)
   :forward (fn [] (mirror-bot (- x (:x (bearings bearing-num)))
                        (- y (:y (bearings bearing-num)))
                        bearing-num))
   :turn-right (fn [] (mirror-bot x y (mod (- bearing-num 1) 4)))
   :turn-left (fn [] (mirror-bot x y (mod (+ bearing-num 1) 4)))})

(:bearing ((:forward ((:forward ((:turn-right (mirror-bot 5 5 0))))))))

(:bearing ((:forward ((:forward ((:turn-left (mirror-bot 5 5 0))))))))

(:coords ((:forward ((:forward ((:turn-right (mirror-bot 5 5 0))))))))