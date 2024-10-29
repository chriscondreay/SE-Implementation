package DBMS_Project;

import java.io.FileNotFoundException;

public interface DBMS_Interface {

	public void connect(String fileName) throws FileNotFoundException; // throws exception
	
	public void disconnect(String fileName) throws FileNotFoundException; // throws exception
	
	public Record insert(Record record);
	
	public boolean contains(String field, String value);
	
	public ArrayList<Record> update(Field search, Field modify);
	
	public ArrayList<Record> select(Field search); // throws exception
	
	public ArrayList<Record> delete(Field search); // throws exception

}
