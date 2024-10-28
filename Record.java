package DBMS_Project;

import java.util.ArrayList;

public class Record {
	
	// single array list that holds field types
	// a single array = one record and would look like this
	// (Field, Field, Field1) = toyota, 4runner, 1017
	private ArrayList<Field> record = new ArrayList<Field>();
	
	// constructors
	public Record() {
		
	}
	
	public int getSize() {
		return record.size();
	}
	
	public void addEntry(Field entry) {
		record.add(entry);
	}

	public Field getField(int index) {
		if (index >= 0 && index < record.size()) {
			return record.get(index);
		}
		return null;
	}

	public void printRecord() {
		for (int i = 0; i < record.size(); i++ ) {
			System.out.print(record.get(i).getValue());
			if (i < record.size() - 1) {
				System.out.print(",");
			}
		}
	
	}
}
