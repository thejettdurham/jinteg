# jinteg
A super-simple integration testing framework &amp; toolbox for Java

I created this project to fill a need that I had. I wanted a framework for writing integration tests that was as simple as possible.  I wanted to be able to easily run the tests from the command line without depending on third party test runners or very specific environments.

The design goals were as such
# Each test class inherits from a base abstract class that handles as much of the wiring as possible
# Each test method returns the result of the test (pass/fail)
# Common methods are provided by a static utility class that can be statically imported
# Actual execution of tests is done by calling the test class externally (through a shell script, for example)

TODO:
# Briefly describe implemenation
# Provide several example usages
