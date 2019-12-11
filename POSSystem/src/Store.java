import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Store {
  private Inventory inv;
  private ArrayList<Cashier> cashiers = new ArrayList<Cashier>();
  private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
  private ArrayList<Register> registers = new ArrayList<Register>();

  public Store() {
	// update all lists
	inv = new Inventory();
	updateInventoryList();
	updateRegisterList();
	updateCashierList();
	updateTransactionList();
  }
  
  
  public void addCashier(String PW, String firstname, String lastname, Boolean admin) throws IOException{
    cashiers.add(new Cashier(PW,firstname,lastname,admin));
    updateCashierFile();
  }

  public void removeCashier(int ID) throws IOException{
	updateCashierList();
	//since this method takes in the unique ID, as opposed to the index value
	//we need to find the object with that ID, hence the loop.
    for (int i=0; i<cashiers.size();i++) {
    	if (ID==cashiers.get(i).getID()) {
    		cashiers.remove(i);
    	}
    	updateCashierFile();
    }
  }

  public Cashier getCashier(int ID) throws InvalidIDException {
	  updateCashierList();
	  for (int i=0; i<cashiers.size();i++) {
		  if (ID==cashiers.get(i).getID()) {
			  return cashiers.get(i);
		  }
	  }
	  //throws exception if it reaches this point
	  //it shouldn't reach this point if the ID exists
	  throw new InvalidIDException("No cashier found with that ID number");
  }
  
  //this method returns the whole list of cashiers
  public ArrayList<Cashier> getCashiers(){
	  updateCashierList();
	  return cashiers;
  }
  public ArrayList<Register> getRegisters(){
	  updateRegisterList();
	  return registers;
  }  
  public ArrayList<Transaction> getTransactions(){
	  updateTransactionList();
	  return transactions;
  }
  
  //similar to the above method, but for inventory report
  public Inventory getInventory(){
	  updateInventoryList();
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

  public Product getProduct(int UPC) throws InvalidIDException{
	updateInventoryList();
	return inv.getProduct(UPC);
  }

  public void addTransaction(Transaction t) throws IOException {
    transactions.add(t);
    updateTransactionFile();
  }

  public void addRegister(double initCash) throws IOException{
    registers.add(new Register(initCash, this));
    updateRegisterFile();
  }
  
  public void removeRegister(int ID) throws InvalidIDException, IOException{
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
	  updateRegisterFile();
  }
  
  public Register getRegister(int ID) throws InvalidIDException {
	  updateRegisterList();
	  for (Register r : registers) {
		  if (r.getID()==ID) {
			  return r;
		  }
	  }
	  throw new InvalidIDException("No Register found with that ID number");
  }
  
  
//------------------------------------------------------------------------------------
//this portion of the class contains all the file updating logic
//------------------------------------------------------------------------------------
	public void updateCashierFile() throws IOException {
		File file = new File("cashiers.txt");
		
		try (PrintWriter output = new PrintWriter(new FileWriter(file, false))) {//I set this to false so it overwrites instead of appending the file
			
			for (Cashier c : cashiers) {
				output.print(c+" ");
			}
			output.close();
		}
	}
	
	//may have to modify this when the transaction class is complete
	public void updateTransactionFile() throws IOException {
		File file = new File("transactions.txt");
		
		try (PrintWriter output = new PrintWriter(new FileWriter(file, false))) {//I set this to false so it overwrites instead of appending the file
			
			for (Transaction t : transactions) {
				output.print(t.stringForFile());
			}
			output.close();
		}
	}
	
	public void updateRegisterFile() throws IOException {
		File file = new File("registers.txt");
		
		try (PrintWriter output = new PrintWriter(new FileWriter(file, false))) {//I set this to false so it overwrites instead of appending the file
			
			for (Register r : registers) {
				output.print(r+" ");
			}
			output.close();
		}
	}
	
	public void updateInventoryFile() throws IOException {
		File file = new File("inventory.txt");
		ArrayList<Product> inventory = inv.getInventory();;
		updateInventoryList();
		try (PrintWriter output = new PrintWriter(new FileWriter(file, false))) {
		
			for (Product p : inventory) {
				output.print(p.getUPC()+" ");
				output.print(p.getProductName()+" ");
				output.print(p.getSupplier()+" ");
				output.print(p.getPrice()+" ");
				output.print(p.getStockedQuantity() +" ");
				output.print(p.getOrderedQuantity()+" ");
				output.print(p.getThreshold()+" ");
			}
			output.close();
		}
	}
	
	public void updateCashierList() {
		Scanner input;
		ArrayList<Cashier> tempList = new ArrayList<Cashier>();
		Cashier c;
		int ID;
		String password;
		String firstname;
		String lastname;
		boolean admin;
		try {
			input = new Scanner(new File("cashiers.txt"));
			
			while (input.hasNext()) {
				ID = input.nextInt();
				password = input.next();
				firstname = input.next();
				lastname = input.next();
				admin = input.nextBoolean();
				c = new Cashier(ID,password,firstname,lastname,admin);
				tempList.add(c);
			}
			cashiers = tempList;
			
			
		}  catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTransactionList() {
		Scanner input;
		ArrayList<Transaction> templist = new ArrayList<Transaction>();
		Transaction t;
		int transactionID;
		String transactionDate;
		Cashier cashier;
		int numProducts;
		ArrayList<Product> cart;
		
		try {
			input = new Scanner(new File("transactions.txt"));
			while (input.hasNext()) {
				cart = new ArrayList<Product>();
				transactionID = input.nextInt();
				transactionDate = input.next() + " " + input.next();//the datetime value has a space in it
				cashier = getCashier(input.nextInt());
				numProducts = input.nextInt();
				for (int i=0;i<numProducts;i++) {
					cart.add(inv.getProduct(input.nextInt()));
				}
				t = new Transaction(transactionID,transactionDate,cart,cashier);
				templist.add(t);
			}
			transactions = templist;
			
		} catch (FileNotFoundException | InvalidIDException e) {
			e.printStackTrace();
		}
	}
	
	public void updateRegisterList() {
		Scanner input;
		ArrayList<Register> templist = new ArrayList<Register>();
		Register r;
		int ID;
		double cashValue;
		
		try {
			input = new Scanner(new File("registers.txt"));
			while (input.hasNext()) {
				ID = input.nextInt();
				cashValue = input.nextDouble();
				r = new Register(ID, cashValue, this);
				templist.add(r);
			}
			registers = templist;
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public void updateInventoryList(){
		Scanner input;
		ArrayList<Product> templist = new ArrayList<Product>();
		Product p;
		int UPC;
		String productName;
		String supplier;
		double price;
		int quantityStocked;
		int quantityOrdered;
		int threshold;
		
		try {
			input = new Scanner(new File("inventory.txt"));
			while (input.hasNext()){
				UPC = input.nextInt();
				productName = input.next();
				supplier = input.next(); // must make sure supplier name is one word (no spaces)
				price = input.nextDouble();
				quantityStocked = input.nextInt();
				quantityOrdered = input.nextInt();
				threshold = input.nextInt();
				p =  new Product(UPC,productName,supplier,price,quantityStocked,quantityOrdered,threshold);
				templist.add(p);
			}
			inv.setInventory(templist);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
}
