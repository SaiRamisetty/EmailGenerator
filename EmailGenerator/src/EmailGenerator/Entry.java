package EmailGenerator;

import java.sql.*;
import java.util.Scanner;

public class Entry {
	static String JDBC_URL = "jdbc:mysql://localhost:3306/emailgenerator";
	static String Username = "root";
	static String Password = "(Lsvs1919)";
	static Scanner Sc = new Scanner(System.in);
//	static Email obj = new Email();
	public static void main(String[] args) {
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, Username, Password);
			System.out.println("Welcome to the Email generator ....");
			System.out.println("Select the choice : ");
			System.out.println("1.Create new email.");
			System.out.println("2.Modify existing email.");
			System.out.println("3.Exit");
			int choice = Sc.nextInt();
			switch(choice){
				case 1 :
					String x = Email.getEmail();
					String y = Email.RandomPass();
					Email.setEmail(connection, x, y);
					System.out.println("Your Email "+ x +" is created Successfully..");
					System.out.println("Your default password is " + y);
					System.out.println("Press 0 to remain the password");
					System.out.println("Press 1 to change the password");
					int p = Sc.nextInt();
					if (p == 0) {
						System.out.println("Your password remains, please note and store your password safely..");
					}
					if (p == 1) {
						Email.ChangePassword(connection, x);
						System.out.println("Your password changed successfully.. ");
					}else {
						System.out.println("Invalid option..");
					}
					break;
				case 2 :
					System.out.print("Enter the email : ");
					String email = Sc.next();
					if (Email.CheckEmailExists(connection,email)) {
						Email.ChangePassword(connection, email);
						System.out.println("Your password changed successfully.. ");
					}
					else {
						System.out.println("Invalid Email..");
					}
					break;
				case 3:
					System.out.println("Thanks for using email generator, Bye..");
				default :
					System.out.println("Invalid choice..");
					
			}
			
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		}

	}
