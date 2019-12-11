import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Product {

	private int UPC; 
	private static int UPCcounter=1; 
	private String productName; 
	private String supplier; 
	private double unitPrice = -1.00; 
	private int quantityStocked = -1; 
	private int quantityOrdered = 0; 
	private int threshold; 
	private int amountNeeded; 
	DecimalFormat df = new DecimalFormat("$###,##0.00");
	
	//private Product[] inventory; 
	
	/* -------------------------------------------------------- 
	 * Functional Methods & Calculations are below 
	 * -------------------------------------------------------- */
	
	//May convert price to string for use in money class? 
	public Product(String productName, String supplier, double price, int quantityStocked, int threshold) { 
		
		//this block is to pull from the running UPC txt file
		Scanner input;
		try {
			input = new Scanner(new File("runningUPCgenerator.txt"));
			UPCcounter = input.nextInt();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		//main constructor
		this.productName = productName; 
		this.supplier = supplier; 
		this.unitPrice = price;
		this.quantityStocked = quantityStocked; 
		this.threshold = threshold; 
		this.UPC = this.UPCcounter; 
		UPCcounter ++; 
		
		//this block is to update the running ID txt file
		try (PrintWriter output = new PrintWriter(new FileWriter("runningUPCgenerator.txt", false))) {//I set this to false so it overwrites instead of appending the file
	    	output.print(UPCcounter);
	    	output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//overloaded constructor for writing from file
	public Product(int UPC, String productName, String supplier, double price, int quantityStocked, int quantityOrdered, int threshold) { 
		this.UPC = UPC;
		this.productName = productName; 
		this.supplier = supplier; 
		this.unitPrice = price;
		this.quantityStocked = quantityStocked; 
		this.quantityOrdered = quantityOrdered;
		this.threshold = threshold; 
		calculateNeeded();
		
		
	}
	
	//add product to the inventory
	public void addQuantity(int add) { 
		quantityStocked += add;
		calculateNeeded();
	}
	
	//remove product from the inventory
	public void removeQuantity(int remove) { 
		quantityStocked -= remove; 
		calculateNeeded();
	}
	
	//how much of something you are ordering for inventory
	public void addToOrderQuantity(int add) { 
		quantityOrdered += add; 
		calculateNeeded();
	}
	
	//removing quantity from an inventory order
	public void removeFromOrderQuantity(int remove) { 
		quantityOrdered -= remove;
		calculateNeeded();
	}
	
	public void calculateNeeded() { 
		amountNeeded = threshold - (quantityStocked + quantityOrdered);
	}
	
	public String toString() { 
		return "UPC:\t"+ UPC+ "\n" + 
				"Product:\t"+ productName + "\n" + 
				"Supplier:\t" + supplier + "\n" + 
				"Price:\t"+ df.format(unitPrice) + "\n" + 
				"Threshold:\t" + threshold + "\n" + 
				"Quantity:\t" + quantityStocked + "\n" + 
				"# Ordered:\t" + quantityOrdered + "\n\n";  
		
	}
	/* -------------------------------------------------------- 
	 * End Functional Methods & Calculations 
	 * -------------------------------------------------------- */
	
	
	/* -------------------------------------------------------- 
	 * Standard Getters and Setters for each variable are below 
	 * -------------------------------------------------------- */
	
	public String getProductName() { 
		return productName; 
	}
	
	public void setProductName(String productName) { 
		this.productName = productName;
	}
	
	public String getSupplier() { 
		return supplier;
	}
	
	public void setSupplier(String supplier) { 
		this.supplier = supplier; 
	}
	
	//may make this a string for use in money class
	public double getPrice() { 
		return unitPrice;
	}
	
	public void setPrice(double price) { 
		unitPrice = price; 
	}
	
	public int getStockedQuantity() { 
		return quantityStocked;
	}
	
	public void setStockedQuantity(int quantityStocked) { 
		this.quantityStocked = quantityStocked;
		calculateNeeded();
	}
	
	public int getOrderedQuantity() {
		return quantityOrdered; 
	}
	
	public void setOrderedQuantity(int quantityOrdered) { 
		this.quantityOrdered = quantityOrdered;
		calculateNeeded();
	}
	
	public int getTotalQuantity() { 
		return quantityStocked + quantityOrdered;
	}
	
	public int getThreshold() { 
		return threshold; 
		
	}
	
	public void setThreshold(int threshold) { 
		this.threshold = threshold; 
		calculateNeeded();
	}
	
	public int getUPC(){ 
		return UPC; 
	}
	
	public int getAmountNeeded() { 
		return amountNeeded;
	}
	
	
	/* -------------------------------------------------------- 
	 * End Getters and Setters Block 
	 * -------------------------------------------------------- */
	
	
	
}
