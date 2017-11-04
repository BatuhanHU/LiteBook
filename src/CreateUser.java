import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateUser {

	private JFrame frame;
	private JTextField textField_School;
	private JTextField textField_NameSurname;
	private JTextField textField_Username;
	private JPasswordField passwordField;
	private JPasswordField passwordField_Re;
	private JTextField textField_Birth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser window = new CreateUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setBounds(100, 100, 342, 438);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Batuhan\\workspace\\assignment4\\Facebooklogo1.png"));
		lblNewLabel.setBounds(10, 11, 300, 135);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Create User");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(100, 133, 118, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setBounds(10, 190, 105, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 215, 105, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblPassword_1 = new JLabel("Password (re-type)");
		lblPassword_1.setBounds(10, 240, 110, 14);
		frame.getContentPane().add(lblPassword_1);
		
		JLabel lblNameSurname = new JLabel("Name Surname");
		lblNameSurname.setBounds(10, 265, 105, 14);
		frame.getContentPane().add(lblNameSurname);
		
		JLabel lblSchoolGraduated = new JLabel("School Graduated");
		lblSchoolGraduated.setBounds(10, 290, 105, 14);
		frame.getContentPane().add(lblSchoolGraduated);
		
		JLabel lblBirthDate = new JLabel("Birth Date");
		lblBirthDate.setBounds(10, 315, 105, 14);
		frame.getContentPane().add(lblBirthDate);
		
		JLabel lblRelationshipStatus = new JLabel("Relationship Status");
		lblRelationshipStatus.setBounds(10, 340, 110, 14);
		frame.getContentPane().add(lblRelationshipStatus);
		
		textField_School = new JTextField();
		textField_School.setBounds(125, 287, 201, 20);
		frame.getContentPane().add(textField_School);
		textField_School.setColumns(10);
		
		textField_NameSurname = new JTextField();
		textField_NameSurname.setBounds(125, 262, 201, 20);
		frame.getContentPane().add(textField_NameSurname);
		textField_NameSurname.setColumns(10);
		
		textField_Username = new JTextField();
		textField_Username.setBounds(125, 187, 86, 20);
		frame.getContentPane().add(textField_Username);
		textField_Username.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(125, 212, 86, 20);
		frame.getContentPane().add(passwordField);
		
		passwordField_Re = new JPasswordField();
		passwordField_Re.setBounds(125, 237, 86, 20);
		frame.getContentPane().add(passwordField_Re);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Single", "Complicated", "Divorced", "In relationship"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(125, 337, 118, 20);
		frame.getContentPane().add(comboBox);
		
		textField_Birth = new JTextField();
		textField_Birth.setBounds(125, 312, 86, 20);
		frame.getContentPane().add(textField_Birth);
		textField_Birth.setColumns(10);
		
		JButton btnCreateUserButton = new JButton("Create");
		btnCreateUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField_Username.getText().equals("")){
					JOptionPane.showMessageDialog(frame,
							"Username box can not be empty",
							"Create Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(new String(passwordField.getPassword()).equals("")){
					JOptionPane.showMessageDialog(frame,
							"Password box can not be empty",
							"Create Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(textField_NameSurname.getText().equals("")){
					JOptionPane.showMessageDialog(frame,
							"Name-Surname box can not be empty",
							"Create Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(textField_School.getText().equals("")){
					JOptionPane.showMessageDialog(frame,
							"School graduated box can not be empty",
							"Create Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(textField_Birth.getText().equals("")){
					JOptionPane.showMessageDialog(frame,
							"Birth Date box can not be empty",
							"Create Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(! new String(passwordField.getPassword()).equals(new String(passwordField_Re.getPassword()))){
					JOptionPane.showMessageDialog(frame,
							"Passwords does not match",
							"Create Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else{
					String[] data = new String[6];
					data[0] = textField_NameSurname.getText();data[1] = textField_Username.getText();data[2] = new String(passwordField.getPassword());
					data[3] = textField_Birth.getText();data[4] = textField_School.getText();data[5] = (String) comboBox.getSelectedItem();
					UserCollection.AddUser(data);
					
					frame.setVisible(false);
					Main main = new Main();
				}
				
			}
		});
		btnCreateUserButton.setBounds(100, 375, 143, 23);
		frame.getContentPane().add(btnCreateUserButton);
	}
}
