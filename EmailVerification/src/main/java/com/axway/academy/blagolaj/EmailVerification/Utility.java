package com.axway.academy.blagolaj.EmailVerification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author blagolaj
 *
 */
public class Utility {

	/**
	 * @param email
	 * @return The "whole" logic of the program
	 */
	public static boolean isValidEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}

}
