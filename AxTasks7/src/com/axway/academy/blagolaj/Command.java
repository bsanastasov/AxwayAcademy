package com.axway.academy.blagolaj;

import java.util.Scanner;

public class Command {
	static Scanner input = new Scanner(System.in);

	static void menu() {

		System.out.println("Input a number operation you want to proceed with");
		System.out.println("1.Encrypt");
		System.out.println("2.Decrypt");
		System.out.println("3. Exit");
		int choice = input.nextInt();
		FileOperation fop = new FileOperation();
		Broker broker = new Broker();
		switch (choice) {
		case 1:
			EncryptFile ef = new EncryptFile(fop);
			broker.takeRequest(ef);
			broker.placeRequests();
			menu();
			break;

		case 2:
			DecryptFile df = new DecryptFile(fop);
			broker.takeRequest(df);
			broker.placeRequests();
			menu();
			break;

		case 3:
			return;

		default:
			System.out.println("Type a vaild operation!");
			menu();
		}

	}

	public static void main(String[] args) {
		menu();
	}
}
