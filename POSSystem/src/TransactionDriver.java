import java.io.IOException;

public class TransactionDriver {

	public static void main(String[] args) {
		
		Store store = new Store();
		try {
			Register register = store.getRegister(1);
			System.out.println(register.unlock(1, "asdf"));
			register.newSale();
			register.addToSale(1);
			register.addToSale(1);
			register.addToSale(2);
			register.addToSale(3);
			System.out.println(register.finalizeSale());
			
			System.out.println();
			System.out.println(register.CashierReportX(1));
			
			register.lock();
		
		} catch (InvalidIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
