### 1. Which of the following activities cannot be automated
- [ ] Test execution
- [ ] Exploratory testing
- [x] Discussing testability issues
- [ ] Test data generation

### 2. How do we describe a good unit test?
- [ ] Flawless, Ready, Self-healing, True, Irresistible
- [ ] Red, Green, Refactor
- [x] Fast, Repeatable, Self-validating, Timely, Isolated
- [ ] Tests should be dependent on other tests

### 3. When is it a good idea to use XPath selectors
- [ ] When CSS or other selectors are not an option or would be brittle and hard to maintain
- [ ] When we need to find an element based on parent/child/sibling relationship
- [ ] When an element is located deep within the HTML (or DOM) structure
- [x] All the above

### 4. Describe the TDD process
Test driven development - a process based on test firs then code rule. The test is written first, then code.
- The cycle is:
    - Add a test
    - Run tests
    - Write code to pass the test
    - Run tests
    - Refactor (clean) code
    - Start over with next app functionality part

### 5. Write 2 test cases or scenarios for a String Calculator application, which has a method ```calculate()``` that takes a string of two numbers separated by a comma as input, and returns the sum.

- **Given** the input " " **When** the method ```calculate()``` is called **Then** I should see "String has no numbers" exception message.
- **Given** the input "1,2,3" **When** the method ```calculate()``` is called **Then** I should see "String must have 2 numbers only" exception message.
