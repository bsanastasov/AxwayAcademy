package com.axway.academy.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.axway.academy.beans.Movie;
import com.axway.academy.beans.UserAccount;


public class DBUtils {
	 
	  public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException {
	 
	      String sql = "Select u.User_Name, u.Password, u.Address from User_Account u "
	              + " where u.User_Name = ? and u.password= ?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setString(1, userName);
	      pstm.setString(2, password);
	      ResultSet rs = pstm.executeQuery();
	 
	      if (rs.next()) {
	    	  String address = rs.getString("Address");
	    	  UserAccount user = new UserAccount();
	          user.setUserName(userName);
	          user.setPassword(password);
	          user.setAddress(address);
	          return user;
	      }
	      return null;
	  }
	 
	  public static UserAccount findUser(Connection conn, String userName) throws SQLException {
	 
	      String sql = "Select u.User_Name, u.Password, u.Address from User_Account u " + " where u.User_Name = ? ";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setString(1, userName);
	 
	      ResultSet rs = pstm.executeQuery();
	 
	      if (rs.next()) {
	          String password = rs.getString("Password");
	          String address = rs.getString("Address");
	          UserAccount user = new UserAccount();
	          user.setUserName(userName);
	          user.setPassword(password);
	          user.setAddress(address);
	          return user;
	      }
	      return null;
	  }
	  
	  public static void addUser(Connection conn, UserAccount u) throws SQLException {
		  String sql = "Insert into User_Account(user_name, password, address) values(?,?,?)";
		  PreparedStatement psmt = conn.prepareStatement(sql);
		  psmt.setString(1, u.getUserName());
		  psmt.setString(2, u.getPassword());
		  psmt.setString(3, u.getAddress());
		  psmt.executeUpdate();
		  
		  
	  }
	  
	  public static List<UserAccount> queryUser(Connection conn) throws SQLException {
			String sql = "Select u.user_name, u.password, u.address from User_Account u";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			List<UserAccount> list = new ArrayList<UserAccount>();
			while (rs.next()) {
				String userName = rs.getString("user_name");
				String password = rs.getString("password");
				String address = rs.getString("address");
				
				UserAccount u = new UserAccount();
				u.setUserName(userName);
				u.setPassword(password);
				u.setAddress(address);
				list.add(u);
			}
			return list;
			
		}
	  
	  public static void deleteUser(Connection conn, String userName) throws SQLException {
			String sql = "Delete from User_Account where user_name=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, userName);
			psmt.executeUpdate();
		}
	
	public static List<Movie> queryMovie(Connection conn) throws SQLException {
		String sql = "Select m.id, m.title, m.star, m.director, m.budget from Movie m";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<Movie> list = new ArrayList<Movie>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String star = rs.getString("star");
			String director = rs.getString("director");
			double budget = rs.getDouble("budget");
			Movie movie = new Movie();
			movie.setId(id);
			movie.setTitle(title);
			movie.setStar(star);
			movie.setDirector(director);
			movie.setBudget(budget);
			list.add(movie);
		}
		return list;
		
	}
	
	public static Movie findMovie(Connection conn, int id) throws SQLException {
		String sql = "Select m.id, m.title, m.star, m.director, m.budget from Movie m where m.id=?";
		
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setInt(1, id);
		ResultSet rs = psmt.executeQuery();
		
		while (rs.next()) {
			String title = rs.getString("title");
			String star = rs.getString("star");
			String director = rs.getString("director");
			double budget = rs.getDouble("budget");
			Movie movie = new Movie(id, title, star, director, budget);
			return movie;
		}
		return null;
	}
	
	public static void exportMovie(Connection conn, int id, String filename) throws SQLException {
		
		
		String sql = "Select m.id, m.title, m.star, m.director, m.budget from Movie m where m.id=?";
		
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setInt(1, id);
		ResultSet rs = psmt.executeQuery();
		
		while (rs.next()) {
			String title = rs.getString("title");
			String star = rs.getString("star");
			String director = rs.getString("director");
			double budget = rs.getDouble("budget");
			Movie movie = new Movie(id, title, star, director, budget);
			Path filepath = Paths.get(filename);
			if (!Files.exists(filepath)) {
				try {
					Files.createFile(filepath);

				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			String content = title.concat(", ").concat(star).concat(", ").concat(director).concat(", ").concat(String.valueOf(budget));
			try {
				Files.write(filepath, content.getBytes());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
	
}
	
	public static void updateMovie(Connection conn, Movie movie) throws SQLException {
		String sql = "Update Movie set title=?, star=?, director=?, budget=? where id=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, movie.getTitle());
		psmt.setString(2, movie.getStar());
		psmt.setString(3, movie.getDirector());
		psmt.setDouble(4, movie.getBudget());
		psmt.setInt(5, movie.getId());
		psmt.executeUpdate();
	}
	
	public static void insertMovie(Connection conn, Movie movie) throws SQLException {
		String sql = "Insert into Movie(id, title, star, director, budget) values(?,?,?,?,?)";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setInt(1, movie.getId());
		psmt.setString(2, movie.getTitle());
		psmt.setString(3, movie.getStar());
		psmt.setString(4, movie.getDirector());
		psmt.setDouble(5, movie.getBudget());
		
		psmt.executeUpdate();
	}
	
	public static void deleteMovie(Connection conn, int id) throws SQLException {
		String sql = "Delete from Movie where id=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setInt(1, id);
		psmt.executeUpdate();
	}
	
	
}
