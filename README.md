# Ingemark CRUD app 


**git clone https://github.com/kdujmic/ingemark.git**


**1) Install Chocolatey**

https://chocolatey.org/install

cmd.exe ( admin elevated)

> ** Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))


**2) Install Java 13**

cmd.exe ( admin elevated)
>** choco install openjdk13


**3) Postgres online connection detail  - https://www.elephantsql.com/**

host: abul.db.elephantsql.com
databaseName: fwlbsmli



**4)  optional - Postgres odbc driveres - optional **

>** choco install psqlodbc


**5) Install project Lombok into eclipse or intellij**

https://projectlombok.org/setup/eclipse
 
https://projectlombok.org/setup/intellij


**6) GIT clone repository**

>** git clone https://github.com/kdujmic/ingemark.git


**7) edit application.properties with postgres connection details**

spring.datasource.url=xxx

spring.datasource.username=xxx

spring.datasource.password=xxx


**8) Maven build**

>** mvn clean install


**9) Run tests**

EndPointTestsHNBServiceOnline - testing CRUD with live HNB connection


EndPointTestsHNBServiceOffline - testing CRUD with WireMock as HNB connection


