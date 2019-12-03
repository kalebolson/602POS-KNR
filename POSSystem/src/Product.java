
public class Product {

	private String productName; 
	private String supplier; 
	private double unitPrice = -1.00; 
	private int quantityStocked = -1; 
	private int quantityOrdered = 0; 
	private int threshold; 
	
	//private Product[] inventory; 
	
	/* -------------------------------------------------------- 
	 * Functional Methods & Calculations are below 
	 * -------------------------------------------------------- */
	
	public Product(String productName, String supplier, double price, int quantityStocked, int threshold) { 
		this.productName = productName; 
		this.supplier = supplier; 
		this.unitPrice = price;
		this.quantityStocked = quantityStocked; 
		this.threshold = threshold; 
	}
	
	//add product to the inventory
	public void addQuantity(int add) { 
		quantityStocked += add;
	}
	
	//remove product from the inventory
	public void removeQuantity(int remove) { 
		quantityStocked -= remove; 
	}
	
	public void addToOrderQuantity(int add) { 
		quantityOrdered += add; 
	}
	
	public void removeFromOrderQuantity(int remove) { 
		quantityOrdered -= remove;
	}
	
	public String toString() { 
		return productName + "\t\t\t" + supplier + "\t\t\t" + unitPrice + "\t\t\t" + threshold + "\t\t\t" + quantityStocked + "\t\t\t" + quantityOrdered;  
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
	}
	
	public int getOrderedQuantity() {
		return quantityOrdered; 
	}
	
	public void setOrderedQuantity(int quantityOrdered) { 
		this.quantityOrdered = quantityOrdered;
	}
	
	public int getTotalQuantity() { 
		return quantityStocked + quantityOrdered;
	}
	
	public int getThreshold() { 
		return threshold; 
	}
	
	public void setThreshold(int threshold) { 
		this.threshold = threshold; 
	}
	
	
	/* -------------------------------------------------------- 
	 * End Getters and Setters Block 
	 * -------------------------------------------------------- */
	
	
	
}
