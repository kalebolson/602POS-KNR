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
import javax.swing.JTextPane;

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
	private JLabel lblLoggedInAs_1;
	private JLabel lblLoggedInAdmin;
	private JLabel lblNewSale;
	private JTextField UPCtextField;

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
		String username;
		String password;
		
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
		lblUsername.setBounds(205, 88, 97, 15);
		loginPanel.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(292, 86, 124, 19);
		loginPanel.add(usernameField);
		usernameField.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(205, 143, 97, 15);
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
		containerPanel.add(transactionPanel, "cashierScreen");
		transactionPanel.setLayout(null);
		
		lblNewLabel = new JLabel("Cashier Menu");
		lblNewLabel.setBounds(302, 12, 111, 15);
		transactionPanel.add(lblNewLabel);
		
		JButton btnNewSale = new JButton("New Sale");
		btnNewSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(containerPanel, "newSaleScreen");
			}
		});
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
		btnLogout.addActionListener(new ActionListener() {
			
			/*
			 * Switches back to login screen when selecting the logout button
			 * and clears username and password fields
			 */
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(containerPanel, "loginScreen");
				usernameField.setText("");
				passwordField.setText("");
			}
		});
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
		
		lblLoggedInAs_1 = new JLabel("Logged in as:");
		lblLoggedInAs_1.setBounds(179, 55, 112, 15);
		managerPanel.add(lblLoggedInAs_1);
		
		lblLoggedInAdmin = new JLabel("Default");
		lblLoggedInAdmin.setBounds(288, 55, 100, 15);
		managerPanel.add(lblLoggedInAdmin);
		
		JPanel newSalePanel = new JPanel();
		containerPanel.add(newSalePanel, "newSaleScreen");
		newSalePanel.setLayout(null);
		
		lblNewSale = new JLabel("New Sale");
		lblNewSale.setBounds(318, 12, 103, 15);
		newSalePanel.add(lblNewSale);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(467, 54, 176, 237);
		newSalePanel.add(textPane);
		
		JLabel lblTransactionDetails = new JLabel("Transaction Details");
		lblTransactionDetails.setBounds(487, 27, 168, 15);
		newSalePanel.add(lblTransactionDetails);
		
		JButton btnCalculateTotal = new JButton("Calculate Total");
		btnCalculateTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCalculateTotal.setBounds(487, 303, 146, 25);
		newSalePanel.add(btnCalculateTotal);
		
		JButton btnFinalizeSale = new JButton("Finalize Sale");
		btnFinalizeSale.setBounds(487, 340, 145, 25);
		newSalePanel.add(btnFinalizeSale);
		
		JLabel lblItemUpcList = new JLabel("Item UPC List");
		lblItemUpcList.setBounds(98, 27, 122, 15);
		newSalePanel.add(lblItemUpcList);
		
		JLabel lblEnterUpc = new JLabel("Enter UPC");
		lblEnterUpc.setBounds(318, 104, 103, 15);
		newSalePanel.add(lblEnterUpc);
		
		UPCtextField = new JTextField();
		UPCtextField.setBounds(288, 131, 133, 19);
		newSalePanel.add(UPCtextField);
		UPCtextField.setColumns(10);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setBounds(288, 162, 133, 25);
		newSalePanel.add(btnAddItem);
		
		JButton btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRemoveItem.setBounds(288, 199, 133, 25);
		newSalePanel.add(btnRemoveItem);
		
		JButton btnCancelSale_1 = new JButton("Cancel Sale");
		btnCancelSale_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(containerPanel, "cashierScreen");
			}
		});
		btnCancelSale_1.setBounds(288, 340, 133, 25);
		newSalePanel.add(btnCancelSale_1);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				try {
					if (Integer.parseInt(username) == store.getCashier(0).getID() && password.equals("masterpassword")) {
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "managerScreen");
						lblLoggedInAdmin.setText(store.getCashier(0).getFirstName() + " " + store.getCashier(0).getLastName());
						store.getRegister(1).unlock(Integer.parseInt(username), password);
					}
					else if (Integer.parseInt(username) == store.getCashier(1).getID() && password.equals("asdf")) {
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "cashierScreen");
						lblLoggedInUser.setText(store.getCashier(1).getFirstName() + " " + store.getCashier(1).getLastName());
						store.getRegister(1).unlock(Integer.parseInt(username), password);
					}
					else if (Integer.parseInt(username) == store.getCashier(2).getID() && password.equals("1234")){
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "cashierScreen");
						lblLoggedInUser.setText(store.getCashier(2).getFirstName() + " " + store.getCashier(2).getLastName());
						store.getRegister(1).unlock(Integer.parseInt(username), password);
					}
					else if (Integer.parseInt(username) == store.getCashier(3).getID() && password.equals("01Q3EE@")){
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "cashierScreen");
						lblLoggedInUser.setText(store.getCashier(3).getFirstName() + " " + store.getCashier(3).getLastName());
						store.getRegister(1).unlock(Integer.parseInt(username), password);
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
