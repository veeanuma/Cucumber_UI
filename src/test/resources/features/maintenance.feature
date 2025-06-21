Feature: Maintenance Request Management

  Background: User is Logged In
    Given User is on login page
    When User enters username and password
    And User clicks on login button
    Then User should be logged in successfully

  Scenario: Create a new maintenance request
    Given User clicks on maintenance button
    Given User clicks on Add New button
#    When User selects portfolio "Test Portfolio"
#    And User selects property "Test Property"
#    And User clicks confirm button
#    And User selects request type "Maintenance"
#    And User enters problem description "Test maintenance issue"
#    And User clicks next button
#    And User selects category "General"
#    And User selects subcategory "Repair"
#    And User enters location "Building A, Room 101"
#    And User selects priority "High"
#    And User selects assignee "John Doe"
#    And User uploads media file "test.jpg"
#    And User enters media notes "Test media notes"
#    And User selects vendor "Test Vendor"
#    And User sets first availability date and time
#    And User sets second availability date and time
#    And User clicks create request button
#    Then Maintenance request should be created successfully
