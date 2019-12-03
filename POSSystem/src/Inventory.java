import java.util.ArrayList;

public class Inventory {

	private ArrayList<Product> inventory = new ArrayList<Product>(); 
	private boolean thresholdReached = false;
	
	/* -------------------------------------------------------- 
	 * Functional Methods & Calculations are below 
	 * -------------------------------------------------------- */
	public void printInventoryReport() { 
		
		System.out.println("UPC\t\t\tProduct\t\t\tSupplier\t\t\tPrice\t\t\tThreshold\t\tIn-Stock\t\tOrdered");
	//	System.out.println(inventory); 
		for(Product p: inventory) { 
			System.out.println(p);
		}
	}
	
	
	//we now sell a new product
	public void addnewItem(Product p) { 
		inventory.add(p);
	}
	

	//for use in returns? 
	public void addItemstoInventory(int upc, int add) { 
		for(Product a: inventory) { 
			if(a.getUPC()==upc) { 
				a.setStockedQuantity(a.getStockedQuantity() + add);
			}
		}
	}
	
	//for use in transaction
	public void removeItemsFromInventory(int upc, int remove) { 
		for(Product a: inventory) { 
			if(a.getUPC()==upc) { 
				a.setStockedQuantity(a.getStockedQuantity() - remove);
			}
		}
	}
	
	//add items into an inventory mgmt order
	public void addItemstoOrder(int upc, int add) { 
		for(Product a: inventory) { 
			if(a.getUPC()==upc) { 
				a.setOrderedQuantity(a.getOrderedQuantity() + add);
			}
		}
	}
	
	//remove items from an inventory mgmt order
	public void removeItemsfromOrder(int upc, int remove) { 
		for(Product a: inventory) { 
			if(a.getUPC()==upc) { 
				a.setOrderedQuantity(a.getOrderedQuantity() - remove);
			}
		}
	}
	
	public void receivePartialOrder(int upc, int received) { 
		for(Product a: inventory) { 
			if(a.getUPC()==upc) { 
				a.setStockedQuantity(a.getStockedQuantity()+ received);
				a.setOrderedQuantity(a.getOrderedQuantity() - received);
			}
		}
	}
	
	public void receiveAllOrders() { 
		for(Product a: inventory) { 
			a.setStockedQuantity(a.getStockedQuantity()+ a.getOrderedQuantity());
			a.setOrderedQuantity(0);
		}
	}
	
	
	public void orderingNeeds() {
		System.out.println();
		System.out.println("Please order more of the following: ");
		for(Product a: inventory) { 
			if(a.getTotalQuantity()<a.getThreshold()) { 
				thresholdReached = true;
				System.out.println("\t\t"+a.getProductName() + " (On-Hand: "+ a.getStockedQuantity() + ", Threshold: " + a.getThreshold() + ")");
			}
		}
		System.out.println("End of ordering needs"); 
	}
	
	
	/* -------------------------------------------------------- 
	 * End Functional Methods & Calculations 
	 * -------------------------------------------------------- */
	
	
	/* -------------------------------------------------------- 
	 * Standard Getters and Setters for each variable are below 
	 * -------------------------------------------------------- */
	
	
	
	/* -------------------------------------------------------- 
	 * End Getters and Setters Block 
	 * -------------------------------------------------------- */
}
