Feature: Ajio Product Scrapper

  Scenario: Scrape Men Shirts
    Given User opens Ajio website
    When User navigates to "men" category and "shirts" section
    Then validate that user is on "MEN'S" page
    When User apply "Men" gender filter
#    Then validate that "Men" filter is applied
#    And User scrapes product data and prints them
     And close Browser

