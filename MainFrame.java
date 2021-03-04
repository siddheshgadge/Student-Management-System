import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.border.*;
import javax.swing.BorderFactory.*;

class MainFrame extends JFrame {
	private JButton addBtn, viewBtn, updateBtn, deleteBtn, chartsBtn;
	private JPanel btnPanel, headerPanel;
	private JLabel heading;

	public MainFrame() {
	
		addBtn = new JButton("Add");
		viewBtn = new JButton("View");
		updateBtn = new JButton("Update");
		deleteBtn = new JButton("Delete");
		chartsBtn = new JButton("Charts");
		btnPanel = new JPanel();
		headerPanel = new JPanel();
		heading = new JLabel("Student Management System");

		layoutControl();

		// Borders

		Border spaceBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		Border lineBorder = BorderFactory.createLineBorder(Color.decode("#121013"));
		Border titleBorder = BorderFactory.createTitledBorder(lineBorder, "MENU", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.decode("#121013"));

		btnPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleBorder));

		//Sizes of the components

		Dimension btnSize = updateBtn.getPreferredSize();
		addBtn.setPreferredSize(btnSize);
		viewBtn.setPreferredSize(btnSize);
		deleteBtn.setPreferredSize(btnSize);
		chartsBtn.setPreferredSize(btnSize);

		// Button clicked events

		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudent a = new AddStudent();
				dispose();
			}
		});

		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateStudent a = new UpdateStudent();
				dispose();
			}
		});

		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteStudent a = new DeleteStudent();
				dispose();
			}
		});

		viewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewStudent a = new ViewStudent();
				dispose();
			}
		});

		chartsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentsCharts a = new StudentsCharts();
				dispose();
			}
		});

		setTitle("Student Management System");
		setSize(400,230);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void layoutControl() {
		btnPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		Insets rightPadding = new Insets(0,0,0,10);

		// First row
		gc.gridy = 0;

		gc.weightx = 0.1;
		gc.weighty = 0.4;
		gc.fill = GridBagConstraints.NONE;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		btnPanel.add(addBtn,gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.WEST;
		btnPanel.add(viewBtn,gc);

		// Next Row
		gc.gridy++;

		gc.weightx = 0.1;
		gc.weighty = 0.4;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		btnPanel.add(updateBtn,gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.WEST;
		btnPanel.add(deleteBtn,gc);

		// Next Row
		gc.gridy++;

		gc.weightx = 0.1;
		gc.weighty = 0.4;

		gc.gridx = 0;
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.CENTER;
		btnPanel.add(chartsBtn,gc);

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;

		btnPanel.add(new JLabel(),gc);

		headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		headerPanel.add(heading);

		setLayout(new BorderLayout());
		add(btnPanel,BorderLayout.CENTER);
		add(headerPanel,BorderLayout.NORTH);

		// Colors
		btnPanel.setBackground(Color.decode("#99f3bd"));
		headerPanel.setBackground(Color.decode("#99f3bd"));

		addBtn.setBackground(Color.decode("#f6f7d4"));
		viewBtn.setBackground(Color.decode("#f6f7d4"));
		deleteBtn.setBackground(Color.decode("#f6f7d4"));
		updateBtn.setBackground(Color.decode("#f6f7d4"));
		chartsBtn.setBackground(Color.decode("#f6f7d4"));

		// Fonts
		Font f = new Font("Bahnschrift SemiBold",Font.BOLD,20);
		heading.setFont(f);

	}
}