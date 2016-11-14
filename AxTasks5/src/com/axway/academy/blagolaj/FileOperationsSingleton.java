package com.axway.academy.blagolaj;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;

public class FileOperationsSingleton {

	private static FileOperationsSingleton fos;

	private FileOperationsSingleton() {

	}

	public static FileOperationsSingleton getInstance() {
		if (fos == null) {
			fos = new FileOperationsSingleton();
		}
		return fos;
	}

	public void makeLiverpoolPerfect() {
		System.out.println("Input a file you want to modify: ");
		Scanner inputFile = new Scanner(System.in);
		String fileName = inputFile.nextLine();
		Path filePath = Paths.get(fileName);

		System.out.println("Input a file which contains words to replace: ");
		String forbiddenWordsFile = inputFile.nextLine();
		Path forbiddenWordsPath = Paths.get(forbiddenWordsFile);
		inputFile.close();

		try {
			if (!Files.exists(filePath)) {
				Files.createFile(filePath);
				String content = "Liverpool first 11 games: Won Lost Drawn Won Won Won Won Drawn Won Won Won";
				Files.write(filePath, content.getBytes());
			}

			String content = new String(Files.readAllBytes(filePath));
			if (!Files.exists(forbiddenWordsPath)) {
				Files.createFile(forbiddenWordsPath);
				Properties prop = new Properties();

				OutputStream output = new FileOutputStream(forbiddenWordsFile);

				// set the properties value
				prop.setProperty("Lost", "Won");
				prop.setProperty("Drawn", "Won");

				// save properties to project root folder
				prop.store(output, null);
			}

			Properties prop = new Properties();
			InputStream input = new FileInputStream(forbiddenWordsFile);
			prop.load(input);

			Enumeration<?> e = prop.propertyNames();

			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				System.out.println(key);
				if (content.contains(key)) {
					content = content.replaceAll(key, value);
				}
			}

			System.out.println("After modifying: " + "\n" + content);
			String timestamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
			Path renameFilePath = Paths.get(fileName.concat("_modified_").concat(timestamp));
			Files.write(filePath, content.getBytes());
			Files.move(filePath, renameFilePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
