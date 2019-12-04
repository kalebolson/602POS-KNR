import java.util.ArrayList;

public class Store{
  private Inventory inv;
  private ArrayList<Cashier> cashiers;
  private ArrayList<Transaction> transactions;
  private ArrayList<Register> registers;

  public Store() {
	//hard coding in register number 1 upon store creation
	Register r = new Register(0.0,this);
	registers.add(r);
	
	//This constructor is where we should add the logic for pulling from text files
  }
  
  
  public void addCashier(String PW, String firstname, String lastname, Boolean admin){
    cashiers.add(new Cashier(PW,firstname,lastname,admin));
  }

  public void removeCashier(int ID){
	//since this method takes in the unique ID, as opposed to the index value
	//we need to find the object with that ID, hence the loop.
    for (int i=0; i<cashiers.size();i++) {
    	if (ID==cashiers.get(i).getID()) {
    		cashiers.remove(i);
    	}
    }
  }

  public Cashier getCashier(int ID) throws InvalidIDException {
	  for (int i=0; i<cashiers.size();i++) {
		  if (ID==cashiers.get(i).getID()) {
			  return cashiers.get(i);
		  }
	  }
	  //throws exception if it reaches this point
	  //it shouldn't reach this point if the ID exists
	  throw new InvalidIDException("No cashier found with that ID number");
  }
  
  //this method returns the whole list of cashiers, it is for CashierReportZ in Register.java
  public ArrayList<Cashier> getCashiers(){
	  return cashiers;
  }
  
  //similar to the above method, but for inventory report
  public Inventory getInventory(){
	  return inv;
  }

  public Transaction getTransaction(int ID) throws InvalidIDException{
	for (Transaction t : transactions) {
		if (t.getTransactionID()==ID) {
			return t;
		}
	}
	//throws exception if it reaches this point
	//it shouldn't reach this point if the ID exists
	throw new InvalidIDException("No transaction found with that ID number");
  }

  //getProduct and addProduct both rely on the inventory class having those methods
  //so those will need to exist
  public Product getProduct(int UPC){
    inv.getProduct(UPC);
  }

  public void addProduct(Product p){
    inv.addProduct(p);
  }

  public void addTransaction(Transaction t){
    transactions.add(t);
    
  }

  public void addRegister(double initCash){
    registers.add(new Register(initCash, this));
  }
  
  public void removeRegister(int ID) throws InvalidIDException{
	  //register 1 is hard coded, setting a rule that it cannot be deleted
	  if (ID==1) {
		  throw new InvalidIDException("Cannot remove register 1");
	  }
	  
	  //since this method takes in the unique ID, as opposed to the index value
	  //we need to find the object with that ID, hence the loop.
	  for (Register r : registers) {
		  if (r.getID()==ID) {
			  registers.remove(r);
		  }
	  }
  }

}
