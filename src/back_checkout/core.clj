(ns back-checkout.core)

(defn sku [c]
  (keyword (str c)))

(defn price [prices-by-good goods]
  (if (empty? goods)
    0
    (prices-by-good (sku (first goods)))))