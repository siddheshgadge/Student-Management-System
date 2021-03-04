import java.util.*;
import javax.swing.table.AbstractTableModel;

class StudentTableModel extends AbstractTableModel{
	
	private List<Student> db;

	private String[] colNames = {"Roll No","Name","Subject-1","Subject-2","Subject-3"};

	public String getColumnName(int column) {
		return colNames[column];
	}

	public void setData(List<Student> db) {
		this.db = db;
	}

	public int getColumnCount() {
		return 5;
	}

	public int getRowCount() {
		return db.size();
	}

	public Object getValueAt(int row, int col) {
		
		Student student = db.get(row);

		switch(col) {
			case 0 -> {return student.getRno();}
			case 1 -> {return student.getName();}
			case 2 -> {return student.getSub1Marks();}
			case 3 -> {return student.getSub2Marks();}
			case 4 -> {return student.getSub3Marks();}
		}

		return null;
	}
}