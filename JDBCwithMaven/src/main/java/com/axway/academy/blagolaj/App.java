package com.axway.academy.blagolaj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class App {

	static void createTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:tested.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			// SQL statement for creating a new table
			String sql = "CREATE TABLE Employees " + "(ID INT PRIMARY KEY     NOT NULL,"
					+ " NAME           TEXT    NOT NULL, " + " SURNAME        TEXT    NOT NULL, "
					+ " BIRTHDATE      TEXT)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	static void condb() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:tested.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			// SQL statement for inserting into a table

			String sql = "INSERT INTO Employees (ID,NAME,SURNAME,BIRTHDATE) "
					+ "VALUES (1, 'Ivan', 'Georgiev', '1995-10-18');";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Employees (ID,NAME,SURNAME,BIRTHDATE) " + "VALUES (2, 'Titko', 'Titkov', '1996-01-21');";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Employees (ID,NAME,SURNAME,BIRTHDATE) "
					+ "VALUES (3, 'Nikolay', 'Sapundzhiev', '1995-03-16');";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Employees (ID,NAME,SURNAME,BIRTHDATE) "
					+ "VALUES (4, 'Atanas', 'Tachev', '1994-04-15');";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Employees (ID,NAME,SURNAME,BIRTHDATE) "
					+ "VALUES (5, 'Dragomir', 'Dragoev', '1991-11-19');";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		System.out.println("Records inserted successfully");
	}

	static void select(String sql) {

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:tested.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			sql = sql.toUpperCase();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String birthdate = "";
				if (sql.contains("MAX")) {
					birthdate = (rs.getString("MAX(birthdate)").replaceAll("-", ""));
					System.out.println("The youngest employee is: ");

				} else if (sql.contains("MIN")) {
					birthdate = (rs.getString("MIN(birthdate)").replaceAll("-", ""));
					System.out.println("The oldest employee is: ");

				} else {
					birthdate = (rs.getString("birthdate").replaceAll("-", ""));
				}
				System.out.println("ID: " + id);
				System.out.println("NAME: " + name);
				System.out.println("SURNAME: " + surname);
				System.out.println("BIRTHDATE = " + birthdate);
				System.out.println();

			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		System.out.println("The select query was executed successfully. ");
	}

	public static void main(String args[]) {

		// createTable();
		// condb();
		select("SELECT ID, NAME, SURNAME, min(BIRTHDATE) FROM Employees");
		select("SELECT ID, NAME, SURNAME, max(BIRTHDATE) FROM Employees");
		select("SELECT ID, NAME, SURNAME, BIRTHDATE FROM Employees");
	}
}
