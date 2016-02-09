(ns back-checkout.core)

(defn sku [c]
  (keyword (str c)))

(defn unit-price [prices-by-good good-sku]
  (get-in prices-by-good [good-sku :unit-price] 0))

(defn regular-price [prices-by-good [good-sku amount]]
  (* amount (unit-price prices-by-good good-sku)))

(defn special-price [prices-by-good [good-sku amount]]
  (get-in prices-by-good [good-sku :special-prices amount]))

(defn good-price [prices-by-good ordered-goods]
  (or (special-price prices-by-good ordered-goods)
      (regular-price prices-by-good ordered-goods)))

(defn price [prices-by-good goods]
  (->> goods
       (map sku)
       frequencies
       (map #(good-price prices-by-good %))
       (reduce +)))
