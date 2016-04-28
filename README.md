# HomeAwayTasks
The page classes are created under the package "com.homeaway.pages"
The test cases classes are created under the package "com.homeaway.testcases"

Testrunner class
Provided a testrunner class to run all the 3 tests through commandline 

TestSuite class
A JUnit TestSuite class to run all the 3 tests 

Framework:
Simple and light framework using JUnit and PageObject design pattern

Assumptions:
The testdata is static and is provided by the test designer in the testcases

Test Case #1 : Submit an Order for iPhone 4s
 * This is the test case to perform validation related to Submitting an Order
 * 
 * 1) Open the Shopping portal on fireforx browser
 * 
 * 2) Search for iPhone 4s by typing in the search box
 * 
 * 3) Select the correct item using the link description
 * 
 * 4) Add the item to the cart
 * 
 * 5) Checkout
 * 
 * 6) Verify the Cost and checks if the total cost is correct summation of other
 * costs
 
 Test Case #2: Update Profile
 This is the test script to perform Account Update validations
 * 1) Script Logs in to an existing account
 * 2) Navigates to the profile page
 * 3) Updates the nickname to a random name
 * 4) Updates the display name to the above name
 * 5) Navigates back to the home page
 * 6) Logout
 * 7) Logs back in using the same account
 * 8) Navigates to the profile page and validates the changes are still existing
 * 9) Reverts back the nickname and display name to initial values for the next run
 
 Test Case #3: Remove from cart and verify empty cart message
 * This is the test case to perform the cart validation
 * 
 * 1) The script looks for the item based on passed parameter; It adds the item
 * to the cart based on passed parameter;
 * 
 * 2) Removes the item from the cart
 * 
 * 3) Validates the empty cart message is displayed
