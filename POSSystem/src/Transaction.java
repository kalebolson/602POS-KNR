import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Transaction {
	private static int count = 0;
	private int transactionID;
	private String transactionDate;
	private double cashTendered;
	private double changeReceived;
	private int UPC;
	private Cashier cashier;
	private ArrayList<Product> cart;
	private LocalDateTime localDateTime;
	private DateTimeFormatter dtf;
	
	
	public Transaction(Cashier c) {
		//this block is to update the running ID txt file
		Scanner input;
		  try {
				input = new Scanner(new File("runningTransactionIDgenerator.txt"));
				count = input.nextInt();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
		//main constructor
		transactionID = ++count;
		localDateTime = LocalDateTime.now();
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		transactionDate = localDateTime.format(dtf);
		cashier = c;
		ArrayList<Product> cart = new ArrayList<Product>();
		
		//this block is to update the running ID txt file
	      try (PrintWriter output = new PrintWriter(new FileWriter("runningTransactionIDgenerator.txt", false))) {//I set this to false so it overwrites instead of appending the file
	      	output.print(count);
	      	output.close();
	  	} catch (IOException e) {
	  		e.printStackTrace();
	  	}
	}
	
	//this is for writing transactions from the txt file
	public Transaction(int ID, String dateTime, ArrayList<Product> cart, Cashier c) {
		transactionID = ID;
		transactionDate = dateTime;
		this.cart = cart;
		cashier = c;
	}
	public String stringForFile() {
		String s = "";
		s+=transactionID+" ";
		s+=transactionDate+" ";
		s+=cashier.getID()+" ";
		s+=cart.size()+" ";
		for (Product p : cart) {
			s+=p.getUPC()+" ";
		}
		return s;
	}
	
	public void addToSale(Product p) {
		cart.add(p);
	}
	
	public void removeFromSale(int UPC) throws InvalidIDException {
		boolean searching = true;
		int i = 0;
		//I'm not using a for loop because I need to break 
		//out of the loop once I remove the product
		while (searching){
			if (cart.get(i).getUPC()==UPC) {
				cart.remove(i);
				searching = false;
			}
			i++;
			if (i==cart.size()-1) {
				throw new InvalidIDException("Can't find that UPC in this order");
			}
		}
	}
	
	// print items sold, their price, sub total, sales tax, and total for a given transactionID
	// public String printReceipt() {}
	
	public int getTransactionID() {
		return transactionID;
	}
	
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	
	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getCashTendered() {
		return cashTendered;
	}

	public void setCashTendered(double cashTendered) {
		this.cashTendered = cashTendered;
	}

	public double getChangeReceived() {
		return changeReceived;
	}

	public void setChangeReceived(double changeReceived) {
		this.changeReceived = changeReceived;
	}
	
//	 public double getTotal() {
//		CalculateTransaction calc = new CalculateTransaction();
//		return Double.parseDouble(calc.calculateTotal(cart));
//	 }
	
//	 public double getSubTotal() {
//		CalculateTransaction calc = new CalculateTransaction();
//		return Double.parseDouble(calc.calculateSubtotal(cart));
//     }
	
	// public String getCashier() {
	// 	return cashier;
	// }
	
	// public void setCashier(String cashier) {
	//	this.cashier = cashier
	//}
}
