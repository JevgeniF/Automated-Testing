# WeatherBall

Weather forecast application for console line.

## About app
The app accepts user input from console line, which can be a single city name or a _*.txt_ file with city names and returns forecast in metric or imperial units on screen (terminal) or saves report(s) as JSON file(s).

The report consists of the following data:
- ```weatherReportDetails``` - details of report setup contains:
    - ```city``` - name of city
    - ```coordinates``` - location of city in format "Lat,Lon"
    - ```temperatureUnit``` - Celsius by default, can be changed to Fahrenheit
- ```currentWeatherReport``` - current weather block with:
    - ```date``` - current date
    - ```temperature``` - current temperature
    - ```humidity``` - current air humidity
    - ```pressure```- current atmosphere pressure
- ```forecastReport``` - weather forecast for the next three days with:
    - ```date``` - date of forecast
    - ```temperature``` - temperature average per day
    - ```average humidity``` - air humidity average per day
    - ```average pressure``` - atmosphere pressure average per day

## Short Technical Preview
The project build with _Apache Maven Framework_ with _Java 16_ prerequisites.
_Logback_ logger implemented for logging info, errors and warnings.
Testing consists of mock-integration, integration and functionality tests. All tests divided into groups and can be run individually with _surefire_ plugin.
_Maven assembly_ plugin responds for jar with dependencies assembly.

# How to Build and Run Application
_Prerequisites. The client must have Maven installed._
_All Maven commands should be run inside root folder (the one with POM.xml file)_
## Code compilation
- Download or clone project
- Open console window in root folder of downloaded project and run the following command from line in order to compile project with run of all tests
```
mvn clean compile
```
  in order to compile without testing
```
mvn clean compile -Dmaven.test.skip=true
```
## Project testing
- Project tested by junit tests divided into  5 profiles:
    - ```mock-integration-tests``` - test API integration scenarios in case, if no connection with real API. Use _Mockito_ library to mock _Weahter API_
    - ```api-integration-tests``` - test integration of real OpenWeatherMap API, like getting data from API
    - ```app-functionality-tests``` - test main functionality of application, like generation of weather reports from API data
    - ```io-integration-tests``` - test integration of input/output, like receiving city names from files and saving weather reports as JSON files.
    - ```cl-functionality-tests``` - tests functionality of console line interface and usability of the project
    
Every profile can be run separately from console line by command
`mvn test -P[name-of-test-profile]`, where `[name-of-test-profile]` must be substituted by real name of profile. Examples:
```
mvn test -Pmock-integration-tests
mvn test -Papi-integration-tests
mvn test -Papp-functionality-tests
mvn test -Pio-integration-tests
mvn test -Pcl-interface-tests
```
To run all test at once, use:
```
mvn test -Pall-tests
```

### Additional testing and debugging modules:
**Logback logger**

There are 3 loggers in the app:
- `apiLogger` - located in WeatherApi class and logs WARN messages in case if response _HTTP_NOT_FOUND_ was received from OpenWeatherMap API.
- `wbLogger` - located in WeatherBall class. Logs INFO messages, when Overloaded method used  to generate weather reports, and ERROR messages, when city from input file was not found by API
- `ioLogger` - located in InOut class and logs ERRORS, while there is something wrong with file reader, and INFO messages, when used Overloaded method to save JSON files or when file writer overwrites earlier saved reports.

Logfile example:
```
01:32:42 INFO  > w.inOut.InOut > saveToJson - Overwriting existing weather report: Weather in Oslo.json
01:32:42 INFO  > w.inOut.InOut > saveToJson - Overloaded method used to save Json files
01:32:42 INFO  > w.a.WeatherApi > setUnits - API UNIT setting changed now: metric
01:32:43 INFO  > w.inOut.InOut > saveToJson - Overwriting existing weather report: Weather in Alabama.json
01:32:43 WARN  > w.a.WeatherApi > getCurrentWeatherData - Error occurred on sending current weather request to API: 404
01:32:43 ERROR > w.inOut.InOut > getCitiesFromFile - Exception occurred when tried to get cities form input file:
Wrong file format. Should be txt file.
01:32:43 ERROR > w.inOut.InOut > getCitiesFromFile - Exception occurred when tried to get cities form input file:
File is empty.
01:32:43 ERROR > w.inOut.InOut > getCitiesFromFile - Exception occurred when tried to get cities form input file:
File is empty.
01:32:43 INFO  > w.inOut.InOut > saveToJson - Overwriting existing weather report: Weather in Alabama.json
01:33:14 WARN  > w.a.WeatherApi > getForecastData - Error occurred on sending forecast request to API: 404
```

**logback .xml** settings file located in `src/resources` directory.
File has settings to output loggers to console (STDOUT) or to file. 
Currently working only Rolling file appender with time based rolling policy: one log file can not hold more than 2 days history or more than 1 MB of data.
The STDOUT assemblers are set up to log different levels by different colors, to improve on screen log readability. Uncomment before assembly to enable.
Logs will be saved to the same folder where WeatherBall jar package located.

**GitLab Pipeline**

As requirement to the project , the CI Pipeline was created on GitLab.
The CI Pipeline has 4 stages:
- Build
- App Testing
- CL Interface Testing
- Package Assembly
 During app testing build 4 testing-profiles run in parallel, to reduce total pipeline work-time.
 
