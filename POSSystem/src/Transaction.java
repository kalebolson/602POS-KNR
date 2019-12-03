import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Transaction {
	private static int count = 0;
	int transactionID;
	String transactionDate;
	private double cashTendered;
	private double changeReceived;
	private int UPC;
	private String cashier;
	// ArrayList<Product> cart;
	LocalDateTime localDateTime;
	DateTimeFormatter dtf;
	
	
	public Transaction() {
		transactionID = ++count;
		localDateTime = LocalDateTime.now();
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		transactionDate = localDateTime.format(dtf);
		// ArrayList<Product> cart;
	}
	
	// public void addProduct(int UML) {}
	// public void removeProduct(int transactionID, int UPC, int quantity) {}
	
	// print items sold, their price, sub total, sales tax, and total for a given transactionID
	public void printReceipt(int transactionID) {}
	
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
	
	// public double getTotal() {
	//	CalculateTransaction calc = new CalculateTransaction();
	//	return Double.parseDouble(calc.calculateTotal(cart));
	// }
	
	// public double getSubTotal() {
	//	CalculateTransaction calc = new CalculateTransaction();
	//	return Double.parseDouble(calc.calculateSubtotal(cart));
    // }
	
	// public String getCashier() {
	// 	return cashier;
	// }
	
	// public void setCashier(String cashier) {
	//	this.cashier = cashier
	//}
}
