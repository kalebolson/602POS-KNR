
public class POSDriver {

	public static void main(String[]args) { 

		//product(name, supplier, price, quantity, threshold)
		Product a = new Product("Apple", "The farm", 0.99, 50, 15); 
		Product b = new Product("Apple", "Amazon Inc.", 4.95, 120, 30); 
		Product c = new Product("Socks", "My Laundry", 8.99, 5, 1);
		Inventory i = new Inventory(); 
		
		i.addnewItem(a);
		i.addnewItem(b);
		i.addnewItem(c);
		i.printInventoryReport();
		System.out.println("=========================================================================================================================================================");
		System.out.println();
		i.removeItemsFromInventory(1, 25);
		i.printInventoryReport();
		i.addItemstoInventory(2, 100);
		System.out.println("=========================================================================================================================================================");
		
		System.out.println();
		i.printInventoryReport();
		System.out.println("=========================================================================================================================================================");
		
		i.addItemstoOrder(1, 250);
		System.out.println();
		i.printInventoryReport();
		System.out.println("=========================================================================================================================================================");
		
		i.receivePartialOrder(1, 250);
		System.out.println();
		i.printInventoryReport();
		System.out.println("=========================================================================================================================================================");
		
		i.removeItemsFromInventory(1, 270);
		System.out.println();
		i.printInventoryReport();
		
		i.orderingNeeds();
		System.out.println("=========================================================================================================================================================");
		
		i.removeItemsFromInventory(2, 191);
		System.out.println();
		i.printInventoryReport();
		
		i.orderingNeeds();
		System.out.println("=========================================================================================================================================================");
		
		i.addItemstoOrder(1, 50);
		i.addItemstoOrder(2, 50);
		System.out.println();
		i.printInventoryReport();
		i.orderingNeeds();
		System.out.println("=========================================================================================================================================================");
		
		i.receiveAllOrders();
		System.out.println();
		i.printInventoryReport();
		i.orderingNeeds();
		System.out.println("=========================================================================================================================================================");
	}
}
