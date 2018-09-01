package armyBase.sd.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import armyBase.sd.request.Encryption;
import armyBase.sd.request.UserRequest;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private JLabel lblUsername;
	private JButton btnLogin;
    private JLabel lblPassword;
    private JButton btnRegister;
	


	/**
	 * Create the frame.
	 */
	public Login() {
		super("Login");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		username = new JTextField();
		username.setBounds(114, 58, 164, 26);
		contentPane.add(username);
		
		password = new JPasswordField();
		password.setBounds(114, 100, 164, 26);
		contentPane.add(password);
		
		lblUsername = new JLabel("email");
		lblUsername.setBounds(15, 58, 69, 20);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("password");
		lblPassword.setBounds(15, 100, 69, 20);
		contentPane.add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(107, 185, 90, 28);
		btnLogin.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  String email = username.getText();
			  	  String passwd = password.getText();
				  UserRequest request = new UserRequest();
				  if (request.login(email, Encryption.encryptPassword(passwd)))
				  {
					  View view = new View(email);
		  			  view.setVisible(true);
				  }
				  else
					  JOptionPane.showMessageDialog(null, "Bad credentials");
		  			  
			  } 
			} );
		contentPane.add(btnLogin);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				 
					  SoldierRegisterView view = new SoldierRegisterView();
		  			  view.setVisible(true);	  
			  } 
			} );
		btnRegister.setBounds(105, 235, 115, 29);
		contentPane.add(btnRegister);
}

}