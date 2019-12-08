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
import javax.swing.JTable;
import java.awt.Font;

public class AppFrame {
	
	private JFrame frmPosSystemLogin;
	private JTextField usernameField;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField passwordField;
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
		try {
			initialize();
		} catch (InvalidIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws InvalidIDException 
	 */
	private void initialize() throws InvalidIDException {
		final Store store = new Store();
		final Register register = store.getRegister(1);
		
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
		
		JPanel cashierPanel = new JPanel();
		containerPanel.add(cashierPanel, "cashierScreen");
		cashierPanel.setLayout(null);
		
		lblNewLabel = new JLabel("Cashier Menu");
		lblNewLabel.setBounds(302, 12, 111, 15);
		cashierPanel.add(lblNewLabel);
		
		JButton btnNewSale = new JButton("New Sale");
		btnNewSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(containerPanel, "newSaleScreen");
				register.newSale();
			}
		});
		btnNewSale.setBounds(285, 94, 128, 25);
		cashierPanel.add(btnNewSale);
		
		JButton btnReturnItem = new JButton("Return Item");
		btnReturnItem.setBounds(285, 168, 128, 25);
		cashierPanel.add(btnReturnItem);
		
		JLabel lblLoggedInAs = new JLabel("Logged in as:");
		lblLoggedInAs.setBounds(195, 54, 101, 15);
		cashierPanel.add(lblLoggedInAs);
		
		JButton btnCancelSale = new JButton("Cancel Sale");
		btnCancelSale.setBounds(285, 131, 128, 25);
		cashierPanel.add(btnCancelSale);
		
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
		cashierPanel.add(btnLogout);
		
		JLabel lblLoggedInUser = new JLabel("Default");
		lblLoggedInUser.setBounds(302, 54, 111, 15);
		cashierPanel.add(lblLoggedInUser);
		
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
		lblItemUpcList.setBounds(71, 27, 122, 15);
		newSalePanel.add(lblItemUpcList);
		
		JLabel lblEnterUpc = new JLabel("Enter UPC");
		lblEnterUpc.setBounds(318, 104, 103, 15);
		newSalePanel.add(lblEnterUpc);
		
		UPCtextField = new JTextField();
		UPCtextField.setBounds(288, 131, 133, 19);
		newSalePanel.add(UPCtextField);
		UPCtextField.setColumns(10);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int UPCInput = Integer.parseInt(UPCtextField.getText());
				try {
					register.addToSale(UPCInput);
				} catch (InvalidIDException e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Invalid UPC");
					e.printStackTrace();
				}
				UPCtextField.setText("");
			}
		});
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
		
		JLabel lblNewLabel_1 = new JLabel("1 - Apples");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(24, 80, 129, 15);
		newSalePanel.add(lblNewLabel_1);
		
		JLabel lblBananas = new JLabel("2 - Bananas");
		lblBananas.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBananas.setBounds(24, 104, 129, 15);
		newSalePanel.add(lblBananas);
		
		JLabel lblChocolate = new JLabel("3 - Chocolate");
		lblChocolate.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblChocolate.setBounds(24, 133, 129, 15);
		newSalePanel.add(lblChocolate);
		
		JLabel lblChicken = new JLabel("4 - Chicken");
		lblChicken.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblChicken.setBounds(24, 157, 129, 15);
		newSalePanel.add(lblChicken);
		
		JLabel lblAlmond = new JLabel("8 - Almond Butter");
		lblAlmond.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAlmond.setBounds(24, 261, 129, 15);
		newSalePanel.add(lblAlmond);
		
		JLabel lblPeanut = new JLabel("7 - Peanut Butter");
		lblPeanut.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPeanut.setBounds(24, 234, 129, 15);
		newSalePanel.add(lblPeanut);
		
		JLabel lblEggs = new JLabel("6 - Eggs");
		lblEggs.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblEggs.setBounds(24, 209, 129, 15);
		newSalePanel.add(lblEggs);
		
		JLabel lblSalmon = new JLabel("5 - Salmon");
		lblSalmon.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSalmon.setBounds(24, 184, 129, 15);
		newSalePanel.add(lblSalmon);
		
		JLabel lblBread = new JLabel("9 - Bread");
		lblBread.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBread.setBounds(24, 288, 129, 15);
		newSalePanel.add(lblBread);
		
		JLabel lblPizza = new JLabel("10 - Pizza");
		lblPizza.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPizza.setBounds(24, 315, 129, 15);
		newSalePanel.add(lblPizza);
		
		JLabel lblPizza_1 = new JLabel("20 - Milk");
		lblPizza_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPizza_1.setBounds(141, 315, 129, 15);
		newSalePanel.add(lblPizza_1);
		
		JLabel lblBread_1 = new JLabel("19 - Pork");
		lblBread_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBread_1.setBounds(141, 288, 129, 15);
		newSalePanel.add(lblBread_1);
		
		JLabel lblAlmond_1 = new JLabel("18 - Bacon");
		lblAlmond_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAlmond_1.setBounds(141, 261, 129, 15);
		newSalePanel.add(lblAlmond_1);
		
		JLabel lblBacon = new JLabel("17 - Broccoli");
		lblBacon.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBacon.setBounds(141, 234, 129, 15);
		newSalePanel.add(lblBacon);
		
		JLabel lblRum = new JLabel("16 - Rum");
		lblRum.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRum.setBounds(141, 209, 129, 15);
		newSalePanel.add(lblRum);
		
		JLabel lblWine = new JLabel("15 - Wine");
		lblWine.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblWine.setBounds(141, 184, 129, 15);
		newSalePanel.add(lblWine);
		
		JLabel lblChicken_1 = new JLabel("14 - Beer");
		lblChicken_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblChicken_1.setBounds(141, 157, 129, 15);
		newSalePanel.add(lblChicken_1);
		
		JLabel lblJuice = new JLabel("13 - Juice");
		lblJuice.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblJuice.setBounds(141, 133, 129, 15);
		newSalePanel.add(lblJuice);
		
		JLabel lblOnions = new JLabel("12 - Onions");
		lblOnions.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblOnions.setBounds(141, 104, 129, 15);
		newSalePanel.add(lblOnions);
		
		JLabel lblOranges = new JLabel("11 - Oranges");
		lblOranges.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblOranges.setBounds(141, 80, 129, 15);
		newSalePanel.add(lblOranges);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				try {
					if (Integer.parseInt(username) == store.getCashier(0).getID() && password.equals("masterpassword")) {
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "managerScreen");
						lblLoggedInAdmin.setText(store.getCashier(0).getFirstName() + " " + store.getCashier(0).getLastName());
						register.unlock(Integer.parseInt(username), password);
					}
					else if (Integer.parseInt(username) == store.getCashier(1).getID() && password.equals("asdf")) {
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "cashierScreen");
						lblLoggedInUser.setText(store.getCashier(1).getFirstName() + " " + store.getCashier(1).getLastName());
						register.unlock(Integer.parseInt(username), password);
					}
					else if (Integer.parseInt(username) == store.getCashier(2).getID() && password.equals("1234")){
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "cashierScreen");
						lblLoggedInUser.setText(store.getCashier(2).getFirstName() + " " + store.getCashier(2).getLastName());
						register.unlock(Integer.parseInt(username), password);
					}
					else if (Integer.parseInt(username) == store.getCashier(3).getID() && password.equals("01Q3EE@")){
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "cashierScreen");
						lblLoggedInUser.setText(store.getCashier(3).getFirstName() + " " + store.getCashier(3).getLastName());
						register.unlock(Integer.parseInt(username), password);
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
