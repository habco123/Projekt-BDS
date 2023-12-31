# BDS App

## Overview

BDS App is an bookstore application

## Building and Running the Application

To build and run the application, follow these steps:

1. Ensure you have [Maven](https://maven.apache.org/install.html) installed on your system.
2. Open a terminal or command prompt.
3. Navigate to the project directory.

### Build the Application

```bash
mvn clean install
```
### Run the Application
```bash
java -jar .\target\bds-app-1.0.0.jar
```
### Goal
My goal is to make app where user can buy books, user can create account where 
he can set up his info like address, contact and name and he can see his order history.
His information are stored in database, password is stored safely via argon2
