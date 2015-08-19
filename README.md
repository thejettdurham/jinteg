# jinteg
A super-simple integration testing framework &amp; toolbox for Java

I created this project to fill a need that I had for a simple way to write consistent integration tests designed to be run in a terminal in a production (or near-production) environment.  I didn't want to rely on specifically tailored environments, complicated mocking constructs, or funky test runners. jinteg provides a simple framework for writing integration tests that can be run directly from java (via a main method) and whose results are easily parsed.

Usage is simple:
* The test class inherits the base class *IntegrationTest*
* Test methods are public, static, and return an instance of *ITResult*
* Finally, the test class needs a main method that runs one of the *run* methods

Here's a simple example:

```java
public class FooIT {
	
	public static ITResult fooConnectsToDatabase() throws DBConnectionException {
		DBConnection conn = new Foo().getDBConnection();
		
		if (conn != null) {
			return new ITPass();
		} else {
			return new ITFail("DatabaseConnector didn't return a connection");
		}
	}
	
	public static ITResult fooConnectsToWebService() throws WebServiceConnectionException() {
		boolean isLoggedIn = Foo.connectToWebService("testuser", "testpassword");
		
		if (isLoggedIn) {
			return new ITPass();
		} else {
			return new ITFail("Login to web service failed");
		}
	}
	
	public static void main(String[] args) {
		run(FooIT.class);
	}
}
```

For more advanced usage and more detailed documentation, visit the wiki.

## Installing

A pre-built jar is available for download in the releases section.

## Hacking and Building

Gradle is the build tool of choice for this project.  There are no special Gradle tasks implemented here, so building is quite simple:
* `gradle eclipse` builds the project and classpath files needed to work in Eclipse
* `gradle jar` builds the jar



