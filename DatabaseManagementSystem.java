package DBMS_Project;

import java.io.*;
import java.util.ArrayList;

public class DatabaseManagementSystem implements DBMS_Interface
{
	private String DBMSFilePath;
	private boolean connected;
	private ArrayList<Record> contents = new ArrayList<Record>();
	
	public DatabaseManagementSystem(){ 
		DBMSFilePath = "";
		connected = false;
	}

	// getters (I don't see the need for setters in this scenario as once connected to the database, 
	// user will not change path or "connected" status till disconnect)
	public String getDBPath() {
        return DBMSFilePath;
    }
    public boolean getConnect() {
    	return connected;
    }
	
	@Override
	public void connect(String fileName) throws FileNotFoundException {
		
		File f = new File(fileName);
		 
        // Checking if the database file exists or not
        if (f.exists()) {

        	DBMSFilePath = fileName;
        	connected = true;
        	
        	// reading csv and adding contents into array once connected
        	BufferedReader br = new BufferedReader(new FileReader(fileName));
        	
        	ArrayList<String> fieldNames = new ArrayList<String>();
        	String line = "";
        	int count = 0;
			
        	try {	
				while((line = br.readLine()) != null) {
					
					// temp record object which holds field values to create a complete record arraylist which will be added into larger contents arraylist
					Record record = new Record();
					
					// stores field names from first line of csv (make, model, year) (count = 0))
					// to make it easier to add to Field(name, value) objects
					if (count == 0) {
						String[] names = line.split(",");
						for (int i = 0; i < names.length; i++) {
							fieldNames.add(names[i]);
							Field pair = new Field(fieldNames.get(i), fieldNames.get(i));
							record.addEntry(pair);
						}
						
					// creates and adds Field objects to Record arraylist
					} else if (count > 0) {
						String[] values = line.split(",");
						for (int i = 0; i < values.length; i++) {
							Field pair = new Field(fieldNames.get(i), values[i]);
							record.addEntry(pair);
						}
					}
				
					// adds record to contents array list
					contents.add(record);
					count++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
            System.out.println("\nDatabase file exists and is connected database.");
           
            try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        } 
        // database file is not found
        else {
        	throw new FileNotFoundException("File not found.");
        }
    }
	
	@Override
	public void disconnect(String fileName) throws FileNotFoundException{
		File f = new File(fileName);
		File thisFile = new File(this.DBMSFilePath);
		 
        // database file is found and connected to database
        if (f.getPath().equals(thisFile.getPath()) && ((this.connected))) {
        	
        	try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(DBMSFilePath));
				
				for (Record record : contents) {
					for (int i = 0; i < record.getSize(); i++) {
						Field currentField = record.getField(i);
						bw.write(currentField.getValue() + ",");
					}	
					bw.newLine();
				}
				
				bw.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
        	contents.clear();
        	DBMSFilePath = "";
        	connected = false;
        	
            System.out.println("\nDatabase file is updated and disconnected.");      
        } 
        // database file is found, but is already disconnected
        else if (f.getPath().equals(this.DBMSFilePath) && (!this.connected)) {
        	
        	System.out.println("\nDatabase is found, but database is already disconnected.");
        }
        // file is not found
        else {
        	throw new FileNotFoundException("File not found.");
        }
	}

	@Override
	public Record insert(Record record) {
		if (!connected) {
			System.out.println("\nNot connected to the database.");
			return null;
		}

		contents.add(contents.size(), record);
		System.out.println("New record inserted to database!");
		return record;
	}

	@Override
	public boolean contains(String field, String value) {
		if (!connected) {
			System.out.println("\nNot connected to the database.");
			return false;
		}

		for (Record record : contents) {
			for (int i = 0; i < record.getSize(); i++) {
				Field currentField = record.getField(i);
				if (currentField.getName().equals(field) && currentField.getValue().equals(value)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public Record update(Field search, Field modify) {
		if (!connected) {
			System.out.println("\nNot connected to the database.");
			return null;
		}
		return null;
	}

	@Override
	public Record select(Field search) {
		if (!connected) {
			System.out.println("\nNot connected to the database.");
			return null;
		}
		if (this.contains(search.getName(),search.getValue()) == true) {
			for (Record record : contents) {
				for (int i = 0; i < record.getSize(); i++) {
					Field currentField = record.getField(i);
					if (currentField.getName().equals(search.getName()) && currentField.getValue().equals(search.getValue())) {
						System.out.println("The record you have selected is: ");
						record.printRecord();
						return record;
					}
				}
			}
			
		} else {
			System.out.println("\nRecord not found.");
		}
		return null;
	}

	@Override
	public Record delete(Field search) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// prints the database records
	public void printDBRecords() {
		for(int i = 0; i < contents.size(); i++) {
    		contents.get(i).printRecord();
    		System.out.println();
    	}
		System.out.println("\n");
	}
}
