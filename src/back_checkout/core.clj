(ns back-checkout.core)

(defn sku [c]
  (keyword (str c)))

(defn unit-price [prices-by-good good-sku]
  (get-in prices-by-good [good-sku :unit-price] 0))

(defn price [prices-by-good goods]
  (->> goods
       (map sku)
       frequencies
       (map #(if-let
              [special-price (get-in prices-by-good
                                     [(first %) :special-prices (second %)])]
              special-price
              (* (second %) (unit-price prices-by-good
                                        (first %)))))
       (reduce +)))