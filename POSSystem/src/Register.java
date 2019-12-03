import java.util.ArrayList;


public class Register {
  private Store store;
  private int registerID;
  private static int registerIDs=1;//will increment by 1 with each new register
  private double cashValue;
  private Cashier currentCashier;

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
  
////the following lines are trash and we are redoing them entirely
//
//  public void makeSale(){
//    boolean saleOpen = true;
//    Transaction t;
//    int[] UPCs = new int[0];
//    int[] temp;
//    while (saleOpen){
//      System.out.println("add, remove, or complete: ");
//      switch (sc.nextLine()){
//        case "add":{
//          System.out.println("Enter UPC");
//          int entry = sc.nextInt();
//          temp = new int[UPCs.length+1];
//          for (int i=0;i<UPCs.length;i++){
//            temp[i]=UPCs[i];
//          }
//          temp[temp.length-1]=entry;
//        }
//        case "remove":{
//          System.out.println("Enter UPC");
//          int entry = sc.nextInt();
//          temp = new int[UPCs.length-1];
//          int rmindex;
//          for (int i=0;i<UPCs.length;i++){
//            if (UPCs[i]==entry) {rmindex = i;}
//          }
//          for (int i=0;i<rmindex;i++){
//            temp[i] = UPCs[i];
//          }
//          for (int i=rmindex;i<temp.length;i++){
//            temp[i]=UPCs[i+1];
//          }
//        }
//        case "complete":{
//          System.out.println(t.getSubTotal());
//          System.out.println(t.getTotal());
//          double payment = t.getTotal();
//          addCash(payment);
//          for (int i : UPCs){
//            store.getProduct(i).sell();
//          }
//          store.addTransaction(t);
//          saleOpen=false;
//        }
//      }
//    }
//  }
//
//
//  public void makeReturn(){
//    Transaction t;
//    t = store.getTransaction(ID);
//    double prevTotal = t.getTotal();
//    int[] UPCs = new int[0];
//    int[] temp;
//    boolean returnOpen = true;
//    while (returnOpen){
//      System.out.println("all, partial, or complete");
//      String entry = sc.nextLine();
//      switch (entry){
//        case "all": {
//          ////this logic will help return products to inventory if not handled in transaction class
//          //temp = new int[t.getItems().length];
//          //int i2=0;
//          //for (Product i : t.getItems()){
//          //  temp[i2]=i.getUPC();
//          //  i2++;
//          //}
//          t.returnAlltoInventory();
//        }
//        case "partial":{
//          System.out.println("Enter UPC:");
//          int entry = sc.nextInt();
//          //you might need to add the same inventory logic as in the "all" case
//          t.returnProductToInventory(UPC);
//        }
//        case "complete":{
//          ////in case you need to utilize inventory, as mentioned earlier
//          for (int i : UPCs){
//            store.getProduct(i).return();
//            double refund = prevTotal = getTotal();
//            removeCash(refund);
//            returnOpen = false;
//          }
//        }
//      }
//    }
//  }

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
