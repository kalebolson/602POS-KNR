
public class RestoreInventoryToDefault {

	public static void main(String[] args) {
		
		Store store = new Store();
		try {
			Register register = store.getRegister(1);
			store.getRegister(1).addnewItem("Apples", "SupremeSupply", 0.70, 50, 15);
			store.getRegister(1).addnewItem("Bananas", "TraderJack's", 0.17, 50, 15);
			store.getRegister(1).addnewItem("Chocolate", "FarmerBub's", 2.50, 50, 15);
			store.getRegister(1).addnewItem("Chicken", "TraderJack's", 2.92, 30, 10);
			store.getRegister(1).addnewItem("Salmon", "SupremeSupply", 10.92, 20, 10);
			store.getRegister(1).addnewItem("Eggs", "FarmerBub's", 3.49, 25, 10);
			store.getRegister(1).addnewItem("Peanutbutter", "SupremeSupply", 3.00, 40, 15);
			store.getRegister(1).addnewItem("Almondbutter", "GeneralDistributers", 6.99, 40, 15);
			store.getRegister(1).addnewItem("Bread", "SupremeSupply", 1.99, 30, 10);
			store.getRegister(1).addnewItem("Pizza", "TraderJack's", 10.99, 40, 15);
			store.getRegister(1).addnewItem("Oranges", "FarmerBub's", 0.54, 50, 15);
			store.getRegister(1).addnewItem("Onions", "FarmerBub's", 0.26, 50, 15);
			store.getRegister(1).addnewItem("Juice", "SupremeSupply", 3.44, 30, 15);
			store.getRegister(1).addnewItem("Beer", "SupremeSupply", 6.99, 100, 20);
			store.getRegister(1).addnewItem("Wine", "SupremeSupply", 11.99, 100, 20);
			store.getRegister(1).addnewItem("Rum", "TraderJack's", 18.50, 100, 20);
			store.getRegister(1).addnewItem("Broccoli", "FarmerBub's", 1.15, 50, 15);
			store.getRegister(1).addnewItem("Bacon", "FarmerBub's", 3.99, 50, 15);
			store.getRegister(1).addnewItem("Pork", "TraderJack's", 2.45, 50, 15);
			store.getRegister(1).addnewItem("Milk", "FarmerBub's", 3.56, 30, 15);
		
		} catch (InvalidIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
