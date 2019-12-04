import java.util.ArrayList;


public class Register {
  private Store store;
  private int registerID;
  private static int registerIDs=1;//will increment by 1 with each new register
  private double cashValue; //current value in the register
  private Cashier currentCashier;
  private Transaction currentTransaction;

  Register(double initCash, Store store){
    registerID = registerIDs;
    registerIDs += 1;
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

  public void addCash(double input){
    cashValue+=input;
  }

  //have this throw an exception instead of print
  public void removeCash(double input) throws InsufficientFundsException {
    if (input<=cashValue){
      cashValue-=input;
    } else {
      throw new InsufficientFundsException("Not enough cash in the register");
    }
  }

  public double getValue(){
    return cashValue;
  }

  public int getID(){
    return this.registerID;
  }
  

  public void newSale() {
	  Transaction t = new Transaction();
	  currentTransaction = t;
  }
  
  public void newReturn(int ID) {
	  try {
		currentTransaction = store.getTransaction(ID);
	} catch (InvalidIDException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public void addToSale(int UPC) {
	  Product p = store.getProduct(UPC);
	  currentTransaction.addToSale(p);
  }
  
  public void removeFromSale(int UPC) {
	  Product p = store.getProduct(UPC);
	  currentTransaction.removeFromSale(p);
  }
  
  public String finalizeSale() {
	  String receipt = currentTransaction+"";
	  cashValue+=currentTransaction.getTotal();
	  
  }
  
  public String finalizeReturn() {
	  String receipt = currentTransaction+"";
	  removeCash(currentTransaction.getTotal());
  }
  

  public void addCashier(String PW, String firstname, String lastname, boolean admin) throws InsufficientRightsException{
    if (currentCashier.isAdmin())
      store.addCashier(PW,firstname,lastname,admin);
    else
      throw new InsufficientRightsException("Insufficient Rights for this task");
  }

  public void removeCashier(int ID) throws InsufficientRightsException{
      if (currentCashier.isAdmin()){
        store.removeCashier(ID);
      } else {
    	  throw new InsufficientRightsException("Insufficient Rights for this task");
      }
    }

  //currently nicoles printInventoryReport method actually prints
  //I'm expecting a string return for this, so we should address that
  public String inventoryReport(){
    Inventory inv = store.getInventory();
    return inv.printInventoryReport();
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
}
