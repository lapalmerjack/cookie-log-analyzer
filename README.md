# Cookie Log Processor

The Cookie Log Processor allows a user to
read a file and use its contents in order to
manipulate and retrieve logged cookies based on the
date at which the cookie was logged.



## Prerequisites

This project requires Java 17 or later versions. If you don't have it installed, you can download
and install it
from [here](https://adoptium.net/).

## Features

- Able to retrieve most active cookies on a particular date

Follow these steps to run the project locally:

1. git clone https://github.com/lapalmerjack/cookie-log-project.git
2. cd cookie-log-project
3. mvn clean install
4. mvn javadoc:javadoc
5.  (On Mac/Linux) java -cp "target/classes:target/dependency/\*" com.cookieloganalyzer.CookieLogAnalyzer -f file_name  -d date
5.  (On Windows) java -cp "target/classes;target/dependency/\*" com.cookieloganalyzer.CookieLogAnalyzer -f file_name  -d date

Once directions between 1 and 4 have been carried out, please repeat number five with different 
filenames and dates in order to have different outcomes.

Command line Parameters:

- -f: the file name (required)
- -d: the date (required) / Date format: Year-Month-Day XXXX-XX-XX

Example for running the program:

1. example input (Mac/Linux): java -cp "target/classes:target/dependency/*" com.cookieloganalyzer.CookieLogAnalyzer -f "cookie_log.csv" -d 2019-30-09
2. example input (Windows): java -cp "target/classes;target/dependency/*" com.cookieloganalyzer.CookieLogAnalyzer -f "cookie_log.csv" -d 2019-30-09


example-output: Xk9P3hFm2wRvLqZj

### Logs

All logs for the program are saved to the application-log/app.log
file.