Pipeline settings file:
```
#course_project pipeline base configuration

image: maven:latest

stages:
    - Build
    - App Testing
    - CL Interface Testing
    - Package Assembly

app-build:
    tags:
        - docker
    stage: Build
    script:
        - cd course_project
        - mvn clean compile -Dmaven.test.skip=true


mock-integration-tests:
    tags:
        - docker
    stage: App Testing
    script:
        - cd course_project
        - mvn test -Pmock-integration-tests
        - echo "mock integration tests completed"
    
    artifacts:
        when: always
        reports:
            junit:
                - course_project/target/surefire-reports/TEST-*.xml

api-integration-tests:
    tags:
        - docker
    stage: App Testing
    script:
        - cd course_project
        - mvn test -Papi-integration-tests
        - echo "api integration tests completed"
    
    artifacts:
        when: always
        reports:
            junit:
                - course_project/target/surefire-reports/TEST-*.xml

app-functionality-tests:
    tags:
        - docker
    stage: App Testing
    script:
        - cd course_project
        - mvn test -Papp-functionality-tests
        - echo "app functionality tests completed"

    artifacts:
        when: always
        reports:
            junit:
                - course_project/target/surefire-reports/TEST-*.xml

io-integration-tests:
    tags:
        - docker
    stage: App Testing
    script:
        - cd course_project
        - mvn test -Pio-integration-tests
        - echo "input-output tests completed"

    artifacts:
        when: always
        reports:
            junit:
                - course_project/target/surefire-reports/TEST-*.xml

cl-interface-tests:
    tags:
        - docker
    stage: CL Interface Testing
    script:
        - cd course_project
        - mvn test -Pcl-interface-tests
        - echo "cl interface tests completed"

    artifacts:
        when: always
        reports:
            junit:
                - course_project/target/surefire-reports/TEST-*.xml

app-install:
    tags:
        - docker
    stage: Package Assembly
    script:
        - cd course_project
        - mvn clean compile assembly:single

```
## Package assembly
To assembly usable jar package file (with all libraries), run the following command from console:
```
mvn clean compile assembly:single 
```
The usable jar file called like `WeatherBall-[version]-[build].jar` will be generated in `target` directory.
> The usability on Windows and Linux machines was not tested yet. For better user experience on MacOs , suggest to rename file with simple name and move it into user $HOME folder, or setup environment path for the app. Otherwise it is required to mention full path for the file to use it.
# User Manual
> You can always find console help / manual by run .jar file without attributes.
- To start work with app, you need assembled before WeatherBall JAR file.
To get weather report, input the following command in console:
```
java -jar [weather ball package name].jar [outputMode] [not necessary:units] [input] [not necessary:outputFolder]
```
Where:
- `[weather ball package name].jar` - real assembled before WeatherBall JAR file name.
- `[outputFormat]` - the attribute changes the mode how the app returns Weather Report to user:
    - `-console` - displays weather report(s) on display, using console
    - `-json` - saves weather report(s) to JSON file (Separately for each city);
- `[not necessary:units]` - not necessary attribute, changes temperature units in reports and can be passed as:
    - `-c` - Set up temperature units for reports to Celsius
    - `-f` - Set up temperature units for reports to Fahrenheit
    - _IF SKIPPED. App uses Celsius by default._
- `[input]` - the attribute holds city name or .txt file name
    - `city` - input any real city name (not `city Rome`, just `Rome`), for quick report generation for one city
    -  `fileName` - input path and name of .txt file (not `fileName cities.txt`, just `citiest.txt`) for batch weather report generation
> Requirements to the file: the file format must be `.txt` only. Other formats are not supported. The file should contain one city per one line. In case, if there are wrong city names in file, such names will be skipped. Example of text file formatting:
```
Tokyo
Lisbon
Berlin
Moscow
Nairobi
Rio
Denver
Helsinki
Bogota
Marseille
Manila
Palermo
Oslo
```
- `[not necessary:outputFolder]` - not necessary attribute, contains
    -  `path` - path of location/directory, where JSON file(s) will be saved.
    - _IF SKIPPED. App will use the folder, where the WeatherBall jar package located to save JSON files.
- **Examples of proper application run commands**
```
java -jar WeatherBall.jar -console Rome
java -jar WeatherBall.jar -console -f Rome
java -jar WeatherBall.jar -json cities.txt
java -jar WeatherBall.jar -json -f cities.txt
java -jar WeatherBall.jar -json cities.txt ./new_location/
java -jar WeatherBall.jar -json -f cities.txt ./new_location/
```
In case of successful run with `-console Rome` attributes, the following result should be readable from console:
```
~ % java -jar WeatherBall.jar -console Rome
=========== WEATHER REPORT ===========
------- Weather Report Details -------
			city: Rome
coordinates: 34.257,-85.1647
temperatureUnit: Celsius
------ Current Weather Report --------
		  date: 2021-11-06
temperature: 8.0
humidity: 87
pressure: 1025
---------- Forecast Report -----------
[		  date: 2021-11-07
temperature: 8.0
humidity: 66
pressure: 1023

, 		  date: 2021-11-08
temperature: 9.5
humidity: 60
pressure: 1024

, 		  date: 2021-11-09
temperature: 11.0
humidity: 62
pressure: 1025

]
~ % 
```
In case of successful run with `-json Rome` attributes, the 
`Weather in Rome.json` file will be saved with the following content:
```
{
    "weatherReportDetails": {
        "city": "Rome",
        "coordinates": "34.257,-85.1647",
        "temperatureUnit": "Celsius"
    },
    "currentWeatherReport": {
        "date": "2021-11-06",
        "temperature": 7.5,
        "humidity": 87,
        "pressure": 1025
    },
    "forecastReport": [
        {
            "date": "2021-11-07",
            "weather": {
                "temperature": 8.0,
                "humidity": 66,
                "pressure": 1023
            }
        },
        {
            "date": "2021-11-08",
            "weather": {
                "temperature": 9.5,
                "humidity": 60,
                "pressure": 1024
            }
        },
        {
            "date": "2021-11-09",
            "weather": {
                "temperature": 11.0,
                "humidity": 62,
                "pressure": 1025
            }
        }
    ]
}
```


