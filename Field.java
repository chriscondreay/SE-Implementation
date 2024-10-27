package DBMS_Project;

public class Field {
	
	private String name;
	private String value;
	
	// constructors
	public Field() {
		this.name = "";
		this.value = "";
	}

	public Field(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	// getters and setters
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return this.value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
