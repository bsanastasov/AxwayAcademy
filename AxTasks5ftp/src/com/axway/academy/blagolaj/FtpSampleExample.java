package com.axway.academy.blagolaj;

public class FtpSampleExample {

	public static void main(String[] args) {
		FtpSample fs = FtpSampleFactorySingleton.getInstance().whichOperation();
		fs.operation();
	}

}
