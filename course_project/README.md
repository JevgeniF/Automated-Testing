# ICD0004 - Course Project

Graded project for ICD0004 automated testing course.

# Project details
- **Technological stack**:
    - Only constraint is that your project README has info regarding:
      - what stack is used
      - installation, compilation and building in the command line
      - how to run tests in the command line
      - how to specify inputs *without changing code itself*
      - how to run the app in the command line
- **Submitting assignment**:  
    - Submit the assignment when all parts (practical and theoretical) are done
    - Create a pull/merge request in Gitlab and assign me as reviewer and assignee
    - **You can ask for feedback until the end of week 16 without any penalties or point deductions**

# Project description

Create an application which reads an input file of city names and produces and output file with weather information about those cities:
- Main details:
    - city name
    - Coordinates in the format latitude, longitude: "59.44, 24.75"
    - temperatureUnit
- Current weather report:
    - weather report date
    - temperature in Celsius
    - humidity
    - pressure
- Following 3 day forecast (not counting current date):
    - forecast date
    - average temperature in Celsius
    - average humidity
    - average pressure

Use the [OpenWeatherMap API](https://openweathermap.org/api):
- [current weather data](https://openweathermap.org/current)
- [5 day weather forecast](https://openweathermap.org/forecast5) 

### Example output file

```json
{
  "weatherReportDetails": {
    "city": "Tallinn",
    "coordinates": "59.44,24.75",
    "temperatureUnit": "Celsius"
  },
  "currentWeatherReport": {
    "date": "2019-10-01",
    "temperature": -5,
    "humidity": 75,
    "pressure": 985
  },
  "forecastReport": [
    {
      "date": "2019-10-01",
      "weather": {
        "temperature": -5,
        "humidity": 75,
        "pressure": 985
      }   
    },
    {
      "date": "2019-10-02",
      "weather": {
        "temperature": -5,
        "humidity": 75,
        "pressure": 985
      }   
    },
    {
      "date": "2019-10-03",
      "weather": {
        "temperature": -5,
        "humidity": 75,
        "pressure": 985
      }   
    }   
  ]   
}
```

# Project parts

## 1. Main details and current weather - 15p

**Note**: Application reads input as string parameter and outputs result to stdout.

### ACCEPTANCE CRITERIA
- COMPLETED: City name can be provided as a string input
- COMPLETED: The output is a weather report with main details and current weather data
- COMPLETED: Main details part has city, coordinates and temperatureUnit properties
- COMPLETED: Coordinates are in the format lat, log. Example: "59.44,24.75"
- COMPLETED: Current weather part has date, temperature, humidity and pressure properties
- COMPLETED: Output is a JSON object
- COMPLETED: At least 3 unit tests exists
- COMPLETED: Mock integration test exists for OWM for the main details data
- COMPLETED: OWM integration is covered by integration tests for the main details data

## 2. Forecast - 15p

**Note**:
- Application reads input as string parameter and outputs result to stdout
- part 1 acceptance criteria apply as well

### ACCEPTANCE CRITERIA
- COMPLETED: City name can be provided as a string input
- COMPLETED: The output is a weather report with main details and current weather data AND forecast report
- COMPLETED: Forecast report part has date, temperature, humidity and pressure for each day
- COMPLETED: Forecast calculates average of temperature, humidity and pressure
- COMPLETED: Forecast is in ascending order (2020-10-01, 2020-10-02, 2020-10-03)
- COMPLETED: At least 3 unit tests exists
- COMPLETED: Mock integration test exists for OWM for the forecast data
- COMPLETED: OWM integration is covered by integration tests for the forecast data

## 3. Read city name from file and produce a JSON file for given city - 15p

**Note**: 
- Application reads input from a file and outputs result to JSON file.
- Refactor your existing tests if need be.

### ACCEPTANCE CRITERIA
- COMPLETED: Only specific file format is allowed (you choose which: txt, csv, json, plain, docx)
- COMPLETED: Display error message if an unsupported file is provided
- COMPLETED: Display error message when file is missing
- COMPLETED: Write 3 integration tests to test integrations between the weather report application and file reader and writer

## 4. Read multiple city names from file and produce a JSON output file for each city - 15p

**Note**: 
- Application reads input from a file, where there can be multiple city names, and outputs the result for each city to its own JSON file.
- Refactor your existing tests if need be.

### ACCEPTANCE CRITERIA
- Can read multiple cities from file
- Can create weather report for given cities into separate JSON files
- Log INFO message when existing weather report file for city is being overwritten
- When an error occurs for invalid city name(s) then weather reports are created only for valid city names 
- When an error occurs for invalid city name(s) then application logs ERROR message for that city.

## 5. Continuous Integration - 10p

**Note**:
- Create a simple CI pipeline for running your regression test suite (all your tests) **automatically** when changes are pushed to master

### ACCEPTANCE CRITERIA
- CI pipeline is run when changes are pushed to master
- CI pipeline fails if any test fails
- CI pipeline passes when all tests have passed 
- CI pipeline produces a log (why did it fail?)

## Theoretical part

**NB!** There are no trick questions, all answers can either be found on the slides or discussed during lectures.  

Create a file ```theory_answers.md``` in a separate package ```theory```.

### 1. Which of the following activities cannot be automated
- [ ] Test execution
- [ ] Exploratory testing
- [ ] Discussing testability issues
- [ ] Test data generation

### 2. How do we describe a good unit test?
- [ ] Flawless, Ready, Self-healing, True, Irresistible
- [ ] Red, Green, Refactor
- [ ] Fast, Repeatable, Self-validating, Timely, Isolated
- [ ] Tests should be dependent on other tests

### 3. When is it a good idea to use XPath selectors
- [ ] When CSS or other selectors are not an option or would be brittle and hard to maintain
- [ ] When we need to find an element based on parent/child/sibling relationship
- [ ] When an element is located deep within the HTML (or DOM) structure
- [ ] All the above

### 4. Describe the TDD process

### 5. Write 2 test cases or scenarios for a String Calculator application, which has a method ```calculate()``` that takes a string of two numbers separated by a comma as input, and returns the sum.
Example:
- **Given** the input "1,5" **When** the method ```calculate()``` is called **Then** I should see "6" as a result.  

**Hint:** "C.O.R.R.E.C.T"

# What will get you burned?

**The following no-no's will rob you of your points (even though all acceptance criterias are covered):**
- Disregarding Clean code
  - method names that do not reflect their purpose
  - unreadable method names and variables
  - Very long classes, methods
  - Storing most of the logging in single class
  - Commenting where readable code would suffice
  - Not following the [DRY, SRP principles](https://medium.com/code-thoughts/dont-repeat-yourself-caa413910753)
- Disregarding Git
  - committing too rarely
  - committing a whole elephant
  - not providing context in commit messages ([How to write a git commit message](https://chris.beams.io/posts/git-commit/))
- Disregarding TDD
  - writing code first then tests later
  - having untestable parts of code
- Not having a README where I could find
  - project authors (This part is optional if you are going solo)
  - what tech stack was used
  - how to build, run and test your application **on the commandline**
