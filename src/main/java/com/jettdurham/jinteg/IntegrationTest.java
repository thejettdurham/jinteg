package com.jettdurham.jinteg;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

// TODO: Javadoc stuff
// TODO: Move default failure and success prefixes from ITResult impls to here.
// TODO: Unit Tests
// TODO: Commit

/**
 * The base class that defines an Integration Test.
 * Test classes must extend this class to get the {@link #run(Class)} method that provides the benefit of this package.
 * 
 * @author jettdurham
 *
 */
public abstract class IntegrationTest {
	
	private static final String FAIL_PREFIX = "FAILURE";
	private static final String SUCCESS_PREFIX = "Success";
	private static final String EXCEPTION_PREFIX = "EXCEPTION";
	
	
	/**
	 * Runs all public static methods in the given class that return a type that implements {@link ITResult}
	 * This run method will stop if any tests fail and/or if any exceptions are thrown.
	 * These behaviors can be overridden by using one of the other run methods.
	 * 
	 * @param kls A reference to the test class itself (not the classes under test)
	 * @throws Exception Given the design of this class, it's impossible to know what specific exceptions can be thrown
	 */
	public static void run(Class<? extends IntegrationTest> kls) throws Exception {
		run(kls, true, true);
	}
	
	/**
	 * Same as {@link #run(Class)}, but allows for execution to continue if a test fails.
	 * This run method will stop if any exceptions are thrown
	 * 
	 * @param kls A reference to the test class itself (not the classes under test)
	 * @param stopOnFailure Whether execution should stop if a test fails
	 * @throws Exception Given the design of this class, it's impossible to know what specific exceptions can be thrown
	 */
	public static void run(Class<? extends IntegrationTest> kls, boolean stopOnFailure) throws Exception {
		run(kls, stopOnFailure, true);
	}
	
	/**
	 * Same as {@link #run(Class)}, but allows for execution to continue if a test fails or an exception is thrown.
	 * 
	 * @param kls A reference to the test class itself (not the classes under test)
	 * @param stopOnFailure Whether execution should stop if a test fails
	 * @param stopOnException Whether execution should stop if an exception is thrown
	 * @throws Exception Given the design of this class, it's impossible to know what specific exceptions can be thrown
	 */
	public static void run(Class<? extends IntegrationTest> kls, boolean stopOnFailure, boolean stopOnException) throws Exception {
		String className = kls.getName();
		System.out.println("Testing class " + className);
		
		List<Method> methods = Arrays.asList(kls.getDeclaredMethods());
		methods.sort((m1, m2) -> m1.getName().compareTo(m2.getName()));
		
		for(Method m: methods) {
			String methodName = m.getName();
			
			if (m.getReturnType().equals(ITResult.class) && Modifier.isPublic(m.getModifiers())) {
				StringBuffer outputLine = new StringBuffer(className + "#" + methodName + " - ");
				
				if (Modifier.isStatic(m.getModifiers())) {
					try {
						ITResult ret = (ITResult)m.invoke(new Object(){}, new Object[]{});
						
						if (!ret.getStatus()) {
							outputLine.append(FAIL_PREFIX + ": " + ret.getStatusMessage());
							if (stopOnFailure) {
								System.out.println(outputLine.toString());
								System.out.println("Stopping due to test failure");
								System.out.print(System.lineSeparator());
								break;
							}
						} else {
							outputLine.append(SUCCESS_PREFIX + ": " + ret.getStatusMessage());
						}
						
					} catch (Exception e) {
						
						if (stopOnException) {
							System.out.println("Stopping due to caught exception in test");
							throw e;
						} else {
							outputLine.append(EXCEPTION_PREFIX);
							System.out.print(stacktraceToString(e));
						}
					}
				} else {
					if (stopOnException) {
						throw new MethodNotStaticException("Test methods must be static");
					} else {
						System.out.print(FAIL_PREFIX + ": Method not static");
					}
				}
			
				System.out.println(outputLine);
			}
		}
	}
	
	private static String stacktraceToString(Exception e) {
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter( writer );
		e.printStackTrace( printWriter );
		printWriter.flush();

		return writer.toString();
	}
	
}