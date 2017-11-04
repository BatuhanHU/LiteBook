import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JMenuItem;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JEditorPane;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.AbstractListModel;

public class ProfilePage {
	private static int selected=0;
	private JFrame frmProfilePage;
	private JTextField TextField_SearchFriends;
	private JList<Object> list = new JList<Object>(UserCollection.users.get(UserCollection.SignedInUserId).getFriends());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfilePage window = new ProfilePage();
					window.frmProfilePage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProfilePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProfilePage = new JFrame();
		frmProfilePage.setTitle("Profile Page");
		frmProfilePage.setResizable(false);
		frmProfilePage.setVisible(true);
		frmProfilePage.setBounds(100, 100, 839, 726);
		frmProfilePage.setLocationRelativeTo(null);
		frmProfilePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProfilePage.getContentPane().setLayout(null);
		
		JLabel lblUserIcon = new JLabel("");
		lblUserIcon.setBounds(10, 38, 158, 171);
		lblUserIcon.setIcon(new ImageIcon("personicon8.png"));
		frmProfilePage.getContentPane().add(lblUserIcon);
		
		JPanel panel_Info = new JPanel();
		panel_Info.setBounds(10, 220, 212, 219);
		panel_Info.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmProfilePage.getContentPane().add(panel_Info);
		panel_Info.setLayout(null);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(20, 11, 151, 14);
		lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_Info.add(lblDateOfBirth);
		
		JLabel lblBirthDate = new JLabel(UserCollection.users.get(UserCollection.SignedInUserId).getDateofBirth());
		lblBirthDate.setBounds(20, 36, 151, 14);
		panel_Info.add(lblBirthDate);
		
		JLabel lblSchoolGraduated = new JLabel("School Graduated");
		lblSchoolGraduated.setBounds(20, 61, 151, 14);
		lblSchoolGraduated.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_Info.add(lblSchoolGraduated);
		
		JLabel lblSchoolName = new JLabel(UserCollection.users.get(UserCollection.SignedInUserId).schoolGraduated);
		lblSchoolName.setBounds(20, 86, 151, 14);
		panel_Info.add(lblSchoolName);
		
