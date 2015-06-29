(ns joy-of-clojure.ch-8-man-vs-monster)

(declare handle-things)

(declare grok-attrs grok-props)

(defmacro domain [name & body]
  `{:tag     :domain
    :attrs   {:name (str '~name)}
    :content [~@(handle-things body)]})

(defn handle-things [things]
  (for [t things]
    {:tag     :thing,
     :attrs   (grok-attrs (take-while (comp not vector?) t))
     :content (if-let [c (grok-props (drop-while (comp not vector?) t))]
                [c]
                [])}))


(defmacro grouping [name & body]
  `{:tag     :grouping,
    :attrs   {:name (str '~name)},
    :content [~@(handle-things body)]})



(defn grok-attrs [attrs]
  (into {:name (str (first attrs))}
        (for [a (rest attrs)]
          (cond
            (list? a) [:isa (str (second a))]
            (string? a) [:comment a]))))

(defn grok-props [props]
  (when props
    {:tag     :properties, :attrs nil,
     :content (apply vector (for [p props]
                              {:tag     :property,
                               :attrs   {:name (str (first p))},
                               :content nil}))}))


(def d
  (domain man-vs-monster
          (grouping people
                    (Human "A stock human")
                    (Man (isa Human)
                         "A man, baby"
                         [Name]
                         [has-beard?]))
          (grouping monsters
                    (Chupacabra
                      "A fierce, yet elusive create"
                      [eats-goats?]))))