import java.util.ArrayList;

public class Inventory {

	private ArrayList<Product> inventory = new ArrayList<Product>(); 
	private boolean thresholdReached = false;
	
	/* -------------------------------------------------------- 
	 * Functional Methods & Calculations are below 
	 * -------------------------------------------------------- */
	public String printInventoryReport() { 
		String s = "";
		s+=("UPC\t\t\tProduct\t\t\tSupplier\t\t\tPrice\t\t\tThreshold\t\tIn-Stock\t\tOrdered");
	//	System.out.println(inventory); 
		for(Product p: inventory) { 
			s+=(p);
		}
		return s;
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
	
	
	public String orderingNeeds() {
		String s = "";
		s+=("Please order more of the following: ");
		for(Product a: inventory) { 
			if(a.getTotalQuantity()<a.getThreshold()) { 
				thresholdReached = true;
				s+=("\t\t"+a.getProductName() + " (On-Hand: "+ a.getStockedQuantity() + ", Threshold: " + a.getThreshold() + ")");
			}
		}
		s+=("End of ordering needs"); 
		return s;
	}
	
	
	/* -------------------------------------------------------- 
	 * End Functional Methods & Calculations 
	 * -------------------------------------------------------- */
	
	
	/* -------------------------------------------------------- 
	 * Standard Getters and Setters for each variable are below 
	 * -------------------------------------------------------- */
	public ArrayList<Product> getInventory(){
		return inventory;
	}
	//THIS OVERWRITES THE ENTIRE INVENTORY
	//USED ONLY FOR READING INVENTORY FROM FILE
	public void setInventory(ArrayList<Product> list) {
		inventory = list;
	}
	
	public Product getProduct(int UPC) throws InvalidIDException {
		for (Product p : inventory) {
			if (p.getUPC()==UPC){
				return p;
			}
		}
		throw new InvalidIDException("No Product found with that UPC number");
	}
	
	
	/* -------------------------------------------------------- 
	 * End Getters and Setters Block 
	 * -------------------------------------------------------- */
}
