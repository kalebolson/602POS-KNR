import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class AppFrame {

	//(nb)- we are going to need multiple jframes.. updating the name to be more descriptive :) 
	private JFrame frmPosSystemLogin;
	private JTextField usernameField;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	
	private JFrame frmPosSystemMainMenuCashier; 
	private JFrame frmPosSystemManagerFunction;
	private JPanel loginPanel;
	private JLabel lblNewLabel;
	private JPanel containerPanel;
	private CardLayout cardLayout;
	private JPanel managerPanel;
	private JLabel lblAdministratorMenu;

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
		Store store = new Store();
		
		cardLayout = new CardLayout();

		frmPosSystemLogin = new JFrame();
		frmPosSystemLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPosSystemLogin.setResizable(false);
		frmPosSystemLogin.setSize(new Dimension(727, 420));
		frmPosSystemLogin.setTitle("POS System 1.0");
		frmPosSystemLogin.getContentPane().setLayout(new BorderLayout(0, 0));
		
		containerPanel = new JPanel();
		frmPosSystemLogin.getContentPane().add(containerPanel);
		containerPanel.setLayout(cardLayout);
		
		loginPanel = new JPanel();
		containerPanel.add(loginPanel, "loginScreen");
		loginPanel.setLayout(null);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(205, 88, 69, 15);
		loginPanel.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(292, 86, 124, 19);
		loginPanel.add(usernameField);
		usernameField.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(205, 143, 65, 15);
		loginPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(292, 141, 124, 19);
		loginPanel.add(passwordField);
		
		// displays associated dialogue message if desired username and password match expected credentials
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(316, 198, 72, 25);
		loginPanel.add(btnLogin);
		
		JLabel lblSystemLogin = new JLabel("System Login");
		lblSystemLogin.setBounds(305, 12, 111, 15);
		loginPanel.add(lblSystemLogin);
		
		JPanel transactionPanel = new JPanel();
		containerPanel.add(transactionPanel, "transactionScreen");
		transactionPanel.setLayout(null);
		
		lblNewLabel = new JLabel("Cashier Menu");
		lblNewLabel.setBounds(302, 12, 111, 15);
		transactionPanel.add(lblNewLabel);
		
		JButton btnNewSale = new JButton("New Sale");
		btnNewSale.setBounds(285, 94, 128, 25);
		transactionPanel.add(btnNewSale);
		
		JButton btnReturnItem = new JButton("Return Item");
		btnReturnItem.setBounds(285, 168, 128, 25);
		transactionPanel.add(btnReturnItem);
		
		JLabel lblLoggedInAs = new JLabel("Logged in as:");
		lblLoggedInAs.setBounds(195, 54, 101, 15);
		transactionPanel.add(lblLoggedInAs);
		
		JButton btnCancelSale = new JButton("Cancel Sale");
		btnCancelSale.setBounds(285, 131, 128, 25);
		transactionPanel.add(btnCancelSale);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(282, 234, 131, 25);
		transactionPanel.add(btnLogout);
		
		JLabel lblLoggedInUser = new JLabel("Default");
		lblLoggedInUser.setBounds(302, 54, 111, 15);
		transactionPanel.add(lblLoggedInUser);
		
		managerPanel = new JPanel();
		containerPanel.add(managerPanel, "managerScreen");
		managerPanel.setLayout(null);
		
		lblAdministratorMenu = new JLabel("Administrator Menu");
		lblAdministratorMenu.setBounds(278, 28, 137, 15);
		managerPanel.add(lblAdministratorMenu);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				try {
					if (Integer.parseInt(username) == store.getCashier(0).getID() && password.equals("masterpassword")) {
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "managerScreen");
						store.getRegister(1).unlock(0, "masterpassword");
					}
					else if (Integer.parseInt(username) == store.getCashier(1).getID() && password.equals("asdf")) {
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "transactionScreen");
						store.getRegister(1).unlock(1, "asdf");
						lblLoggedInUser.setText(store.getCashier(1).getFirstName() + " " + store.getCashier(1).getLastName());
					}
					else if (Integer.parseInt(username) == store.getCashier(2).getID() && password.equals("1234")){
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "transactionScreen");
						store.getRegister(1).unlock(2, "1234");
						lblLoggedInUser.setText(store.getCashier(2).getFirstName() + " " + store.getCashier(2).getLastName());
					}
					else if (Integer.parseInt(username) == store.getCashier(3).getID() && password.equals("01Q3EE@")){
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "transactionScreen");
						store.getRegister(1).unlock(3, "01Q3EE@");
						lblLoggedInUser.setText(store.getCashier(3).getFirstName() + " " + store.getCashier(3).getLastName());
					}
					else {
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Invalid Username or Password");
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidIDException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
