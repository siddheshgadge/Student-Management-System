import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

class StudentsCharts extends JFrame {
	private JPanel btnPanel;
	private JButton backBtn;
	private JButton downloadBtn;
	private ChartPanel barGraph;

	public StudentsCharts() {
		btnPanel = new JPanel();
		backBtn = new JButton("BACK");
		downloadBtn = new JButton("DOWNLOAD");

		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","root","abc456");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("call p3()");  
			while(rs.next())  {
				ds.addValue(rs.getInt(2),rs.getString(1),"Subject-1");
				ds.addValue(rs.getInt(3),rs.getString(1),"Subject-2");
				ds.addValue(rs.getInt(4),rs.getString(1),"Subject-3");
			} 
			con.close();  
		}catch(Exception e){
			JOptionPane.showMessageDialog(StudentsCharts.this,"Something went wrong" ,"Error",JOptionPane.ERROR_MESSAGE);

		}  

		// Graph 

		JFreeChart chart = ChartFactory.createBarChart("Top-3 Student's Performance","Subjects","Marks",ds,PlotOrientation.VERTICAL,true,false,false);

		barGraph = new ChartPanel(chart);
		barGraph.setVisible(true);

		layoutControl();

		// button size
		Dimension btnSize = downloadBtn.getPreferredSize();
		backBtn.setPreferredSize(btnSize);

		// Button Click events
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame  m = new MainFrame();
				dispose();
			}
		});

		downloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File img = new File("Top3_Student's_Performance.jpeg");
					ChartUtilities.saveChartAsJPEG(img,chart,700,400);
					JOptionPane.showMessageDialog(StudentsCharts.this,"Download Successful!");
				} catch(IOException ve) {
					JOptionPane.showMessageDialog(StudentsCharts.this,"Download UnSuccessful!","IOException Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		setTitle("Student's Performance");
		setSize(700,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void layoutControl() {
		setLayout(new BorderLayout());
		add(barGraph,BorderLayout.CENTER);
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnPanel.add(backBtn);
		btnPanel.add(downloadBtn);
		add(btnPanel,BorderLayout.SOUTH);

		// Colors
		barGraph.setBackground(Color.decode("#d2f6c5"));
		btnPanel.setBackground(Color.decode("#d2f6c5"));

		downloadBtn.setBackground(Color.decode("#f6f7d4"));
		backBtn.setBackground(Color.decode("#f6f7d4"));
	}
}