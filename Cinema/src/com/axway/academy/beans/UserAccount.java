package com.axway.academy.beans;

public class UserAccount {
	    
	   private String userName;
	   private String password;
	   private String address;
	
	   public UserAccount() {
	        
	   }
	   
	   public UserAccount(String userName, String password, String address) {
		   this.userName = userName;
		   this.password = password;
		   this.address = address;
	   }
	    
	   public String getUserName() {
	       return userName;
	   }
	 
	   public void setUserName(String userName) {
	       this.userName = userName;
	   }
	 
	 
	   public String getPassword() {
	       return password;
	   }
	 
	   public void setPassword(String password) {
	       this.password = password;
	   }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	 
	}
