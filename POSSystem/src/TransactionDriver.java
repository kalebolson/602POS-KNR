
public class TransactionDriver {

	public static void main(String[] args) {
		
		Store store = new Store();
		try {
			Register register = store.getRegister(1);
			register.addnewItem("Apples", "Trader Joes", 0.25, 50, 15);
			register.addnewItem("Bananas", "Aldi's", 0.15, 50, 15);
		
		} catch (InvalidIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
