package com.axway.academy.blagolaj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class FileOperation {

	Scanner input = new Scanner(System.in);
	Random rand = new Random();
	FileInputStream inputStream;
	String theKey = getRandKey();

	public void encrypt() {

		System.out.println("Enter a file you want to encrypt: ");
		String fileName = input.nextLine();
		File inputFile = new File(fileName);
		try {
			inputStream = new FileInputStream(inputFile);
		} catch (FileNotFoundException e) {

			System.out.println("There is no such file");
			e.printStackTrace();
		}

		System.out.println("Enter the file which will contain the encrypted content: ");
		String fileEncryptedName = input.nextLine();
		File outputFile = new File(fileEncryptedName);

		cripting(Cipher.ENCRYPT_MODE, getRandKey(), inputFile, outputFile);
		String fileStoring = fileEncryptedName.concat("StoringKeyFilePair");
		storeKeyFilePair(fileEncryptedName, theKey, fileStoring);
	}

	public void decrypt() {

		System.out.println("Enter a file you want to decrypt: ");
		String fileName = input.nextLine();
		File inputFile = new File(fileName);
		if (inputFile.exists()) {
			System.out.println("Enter the file which will contain the decrypted content: ");
			String fileDecryptedName = input.nextLine();
			File outputFile = new File(fileDecryptedName);
			String fileStoring = fileName.concat("StoringKeyFilePair");
			String keyToDecrypt = getKeyToDecrypt(fileName, theKey, fileStoring);
			System.out.println(keyToDecrypt);
			cripting(Cipher.DECRYPT_MODE, keyToDecrypt, inputFile, outputFile);
		} else {
			System.out.println("There is no such file. Try again.");
			decrypt();
		}
		// storeKeyFilePair(fileName, keyToDecrypt, fileStoring);

	}

	public void cripting(int cipherMode, String key, File inputFile, File outputFile) {

		try {
			Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(cipherMode, secretKey);

			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);

			FileOutputStream outputStream = new FileOutputStream(outputFile);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (InvalidKeyException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();
		} catch (BadPaddingException e) {
			System.out.println("Wrong key");
			e.printStackTrace();
		}

	}

	public void encryptKey(String key) {

	}

	public String padTextToBeMultipleTo16(String text) {
		int textSize = text.getBytes().length;
		int leftover = textSize % 16;
		if (leftover > 0) {
			for (int i = 0; i < 16 - leftover; i++) {
				text = text + " ";
			}
		}
		return text;
	}

	public String getRandKey() {
		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance("AES");

		} catch (NoSuchAlgorithmException e) {
			System.out.println("There is no such algorithm");
			e.printStackTrace();
		}
		keyGenerator.init(192, new SecureRandom());
		SecretKey key = keyGenerator.generateKey();
		String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
		return encodedKey;

	}

	public String getKeyToDecrypt(String fileName, String theKey, String fileStoring) {
		Properties prop = new Properties();
		InputStream input;
		String key = "";

		try {
			input = new FileInputStream(fileStoring);
			prop.load(input);
			// System.out.println(prop.getProperty(fileName));
			key = prop.getProperty(fileName);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return key;
	}

	public void storeKeyFilePair(String fileName, String theKey, String fileStoring) {

		Path fileNameProp = Paths.get(fileStoring);
		OutputStream output;
		try {
			if (!Files.exists(fileNameProp)) {

				Files.createFile(fileNameProp);

				Properties prop = new Properties();
				prop.setProperty(fileName, theKey);
				output = new FileOutputStream(fileStoring);
				// set the properties value
				// save properties to project root folder

				prop.store(output, null);
				output.close();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
