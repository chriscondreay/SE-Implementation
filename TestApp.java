package DBMS_Project;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestApp {
	
	public static void main(String args[]) {
		
		DatabaseManagementSystem carDB = new DatabaseManagementSystem();
		
		System.out.print("Enter the file path of your csv file: ");
		Scanner sc = new Scanner(System.in);
		String fileName = sc.nextLine();
		
		// testing method to connect to database file
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
		
		// prints records from database to ensure file is connected and files are correctly add to arraylist
		carDB.printDBRecords();
		
		Record newCar = new Record();
		newCar.addEntry(new Field("make", "honda"));
		newCar.addEntry(new Field("model", "civic type R"));
		newCar.addEntry(new Field("year", "2019"));

		// prints database records to ensure the record was inserted and the database was updated correctly
		carDB.insert(newCar);
		carDB.printDBRecords();

		System.out.println("Checking if database contains a 'honda' record.");
		System.out.println(carDB.contains("make", "Honda"));

		System.out.println("Checking if database contains a 'chevrolet' record.");
		System.out.println(carDB.contains("make", "chevrolet"));

		// testing to see if select method
		Field honda = new Field("make", "honda");
		Field lambo = new Field("make", "lambo");
		carDB.select(honda);
		carDB.select(lambo);
		
		// testing disconnect method
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
    }
}
