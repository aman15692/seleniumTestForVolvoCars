FROM maven:3.6.0-jdk-8-alpine
COPY src /home/volvoCarsTestAutomationFramework/src
COPY config /home/volvoCarsTestAutomationFramework/config
COPY pom.xml /home/volvoCarsTestAutomationFramework
RUN mvn -f home/volvoCarsTestAutomationFramework/pom.xml clean test -DskipTests=true
