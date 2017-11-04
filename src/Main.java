import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.ListModel;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class Main {

	private JFrame frmFacebookLoginPage;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            Scanner scanner = new Scanner(new File(args[0]));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] data = line.split("\t");
                UserCollection.AddUser(data);
            }
            scanner.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("No File Found!");
            return;
        }
        try {

            Scanner scanner = new Scanner(new File(args[1]));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] data = line.split("\t");
                switch (data[0]){
                    case "ADDFRIEND":
                        UserCollection.AddFriend(data[1], data[2]);
                        break;
                    case "BLOCKFRIEND":
                        UserCollection.Block(data[1], data[2]);
                        break;
                    case "ADDPOST-TEXT":
                        UserCollection.users.get(findUser(data[1])).addTextPost(data[2], Double.valueOf(data[3]), Double.valueOf(data[4]), data[5]);
                        break;
                    case "ADDPOST-IMAGE":
                        UserCollection.users.get(findUser(data[1])).addImagePost(data[2], Double.valueOf(data[3]), Double.valueOf(data[4]), data[5], data[6], data[7]);
                        break;
                    case "ADDPOST-VIDEO":
                        UserCollection.users.get(findUser(data[1])).addVideoPost(data[2], Double.valueOf(data[3]), Double.valueOf(data[4]), data[5], data[6], Integer.valueOf(data[7]));
                        break;
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("No File Found!");
            return;
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmFacebookLoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static int findUser(String name){
        for(User person:UserCollection.users){
            if(person.username.equals(name)){
                return UserCollection.users.indexOf(person);
            }
        }
        return -1;
    }

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFacebookLoginPage = new JFrame();
		frmFacebookLoginPage.setTitle("Facebook Login Page");
		frmFacebookLoginPage.setResizable(false);
		frmFacebookLoginPage.setVisible(true);
		frmFacebookLoginPage.setBounds(1000, 100, 577, 519);
		frmFacebookLoginPage.setLocationRelativeTo(null);
		frmFacebookLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFacebookLoginPage.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(411, 83, 138, 20);
		frmFacebookLoginPage.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(411, 114, 138, 20);
		frmFacebookLoginPage.getContentPane().add(passwordField);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(320, 86, 81, 14);
		frmFacebookLoginPage.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(320, 117, 81, 14);
		frmFacebookLoginPage.getContentPane().add(lblPassword);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("") ||new String(passwordField.getPassword()).equals("")){
					JOptionPane.showMessageDialog(frmFacebookLoginPage,
							"Username or Password is can not be empty",
							"Login Error",
							JOptionPane.ERROR_MESSAGE);
		}
				else if(UserCollection.UserSign(textField.getText(), new String(passwordField.getPassword()))){
							frmFacebookLoginPage.setVisible(false);
							ProfilePage profilepage = new ProfilePage();
				}
				else{
							JOptionPane.showMessageDialog(frmFacebookLoginPage,
									"Username or Password is wrong",
									"Login Error",
									JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginButton.setBounds(411, 160, 138, 23);
		frmFacebookLoginPage.getContentPane().add(loginButton);
		
		JLabel Facebooklogo = new JLabel("");
		Facebooklogo.setIcon(new ImageIcon("Facebooklogo1.png"));
		Facebooklogo.setBounds(10, 11, 300, 172);
		frmFacebookLoginPage.getContentPane().add(Facebooklogo);
		JList<Object> list = new JList<Object>(UserCollection.getAllUsernames());
		list.setVisibleRowCount(4);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 324, 539, 78);
		frmFacebookLoginPage.getContentPane().add(list);
		
		JButton removeuserButton = new JButton("Remove User");
		removeuserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = 0;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this user?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					UserCollection.Removeuser((String) list.getSelectedValue());
					frmFacebookLoginPage.setVisible(false);
					Main main = new Main();
				}	
			}
		});
		removeuserButton.setBounds(10, 413, 155, 23);
		frmFacebookLoginPage.getContentPane().add(removeuserButton);
		
		JButton NewuserButton = new JButton("New User");
		NewuserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateUser createuser = new CreateUser();
				frmFacebookLoginPage.setVisible(false);
			}
		});
		NewuserButton.setBounds(453, 413, 96, 23);
		frmFacebookLoginPage.getContentPane().add(NewuserButton);
		
		JLabel lblRegistredUsers = new JLabel("Registred Users");
		lblRegistredUsers.setBounds(10, 299, 108, 14);
		frmFacebookLoginPage.getContentPane().add(lblRegistredUsers);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		frmFacebookLoginPage.setJMenuBar(menuBar);
		
		JLabel lblSystem = new JLabel("System");
		lblSystem.setBackground(Color.WHITE);
		menuBar.add(lblSystem);
	}
}
