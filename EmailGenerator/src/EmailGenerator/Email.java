package EmailGenerator;

import java.sql.*;
import java.util.Scanner;

public class Email {
	static Scanner sc =new Scanner(System.in);
	
	static String EmailGen(String Firstname , String Lastname) {
		String email = Firstname + "." + Lastname + "@" + "gmail.com";
		return email;
	}
	static String RandomPass(){
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()";
		char [] a = s.toCharArray();
		String password = "";
		for (int i = 0; i < 10 ; i++) {
			int p = (int) (Math.random() * s.length());
			password = password+a[p];
		}
		return password;
	}
	static String getEmail() {
		System.out.print("Enter Your Firstname :");
		String Firstname = sc.next();
		System.out.print("Enter Your Lastname : ");
		String Lastname = sc.next();
		return EmailGen(Firstname, Lastname);
	}
	static void setEmail(Connection connection,String x,String y) {
		try {
		if (CheckEmailExists(connection, x)) {
			System.out.println("This email alreadt exists..");
			getEmail();
		}
		else {
			String sql ="INSERT INTO emails(email, password) VALUES (?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, x);
			statement.setString(2, y);
			statement.executeUpdate();
		}
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	static void ChangePassword(Connection connection,String email) throws SQLException {
		System.out.println("Enter the new password :");
		String Password = sc.next();
		String Sql = "UPDATE emails SET password = ? WHERE email = ?";
		PreparedStatement statement = connection.prepareStatement(Sql);
		statement.setString(1, Password);
		statement.setString(2, email);
		statement.executeUpdate();
	}
	
	static boolean CheckEmailExists(Connection connection, String x) throws SQLException {
		String sql = "Select * from emails where email = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, x);
		ResultSet resultset = statement.executeQuery();
		return resultset.next();
	}
	
	
}
