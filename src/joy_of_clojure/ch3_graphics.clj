(ns joy-of-clojure.ch3-graphics)

(def frame (java.awt.Frame.))

(.setVisible frame true)

(.setSize frame (java.awt.Dimension. 200 200))

(def gfx (.getGraphics frame))


(defn clear [g] (.clearRect g 0 0 200 200))


(defn f-values [f xs ys]
  (for [x (range xs) y (range ys)]
    [x y (rem (f x y) 256)]))

(defn draw-values [f xs ys]
  (clear gfx)
  (.setSize frame (java.awt.Dimension. xs xs))
  (doseq [[x y v] (f-values f xs ys)]
    (.setColor gfx (java.awt.Color. v v v))
    (.fillRect gfx x y 1 1)))

(draw-values bit-and 256 256)

(draw-values + 256 256)

(draw-values * 256 256)
