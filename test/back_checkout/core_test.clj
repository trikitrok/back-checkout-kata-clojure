(ns back-checkout.core-test
  (:require
    [midje.sweet :refer :all]
    [back-checkout.core :as checkout]))

(facts
  "about the checkout system"

  (let [price (partial checkout/price {})]

    (fact
      "the price for zero products is 0"
      (price "") => 0)))
