## Assignment

### Details
- Points: 15p total
- Deadline: End of 16th week... but be careful there :)
- Weapons: Any programming language and framework can be used **as long as** similar *functionality* and level of *maintainability* is implemented in the framework.
- Submitting assignment:
    - Make a merge request where ALL the changes are visible
    - Do not merge the branch
    - Make me assignee
    - Make me reviewer
    - DO NOT MERGE THE BRANCH

### Goals

- Add a layer of abstraction to hide setup and repetitive tasks
- Framework enables to efficiently build requests and handle API calls
- Write tests using custom developed framework (better readability and maintainability)
- Flexible test data usage and management

### Tests
Improve existing framework (if you done it with Java+RestAssured) or develop a new one and implement the following tests:
- post authentication with correct credentials returns 200
- post authentication with incorrect credentials returns message "Bad credentials"
- post authentication returns token
- post booking with wrong Accept header returns 418
- put booking should return 200
- delete booking should return 201
