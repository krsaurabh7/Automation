# To run the test from Terminal
Steps :-
1. clone or download the project from git
2. go to root directory of the project and run following commands
3. $ mvn clean install
4  $ mvn -Dtest=Runner test
5  Report will be available in cucumber-reports folder
6. If the test pass then no screenshot will be attached to the report 
   but it will be saved in screenshots folder
7. If the test fails then screenshot will be attached to HTML report at the end of report and also saved in screenshots folder.
   (Screenshot is attached as logo at the end of report as there is issue in cucumber-extent source code, 
   though clicking on it displays it properly).
8. If you want to run the script in chrome then browser config can be changed from /config/Configuration.properties
9. Feature file is located at src/test/resources/features/swiggyApp.feature 

# To run the test from IDE
1. open the project using pom.xml in project root folder
2. run src/test/java/runner/Runner using junit

 
 


