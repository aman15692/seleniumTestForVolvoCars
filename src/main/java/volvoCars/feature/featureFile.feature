Feature: Validate volovoCars Home Page
  Description: The purpose of this test to validate volvo cars page via automation

  Scenario: validate volvo cars home page
    Given user navigates to the volvocars application
    When user log-in to the application then verify policy and aceept cookies
    Then user is on volvo home page
    Then user click on menu and verify all menu items
#    Then user click on our cars and verify all cars category
