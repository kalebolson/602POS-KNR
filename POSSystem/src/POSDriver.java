
public class POSDriver {

	public static void main(String[]args) throws InvalidIDException {
		Store a = new Store(); 
		Register r = new Register(1.00, a); 
		System.out.println(a.getCashier(233));

		//System.out.println(r.inventoryList());
	}


}
