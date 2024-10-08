# Use the official Tomcat base image
FROM tomcat:9.0-jdk11

# Set the working directory in the container
WORKDIR /usr/local/tomcat/webapps/

# Copy the WAR file generated by Maven to the Tomcat webapps directory
COPY target/EmployeeManagement.war /usr/local/tomcat/webapps/

# Expose port 8080 (Tomcat default port)
EXPOSE 8081

# Start Tomcat when the container launches
CMD ["catalina.sh", "run"]
