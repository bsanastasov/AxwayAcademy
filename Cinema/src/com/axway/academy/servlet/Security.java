package com.axway.academy.servlet;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
    public String encryptPasswordReceivedFromLoginPage(String unecryptedPassword){
        String salt = "RandomStringForExtraSecurity@#$!%^&*(*)1234567890";
        MessageDigest messageDigest=null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update((unecryptedPassword+salt).getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return (new BigInteger(messageDigest.digest())).toString(16);
         
    }
     
    public String retrieveEncryptedAndSaltedPasswordFromDatabase(String userName){
        //Normally you would store salted and encrypted passwords for all users in database
        //At the time of login you will salt and encrypt the password entered by user on login 
        //page and compare it with the one in database.
        //For sake of simplicity we are hardcoding the salted password hash here,
        //but in real world it will come from database. Password is mysecret123
        
    	return "51fdef7d07bef69c03c3ad9337951c63";
    }
     
    public boolean verifyPassword(String userName, String unecryptedPassword){
        String encryptedLoginPagePassword = encryptPasswordReceivedFromLoginPage(unecryptedPassword);
        String encryptedPasswordFromDatabase = retrieveEncryptedAndSaltedPasswordFromDatabase(userName);
        if (encryptedLoginPagePassword.equals(encryptedPasswordFromDatabase))
            return true;
        return false;
    }
}
