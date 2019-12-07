import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Transaction {
	private static int IDgenerator;
	private int transactionID;
	private String transactionDate;
	private double cashTendered;
	private double changeReceived;
	private int UPC;
	private Cashier cashier;
	private ArrayList<Product> cart;
	private LocalDateTime localDateTime;
	private DateTimeFormatter dtf;
	public static final BigDecimal SALES_TAX_RATE = new BigDecimal("0.07875");
	private BigDecimal subtotal = new BigDecimal("0.00");
	private BigDecimal total = new BigDecimal("0.00");
	DecimalFormat df = new DecimalFormat("$###,##0.00");
	
	public Transaction(Cashier c) {
		//this block is to update the running ID txt file
		Scanner input;
		  try {
				input = new Scanner(new File("runningTransactionIDgenerator.txt"));
				IDgenerator = input.nextInt();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
		//main constructor
		transactionID = IDgenerator;
		IDgenerator++;
		localDateTime = LocalDateTime.now();
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		transactionDate = localDateTime.format(dtf);
		cashier = c;
		cart = new ArrayList<Product>();
		
		//this block is to update the running ID txt file
	      try (PrintWriter output = new PrintWriter(new FileWriter("runningTransactionIDgenerator.txt", false))) {//I set this to false so it overwrites instead of appending the file
	      	output.print(IDgenerator);
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
	
	// used for the text file writer. it outputs the exact format that the readers knows what to do with
	// found in store class line 153
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
	
	public ArrayList<Product> getCart() {
		return cart;
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
	
	public double getTotal() {
		getSubTotal();
		total = subtotal.add(new BigDecimal(String.valueOf(getSalesTax())));	
		total = total.setScale(2, RoundingMode.HALF_UP);
		return total.doubleValue();
	}
	
	public double getSalesTax() {
		BigDecimal salesTax = subtotal.multiply(SALES_TAX_RATE);
		salesTax = salesTax.setScale(2, RoundingMode.HALF_UP);
		return salesTax.doubleValue();
	}
	
	public double getSubTotal() {
		BigDecimal sum = new BigDecimal("0.00");
		for (int i= 0; i < cart.size(); i++) {
			sum = sum.add(new BigDecimal(String.valueOf(cart.get(i).getPrice())));
		}
		sum = sum.setScale(2, RoundingMode.HALF_UP);
		subtotal = sum;
		return subtotal.doubleValue();
    }
	
	public Cashier getCashier() {
		return cashier;
	}
	
	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}
	
	public String toString() {
		String cartItems = "";
		for (int i = 0; i < cart.size(); i++) {
			cartItems = cartItems + "\n" + cart.get(i).getProductName() + 
					" " + df.format(cart.get(i).getPrice());
		}
		
		return "Transaction ID: " + transactionID +
				"\n" + cartItems +
				"\n\nSubtotal: " + df.format(getSubTotal()) +
				"\nSales Tax: " + df.format(getSalesTax()) +
				"\nTotal: " + df.format(getTotal());
	}
}
