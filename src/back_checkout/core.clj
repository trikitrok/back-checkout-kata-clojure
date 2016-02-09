(ns back-checkout.core)

(defn sku [c]
  (keyword (str c)))

(defn unit-price [prices-by-good good-sku]
  (get-in prices-by-good [good-sku :unit-price] 0))

(defn special-price [prices-by-good [good-sku amount]]
  (get-in prices-by-good [good-sku :special-prices amount]))

(defn price [prices-by-good goods]
  (->> goods
       (map sku)
       frequencies
       (map #(if-let
              [price (special-price prices-by-good %)]
              price
              (* (second %) (unit-price prices-by-good
                                        (first %)))))
       (reduce +)))