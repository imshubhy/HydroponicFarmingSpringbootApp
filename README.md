# REST API for Hydroponic Farming Application

This project is about Building REST APIs for Hydroponic Farming which can be used by users/Admin for  different services.    
The unique features of this project is I have implemented user validation at every step that will based upon authenticated unique key, for every user. 
And here I am following proper architecture based design that's allow us to write neat and clean code.
 
I have written the business logic in such a way that at any step we can easily access current user whether it would be admin or user.
With the help of key(uuid) and we can implement the required business logic as per need.

<br />

# Tech Stack

<img align="left" src="https://1000logos.net/wp-content/uploads/2020/09/Java-Logo.png" alt="drawing" width="100"/>
<img align="left"  src="https://download.logo.wine/logo/Spring_Framework/Spring_Framework-Logo.wine.png" alt="drawing" width="100"/>
<img src="https://download.logo.wine/logo/MySQL/MySQL-Logo.wine.png" alt="drawing" width="100"/>


<img align = "left" src="https://www.dariawan.com/media/images/tech-spring-boot.width-1024.png" alt="drawing" width="100"/>
<img align="left"  src="https://upload.wikimedia.org/wikipedia/commons/2/22/Hibernate_logo_a.png" alt="drawing" width="100"/>
<img  align="left" src="https://miro.medium.com/max/818/1*zc-LgogGtr7fFHF9e1M8wA.png" alt="drawing" width="100"/>

<img src="https://maven.apache.org/images/maven-logo-white-on-black.purevec.svg" alt="drawing" width="100"/>


<img src="https://zooz.github.io/predator/images/restapi.png" alt="drawing" width="100"/>

<br />

<br />


![Screenshot (34)](https://user-images.githubusercontent.com/90818364/200982924-8563d0cb-8da4-49b6-ad1d-d21a16dee7b3.png)

![Screenshot (33)](https://user-images.githubusercontent.com/90818364/200982954-075c1019-998f-45bf-a729-a9070e348f4e.png)

# ER Diagram:
![farmingDB_ERD](https://user-images.githubusercontent.com/90818364/200984773-3432f748-bedb-4ff8-9661-acd03d8c0ddd.png)
<br />

# Installation & Run
 - Before running the API server, you should update the database config inside the application.properties file.
- Update the port number, username and password as per your local database configuration.

```
    server.port=8080

    spring.datasource.url=jdbc:mysql://localhost:3306/farmingDB;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
```
