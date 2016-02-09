(ns back-checkout.core-test
  (:require
    [midje.sweet :refer :all]
    [back-checkout.core :as checkout]))

(facts
  "about the checkout system"

  (let [prices-by-good {:A {:unit-price 50
                            :special-prices {3 130}}
                        :B {:unit-price 30
                            :special-prices {2 70}}
                        :C {:unit-price 100
                            :special-prices {3 200}}
                        :D {:unit-price 500
                            :special-prices {2 600}}
                        :E {:unit-price 80}}
        price (partial checkout/price prices-by-good)]

    (fact
      "the price for zero goods is 0"
      (price "") => 0)

    (fact
      "one unit of a good costs its unit price"
      (price "A") => 50
      (price "B") => 30)

    (fact
      "one unit of several goods cost the sum of their unit prices"
      (price "AB") => 80)

    (fact
      "several units of a good cost the sum of its unit prices
      if there is no special price for the given amount of goods"
      (price "AA") => 100)

    (fact
      "several units of a good cost the special price
      for the given amount of goods"
      (price "AAA") => 130)

    (fact
      "several units of goods with and without special prices"
      (price "AAABBBCCCDE") => 1000)))
