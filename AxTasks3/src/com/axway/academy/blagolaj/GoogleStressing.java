package com.axway.academy.blagolaj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GoogleStressing {

	private static void HTTPClientGoogle() {

		BufferedReader br = null;
		InputStreamReader ir = null;
		InputStream is = null;
		HttpURLConnection conn = null;

		try {
			URL url = new URL("http://www.google.bg");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			int responseCode = conn.getResponseCode();
			String responseMessage = conn.getResponseMessage();
			System.out.println("The response code is: " + responseCode);
			System.out.println("The response message is: " + responseMessage);

			is = conn.getInputStream();
			ir = new InputStreamReader(is);
			br = new BufferedReader(ir);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

		} catch (MalformedURLException e) {
			System.out.println("Cannot form URL.");
		} catch (IOException e) {
			System.out.println("Problems openning connection.");
			e.printStackTrace();
		} finally {

			try {
				if (br != null) {
					br.close();
				}
				if (ir != null) {
					ir.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error ocquired in closing streams.");
				ioe.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		HTTPClientGoogle();
	}

}
