# ERServer - Unit Testing Legacy Code in Java

ERServer is the sample application for the [Pluralsight](http://pluralsight.com) course [Unit Testing Legacy Code in Java](http://pluralsight.com).  It is a Java application to support a hospital emergency room's application.  The packages are arranged by course modules.

## Prerequisites / Requirements

  * Java 8 or higher is required to compile and run ERServer.  
  * [Maven 3.5+]( https://maven.apache.org/install.html) is also required.
  * And git, of course

## Running ERServer

You will need to use Maven at the command line or from your IDE to compile ERServer.  Once built, there is a Java main program that can be run.

### Build ERServer

`mvn clean install` should compile and build ERServer.

### Launch ERServer

The module3 package contains the Java class ERServerRunner, which when run will launch ERServer on http://localhost:8088.

## Running Fitnesse

Although not needed to learn the techniques in the course, Fitnesse is included as an example of how customer-facing fitnesse tests may help expose the critical business logic of a legacy application to customers for documentation and confirmation purposes.

Fitnesse is a java jar, and is contained in the fitnesse folder of the project.  Simply CD to this directory and launch fitnesse on desired port (8081 in this example):

`java -jar fitnesse-standalone.jar -p 8081`
