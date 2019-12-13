import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Register {
	
  private Store store;
  private int registerID;
  private static int registerIDs=1;//will increment by 1 with each new register
  private double cashValue; //current value in the register
  private Cashier currentCashier;
  private Transaction currentTransaction;
  DecimalFormat df = new DecimalFormat("$###,##0.00");
  private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
  private LocalDateTime now = LocalDateTime.now();
  private static final BigDecimal SALES_TAX = new BigDecimal("0.07875");

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
  
  public void lock() throws IOException {
	  currentCashier.getShift().setTimeout();
	  store.updateCashierFile();
	  
  }
  
  public boolean unlock(int ID, String PW) throws IOException{
	  Cashier c;
	  try {
		  c = store.getCashier(ID);  
		  System.out.println(""+c);
		  System.out.println(""+ID);
		  System.out.println(""+ c.checkPassword(PW));
	  } catch (InvalidIDException e) {
		  return false;
	  }
	  
	  if (c.checkPassword(PW)) {
		  currentCashier = c;
		  try {
			  if (c.getShift() == null) { 
				  c.setShift();
				  store.updateCashierFile();
			  }
			  else if (!dtf.format(now).equals(c.getShift().getTimein().substring(0, 10))) {
				  c.setShift();
				  store.updateCashierFile();
			  }
			  else return true;
		  } catch (IndexOutOfBoundsException e) {}
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
  
  public void finalizeSale() throws IOException, InvalidIDException {
	  for (Product p : currentTransaction.getCart()) {
		  store.getInventory().removeItemsFromInventory(p.getUPC(), 1);
		  try {
			  store.updateInventoryFile(); 
		  }catch(IOException e2) { 
			  e2.printStackTrace();
		  }
	  }
	  try {
		store.addTransaction(currentTransaction);
		//store.updateInventoryFile();
		addCash(currentTransaction.getTotal());
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	 
	  //have to add this line because at some point a write-from-file method has
	  //disconnected currentCashier from the correct memory location
	  currentCashier = store.getCashier(currentCashier.getID());
	  
	  currentCashier.getShift().addEvent(new Event(currentTransaction.getTransactionID(), "Sale"));
	  store.updateCashierFile();
	 }
	  
  
  public void finalizeReturn() throws InvalidIDException, InsufficientFundsException, IOException {
	  if (currentTransaction.getTotal() <= cashValue){
	      removeCash(currentTransaction.getTotal());
	    } else {
	      throw new InsufficientFundsException("Not enough cash in the register");
	    }
	  	currentTransaction.getCart().removeAll(currentTransaction.getCart());
	  store.updateTransactionFile();
	  
	  //have to add this line because at some point a write-from-file method has
	  //disconnected currentCashier from the correct memory location
	  currentCashier = store.getCashier(currentCashier.getID());
	  
	  currentCashier.getShift().addEvent(new Event(currentTransaction.getTransactionID(), "Return"));
	  store.updateCashierFile();
  }
  
  // overloaded method to return just a single item
  public void finalizeReturn(int UPC) throws InvalidIDException, IOException, InsufficientFundsException {
	  BigDecimal itemPrice = new BigDecimal(String.valueOf(getCurrentTransaction().getPrice(UPC)));
	  itemPrice = itemPrice.add(itemPrice.multiply(SALES_TAX));
	  itemPrice.setScale(2, RoundingMode.HALF_UP);
	  removeCash(itemPrice.doubleValue());
	  currentTransaction.removeFromSale(UPC);
	  store.updateTransactionFile();
	  
	  //have to add this line because at some point a write-from-file method has
	  //disconnected currentCashier from the correct memory location
	  currentCashier = store.getCashier(currentCashier.getID());
	  
	  currentCashier.getShift().addEvent(new Event(currentTransaction.getTransactionID(), "Return"));
	  store.updateCashierFile();
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
  
  
  //add a new product type to the inventory
  public void addnewItem(String productName, String supplier, double price, int quantityStocked, int threshold) {
	  store.getInventory().addnewItem(new Product(productName,supplier,price,quantityStocked,threshold));
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  
  //remove a product type from the inventory
  public void removeProductType(int UPC) { 
	  store.getInventory().removeProductTypefromInventory(UPC);
	  try {
		store.updateInventoryFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  
  //add quantity of a given product to the inventory
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
  
  //remove quantity of a given product from the inventory
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
  
  public String inventoryList() {
	  Inventory inv = store.getInventory();
	  return inv.inventoryReferenceList();
  }
  
  public String orderingNeedsReport() {
	  Inventory inv = store.getInventory();
	  return inv.orderingNeeds();
  }
  
  public String pendingOrdersReport() {
	  Inventory inv = store.getInventory();
	  return inv.orderedItems();
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
	    s+="In: "+c.getShift().getTimein()+" | ";
	    s+="Out: "+c.getShift().getTimeout()+"\n";
	    ArrayList<Event> events = c.getShift().getEvents();
	    for (int i=0;i<events.size();i++){
	      s+=events.get(i)+"\n";
	    }
	    s+="\n";
	    return s;
	} catch (InvalidIDException e) {
		return "No cashier found with that ID number";
	} catch (NullPointerException e) {
		return "Cashier "+ID+" has no shift on record \n";
	}
  }
  
  public String CashierReportCondensed(int ID){
	    Cashier c;
		try {
			c = store.getCashier(ID);
		    String s = "";
		    s+=c.getID()+" | "+c.getFirstName()+" | "+c.getLastName() + "\n";
		    return s;
		} catch (InvalidIDException e) {
			return "No cashier found with that ID number";
		} catch (NullPointerException e) {
			return "Cashier "+ID+" has no shift on record \n";
		}
	  }
  
  public String CashierReportFullCondensed(){
	    String s = "";
	    ArrayList<Cashier> cashiers = store.getCashiers();
	    for (Cashier c : cashiers) {
	    	s+=CashierReportCondensed(c.getID()); //Z report is just an X report for all cashiers
	    }
	    return s;
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