		JLabel lblRelationshipStatus = new JLabel("Relationship Status");
		lblRelationshipStatus.setBounds(20, 111, 151, 14);
		lblRelationshipStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_Info.add(lblRelationshipStatus);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(20, 136, 151, 20);
		if(UserCollection.users.get(UserCollection.SignedInUserId).relationship_status.equals("Single")){
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Single", "Complicated", "Divorced", "In relationship"}));
		}
		else if(UserCollection.users.get(UserCollection.SignedInUserId).relationship_status.equals("Complicated")){
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Complicated", "Single", "Divorced", "In relationship"}));
		}
		else if(UserCollection.users.get(UserCollection.SignedInUserId).relationship_status.equals("Divorced")){
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Divorced", "Single", "Complicated", "In relationship"}));
		}
		else{
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"In relationship", "Single", "Complicated", "Divorced"}));
		}
		panel_Info.add(comboBox);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserCollection.users.get(UserCollection.SignedInUserId).updateProfile((String) comboBox.getSelectedItem());
				frmProfilePage.setVisible(false);
	        	ProfilePage profilepage = new ProfilePage();
			}
		});
		btnUpdate.setBounds(20, 167, 99, 23);
		panel_Info.add(btnUpdate);
		
		JLabel lblInfo = new JLabel("Information");
		lblInfo.setBounds(10, 206, 212, 14);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		frmProfilePage.getContentPane().add(lblInfo);
		
		JLabel lblFriends = new JLabel("Friends");
		lblFriends.setBounds(10, 450, 46, 14);
		frmProfilePage.getContentPane().add(lblFriends);
		
		
		
		JRadioButton rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.setBounds(62, 446, 71, 23);
		frmProfilePage.getContentPane().add(rdbtnNormal);
		rdbtnNormal.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	selected = 0;
	        	frmProfilePage.setVisible(false);
	        	ProfilePage profilepage = new ProfilePage();
	        }
	    });
		
		
		JRadioButton rdbtnBlocked = new JRadioButton("Blocked");
		rdbtnBlocked.setBounds(135, 446, 87, 23);
		frmProfilePage.getContentPane().add(rdbtnBlocked);
		rdbtnBlocked.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	selected = 1;
	        	frmProfilePage.setVisible(false);
	        	ProfilePage profilepage = new ProfilePage();
	        }
	    });
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNormal);
		group.add(rdbtnBlocked);
		
		
		if(selected == 1){
			list = new JList<Object>(UserCollection.users.get(UserCollection.SignedInUserId).getBlockedUsers());
			rdbtnBlocked.setSelected(true);
		}
		else{
			rdbtnNormal.setSelected(true);
		}
		
		
		JPanel panel_Remove = new JPanel();
		panel_Remove.setBounds(10, 475, 212, 186);
		panel_Remove.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmProfilePage.getContentPane().add(panel_Remove);
		panel_Remove.setLayout(null);
		

		JScrollPane scrollPane_Friends = new JScrollPane(list);
		scrollPane_Friends.setBounds(10, 11, 192, 139);
		scrollPane_Friends.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_Remove.add(scrollPane_Friends);
		
		JButton btnRemoveSelectedUser = new JButton("Remove Selected User");
		btnRemoveSelectedUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected == 1){
					UserCollection.users.get(UserCollection.SignedInUserId).unblock((String) list.getSelectedValue());
					frmProfilePage.setVisible(false);
		        	ProfilePage profilepage = new ProfilePage();
				}
				else{
					UserCollection.users.get(UserCollection.SignedInUserId).removeFriend((String) list.getSelectedValue());
					frmProfilePage.setVisible(false);
		        	ProfilePage profilepage = new ProfilePage();
				}
			}
		});
		btnRemoveSelectedUser.setBounds(10, 152, 192, 23);
		panel_Remove.add(btnRemoveSelectedUser);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(232, 220, 591, 441);
		frmProfilePage.getContentPane().add(tabbedPane);
		
		JPanel panel_Posts = new JPanel();
		tabbedPane.addTab("Posts", null, panel_Posts, null);
		panel_Posts.setLayout(null);
		
		JPanel panel_FriendsPosts = new JPanel();
		tabbedPane.addTab("Friend's Posts", null, panel_FriendsPosts, null);
		panel_FriendsPosts.setLayout(null);
		
		JLabel lblNameSurname = new JLabel(UserCollection.users.get(UserCollection.SignedInUserId).name);
		lblNameSurname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNameSurname.setBounds(178, 159, 349, 36);
		frmProfilePage.getContentPane().add(lblNameSurname);
		
		JScrollPane scrollPane_Search = new JScrollPane((Component) null);
		scrollPane_Search.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_Search.setBounds(246, 0, 204, 105);
		scrollPane_Search.setVisible(false);
		frmProfilePage.getContentPane().add(scrollPane_Search);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 0, 255));
		frmProfilePage.setJMenuBar(menuBar);
		
		Component horizontalStrut = Box.createHorizontalStrut(159);
		menuBar.add(horizontalStrut);
		
		JLabel lblSearchFriends = new JLabel("Search Friends");
		lblSearchFriends.setForeground(new Color(255, 255, 255));
		menuBar.add(lblSearchFriends);
		
		TextField_SearchFriends = new JTextField();
		menuBar.add(TextField_SearchFriends);
		TextField_SearchFriends.setHorizontalAlignment(SwingConstants.LEFT);
		TextField_SearchFriends.setColumns(10);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(176);
		menuBar.add(horizontalStrut_1);
		
		JButton btnCreatePost = new JButton("Create a Post");
		btnCreatePost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProfilePage.setVisible(false);
				AddPost addpost = new AddPost();
			}
		});
		menuBar.add(btnCreatePost);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(24);
		menuBar.add(horizontalStrut_2);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmProfilePage.setVisible(false);
				Main main = new Main();
			}
		});
		menuBar.add(btnLogout);
	}
}
