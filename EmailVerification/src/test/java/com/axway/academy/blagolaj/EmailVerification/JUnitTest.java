package com.axway.academy.blagolaj.EmailVerification;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class JUnitTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * 
	 * @param testName
	 *            name of the test case
	 */

	String[] validEmails = truesEmails();
	String[] invalidEmails = falsesEmails();

	public JUnitTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(JUnitTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	// Generate an array of valid emails, easily to test them
	public static String[] truesEmails() {
		String[] validEmails = { "qko@yahoo.com", "comi-sd@yahoo.com", "liverpool.1@gmail.com", "manutd_1325@abv.bg",
				"chelseaaa-0@mdom.net", "westham@gmail.com" };
		return validEmails;

	}

	// Generate an array of invalid emails, easily to test them
	public static String[] falsesEmails() {
		String[] invalidEmails = { "qko@yahooom", "____comi-sd@yahocom", "lgmail.com", "demii_1325abv.bg",
				"chelseaa@@a-0@mdom.net", "westham@gmaiau" };
		return invalidEmails;

	}

	// Test th valid emails
	public void testValidationTrue() {

		for (String email : validEmails) {
			boolean valid = Utility.isValidEmail(email);
			System.out.println("Email: " + email + " is expected to be true and is: " + valid);
			assertEquals(valid, true);
		}

	}

	// Test the invalid emails
	public void testValidationFalse() {
		for (String email : invalidEmails) {
			boolean valid = Utility.isValidEmail(email);
			System.out.println("Email: " + email + " is expected to be false and is: " + valid);
			assertEquals(valid, false);
		}
	}
}
