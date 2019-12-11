
public class POSDriver {

	public static void main(String[]args) {
		Store a = new Store(); 
		Register r = new Register(1.00, a); 

		System.out.println(r.orderingNeedsCondensedReport());
	}


}
