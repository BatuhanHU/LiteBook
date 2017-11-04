import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class AddPost {

	private JFrame frmAddPost;
	private JTextField textField_text;
	private JTextField textField_latitude;
	private JTextField textField_longitude;
	private JTextField textField_duration_width;
	private JTextField textField_height;
	private JTextField textField_filename;
	private static int selection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPost window = new AddPost();
					window.frmAddPost.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddPost() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddPost = new JFrame();
		frmAddPost.setTitle("Add Post");
		frmAddPost.setResizable(false);
		frmAddPost.setVisible(true);
		frmAddPost.setBounds(100, 100, 450, 246);
		frmAddPost.setLocationRelativeTo(null);
		frmAddPost.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddPost.getContentPane().setLayout(null);
		
		JLabel lblText = new JLabel("Text:");
		lblText.setBounds(10, 54, 84, 14);
		frmAddPost.getContentPane().add(lblText);
		
		JLabel lblLatitude = new JLabel("Latitude:");
		lblLatitude.setBounds(10, 79, 84, 14);
		frmAddPost.getContentPane().add(lblLatitude);
		
		JLabel lblFilename = new JLabel("Filename:");
		lblFilename.setVisible(false);
		lblFilename.setBounds(10, 120, 84, 14);
		frmAddPost.getContentPane().add(lblFilename);
		
		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setVisible(false);
		lblDuration.setBounds(10, 145, 84, 14);
		frmAddPost.getContentPane().add(lblDuration);
		
		
		
		textField_text = new JTextField();
		textField_text.setBounds(104, 51, 320, 20);
		frmAddPost.getContentPane().add(textField_text);
		textField_text.setColumns(10);
		
		textField_latitude = new JTextField();
		textField_latitude.setBounds(104, 76, 86, 20);
		frmAddPost.getContentPane().add(textField_latitude);
		textField_latitude.setColumns(10);
		
		JLabel lblLongitude = new JLabel("Longitude:");
		lblLongitude.setBounds(200, 79, 59, 14);
		frmAddPost.getContentPane().add(lblLongitude);
		
		textField_longitude = new JTextField();
		textField_longitude.setColumns(10);
		textField_longitude.setBounds(269, 76, 86, 20);
		frmAddPost.getContentPane().add(textField_longitude);
		
		textField_duration_width = new JTextField();
		textField_duration_width.setVisible(false);
		textField_duration_width.setBounds(104, 142, 59, 20);
		frmAddPost.getContentPane().add(textField_duration_width);
		textField_duration_width.setColumns(10);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setVisible(false);
		lblHeight.setBounds(173, 145, 46, 14);
		frmAddPost.getContentPane().add(lblHeight);
		
		textField_height = new JTextField();
		textField_height.setVisible(false);
		textField_height.setColumns(10);
		textField_height.setBounds(229, 142, 59, 20);
		frmAddPost.getContentPane().add(textField_height);
		
		textField_filename = new JTextField();
		textField_filename.setVisible(false);
		textField_filename.setBounds(104, 117, 184, 20);
		frmAddPost.getContentPane().add(textField_filename);
		textField_filename.setColumns(10);
		
		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setVisible(false);
		lblWidth.setBounds(10, 145, 84, 14);
		frmAddPost.getContentPane().add(lblWidth);
		
		JLabel lblPostType = new JLabel("Post Type");
		lblPostType.setBounds(10, 11, 84, 14);
		frmAddPost.getContentPane().add(lblPostType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = (String) comboBox.getSelectedItem();

                switch (s) {
                    case "TextPost":
                    	lblFilename.setVisible(false);textField_filename.setVisible(false);
                    	lblWidth.setVisible(false);textField_duration_width.setVisible(false);
                    	lblHeight.setVisible(false);textField_height.setVisible(false);
                		lblDuration.setVisible(false);
                        break;
                    case "ImagePost":
                    	lblFilename.setVisible(true);textField_filename.setVisible(true);
                    	lblWidth.setVisible(true);textField_duration_width.setVisible(true);
                    	lblHeight.setVisible(true);textField_height.setVisible(true);
                    	lblDuration.setVisible(false);
                        break;
                    case "VideoPost":
                    	lblFilename.setVisible(true);textField_filename.setVisible(true);
                    	lblWidth.setVisible(false);textField_duration_width.setVisible(true);
                    	lblHeight.setVisible(false);textField_height.setVisible(false);
                    	lblDuration.setVisible(true);
                        break;
                }
            }
        });
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TextPost", "ImagePost", "VideoPost"}));
		comboBox.setBounds(104, 8, 119, 20);
		frmAddPost.getContentPane().add(comboBox);
		
		JButton btnAddPost = new JButton("Add Post");
		btnAddPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_text.getText().equals("") || textField_latitude.getText().equals("") || textField_longitude.getText().equals("")){
					JOptionPane.showMessageDialog(frmAddPost,
							"Please fill all the boxes",
							"Post Create Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else{
					if(((String) comboBox.getSelectedItem()).equals("TextPost")){
						try{
							UserCollection.users.get(UserCollection.SignedInUserId).addTextPost(textField_text.getText(), Double.valueOf(textField_longitude.getText()), Double.valueOf(textField_latitude.getText()), "");
							frmAddPost.setVisible(false);
							ProfilePage profilepage = new ProfilePage();
						}
						catch(Exception e1){
							JOptionPane.showMessageDialog(frmAddPost,
									"Please fill all the boxes with correct variables",
									"Variable Error",
									JOptionPane.ERROR_MESSAGE);						
							}
						
					}
					else if(((String) comboBox.getSelectedItem()).equals("ImagePost")){
						if(textField_filename.getText().equals("") || textField_height.getText().equals("") || textField_duration_width.getText().equals("")){
							JOptionPane.showMessageDialog(frmAddPost,
									"Please fill all the boxes",
									"Post Create Error",
									JOptionPane.ERROR_MESSAGE);
						}
						else{
							try{
								UserCollection.users.get(UserCollection.SignedInUserId).addImagePost(textField_text.getText(),
											Double.valueOf(textField_longitude.getText()), Double.valueOf(textField_latitude.getText()), "",
											textField_filename.getText(),textField_duration_width.getText() + "<x>" + textField_height.getText());
								frmAddPost.setVisible(false);
								ProfilePage profilepage = new ProfilePage();
							}
							catch(Exception e1){
								JOptionPane.showMessageDialog(frmAddPost,
										"Please fill all the boxes with correct variables",
										"Variable Error",
										JOptionPane.ERROR_MESSAGE);						
								}
						}
					}
					else{
						if(textField_filename.getText().equals("") || textField_duration_width.getText().equals("")){
							JOptionPane.showMessageDialog(frmAddPost,
									"Please fill all the boxes",
									"Post Create Error",
									JOptionPane.ERROR_MESSAGE);
						}
						else{
							try{
								UserCollection.users.get(UserCollection.SignedInUserId).addVideoPost(textField_text.getText(), 
										Double.valueOf(textField_longitude.getText()), Double.valueOf(textField_latitude.getText()), "",
										textField_filename.getText(),Integer.valueOf(textField_duration_width.getText()));
								frmAddPost.setVisible(false);
								ProfilePage profilepage = new ProfilePage();
							}
							catch(Exception e1){
								JOptionPane.showMessageDialog(frmAddPost,
										"Please fill all the boxes with correct variables",
										"Variable Error",
										JOptionPane.ERROR_MESSAGE);						
								}
						}
					}
				}
				
			}
		});
		btnAddPost.setBounds(335, 7, 89, 23);
		frmAddPost.getContentPane().add(btnAddPost);
		
		
	}

}
