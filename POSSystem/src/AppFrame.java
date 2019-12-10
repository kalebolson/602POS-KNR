import java.awt.EventQueue;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

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
	private JPanel inventoryMgmtPanel;
	private JLabel lblAdministratorMenu;
	private JLabel lblInvMgmtMenu;
	private JLabel lblLoggedInAs_1;
	private JLabel lblLoggedInAdmin;
	private JLabel lblNewSale;
	private JTextField UPCtextField;
	private JTextArea txtrReceipt;
	private JTextField transactionIDCSField;
	private JTextField transactionIDRIField;
	private JTextField upcRIField;
	private JTextArea txtrInvReport;
	private JTextArea txtrInvNotifications;
	private JTextArea txtrReporting;
	private JTextField UPCinvTextField;
	private JTextField setThresholdField;
	private JTextField setPriceField;

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
				txtrReceipt.setText(register.calculateSale());
			}
		});
		btnNewSale.setBounds(285, 94, 128, 25);
		cashierPanel.add(btnNewSale);
		
		JButton btnReturnItem = new JButton("Return Item");
		btnReturnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(containerPanel, "returnItemScreen");
			}
		});
		btnReturnItem.setBounds(285, 168, 128, 25);
		cashierPanel.add(btnReturnItem);
		
		JLabel lblLoggedInAs = new JLabel("Logged in as:");
		lblLoggedInAs.setBounds(509, 0, 101, 15);
		cashierPanel.add(lblLoggedInAs);
		
		JButton btnCancelSale = new JButton("Cancel Sale");
		btnCancelSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(containerPanel, "cancelSaleScreen");
			}
		});
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
		lblLoggedInUser.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblLoggedInUser.setBounds(616, 0, 111, 15);
		cashierPanel.add(lblLoggedInUser);
		
		  //****************************************************************************************** 
		  //Manager UI 
		  //****************************************************************************************** 
		
		
		//*********************Create managerScreen*******************************
		managerPanel = new JPanel();
		containerPanel.add(managerPanel, "managerScreen");
		managerPanel.setLayout(null);
		
		lblAdministratorMenu = new JLabel("Administrator Menu");
		lblAdministratorMenu.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAdministratorMenu.setBounds(265, 28, 201, 15);
		managerPanel.add(lblAdministratorMenu);
		
		lblLoggedInAs_1 = new JLabel("Logged in as:");
		lblLoggedInAs_1.setBounds(506, 0, 112, 15);
		managerPanel.add(lblLoggedInAs_1);
		
		lblLoggedInAdmin = new JLabel("Default Admin");
		lblLoggedInAdmin.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblLoggedInAdmin.setBounds(615, 0, 100, 15);
		managerPanel.add(lblLoggedInAdmin);
		
		JButton btnStoreFunctions = new JButton("Store Functions");
		btnStoreFunctions.setBounds(487, 240, 200, 25);
		btnStoreFunctions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//register.finalizeSale();
				cardLayout.show(containerPanel, "inventoryMgmtScreen");
				
			}
		});
		managerPanel.add(btnStoreFunctions);
		
		JButton btnOrderMgmt = new JButton("Ordering and Receiving");
		btnOrderMgmt.setBounds(487, 275, 200, 25);
		btnOrderMgmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//register.finalizeSale();
				cardLayout.show(containerPanel, "inventoryMgmtScreen");
				
			}
		});
		managerPanel.add(btnOrderMgmt);
		
		JButton btnInventoryMgmt = new JButton("Inventory Management");
		btnInventoryMgmt.setBounds(487, 310, 200, 25);
		btnInventoryMgmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//register.finalizeSale();
				cardLayout.show(containerPanel, "inventoryMgmtScreen");
				
			}
		});
		managerPanel.add(btnInventoryMgmt);
		
		JButton btnAdminLogout = new JButton("Logout");
		btnAdminLogout.setBounds(487, 345, 200, 25);
		btnAdminLogout.addActionListener(new ActionListener() {
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
		managerPanel.add(btnAdminLogout);
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(24, 80, 440, 223);
		managerPanel.add(scrollPane3);
		
		txtrReporting = new JTextArea();
		txtrReporting.setEditable(false);
		scrollPane3.setViewportView(txtrReporting);
		
		JLabel lblManagerNotifications = new JLabel("Notifications");
		lblManagerNotifications.setBounds(487, 52, 122, 15);
		managerPanel.add(lblManagerNotifications);
		
		JScrollPane scrollPane4 = new JScrollPane();
		scrollPane4.setBounds(487, 80, 200, 150);
		managerPanel.add(scrollPane4);
		
		txtrInvNotifications = new JTextArea();
		txtrInvNotifications.setEditable(false);
		scrollPane4.setViewportView(txtrInvNotifications);
		

		//*********************END Create managerScreen****************************
		
		
		//*********************Create inventoryMgmtScreen**************************
		inventoryMgmtPanel = new JPanel();
		containerPanel.add(inventoryMgmtPanel, "inventoryMgmtScreen");
		inventoryMgmtPanel.setLayout(null);
		
		lblInvMgmtMenu = new JLabel("Inventory Management Menu");
		lblInvMgmtMenu.setFont(new Font("Dialog", Font.BOLD, 14));
		lblInvMgmtMenu.setBounds(250, 28, 250, 18);
		inventoryMgmtPanel.add(lblInvMgmtMenu);
		
		lblLoggedInAs_1 = new JLabel("Logged in as:");
		lblLoggedInAs_1.setBounds(506, 0, 112, 15);
		inventoryMgmtPanel.add(lblLoggedInAs_1);
		
		lblLoggedInAdmin = new JLabel("Default");
		lblLoggedInAdmin.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblLoggedInAdmin.setBounds(615, 0, 100, 15);
		inventoryMgmtPanel.add(lblLoggedInAdmin); 
		
		JLabel lblItemUpcList2 = new JLabel("Item UPC List");
		lblItemUpcList2.setBounds(71, 52, 122, 15);
		inventoryMgmtPanel.add(lblItemUpcList2);
		
		JLabel lblEnterUpc2 = new JLabel("Enter UPC");
		lblEnterUpc2.setBounds(260, 80, 75, 15);
		inventoryMgmtPanel.add(lblEnterUpc2);
		
		UPCinvTextField = new JTextField();
		UPCinvTextField.setBounds(360, 80, 60, 19);
		UPCinvTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int UPC = Integer.parseInt(UPCinvTextField.getText());
				try {
					txtrInvReport.setText(register.productReport(UPC) + "\n text field report");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Invalid UPC");
				}
			}
		});
		UPCinvTextField.setColumns(10);
		inventoryMgmtPanel.add(UPCinvTextField);
		
		JLabel lblThreshold = new JLabel("Enter Threshold");
		lblThreshold.setBounds(260, 133, 100, 15);
		inventoryMgmtPanel.add(lblThreshold);
		
		setThresholdField = new JTextField();
		setThresholdField.setBounds(360, 133, 60, 19);
		inventoryMgmtPanel.add(setThresholdField);
		setThresholdField.setColumns(10);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(487, 80, 200, 150);
		inventoryMgmtPanel.add(scrollPane2);
		
		txtrInvReport = new JTextArea();
		txtrInvReport.setEditable(false);
		scrollPane2.setViewportView(txtrInvReport);
		
		JButton btnSetThreshold = new JButton("Set Threshold");
		btnSetThreshold.setBounds(260, 157, 160, 25);
		btnSetThreshold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int UPC = Integer.parseInt(UPCinvTextField.getText());
				int Threshold = Integer.parseInt(setThresholdField.getText());
				try {
					register.setThreshold(UPC, Threshold);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Invalid Threshold");
				}
				txtrInvReport.setText(register.productReport(UPC) + "\n done");
				UPCinvTextField.setText("");
			}
		});
		inventoryMgmtPanel.add(btnSetThreshold);
		
		JLabel lblPrice = new JLabel("Enter Price");
		lblPrice.setBounds(260, 209, 100, 15);
		inventoryMgmtPanel.add(lblPrice);
		
		setPriceField = new JTextField();
		setPriceField.setBounds(360, 209, 60, 19);
		inventoryMgmtPanel.add(setPriceField);
		setPriceField.setColumns(10);

		JButton btnSetPrice = new JButton("Set Price");
		btnSetPrice.setBounds(260, 234, 160, 25);
		btnSetPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int UPC = Integer.parseInt(UPCinvTextField.getText());
				int Threshold = Integer.parseInt(setPriceField.getText());
				try {
					register.setThreshold(UPC, Threshold);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Invalid Threshold");
				}
				txtrInvReport.setText(register.productReport(UPC) + "\n done");
				//UPCinvTextField.setText("");
			}
		});
		inventoryMgmtPanel.add(btnSetPrice);
		
		JLabel lblApples2 = new JLabel("1 - Apples");
		lblApples2.setBounds(24, 80, 129, 15);
		lblApples2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblApples2);
		
		JLabel lblBananas2 = new JLabel("2 - Bananas");
		lblBananas2.setBounds(24, 104, 129, 15);
		lblBananas2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblBananas2);
		
		JLabel lblChocolate2 = new JLabel("3 - Chocolate");
		lblChocolate2.setBounds(24, 133, 129, 15);
		lblChocolate2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblChocolate2);
		
		JLabel lblChicken2 = new JLabel("4 - Chicken");
		lblChicken2.setBounds(24, 157, 129, 15);
		lblChicken2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblChicken2);
		
		JLabel lblAlmond2 = new JLabel("8 - Almond Butter");
		lblAlmond2.setBounds(24, 261, 129, 15);
		lblAlmond2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblAlmond2);
		
		JLabel lblPeanut2 = new JLabel("7 - Peanut Butter");
		lblPeanut2.setBounds(24, 234, 129, 15);
		lblPeanut2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblPeanut2);
		
		JLabel lblEggs2 = new JLabel("6 - Eggs");
		lblEggs2.setBounds(24, 209, 129, 15);
		lblEggs2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblEggs2);
		
		JLabel lblSalmon2 = new JLabel("5 - Salmon");
		lblSalmon2.setBounds(24, 184, 129, 15);
		lblSalmon2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblSalmon2);
		
		JLabel lblBread2 = new JLabel("9 - Bread");
		lblBread2.setBounds(24, 288, 129, 15);
		lblBread2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblBread2);
		
		JLabel lblPizza2 = new JLabel("10 - Pizza");
		lblPizza2.setBounds(24, 315, 129, 15);
		lblPizza2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblPizza2);
		
		JLabel lblMilk2 = new JLabel("20 - Milk");
		lblMilk2.setBounds(141, 315, 129, 15);
		lblMilk2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblMilk2);
		
		JLabel lblPork2 = new JLabel("19 - Pork");
		lblPork2.setBounds(141, 288, 129, 15);
		lblPork2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblPork2);
		
		JLabel lblBacon2 = new JLabel("18 - Bacon");
		lblBacon2.setBounds(141, 261, 129, 15);
		lblBacon2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblBacon2);
		
		JLabel lblBroccoli2 = new JLabel("17 - Broccoli");
		lblBroccoli2.setBounds(141, 234, 129, 15);
		lblBroccoli2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblBroccoli2);
		
		JLabel lblRum2 = new JLabel("16 - Rum");
		lblRum2.setBounds(141, 209, 129, 15);
		lblRum2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblRum2);
		
		JLabel lblWine2 = new JLabel("15 - Wine");
		lblWine2.setBounds(141, 184, 129, 15);
		lblWine2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblWine2);
		
		JLabel lblBeer2 = new JLabel("14 - Beer");
		lblBeer2.setBounds(141, 157, 129, 15);
		lblBeer2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblBeer2);
		
		JLabel lblJuice2 = new JLabel("13 - Juice");
		lblJuice2.setBounds(141, 133, 129, 15);
		lblJuice2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblJuice2);
		
		JLabel lblOnions2 = new JLabel("12 - Onions");
		lblOnions2.setBounds(141, 104, 129, 15);
		lblOnions2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblOnions2);
		
		JLabel lblOranges2 = new JLabel("11 - Oranges");
		lblOranges2.setBounds(141, 80, 129, 15);
		lblOranges2.setFont(new Font("Dialog", Font.PLAIN, 12));
		inventoryMgmtPanel.add(lblOranges2);
		
		JButton btnAdminLogout2 = new JButton("Logout");
		btnAdminLogout2.setBounds(487, 345, 200, 25);
		btnAdminLogout2.addActionListener(new ActionListener() {
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
		inventoryMgmtPanel.add(btnAdminLogout2);
		
		JButton btnOrderMgmt2 = new JButton("Ordering and Receiving");
		btnOrderMgmt2.setBounds(487, 275, 200, 25);
		btnOrderMgmt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//register.finalizeSale();
				cardLayout.show(containerPanel, "inventoryMgmtScreen");
				
			}
		});
		inventoryMgmtPanel.add(btnOrderMgmt2);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(487, 310, 200, 25);
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//register.finalizeSale();
				cardLayout.show(containerPanel, "managerScreen");
				
			}
		});
		inventoryMgmtPanel.add(btnMainMenu);
		
		//*********************END Create inventoryMgmtScreen**********************
		
		  //****************************************************************************************** 
		  //END Manager UI 
		  //****************************************************************************************** 
		
		JPanel newSalePanel = new JPanel();
		containerPanel.add(newSalePanel, "newSaleScreen");
		newSalePanel.setLayout(null);
		
		lblNewSale = new JLabel("New Sale");
		lblNewSale.setBounds(318, 12, 103, 15);
		lblNewSale.setFont(new Font("Dialog", Font.BOLD, 14));
		newSalePanel.add(lblNewSale);
		
		JLabel lblTransactionDetails = new JLabel("Transaction Details");
		lblTransactionDetails.setBounds(496, 27, 168, 15);
		newSalePanel.add(lblTransactionDetails);
		
		JButton btnFinalizeSale = new JButton("Finalize Sale");
		btnFinalizeSale.setBounds(487, 310, 145, 25);
		btnFinalizeSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				register.finalizeSale();
				cardLayout.show(containerPanel, "cashierScreen");
			}
		});
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
		btnAddItem.setBounds(288, 162, 133, 25);
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int UPC = Integer.parseInt(UPCtextField.getText());
				try {
					register.addToSale(UPC);
				} catch (InvalidIDException e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Invalid UPC");
				}
				txtrReceipt.setText(register.calculateSale());
				UPCtextField.setText("");
			}
		});
		newSalePanel.add(btnAddItem);
		
		JButton btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setBounds(288, 199, 133, 25);
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int UPC = Integer.parseInt(UPCtextField.getText());
				try {
					register.removeFromSale(UPC);
				} catch (InvalidIDException e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Invalid UPC");
				}
				txtrReceipt.setText(register.calculateSale());
				UPCtextField.setText("");
			}
		});
		newSalePanel.add(btnRemoveItem);
		
		JButton btnCancelSale_1 = new JButton("Cancel Sale");
		btnCancelSale_1.setBounds(288, 310, 133, 25);
		btnCancelSale_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(containerPanel, "cashierScreen");
			}
		});
		newSalePanel.add(btnCancelSale_1);
		
		JLabel lblNewLabel_1 = new JLabel("1 - Apples");
		lblNewLabel_1.setBounds(24, 80, 129, 15);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblNewLabel_1);
		
		JLabel lblBananas = new JLabel("2 - Bananas");
		lblBananas.setBounds(24, 104, 129, 15);
		lblBananas.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblBananas);
		
		JLabel lblChocolate = new JLabel("3 - Chocolate");
		lblChocolate.setBounds(24, 133, 129, 15);
		lblChocolate.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblChocolate);
		
		JLabel lblChicken = new JLabel("4 - Chicken");
		lblChicken.setBounds(24, 157, 129, 15);
		lblChicken.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblChicken);
		
		JLabel lblAlmond = new JLabel("8 - Almond Butter");
		lblAlmond.setBounds(24, 261, 129, 15);
		lblAlmond.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblAlmond);
		
		JLabel lblPeanut = new JLabel("7 - Peanut Butter");
		lblPeanut.setBounds(24, 234, 129, 15);
		lblPeanut.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblPeanut);
		
		JLabel lblEggs = new JLabel("6 - Eggs");
		lblEggs.setBounds(24, 209, 129, 15);
		lblEggs.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblEggs);
		
		JLabel lblSalmon = new JLabel("5 - Salmon");
		lblSalmon.setBounds(24, 184, 129, 15);
		lblSalmon.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblSalmon);
		
		JLabel lblBread = new JLabel("9 - Bread");
		lblBread.setBounds(24, 288, 129, 15);
		lblBread.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblBread);
		
		JLabel lblPizza = new JLabel("10 - Pizza");
		lblPizza.setBounds(24, 315, 129, 15);
		lblPizza.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblPizza);
		
		JLabel lblPizza_1 = new JLabel("20 - Milk");
		lblPizza_1.setBounds(141, 315, 129, 15);
		lblPizza_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblPizza_1);
		
		JLabel lblBread_1 = new JLabel("19 - Pork");
		lblBread_1.setBounds(141, 288, 129, 15);
		lblBread_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblBread_1);
		
		JLabel lblAlmond_1 = new JLabel("18 - Bacon");
		lblAlmond_1.setBounds(141, 261, 129, 15);
		lblAlmond_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblAlmond_1);
		
		JLabel lblBacon = new JLabel("17 - Broccoli");
		lblBacon.setBounds(141, 234, 129, 15);
		lblBacon.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblBacon);
		
		JLabel lblRum = new JLabel("16 - Rum");
		lblRum.setBounds(141, 209, 129, 15);
		lblRum.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblRum);
		
		JLabel lblWine = new JLabel("15 - Wine");
		lblWine.setBounds(141, 184, 129, 15);
		lblWine.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblWine);
		
		JLabel lblChicken_1 = new JLabel("14 - Beer");
		lblChicken_1.setBounds(141, 157, 129, 15);
		lblChicken_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblChicken_1);
		
		JLabel lblJuice = new JLabel("13 - Juice");
		lblJuice.setBounds(141, 133, 129, 15);
		lblJuice.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblJuice);
		
		JLabel lblOnions = new JLabel("12 - Onions");
		lblOnions.setBounds(141, 104, 129, 15);
		lblOnions.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblOnions);
		
		JLabel lblOranges = new JLabel("11 - Oranges");
		lblOranges.setBounds(141, 80, 129, 15);
		lblOranges.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblOranges);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(468, 54, 208, 223);
		newSalePanel.add(scrollPane);
		
		txtrReceipt = new JTextArea();
		txtrReceipt.setEditable(false);
		scrollPane.setViewportView(txtrReceipt);
		
		JLabel label_1 = new JLabel("Logged in as:");
		label_1.setBounds(497, 0, 101, 15);
		newSalePanel.add(label_1);
		
		JLabel lblLoggedInUserNS = new JLabel("Default");
		lblLoggedInUserNS.setBounds(604, 0, 111, 15);
		lblLoggedInUserNS.setFont(new Font("Dialog", Font.PLAIN, 12));
		newSalePanel.add(lblLoggedInUserNS);
		
		JPanel cancelSalePanel = new JPanel();
		containerPanel.add(cancelSalePanel, "cancelSaleScreen");
		cancelSalePanel.setLayout(null);
		
		transactionIDCSField = new JTextField();
		transactionIDCSField.setBounds(286, 93, 124, 19);
		cancelSalePanel.add(transactionIDCSField);
		transactionIDCSField.setColumns(10);
		
		JLabel lblCancelSale = new JLabel("Cancel Sale");
		lblCancelSale.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCancelSale.setBounds(306, 12, 115, 15);
		cancelSalePanel.add(lblCancelSale);
		
		JLabel lblTransactionId = new JLabel("Transaction ID");
		lblTransactionId.setBounds(156, 95, 98, 15);
		cancelSalePanel.add(lblTransactionId);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(457, 59, 222, 300);
		cancelSalePanel.add(scrollPane_2);
		
		JTextArea txtAreaCS = new JTextArea();
		scrollPane_2.setViewportView(txtAreaCS);
		
		JButton btnCancelEntireSale = new JButton("Cancel Sale");
		btnCancelEntireSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					register.finalizeReturn();
				} catch (InvalidIDException e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Error: Invalid UPC");
				} catch (InsufficientFundsException e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Error: Insufficient Funds");
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Error: Unable to Process Return");
				}
				txtAreaCS.setText(register.calculateSale());
			}
		});
		btnCancelEntireSale.setBounds(286, 208, 124, 25);
		cancelSalePanel.add(btnCancelEntireSale);
		
		JButton btnMainMenuCS = new JButton("Main Menu");
		btnMainMenuCS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(containerPanel, "cashierScreen");
				txtAreaCS.setText("");
			}
		});
		btnMainMenuCS.setBounds(286, 311, 124, 25);
		cancelSalePanel.add(btnMainMenuCS);
		
		JLabel lblTransaction = new JLabel("Transaction");
		lblTransaction.setBounds(526, 27, 115, 15);
		cancelSalePanel.add(lblTransaction);
		
		JLabel label_4 = new JLabel("Logged in as:");
		label_4.setBounds(497, 0, 101, 15);
		cancelSalePanel.add(label_4);
		
		JLabel lblLoggedInUserCS = new JLabel("Default");
		lblLoggedInUserCS.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblLoggedInUserCS.setBounds(604, 0, 111, 15);
		cancelSalePanel.add(lblLoggedInUserCS);
		
		JButton btnSearchCS = new JButton("Search");
		btnSearchCS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					register.newReturn(Integer.parseInt(transactionIDCSField.getText()));
				} catch (NumberFormatException | InvalidIDException e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Invalid Transaction ID");
				}
				txtAreaCS.setText(register.calculateSale());
				transactionIDCSField.setText("");
			}
		});
		btnSearchCS.setBounds(286, 124, 124, 25);
		cancelSalePanel.add(btnSearchCS);
		
		JPanel returnItemPanel = new JPanel();
		containerPanel.add(returnItemPanel, "returnItemScreen");
		returnItemPanel.setLayout(null);
		
		transactionIDRIField = new JTextField();
		transactionIDRIField.setColumns(10);
		transactionIDRIField.setBounds(292, 93, 124, 19);
		returnItemPanel.add(transactionIDRIField);
		
		JLabel label = new JLabel("Transaction ID");
		label.setBounds(162, 95, 98, 15);
		returnItemPanel.add(label);
		
		JLabel lblReturnItem = new JLabel("Return Item");
		lblReturnItem.setFont(new Font("Dialog", Font.BOLD, 14));
		lblReturnItem.setBounds(309, 12, 115, 15);
		returnItemPanel.add(lblReturnItem);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(449, 65, 237, 300);
		returnItemPanel.add(scrollPane_1);
		
		JTextArea textAreaRI = new JTextArea();
		scrollPane_1.setViewportView(textAreaRI);
		
		JButton buttonMainMenuRI = new JButton("Main Menu");
		buttonMainMenuRI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(containerPanel, "cashierScreen");
				textAreaRI.setText("");
			}
		});
		
		buttonMainMenuRI.setBounds(292, 313, 124, 25);
		returnItemPanel.add(buttonMainMenuRI);
		
		JLabel label_2 = new JLabel("Transaction");
		label_2.setBounds(517, 38, 115, 15);
		returnItemPanel.add(label_2);
		
		JButton btnReturnItemRI = new JButton("Return Item");
		btnReturnItemRI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					register.finalizeReturn(Integer.parseInt(upcRIField.getText()));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Error: Invalid UPC");
				} catch (InvalidIDException e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Error: Invalid UPC");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Error: Unable to Process Return");
				} catch (InsufficientFundsException e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Error: Insufficient Funds");
				}
				textAreaRI.setText(register.calculateSale());
				upcRIField.setText("");
			}
		});
		btnReturnItemRI.setBounds(292, 218, 124, 25);
		returnItemPanel.add(btnReturnItemRI);
		
		upcRIField = new JTextField();
		upcRIField.setColumns(10);
		upcRIField.setBounds(292, 187, 124, 19);
		returnItemPanel.add(upcRIField);
		
		JLabel lblEnterUpc_1 = new JLabel("UPC");
		lblEnterUpc_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEnterUpc_1.setBounds(162, 189, 98, 15);
		returnItemPanel.add(lblEnterUpc_1);
		
		JLabel label_6 = new JLabel("Logged in as:");
		label_6.setBounds(497, 0, 101, 15);
		returnItemPanel.add(label_6);
		
		JLabel lblLoggedInUserRI = new JLabel("Default");
		lblLoggedInUserRI.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblLoggedInUserRI.setBounds(604, 0, 111, 15);
		returnItemPanel.add(lblLoggedInUserRI);
		
		JButton btnSearchRI = new JButton("Search");
		btnSearchRI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					register.newReturn(Integer.parseInt(transactionIDRIField.getText()));
				} catch (NumberFormatException | InvalidIDException e) {
					JOptionPane.showMessageDialog(frmPosSystemLogin, "Invalid Transaction ID");
				}
				textAreaRI.setText(register.calculateSale());
				transactionIDRIField.setText("");
			}
		});
		btnSearchRI.setBounds(292, 121, 124, 25);
		returnItemPanel.add(btnSearchRI);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				try {
					if (Integer.parseInt(username) == store.getCashier(0).getID() && password.equals("asdf")) {
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "managerScreen");
						lblLoggedInAdmin.setText(store.getCashier(0).getFirstName() + " " + store.getCashier(0).getLastName());
						lblLoggedInUserNS.setText(store.getCashier(0).getFirstName() + " " + store.getCashier(0).getLastName());
						lblLoggedInUserRI.setText(store.getCashier(0).getFirstName() + " " + store.getCashier(0).getLastName());
						lblLoggedInUserCS.setText(store.getCashier(0).getFirstName() + " " + store.getCashier(0).getLastName());
						register.unlock(Integer.parseInt(username), password);
					}
					else if (Integer.parseInt(username) == store.getCashier(1).getID() && password.equals("asdf")) {
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "cashierScreen");
						lblLoggedInUser.setText(store.getCashier(1).getFirstName() + " " + store.getCashier(1).getLastName());
						lblLoggedInUserNS.setText(store.getCashier(1).getFirstName() + " " + store.getCashier(1).getLastName());
						lblLoggedInUserRI.setText(store.getCashier(1).getFirstName() + " " + store.getCashier(1).getLastName());
						lblLoggedInUserCS.setText(store.getCashier(1).getFirstName() + " " + store.getCashier(1).getLastName());
						register.unlock(Integer.parseInt(username), password);
					}
					else if (Integer.parseInt(username) == store.getCashier(2).getID() && password.equals("1234")){
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "cashierScreen");
						lblLoggedInUser.setText(store.getCashier(2).getFirstName() + " " + store.getCashier(2).getLastName());
						lblLoggedInUserNS.setText(store.getCashier(2).getFirstName() + " " + store.getCashier(2).getLastName());
						lblLoggedInUserRI.setText(store.getCashier(2).getFirstName() + " " + store.getCashier(2).getLastName());
						lblLoggedInUserCS.setText(store.getCashier(2).getFirstName() + " " + store.getCashier(2).getLastName());
						register.unlock(Integer.parseInt(username), password);
					}
					else if (Integer.parseInt(username) == store.getCashier(3).getID() && password.equals("01Q3EE@")){
						JOptionPane.showMessageDialog(frmPosSystemLogin, "Login Successful");
						cardLayout.show(containerPanel, "cashierScreen");
						lblLoggedInUser.setText(store.getCashier(3).getFirstName() + " " + store.getCashier(3).getLastName());
						lblLoggedInUserNS.setText(store.getCashier(3).getFirstName() + " " + store.getCashier(3).getLastName());
						lblLoggedInUserRI.setText(store.getCashier(3).getFirstName() + " " + store.getCashier(3).getLastName());
						lblLoggedInUserCS.setText(store.getCashier(3).getFirstName() + " " + store.getCashier(3).getLastName());
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
