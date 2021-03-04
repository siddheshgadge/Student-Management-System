import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;
import java.awt.event.*;
import java.io.*;

public class TablePanel extends JPanel {
	private JTable table;
	private StudentTableModel tableModel;

	public TablePanel(){
		tableModel = new StudentTableModel();
		table = new JTable(tableModel);

		setLayout(new BorderLayout());

		add(new JScrollPane(table),BorderLayout.CENTER);
	}

	public void setData(List<Student> db) {
		tableModel.setData(db);
	}

	public void saveToFile(File file) throws IOException {

		try {

       		TableModel model = table.getModel();
      		FileWriter csv = new FileWriter(new File(file.getPath()));

        	for (int i = 0; i < model.getColumnCount(); i++) {
      	    	csv.write(model.getColumnName(i) + ",");
        	}

        	csv.write("\n");

        	for (int i = 0; i < model.getRowCount(); i++) {
            	for (int j = 0; j < model.getColumnCount(); j++) {
               		csv.write(model.getValueAt(i, j).toString() + ",");
            	}
            	csv.write("\n");
        	}
        	JOptionPane.showMessageDialog(this,"Exported successfully");
        	csv.close();

    	} catch (IOException e) {
        	JOptionPane.showMessageDialog(new ViewStudent(),"Could not Find File name you specified!","File not found",JOptionPane.ERROR_MESSAGE);
    	}
	}
}