package com.axway.academy.blagolaj.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpOperations {

	private static void menu() throws FileNotFoundException {

		String user = "bopa";
		String password = "123";
		String server = "localhost";
		FTPClient ftp = new FTPClient();
		FileInputStream fis = null;
		FileOutputStream out = null;

		System.out.println("Insert the operation you want to proceed with: ");
		System.out.println("1.List files");
		System.out.println("2.Download file");
		System.out.println("3.Upload file");
		System.out.println("4.Delete file");
		Scanner input = new Scanner(System.in);
		String choice = input.next();

		try {

			ftp.connect(server);
			ftp.login(user, password);
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTP.BINARY_FILE_TYPE);

			switch (choice.toUpperCase()) {

			case "LIST":

				System.out.println("Type the directory you want to list: ");
				Scanner inputList = new Scanner(System.in);
				String directoryPath = inputList.nextLine();
				FTPFile[] files = ftp.listFiles(directoryPath);

				for (FTPFile file : files) {
					System.out.println(file.getName());
				}
				break;

			case "DOWNLOAD":

				System.out.println("Type the file you want to download: ");
				Scanner inputDownload = new Scanner(System.in);
				String filePath = inputDownload.nextLine();
				String extension = "";
				int i = filePath.lastIndexOf(".");
				if (i > 0) {
					extension = filePath.substring(i);
				}
				out = new FileOutputStream(new File("newFile" + extension));

				boolean downloaded = ftp.retrieveFile(filePath, out);
				if (downloaded) {
					System.out.println("Downloading was successfull");
				} else {
					System.out.println("Downloading was NOT successfull");
				}
				break;

			case "UPLOAD":

				System.out.println("Type the file you want to upload: ");
				Scanner inputUpload = new Scanner(System.in);
				String filePathUpload = inputUpload.nextLine();
				File firstLocalFile = new File(filePathUpload);

				String nameOfUploadedFile = "";
				int j = filePathUpload.lastIndexOf("/");
				if (j > 0) {
					nameOfUploadedFile = filePathUpload.substring(j);
				}

				String firstRemoteFile = "/home/bopa/" + nameOfUploadedFile;
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
				break;

			case "DELETE":

				System.out.println("Type the file you want to delete: ");
				Scanner inputDelete = new Scanner(System.in);
				String fileToDelete = inputDelete.nextLine();
				boolean deleted = ftp.deleteFile(fileToDelete);

				if (deleted) {
					System.out.println("Deleting was successfull");
				} else {
					System.out.println("Deleting was NOT successfull");
				}
				break;

			default:
				throw new IllegalArgumentException("Invalid operation: " + choice);
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
				// if (out != null) {
				// out.close();
				// }
				ftp.disconnect();

				if (ftp != null) {
					ftp.disconnect();
				}
			} catch (IOException e) {
				System.out.println("Problem disconnecting");
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		menu();
	}

}
