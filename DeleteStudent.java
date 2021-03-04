import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.border.*;
import javax.swing.BorderFactory.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

public class DeleteStudent extends JFrame {

private JLabel rnoLabel,nameLabel,sub1Label,sub2Label,sub3Label;
private JTextField rnoText,nameText,sub1Text,sub2Text,sub3Text;
private JButton deleteBtn, backBtn;
private JPanel formPanel;
private int rno;

	public DeleteStudent() {
		rnoLabel =  new JLabel("Enter roll No : ");
		rnoText = new JTextField(20);
		deleteBtn = new JButton("DELETE");
		backBtn = new JButton("BACK");
		formPanel = new JPanel();

		layoutControl();

		// Border

		Border spaceBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		Border lineBorder = BorderFactory.createLineBorder(Color.decode("#121013"));
		Border titleBorder = BorderFactory.createTitledBorder(lineBorder, "DELETE STUDENT INFO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.decode("#121013"));

		formPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleBorder));

		// Button size
		Dimension btnSize = deleteBtn.getPreferredSize();
		backBtn.setPreferredSize(btnSize);

		// Button clicked events

		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame  m = new MainFrame();
				dispose();
			}
		});

		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isValidate = validateTextFields();
				if(isValidate) {
					Configuration cfg = new Configuration();
					cfg.configure("hibernate.cfg.xml");

					SessionFactory sf = cfg.buildSessionFactory();
					Session s = null;
					Transaction t = null;
					try {

						s = sf.openSession();

						t = s.beginTransaction();

						Student stu = (Student)s.get(Student.class, rno);
						if(stu == null) {
							JOptionPane.showMessageDialog(DeleteStudent.this,rno + " does not exists!","No student found",JOptionPane.ERROR_MESSAGE);
							rnoText.setText("");
							rnoText.requestFocus();
						} else {
							s.delete(stu);
							JOptionPane.showMessageDialog(DeleteStudent.this,rno + " roll number Deleted Successfully!");
							rnoText.setText("");
							rnoText.requestFocus();
						}
						t.commit();
					} catch (Exception error) {
						JOptionPane.showMessageDialog(DeleteStudent.this,"Database Error --> " + error,"Database Error",JOptionPane.ERROR_MESSAGE);
						t.rollback();
					} finally {
						s.close();
					}
				}	
			}
		});

		setTitle("Delete Student");
		setSize(300,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void layoutControl() {
		formPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		formPanel.add(rnoLabel);
		formPanel.add(rnoText);
		formPanel.add(deleteBtn);
		formPanel.add(backBtn);
		setLayout(new BorderLayout());
		add(formPanel,BorderLayout.CENTER);

		// Colors
		formPanel.setBackground(Color.decode("#99f3bd"));

		deleteBtn.setBackground(Color.decode("#f6f7d4"));
		backBtn.setBackground(Color.decode("#f6f7d4"));
	}

	private boolean validateTextFields() {
		try {
			rno = Integer.parseInt(rnoText.getText());
			if(rno < 0) {
				JOptionPane.showMessageDialog(DeleteStudent.this,"Enter correct Roll Number!","Wrong Input",JOptionPane.ERROR_MESSAGE);
				rnoText.setText("");
				rnoText.requestFocus();
				return false;
			}
		} catch(NumberFormatException ve){
			JOptionPane.showMessageDialog(DeleteStudent.this,"Please Enter correct roll number!","Wrong Input",JOptionPane.ERROR_MESSAGE);
			rnoText.setText("");
			rnoText.requestFocus();
			return false;
		}
		return true;
	}
}