package com.axway.academy.blagolaj;

import java.util.Scanner;

public class FtpSampleFactorySingleton {

	private static FtpSampleFactorySingleton fs;
	private Scanner input;

	private FtpSampleFactorySingleton() {
	}

	public static FtpSampleFactorySingleton getInstance() {
		if (fs == null) {
			fs = new FtpSampleFactorySingleton();
		}
		return fs;
	}

	public FtpSample whichOperation() {

		System.out.println("Insert the operation you want to proceed with: ");
		System.out.println("LIST files");
		System.out.println("DOWNLOAD file");
		System.out.println("UPLOAD file");
		System.out.println("DELETE file");
		System.out.println("EXIT");
		input = new Scanner(System.in);
		String choice = input.nextLine().toUpperCase();
		switch (choice) {
		case "LIST":
			return new FtpSampleListing();

		case "DOWNLOAD":
			return new FtpSampleDownload();

		case "UPLOAD":
			return new FtpSampleUpload();

		case "DELETE":
			return new FtpSampleDelete();

		case "EXIT":
			System.exit(0);

		default:
			System.out.println("Type valid command from the menu:");
			System.out.println();
			return whichOperation();
		}
	}

}
