# Apache Log4j Audit Sample

This is a sample project that illustrates how to create cusom Audit Event classes and a custom Audit Service using 
Log4j-Audit. 

## Packages

### Audit Service API

The Audit Service API project contains the catalog that will be managed by the Log4j Catalog Web Application. That web
application simply needs to be configured with the url of this project and the path to the JSON catalog within the
project. Whenever a Maven build is run the catalog will be read and a new set of Java Interfaces will be generated.
The interfaces can be used by Java applications to perform audit logging.

### Audit Service 

The Audit Service is a web application that allows applications (especially those not written in Java) to perform
audit logging while still having ensuring that the audit events conform to the definitions in the catalog.
