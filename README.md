# Project Structure

### 1. The folder called Project, which contains four folders:

* Apache Commons Configuration.
* Apache Commons Math.
* Apache Commons Digester
* Jfree Chart.

Every Project has its  own folder and each folder contains the following:

The pom files for the five different versions of each project.
Folders named 'Metric 1,2,4', Metric3, Metric5, Metric6 containing the results for the respective metrics.

### 2. Folder Correlation Data Analysis: 

This contains the correlation data and the correlation coefficients in seperate .xlsx files as well as the .csv files extracted from the Pit and Jacoco.
It also contains the steps and description about the tool used to generate correlation and cofficient.

### 3. Documents: 
This contains all of the reports and documents related to this project.

# Project Description

This Project is aimed at analyzing various open source systems from “The Apache Software Foundation. These open source systems first undergo a data collection process under which they are subjected to various tools which collect data for different metrics. Later this collected data undergoes Correlation analysis inorder to find some rationales based on the data. By Performing this assessment, we get an idea of the overall quality of the SUT (System under test) as well as helps us to get an idea of the impacts of decision made during the software development life cycle.

## Metrics

The chosen systems undergo a rigorous analysis under different tools to measure various metrics including:

### 1. Metric 1 & 2 - Test Coverage Metric

Test coverage can be done in two ways; namely code statement coverage and code branch coverage.

Statement coverage shows exactly how much percentage of statements of the given code are executed.
Branch coverage verifies that all branches of the code are executed, meaning that all edges in the control flow graph are executed.

### 2. Metric 3- Mutation Score

Mutation Testing includes changing the code — a small part at a time — creating mutants and running the unit tests suite repeatedly.
If the tests still pass — meaning that they were not able to identify the mutant (which might represent a wrong behavior) — we can conclude that they are less strong, and vice-versa.
We have used PiTest tool for calculating the mutation coverage for our projects.

### 3. Metric 4- Cyclomatic Complexity : McCabe

According to its definition by McCabe (1996); cyclomatic complexity is the minimum number of paths that can, in (linear) combination, generate all possible paths through a method.

We are using JaCoCo plugin to calculate the cyclomatic complexity.
Based on the coverage status of each branch JaCoCo also calculates covered and missed complexity for each method.Missed complexity again is an indication for the number of test cases missing to fully cover a module. Note that as JaCoCo does not consider exception handling as branches try/catch blocks will also not increase complexity.

### 4. Metric 5- Sofware Maintainance Metric : Code Churn

Code churn is a means to figure out the changes in the code from one release to next.
It monitors changes in code like refactoring , adding new features etc. to ensure that the code remains stable after these changes.
We have used CLOC to determine the number of lines of code added, modified and deleted.

### 5. Metric 6- Software quality metric : Code Smells

A code smell is a surface indication that usually corresponds to a deeper problem in the system.
Code Smells indicate that the code needs refactoring and/or cleaning to increase its quality.
There are various code smells like duplicated Code and Logic ,long Methods and Classes,Dead Code ,etc.
We used SonarCloud and Jdeodorant to identify the code smells in out projects.


# Projects

We have Chosen the following Projects for analysis:-

### Jfree Chart                   SLOC ~ 120K
Versions:-
* 1.0.19
* 1.0.17
* 1.0.16
* 1.0.15
* 1.0.14

### Apache Commons Math.            SLOC ~ 130K
Versions:-
* 3.6
* 3.5
* 3.4
* 3.3
* 3.2

### Apache Commons Configuration         SLOC ~ 80K
Versions:-
* 2.7
* 2.6
* 2.5
* 2.4
* 2.0

### Apache Commons Digester             SLOC ~27K
Versions:-
* 3.2
* 3.0
* 2.1
* 2.0
* 1.8


## Tools/Plugins used for Metric Measurement

## 1. Jacoco Maven Plugin:-

This Plugin has been used to collect data for Metric 1,2 and 4.
Each pom.xml file of each version of each Project contains this plugin in Build.Plugins.Plugin Tag.
Inorder to generate the reports we follow below steps:-
Insert the plugin in pom.xml in the root directory of the project
Run Maven Clean Goal.
Run the Maven Test Goal.
We get the reports in jacoco-ut folder which is currently present in the above folder structure in each project/version sub-folders

## 2. PiTest Plugin
We get the reports in a Pit-test reports folder after successfully running pit-test plugin.
The output is in the form of index.html format for desired result We had to build script inorder to extract data for further correlation.

## 3. Code Churn
Download and install cloc

Command used :

cloc --diff “version1Code” “Version2Code” --out=report.csv  

This creates a file report.csv that contains the count of files ,blank lines, commented lines and lines of code that are same, added , modified and removed in each of the languages used in the given projects .
We consider the sum of values of LOC for added/modified/deleted sections and get a Code Churn value.

## 4. SonarCloud :
It is online platform that help to evaluate different type of metrices like code smells, line of code etc of any project uploaded to github repository.


## 5. Jdeodorant :
This is an eclipse plugin which helps to find different types of code smells in the given projects such as Long methods, God Class, Envy Class, Duplicated code etc.


## 6. Intellij Jdeodorant :
This is an Intellij plugin which helps to find different types of code smells in the given projects such as Long methods, God Class, Envy Class.


# Correlation Anaylysis :
Steps to generate correlation chart and spearman coefficient :
 e.g We have to generate correlation between metric 1 and metric 4 of JFree chart 

1. Get Metric 1 (Line Coverage )columns values from Jacoco.csv file as we are calculating it for class level.
2. Similarly get Metric 4 (Complexity Covered ) columns from Jacoco.Csv
3. We have used tool provided on the following website : 

https://www.wessa.net/rwasp_spearman.wasp
 
4. Put column values in x and y data in above given website and compute.
5. This will generate the coefficient and corresponding chart of the values.


## Team Details

* Karan Sharma,         40080005, 95sharma.karan@gmail.com

* Shivam Nautiyal,      40090841, shivam.nautiyal20@gmail.com

* Nikunj Arora,         40104832, nikunjarora333@gmail.com

* Sardar Mutesham Ali,  40094168, sardarms9@gmail.com

* Saikiran Alagatham,   40103833, saikiran.ask007@gmail.com
