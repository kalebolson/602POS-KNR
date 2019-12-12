import java.io.IOException;

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
			register.finalizeSale();
			register.lock();
		
		} catch (InvalidIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
