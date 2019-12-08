
public class TransactionDriver {

	public static void main(String[] args) {
		
		Store store = new Store();
		try {
			Register register = store.getRegister(1);
			register.unlock(1, "asdf");
			register.newSale();
			register.addToSale(1);
			register.addToSale(1);
			register.addToSale(2);
			register.addToSale(3);
			System.out.println(register.finalizeSale());
			register.lock();
			System.out.println(register.CashierReportX(1));
		
		} catch (InvalidIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
