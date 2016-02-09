(ns back-checkout.core)

(defn sku [c]
  (keyword (str c)))

(defn price [prices-by-good goods]
  (prices-by-good (sku (first goods)) 0))