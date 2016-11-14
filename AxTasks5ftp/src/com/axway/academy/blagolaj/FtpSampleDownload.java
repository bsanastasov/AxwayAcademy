package com.axway.academy.blagolaj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpSampleDownload implements FtpSample {

	@Override
	public void operation() {

		FileOutputStream out = null;
		FTPClient ftp = new FTPClient();
		Scanner input = new Scanner(System.in);

		System.out.println("Server: ");
		String server = input.nextLine();
		System.out.println("Username: ");
		String username = input.nextLine();
		System.out.println("Pass: ");
		String password = input.nextLine();

		try {

			ftp.connect(server);
			ftp.login(username, password);
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTP.BINARY_FILE_TYPE);

			System.out.println("Type the file you want to download: ");
			String filePath = input.nextLine();
			File fileToDownload = new File(filePath);
			String fileName = fileToDownload.getName();
			out = new FileOutputStream(new File(fileName));

			boolean downloaded = ftp.retrieveFile(filePath, out);
			if (downloaded) {
				System.out.println("Downloading was successfull");
			} else {
				System.out.println("Downloading was NOT successfull");
			}
		} catch (SocketException e) {
			System.out.println("Problem establishing FTP connection");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problem establishing FTP connection");
			e.printStackTrace();
		} finally {

			// closing streams and disconnect
			try {
				if (out != null) {
					out.close();
				}
				ftp.disconnect();

				if (ftp != null) {
					ftp.disconnect();
				}
				if (input != null) {
					input.close();
				}
			} catch (FileNotFoundException fnfe) {
				System.out.println("There is no such file");
			} catch (IOException e) {
				System.out.println("Problem disconnecting");
				e.printStackTrace();
			}
		}
	}
}
