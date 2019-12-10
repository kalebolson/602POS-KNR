import java.text.DecimalFormat;
import java.util.ArrayList;

public class Inventory {

	private ArrayList<Product> inventory = new ArrayList<Product>(); 
	private boolean thresholdReached = false;
	DecimalFormat df = new DecimalFormat("$###,##0.00");
	
	/* -------------------------------------------------------- 
	 * Functional Methods & Calculations are below 
	 * -------------------------------------------------------- */
	public String printInventoryReport() { 
		String s = "";
		//s+=("UPC\t\t\tProduct\t\t\tSupplier\t\t\tPrice\t\t\tThreshold\t\tIn-Stock\t\tOrdered");
	//	System.out.println(inventory); 
		for(Product p: inventory) { 
			s+=(p);
		}
		return s;
	}
	
	public String printProductReport(int upc) { 
		for(Product a: inventory) { 
			if(a.getUPC()==upc) {  
				return "Product:\t" + a.getProductName() + " (" + a.getUPC() + ")" + 
						"\nSupplier:\t" + a.getSupplier() + 
						"\nPrice:   \t" + df.format(a.getPrice()) + 
						"\n\nQuantity:\t" + a.getStockedQuantity() + 
						"\nThreshold:\t" + a.getThreshold()+
						"\n# Ordered:\t" + a.getOrderedQuantity();
				
			}
			//else return "nope";
		}
		return "double nope"; 
	}
	
	//set inventory threshold
	public void setProductThreshold(int upc, int threshold) { 
		for(Product a: inventory) { 
			if(a.getUPC()==upc) { 
				a.setThreshold(threshold);
			}
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
	
	
	public String orderingNeeds() {
		String s = "";
		s+=("Please order more of the following: \nProduct [On-Hand/Threshold]\n\n");
		for(Product a: inventory) { 
			if(a.getTotalQuantity()<a.getThreshold()) { 
				thresholdReached = true;
				s+=("   "+a.getProductName() + " ["+ a.getStockedQuantity() + "/" + a.getThreshold() + "]");
			}
		}
		s+=("\n\nEnd of ordering needs"); 
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
