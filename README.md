## About the Web Application

### Overview
The application displays a list of requests submitted to an electricity board for power connections to customers house. The application is typically supposed to be used by a internal employees of an electricity department.
In the dashboard, along with the list of applications, the user can also see a vizualizations of the connection requests made across the year of 2021, month wise, along with an option to view the summary of connections of particular types.
Bottom right corner displays the basic summary in terms of number of requests that are pending, fulfilled and so on.
The user (or the reviewer in this case) has an option to view/edit the application status of a particular connection request by clicking on the row and editing the information. He can change the sanctioned load, change the status of the application and also give suitable comments as necessary.
In the main screen, the user can filter with respect to the applicant's id using the search bar and also filter out the list based on the date ranges of the application being filed.
Navigating through pages is also enabled.

### Setting up the project in a local environment
The UI of the application is written in Angular and the Backend is written in Spring boot.

#### Setting up the database
The backend application requires Postgres database to store and retrive data. The steps to configure it are:
1. Download [Postgres](https://www.postgresql.org/download/) 
2. Install it and while installing select `5433` as the default port. This is required since the backend application requires connecting to the database running at 5433 port.
3. Create a user with the username `postgres` and password `postgres`. This step can also be done during the installation process using the GUI assisted steps.
4. Once the installation is complete. Start `pgAdmin`. pgAdmin is the UI client for postgres. It makes interacting with the database easy.
5. Create a database with the name `electricity`. This is the database that the backend service connects to. 
   
#### The build process for the Backend is
1. Install Java ( version >= 17).
1. Go to the directory `BCG_Assignment_Submission_Abhishek\EBDashboard\eb-dashboard-backend`. This is the base directory for the backend code.
2. Open the terminal at this location
3. Run the `mvnw clean install` command. It will install the dependencies that the backend needs to run. `mvnw` is a Maven wrapper included with the project files itself. Maven is the build tool required to run Maven projects (Springboot is one of them).
4. Run the application in your local using the command `mvn spring-boot:run`. This will run the backend at port `7777`.


#### The build process for the UI is
1. Install NodeJs (version 16) and Angular (version 14).
1. Go to the directory `BCG_Assignment_Submission_Abhishek\EBDashboard\eb-dashboard-frontend`. This is the base directory for the frontend code.
2. Open the terminal at this location
3. Run the `npm install` command. It will install the dependencies that the UI needs to run.
4.  Run the application in your local using the command `ng serve --open`. This will run the UI and a browser window will open with the rendered UI. This will take the defautl port `4200` if available or chose any other port automatically.

   
##### That is it. With the above steps done fine, the application should work smoothly. If you are facing any issue with the setup, feel free to reach me at, `aa.iitbbs@gmail.com`. I will be happy to help.
