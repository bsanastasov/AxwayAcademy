package com.axway.academy.blagolaj;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpSampleDelete implements FtpSample {

	@Override
	public void operation() {

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

			System.out.println("Type the file you want to delete: ");
			String fileToDelete = input.nextLine();
			boolean deleted = ftp.deleteFile(fileToDelete);

			if (deleted) {
				System.out.println("Deleting was successfull");
			} else {
				System.out.println("Deleting was NOT successfull");
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
