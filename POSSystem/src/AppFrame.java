import java.awt.EventQueue;
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

	private JFrame frmPosSystem;
	private JTextField usernameField;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrame window = new AppFrame();
					window.frmPosSystem.setVisible(true);
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
		frmPosSystem = new JFrame();
		frmPosSystem.setResizable(false);
		frmPosSystem.setSize(new Dimension(520, 260));
		frmPosSystem.setTitle("POS System 1.0");
		frmPosSystem.getContentPane().setLayout(null);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(180, 30, 69, 15);
		frmPosSystem.getContentPane().add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(180, 50, 155, 19);
		frmPosSystem.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		// displays associated dialogue message if desired username and password match expected credentials
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(221, 158, 72, 25);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				if (username.equals("rmartinez") && password.equals("poop")) {
					JOptionPane.showMessageDialog(frmPosSystem, "Login Successful");
				}
				else {
					JOptionPane.showMessageDialog(frmPosSystem, "Invalid Username or Password");
				}
			}
		});
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(180, 74, 65, 15);
		frmPosSystem.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(180, 94, 155, 19);
		frmPosSystem.getContentPane().add(passwordField);
		frmPosSystem.getContentPane().add(btnLogin);
	}
}
