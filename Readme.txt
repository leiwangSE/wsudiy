Project: Implement the design of a database for a web site of youtube videos (say DIYVideo.com) that teach people how to do things by yourself, such as house renovation. 
Group members: Lei Wang (No access ID cause honor student) and Mahzad Tafvizi (Access ID: Gy1829).

Prerequisite tools need to install and configure:
1.Eclipse JAVA EE -  https://www.eclipse.org/downloads/packages/release/2020-09/r/eclipse-ide-enterprise-java-developers 
2.Apache v9.0 Sever - https://tomcat.apache.org/download-90.cgi 
3.MySQL community server - https://dev.mysql.com/downloads/mysql/ (this and below workbench can be installed by MySQL installer -  https://dev.mysql.com/downloads/windows/installer/8.0.html )
4.MySQL workbench (GUI for MySQL database, recommended): https://dev.mysql.com/downloads/workbench/ 


---------------------------------------------
Instructions of how to import and configure:
--MySQL database import and execute sql statement--
1.Run MySQL workbench and connect the MySQL server with username: root and password: pass1234(Credential has to the same, cause in web.xml the project connect with database wsudiy with this credential).
2.Click File -> Open SQL script -> open the WSUDIY.sql file.
3.Implement corresponding SQL statement to "CREATE the database", "USE database", "CREATE table", and "INSERT tuple into table", so that in the login page, you can login as root user or other accounts.

--Eclipse project import and configure--
1. Run Eclipse EE
1. Click File in top menu bar;
2. Click import and then search for War format;
3. Find the War file directory and click finish, then the project will be imported. 
4. In the Servers tag at bottom Console area, add the Tomcat v9.0 Server and select your downloaded Apache file path.
(another way to deploy the project is through Apache server directly without Eclipse, which will update in part 2)


How to run the project:
1. Right-click the project DIY Video and select "Run as" -> "Run on Server". Then the welcome page Login.jsp will display on browser.
2. Login with root credential: root and pass1234;
3. If you don't have the account, click "Register" to register a new account.
