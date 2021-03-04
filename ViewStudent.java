import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

class ViewStudent extends JFrame {
	
	private JPanel btnPanel;
	private TablePanel tablePanel;
	private JButton backBtn;
	private JButton exportBtn;
	private JFileChooser fileChooser;

	public ViewStudent() {

		btnPanel = new JPanel();
		backBtn = new JButton("BACK");
		exportBtn = new JButton("EXPORT");
		tablePanel = new TablePanel();
		fileChooser = new JFileChooser();

		layoutControl();

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory sf = cfg.buildSessionFactory();
		Session s = null;

		List<Student> st = new ArrayList<>();

		try {

			s = sf.openSession();

			st = s.createQuery("from Student order by rno").list();

			tablePanel.setData(st);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(ViewStudent.this, "Something went wrong!","Database Error",JOptionPane.ERROR_MESSAGE);
		} finally {
			s.close();
		}

		// Button sizes
		Dimension btnSize = exportBtn.getPreferredSize();
		backBtn.setPreferredSize(btnSize);

		// Button clicked events

		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame  m = new MainFrame();
				dispose();
			}
		});

		exportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showSaveDialog(ViewStudent.this) == JFileChooser.APPROVE_OPTION) {
					try {
						tablePanel.saveToFile(fileChooser.getSelectedFile());
					} catch(Exception ve) {
						JOptionPane.showMessageDialog(ViewStudent.this,"Could not save data to file! " + ve,"Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		setTitle("View Students");
		setSize(400,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void layoutControl() {
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnPanel.add(backBtn);
		btnPanel.add(exportBtn);
		setLayout(new BorderLayout());
		add(tablePanel,BorderLayout.CENTER);
		add(btnPanel,BorderLayout.SOUTH);

		// Colors
		tablePanel.setBackground(Color.decode("#d2f6c5"));
		btnPanel.setBackground(Color.decode("#d2f6c5"));

		exportBtn.setBackground(Color.decode("#f6f7d4"));
		backBtn.setBackground(Color.decode("#f6f7d4"));
	}
}