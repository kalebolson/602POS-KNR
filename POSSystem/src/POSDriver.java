
public class POSDriver {

	public static void main(String[]args) {
		Store a = new Store(); 
		Register r = new Register(1.00, a); 
		
		System.out.println(r.inventoryReport());
		System.out.println(); 
		//this should print apple information
		System.out.println(r.productReport(1));
		
		//this should print banana information.. if not, will print "nope"
		System.out.println(r.productReport(2));
	}


}
