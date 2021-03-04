import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.border.*;
import javax.swing.BorderFactory.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

public class UpdateStudent extends JFrame {

private JLabel rnoLabel,nameLabel,sub1Label,sub2Label,sub3Label;
private JTextField rnoText,nameText,sub1Text,sub2Text,sub3Text;
private JButton saveBtn, backBtn;
private JPanel formPanel;
private int rno,sub1Marks,sub2Marks,sub3Marks;
private String name;

	public UpdateStudent() {
		rnoLabel =  new JLabel("Enter roll No : ");
		nameLabel =  new JLabel("Enter Name : ");
		sub1Label =  new JLabel("Enter marks for subject-1 : ");
		sub2Label =  new JLabel("Enter marks for subject-2 : ");
		sub3Label =  new JLabel("Enter marks for subject-3 : ");
		rnoText = new JTextField(20);
		nameText = new JTextField(20);
		sub1Text = new JTextField(20);
		sub2Text = new JTextField(20);
		sub3Text = new JTextField(20);
		saveBtn = new JButton("SAVE");
		backBtn = new JButton("BACK");
		formPanel = new JPanel();

		layoutControl();

		// Border

		Border spaceBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		Border lineBorder = BorderFactory.createLineBorder(Color.decode("#121013"));
		Border titleBorder = BorderFactory.createTitledBorder(lineBorder, "UPDATE STUDENT INFO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.decode("#121013"));

		formPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleBorder));

		// Button clicked events

		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame  m = new MainFrame();
				dispose();
			}
		});

		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isValidate = validateTextFields();
				Configuration cfg = new Configuration();
				cfg.configure("hibernate.cfg.xml");

				SessionFactory sf = cfg.buildSessionFactory();
				Session s = null;
				Transaction t = null;

				if(isValidate) {
					try {

						s = sf.openSession();

						t = s.beginTransaction();

						Student stu = (Student)s.get(Student.class, rno);
						if(stu == null) {
							JOptionPane.showMessageDialog(UpdateStudent.this,rno + " does not 	exists!","No student found",JOptionPane.ERROR_MESSAGE);
							rnoText.setText("");
							rnoText.requestFocus();
						} else {
							stu.setName(name);
							stu.setSub1Marks(sub1Marks);
							stu.setSub2Marks(sub2Marks);
							stu.setSub3Marks(sub3Marks);
							s.save(stu);
							JOptionPane.showMessageDialog(UpdateStudent.this,rno + "  Updated Successfully!");
						}
							t.commit();
							rnoText.setText("");
							rnoText.requestFocus();
							nameText.setText("");
							sub1Text.setText("");
							sub2Text.setText("");
							sub3Text.setText("");
						} catch (Exception error) {
							JOptionPane.showMessageDialog(UpdateStudent.this," Database Error -> " + error,"No student found",JOptionPane.ERROR_MESSAGE);
							t.rollback();
						} finally {
							s.close();
						}
					}
				}
			});

	
		setTitle("Update Student");
		setSize(300,350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void layoutControl() {
		formPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		formPanel.add(rnoLabel);
		formPanel.add(rnoText);
		formPanel.add(nameLabel);
		formPanel.add(nameText);
		formPanel.add(sub1Label);
		formPanel.add(sub1Text);
		formPanel.add(sub2Label);
		formPanel.add(sub2Text);
		formPanel.add(sub3Label);
		formPanel.add(sub3Text);
		formPanel.add(saveBtn);
		formPanel.add(backBtn);
		setLayout(new BorderLayout());
		add(formPanel,BorderLayout.CENTER);

		// Colors
		formPanel.setBackground(Color.decode("#99f3bd"));

		saveBtn.setBackground(Color.decode("#f6f7d4"));
		backBtn.setBackground(Color.decode("#f6f7d4"));
	}

	private boolean validateTextFields() {
		//Validating roll number
		try {
			rno = Integer.parseInt(rnoText.getText());
			if(rno < 0) {
				JOptionPane.showMessageDialog(UpdateStudent.this,"Please Enter correct Roll Number!","Wrong Input",JOptionPane.ERROR_MESSAGE);
				rnoText.setText("");
				rnoText.requestFocus();
				return false;
			}
		} catch(NumberFormatException ve){
			JOptionPane.showMessageDialog(UpdateStudent.this,"Please Enter correct roll number!","Wrong Input",JOptionPane.ERROR_MESSAGE);
			rnoText.setText("");
			rnoText.requestFocus();
				return false;
		}
		// Validating name
		 name =  nameText.getText().toLowerCase().trim();
		if(name.length() < 2){
			JOptionPane.showMessageDialog(UpdateStudent.this,"Please Enter correct name!","Wrong Input",JOptionPane.ERROR_MESSAGE);
			nameText.setText("");
			nameText.requestFocus();
				return false;
		} else if(isNotAlphabet(name)) {
			JOptionPane.showMessageDialog(UpdateStudent.this,"Name must have alphabets!","Wrong Input",JOptionPane.ERROR_MESSAGE);
			nameText.setText("");
			nameText.requestFocus();
				return false;
		}

		// Validating marks
		try {
			sub1Marks = Integer.parseInt(sub1Text.getText());
			if(sub1Marks < 0 || sub1Marks > 100) {
				JOptionPane.showMessageDialog(UpdateStudent.this,"subject-1 marks incorrect!","Wrong Input",JOptionPane.ERROR_MESSAGE);
				sub1Text.setText("");
				sub1Text.requestFocus();
				return false;
			}
		} catch(NumberFormatException ve) {
			JOptionPane.showMessageDialog(UpdateStudent.this,"Please Enter correct marks for subject-1","Wrong Input",JOptionPane.ERROR_MESSAGE);
			sub1Text.setText("");
			sub1Text.requestFocus();
				return false;
		}

		try {
			sub2Marks = Integer.parseInt(sub2Text.getText());
			if(sub2Marks < 0 || sub2Marks > 100) {
				JOptionPane.showMessageDialog(UpdateStudent.this,"subject-2 marks incorrect!","Wrong Input",JOptionPane.ERROR_MESSAGE);
				sub2Text.setText("");
				sub2Text.requestFocus();
				return false;
			}
		} catch(NumberFormatException ve) {
			JOptionPane.showMessageDialog(UpdateStudent.this,"Please Enter correct marks for subject-2","Wrong Input",JOptionPane.ERROR_MESSAGE);
			sub2Text.setText("");
			sub2Text.requestFocus();
				return false;
		}

		try {
			sub3Marks = Integer.parseInt(sub3Text.getText());
			if(sub3Marks < 0 || sub3Marks > 100) {
				JOptionPane.showMessageDialog(UpdateStudent.this,"subject-3 marks incorrect!","Wrong Input",JOptionPane.ERROR_MESSAGE);
				sub3Text.setText("");
				sub3Text.requestFocus();
				return false;
			}
		} catch(NumberFormatException ve) {
			JOptionPane.showMessageDialog(UpdateStudent.this,"Please Enter correct marks for subject-3","Wrong Input",JOptionPane.ERROR_MESSAGE);
			sub3Text.setText("");
			sub3Text.requestFocus();
				return false;
		}
		return true;
	}

	private boolean isNotAlphabet(String name) {
		for(int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if(Character.isAlphabetic(ch)) {
				continue;
			} else {
				return true;
			}
		}
		return false;
	}
}