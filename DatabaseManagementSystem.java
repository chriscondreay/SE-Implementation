package DBMS_Project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManagementSystem implements DBMS_Interface
{
	private String DBMSFilePath;
	private boolean connected;
	
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
        if (!f.exists()|| !f.isFile()) {
			// database file is not found
			throw new FileNotFoundException("Database file not found");
        }

		DBMSFilePath = fileName;
		connected = true;

		System.out.println("Database file exists and is connected database.");


    }

	@Override
	public void disconnect(String fileName) throws FileNotFoundException{
		File f = new File(fileName);
		File thisFile = new File(this.DBMSFilePath);
		 
        // database file is found and connected to database
        if (f.getPath().equals(thisFile.getPath()) && ((this.connected == true))) {

        	DBMSFilePath = "";
        	connected = false;
   
            System.out.println("Database is disconnected.");
            
        } 
        // database file is found, but is already disconnected
        else if (f.getPath().equals(this.DBMSFilePath) && (this.connected == false)) {
        	
        	System.out.println("Database is found, but database is already disconnected.");
        	
        }
        // file is not found
        else {
        FileNotFoundException e = new FileNotFoundException("File not found.");
        throw e;
        
        }
	}

	@Override
	public Record insert(Record record) {
		// TODO Auto-generated method stub
		return null;
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

		// checked if the database is connected
		if (!connected) {
			System.out.println("Databse is not connected");
			return new Record[0];
		}

		List<Record> matchingRecords = new ArrayList<>();

		Record[] records = new Record[0];

		// get the value of the field and compare it with the value
		for (Record record : records) {
			if (record.getFieldValue(search.getFieldName()).equals(search.getValue())) {
				matchingRecords.add(record);
			}
		}
		return matchingRecords.toArray(new Record[0]);
	}

	@Override
	public Record[] delete(Field search) {
		// TODO Auto-generated method stub
		return null;
	}

}
