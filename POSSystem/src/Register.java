import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class Register {
	
  private Store store;
  private int registerID;
  private static int registerIDs=1;//will increment by 1 with each new register
  private double cashValue; //current value in the register
  private Cashier currentCashier;
  private Transaction currentTransaction;
  DecimalFormat df = new DecimalFormat("$###,##0.00");

  Register(double initCash, Store store){
	  //this block is to update the running ID txt file
	  Scanner input;
	  try {
			input = new Scanner(new File("runningRegisterIDgenerator.txt"));
			registerIDs = input.nextInt();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	  
      registerID = registerIDs;
      registerIDs += 1;
      cashValue = initCash;
      this.store = store;
      
      //this block is to update the running ID txt file
      try (PrintWriter output = new PrintWriter(new FileWriter("runningRegisterIDgenerator.txt", false))) {//I set this to false so it overwrites instead of appending the file
      	output.print(registerIDs);
      	output.close();
  	} catch (IOException e) {
  		e.printStackTrace();
  	}
  }
  
  Register(int ID, double initCash, Store store){
	    registerID = ID;
	    cashValue = initCash;
	    this.store = store;
	  }

  //this is a method the UI could use to get the OK to move past the lock screen
  //the method returns a boolean, based on input credentials
  //it also assigns the current cashier, and initializes a shift for that cashier
  
  public void lock() {
	  currentCashier.getShift().setTimeout();
  }
  
  public boolean unlock(int ID, String PW){
	  Cashier c;
	  try {
		  c = store.getCashier(ID);  
	  } catch (InvalidIDException e) {
		  return false;
	  }
	  
	  if (c.checkPassword(PW)) {
		  currentCashier = c;
		  c.setShift();
		  return true;
	  } else {
		  return false;
	  }
  }


  
  //****************************************************************************************** 
  //All methods relating to sales and returns
  //****************************************************************************************** 
  
  public void newSale() {
	  Transaction t = new Transaction(currentCashier);
	  currentTransaction = t;
  }
  
  public void newReturn(int ID) throws InvalidIDException {
	  try {
		currentTransaction = store.getTransaction(ID);
	} catch (InvalidIDException e) {
		throw new InvalidIDException("Transaction ID does not exist");
	}
  }
  
  public void addToSale(int UPC) throws InvalidIDException {
	  Product p = store.getProduct(UPC);
	  currentTransaction.addToSale(p);
  }
  
  public void removeFromSale(int UPC) throws InvalidIDException {
	  currentTransaction.removeFromSale(UPC);
  }
  
  public String calculateSale() {
	  return currentTransaction.toString();
  }
  
  public void finalizeSale() {
	  for (Product p : currentTransaction.getCart()) {
		  store.getInventory().removeItemsFromInventory(p.getUPC(), 1);
	  }
	  try {
		store.addTransaction(currentTransaction);
		store.updateInventoryFile();
		addCash(currentTransaction.getTotal());
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	 
	  currentCashier.getShift().addEvent(new Event(currentTransaction, "Sale"));
	 }
	  
  
  public void finalizeReturn() throws InvalidIDException, InsufficientFundsException, IOException {
	  if (currentTransaction.getTotal() <= cashValue){
	      removeCash(currentTransaction.getTotal());
	    } else {
	      throw new InsufficientFundsException("Not enough cash in the register");
	    }
	  	currentTransaction.getCart().removeAll(currentTransaction.getCart());
	  store.updateTransactionFile();
  }
  
  // overloaded method to return just a single item
  public void finalizeReturn(int UPC) throws InvalidIDException, IOException, InsufficientFundsException {
	  removeCash(currentTransaction.getPrice(UPC));
	  currentTransaction.removeFromSale(UPC);
	  store.updateTransactionFile();
  }
  
  //****************************************************************************************** 
  //End sales and returns block
  //****************************************************************************************** 
  

  //****************************************************************************************** 
  //Other general store functions
  //****************************************************************************************** 
  
  public void addCash(double input) throws IOException {
	    cashValue+=input;
	    store.updateRegisterFile();
  }

  public void removeCash(double input) throws InsufficientFundsException, IOException {
	    if (input<=cashValue){
	      cashValue-=input;
	      store.updateRegisterFile();
	    } 
	    else {
	      throw new InsufficientFundsException("Not enough cash in the register");
	    }
  }
  
  public double getValue(){
	    return cashValue;
	  }

  public int getID(){
	    return this.registerID;
	  }
  
  public void addCashier(String PW, String firstname, String lastname, boolean admin) throws InsufficientRightsException, IOException{
    if (currentCashier.isAdmin())
      store.addCashier(PW,firstname,lastname,admin);
    else
      throw new InsufficientRightsException("Insufficient Rights for this task");
  }

  public void removeCashier(int ID) throws InsufficientRightsException, IOException{
      if (currentCashier.isAdmin()){
        store.removeCashier(ID);
      } else {
    	  throw new InsufficientRightsException("Insufficient Rights for this task");
      }
    }
  
  public void addRegister(double initCash) throws IOException, InsufficientRightsException {
	  if (currentCashier.isAdmin())
	  	store.addRegister(initCash);
	  else
	  	throw new InsufficientRightsException("Insufficient Rights for this task");	
  }
  
  public void removeRegister(int ID) throws InvalidIDException, InsufficientRightsException, IOException {
	  if (currentCashier.isAdmin())
		 store.removeRegister(ID);
	  else
		 throw new InsufficientRightsException("Insufficient Rights for this task");	
  }

  //****************************************************************************************** 
  //End general store functions block
  //****************************************************************************************** 
  
  //******************************************************************************************
  //this block implements the inventory class in register
  //we have to update the txt file with each method as well
  //******************************************************************************************
  
  public void addnewItem(String productName, String supplier, double price, int quantityStocked, int threshold) {
	  store.getInventory().addnewItem(new Product(productName,supplier,price,quantityStocked,threshold));
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  public void addItemstoInventory(int upc, int quantity) {
	  store.getInventory().addItemstoInventory(upc, quantity);
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  //overloaded for when we just want to add 1
  public void addItemstoInventory(int upc) {
	  store.getInventory().addItemstoInventory(upc, 1);
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  public void removeItemsFromInventory(int upc, int quantity) {
	  store.getInventory().removeItemsFromInventory(upc, quantity);
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  //overloaded for when we just want to add 1
  public void removeItemsFromInventory(int upc) {
	  store.getInventory().removeItemsFromInventory(upc, 1);
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  public void addItemstoOrder(int upc, int quantity) {
	  store.getInventory().addItemstoOrder(upc, quantity);
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  public void addItemstoOrder(int upc) {
	  store.getInventory().addItemstoOrder(upc, 1);
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  public void removeItemsfromOrder(int upc, int quantity) {
	  store.getInventory().removeItemsfromOrder(upc, quantity);
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  public void removeItemsfromOrder(int upc) {
	  store.getInventory().removeItemsfromOrder(upc, 1);
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  public void receivePartialOrder(int upc, int quantity) {
	  store.getInventory().receivePartialOrder(upc, quantity);
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  public void receivePartialOrder(int upc) {
	  store.getInventory().receivePartialOrder(upc, 1);
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  
  public Transaction getCurrentTransaction() {
	return currentTransaction;
  }

  public void setCurrentTransaction(Transaction currentTransaction) {
	this.currentTransaction = currentTransaction;
  }
  
  public void setThreshold(int UPC, int threshold) { 
	  store.getInventory().setProductThreshold(UPC, threshold); 
	  try {
			store.updateInventoryFile();
			//System.out.println("Success in reaching the update!");
		} catch (IOException e) {
			e.printStackTrace();
		}
  }

  public void setPrice(int UPC, double price) { 
	  store.getInventory().setProductPrice(UPC, price); 
	  try {
			store.updateInventoryFile();
			//System.out.println("Success in reaching the update!");
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
	public void receiveAllOrders() {
	  store.getInventory().receiveAllOrders();
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
	
	
  public String orderingNeeds() {
	  return store.getInventory().orderingNeeds();
  }
  //******************************************************************************************
  //end of inventory class implementation
//******************************************************************************************  
  
  //****************************************************************************************** 
  //Reports
  //****************************************************************************************** 
  public String inventoryReport(){
    Inventory inv = store.getInventory();
    return inv.printInventoryReport();
  }
  
  public String productReport(int upc) {
	  Inventory inv = store.getInventory();
	  return inv.printProductReport(upc);
  }
  
  public String orderingNeedsReport() {
	  Inventory inv = store.getInventory();
	  return inv.orderingNeeds();
  }
  
  public String orderingNeedsCondensedReport() {
	  Inventory inv = store.getInventory();
	  return inv.orderingNeedsCondensed();
  }
  
  public String CashierReportX(int ID){
    Cashier c;
	try {
		c = store.getCashier(ID);
	    String s = "";
	    s+=c.getID()+" | "+c.getFirstName()+" | "+c.getLastName()+"\n";
	    ArrayList<Event> events = c.getShift().getEvents();
	    for (int i=0;i<events.size();i++){
	      s+=events.get(i)+"\n";
	    }
	    return s;
	} catch (InvalidIDException e) {
		e.printStackTrace();
		return "No cashier found with that ID number";
	}
  }

  public String CashierReportZ(){
    String s = "";
    ArrayList<Cashier> cashiers = store.getCashiers();
    for (Cashier c : cashiers) {
    	s+=CashierReportX(c.getID()); //Z report is just an X report for all cashiers
    }
    return s;
  }
  //****************************************************************************************** 
  //End Reports
  //****************************************************************************************** 
  
  @Override
  public String toString() {
	  return registerID+" "+" "+cashValue;
  }
}
