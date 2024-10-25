package DBMS_Project;

import DBMS_Project.DatabaseManagementSystem;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestApp {
	
	public static void main(String args[]) {
		
		// connect to database file
		
		DatabaseManagementSystem carDB = new DatabaseManagementSystem();

		System.out.print("Enter the file path of your csv file: ");
		Scanner sc = new Scanner(System.in);
		String fileName = sc.nextLine();

		try {
			carDB.connect(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// showing that system is connected to database
		System.out.println();
		System.out.println("Database file path is: " + carDB.getDBPath());
		System.out.println("Database is connected: " + carDB.getConnect());
		System.out.println();
		
		try {
			carDB.disconnect(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// showing that system isn't connected to database
		System.out.println();
		System.out.println("Database file path is: " + carDB.getDBPath());
		System.out.println("Database is connected: " + carDB.getConnect());
		System.out.println();
		
		
		// attempt to connect to a database file (not found purpose)
		
		// other tests
		
		// disconnect from database
		
    }
}
