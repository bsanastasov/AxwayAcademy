package com.axway.academy.blagolaj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Properties;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author blagolaj
 *
 */
public class FileOperation {

	private Scanner input = new Scanner(System.in);
	private String theKey = getRandKey();
	FileInputStream inputStream;

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

		cripting(Cipher.ENCRYPT_MODE, theKey, inputFile, outputFile);
		criptingKey(theKey);
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

			cripting(Cipher.DECRYPT_MODE, keyToDecrypt, inputFile, outputFile);
		} else {
			System.out.println("There is no such file. Try again.");
			decrypt();
		}

	}

	/**
	 * @param cipherMode
	 * @param key
	 * @param inputFile
	 * @param outputFile
	 */
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

	/**
	 * @param key
	 * @param encryptionKey
	 * @return
	 */
	public byte[] encryptKey(String key, PublicKey encryptionKey) {
		Cipher cipher;
		byte[] encryptedKey = null;
		try {
			cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
			encryptedKey = cipher.doFinal(key.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (InvalidKeyException e) {

			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();
		} catch (BadPaddingException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		return encryptedKey;
	}

	/**
	 * @param encryptedContent
	 * @param encryptionKey
	 * @return
	 */
	public String decryptKey(byte[] encryptedContent, PrivateKey encryptionKey) {
		Cipher cipher;
		String decryptedKey = null;
		try {
			cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
			cipher.init(Cipher.DECRYPT_MODE, encryptionKey);
			decryptedKey = new String(cipher.doFinal(encryptedContent), "UTF-8");
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (InvalidKeyException e) {

			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();
		} catch (BadPaddingException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		return decryptedKey;
	}

	/**
	 * @param key
	 */
	public void criptingKey(String key) {

		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		KeyPair keyPair = keyGen.generateKeyPair();

		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();

		byte[] encryptedText = encryptKey(key, publicKey);
		System.out.println("Encrypted key for files:" + new String(encryptedText));

		Encoder encoder = Base64.getEncoder();
		System.out.println("Base64 encoded encrypted key for files: " + encoder.encodeToString(encryptedText));

		String decryptedStringWithOriginalKey = decryptKey(encryptedText, privateKey);
		System.out.println("Decrypted key for files with correct key:" + decryptedStringWithOriginalKey.trim());

	}

	/**
	 * @return
	 */
	public String getRandKey() {
		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance("AES");

		} catch (NoSuchAlgorithmException e) {
			System.out.println("There is no such algorithm");
			e.printStackTrace();
		}
		keyGenerator.init(128, new SecureRandom());
		SecretKey key = keyGenerator.generateKey();
		String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
		return encodedKey;

	}

	/**
	 * @param fileName
	 * @param theKey
	 * @param fileStoring
	 * @return
	 */
	public String getKeyToDecrypt(String fileName, String theKey, String fileStoring) {
		Properties prop = new Properties();
		InputStream input;
		String key = "";

		try {
			input = new FileInputStream(fileStoring);
			prop.load(input);

			key = prop.getProperty(fileName);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return key;
	}

	/**
	 * @param fileName
	 * @param theKey
	 * @param fileStoring
	 */
	public void storeKeyFilePair(String fileName, String theKey, String fileStoring) {

		Path fileNameProp = Paths.get(fileStoring);
		OutputStream output;
		try {
			if (!Files.exists(fileNameProp)) {

				Files.createFile(fileNameProp);

				Properties prop = new Properties();
				prop.setProperty(fileName, theKey);
				output = new FileOutputStream(fileStoring);

				prop.store(output, null);
				output.close();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
