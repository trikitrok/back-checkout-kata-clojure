(ns back-checkout.core)

(defn price [prices-by-good goods]
  (if (empty? goods)
    0
    (prices-by-good (keyword (str (first goods))))))