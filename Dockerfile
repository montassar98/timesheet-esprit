FROM tomcat:jre8-openjdk

COPY target/timesheet-1.0.war /usr/local/tomcat/webapps/timesheet.war