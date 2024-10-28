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
						}
					// creates and adds Field objects to Record arraylist
					} else if (count > 1) {
						String[] values = line.split(",");
						for (int i = 0; i < values.length; i++) {
							Field pair = new Field(fieldNames.get(i), values[i]);
							record.addEntry(pair);
						}
					}
					
					// checks to make sure lines 1 (make, model, year) and 2 (string, string, string) from csv are not added, but all the complete
					// records afterwards are
					if (count > 1) {
						contents.add(record);
					}
					count++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
       
            System.out.println("\nDatabase file exists and is connected database.");
            
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
	
	        	DBMSFilePath = "";
	        	connected = false;
	   
	            System.out.println("\nDatabase is disconnected.");      
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
			System.out.println("Not connected to the database.");
			return null;
		}

		contents.add(record);
		System.out.println("New record inserted to database!");
		return record;
	}

	@Override
	public boolean contains(String field, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Record[] update(Field search, Field modify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record[] select(Field search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record[] delete(Field search) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// prints the database records
	public void printDBRecords() {
		for(int i = 0; i < contents.size()-1; i++) {
    		contents.get(i).printRecord();
    		System.out.println();
    		}
	}
}
