(ns back-checkout.core)

(defn sku [c]
  (keyword (str c)))

(defn unit-price [prices-by-good good-sku]
  (get-in prices-by-good [good-sku :unit-price] 0))

(defn price [prices-by-good goods]
  (reduce
    #(+ %1 (unit-price prices-by-good (sku %2)))
    0
    goods))