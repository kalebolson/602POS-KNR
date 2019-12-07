yimport java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Dimension;

public class AppFrame {

	//(nb)- we are going to need multiple jframes.. updating the name to be more descriptive :) 
	private JFrame frmPosSystemLogin;
	private JTextField usernameField;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	
	private JFrame frmPosSystemMainMenu; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrame window = new AppFrame();
					window.frmPosSystemLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmPosSystemLogin = new JFrame();
		frmPosSystemLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPosSystemLogin.setResizable(false);
		frmPosSystemLogin.setSize(new Dimension(520, 260));
		frmPosSystemLogin.setTitle("Awesome POS System 1.0");
		frmPosSystemLogin.getContentPane().setLayout(null);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(180, 30, 69, 15);
		frmPosSystemLogin.getContentPane().add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(180, 50, 155, 19);
		frmPosSystemLogin.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		// displays associated dialogue message if desired username and password match expected credentials
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(221, 158, 72, 25);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				//LOL 
				if (username.equals("rmartinez") && password.equals("poop")) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
				}
				else if (username.equals("speakfriendandenter") && password.equals("mellon")) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "The Mines of Moria have opened!!!");
				}
				else {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Invalid Username or Password");
				}
			}
		});
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(180, 74, 65, 15);
		frmPosSystemLogin.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(180, 94, 155, 19);
		frmPosSystemLogin.getContentPane().add(passwordField);
		frmPosSystemLogin.getContentPane().add(btnLogin);
	}
}
