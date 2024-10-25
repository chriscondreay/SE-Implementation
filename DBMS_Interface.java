package DBMS_Project;

public interface DBMS_Interface {

	public void connect(String fileName); // throws exception
	
	public void disconnect(String fileName); // throws exception
	
	public Record insert(Record record);
	
	public boolean contains(String field, String value);
	
	public Record[] update(Field search, Field modify);
	
	public Record[] select(Field search); // throws exception
	
	public Record[] delete(Field search); // throws exception

}