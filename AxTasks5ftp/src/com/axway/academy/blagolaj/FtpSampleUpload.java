package com.axway.academy.blagolaj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpSampleUpload implements FtpSample {

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

			System.out.println("Type the file you want to upload: ");
			String filePathUpload = input.nextLine();
			File firstLocalFile = new File(filePathUpload);

			String nameOfUploadedFile = firstLocalFile.getName();
			String firstRemoteFile = nameOfUploadedFile;
			InputStream inputStream = new FileInputStream(firstLocalFile);
			System.out.println("Start uploading first file");
			boolean uploaded = ftp.storeFile(firstRemoteFile, inputStream);
			inputStream.close();
			if (uploaded) {
				System.out.println("The first file is uploaded successfully.");
			} else {
				System.out.println("not");
			}
			ftp.logout();
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
