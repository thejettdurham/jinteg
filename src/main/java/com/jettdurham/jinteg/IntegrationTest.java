package com.jettdurham.jinteg;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public abstract class IntegrationTest {
	
	private static final String FAIL_PREFIX = "FAILURE";
	private static final String EXCEPTION_PREFIX = "EXCEPTION";
	
	public static void run(Class<?> kls) throws Exception {
		run(kls, true, true);
	}
	
	public static void run(Class<?> kls, boolean stopOnFailure) throws Exception {
		run(kls, stopOnFailure, true);
	}
	
	public static void run(Class<?> kls, boolean stopOnFailure, boolean stopOnException) throws Exception {
		String className = kls.getName();
		System.out.println("Testing class " + className);
		
		List<Method> methods = Arrays.asList(kls.getDeclaredMethods());
		methods.sort((m1, m2) -> m1.getName().compareTo(m2.getName()));
		
		for(Method m: methods) {
			String methodName = m.getName();
			
			if (m.getReturnType().equals(ITReturn.class)) {
				StringBuffer outputLine = new StringBuffer(className + "#" + methodName + " - ");
				
				if (Modifier.isStatic(m.getModifiers())) {
					try {
						ITReturn ret = (ITReturn)m.invoke(new Object(){}, new Object[]{});
						
						if (!ret.getStatus()) {
							outputLine.append(ret.getStatusMessage());
							if (stopOnFailure) {
								System.out.println(outputLine.toString());
								System.out.println("Stopping due to test failure");
								System.out.print(System.lineSeparator());
								break;
							}
						} else {
							outputLine.append(ret.getStatusMessage());
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