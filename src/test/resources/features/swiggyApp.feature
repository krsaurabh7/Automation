Feature: Place order as guest user

  Scenario: Unable to place order as user registration is not successful
    Given launch the application url in the browser
    When user enters delivery location as "Indiranagar Bengaluru"
    And selects first result
    And clicks on Search
    And search for restaurant called "Little Bites" and clicks on it
    And adds items and their quantities to the cart
      | items  | quantity|
      | Cafe Frappe | 2 |
      | Mocha Freeze | 1 |
      | Coffee Caramello | 2 |
      | Cold Coffee Classic| 1 |
    And clicks on Checkout
    Then verifies items and quantity added in checkout page
      | items  | quantity|
      | Cafe Frappe | 2 |
      | Mocha Freeze | 1 |
      | Coffee Caramello | 2 |
      | Cold Coffee Classic| 1 |
    When clicks on New to Swiggy SIGN UP
    And enters details like "abc abc" "2352353227" "sdfsdfsf" and "abc@def.com"
    And clicks on Have a referral code
    And clicks on CONTINUE
    Then verifies Error message at Email field Email id already exists
    And changes the quantity of "Cafe Frappe" to 1



