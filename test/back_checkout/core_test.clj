(ns back-checkout.core-test
  (:require
    [midje.sweet :refer :all]
    [back-checkout.core :as checkout]))

(facts
  "about the checkout system"

  (let [prices-by-good {:A {:unit-price 50}
                        :B {:unit-price 30}}
        price (partial checkout/price prices-by-good)]

    (fact
      "the price for zero products is 0"
      (price "") => 0)

    (fact
      "one product costs its unit price"
      (price "A") => 50
      (price "B") => 30)))
