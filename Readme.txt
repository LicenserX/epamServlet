Install Apache Tomcat 8 or later
Clone or download repository git clone https://github.com/LicenserX/epamServlet
Go to Servlet directory
Build package -Dmaven.test.skip=true (tests require running Tomcat with application)
Start Tomcat by running bin\startup.bat (or bin\startaup.sh for Linux)
Tomcat will automatically deploy the war
Open http://localhost:8080/ in your browser