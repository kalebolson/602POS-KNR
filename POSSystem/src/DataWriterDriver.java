import java.io.FileNotFoundException;
import java.io.IOException;

//I'm writing this class exclusively to write and test my data writing files
//This will not be a part of the program

//Now that I'm a ways into writing it, this is a good demonstration of how to use this program.
//You guys should take a look at this, especially when writing UI. 

public class DataWriterDriver {
	public static void main(String args[]) {
		
		//we need a store for starters
		Store myStore;
		myStore = new Store();
		
		
		//the register is what we actually interact with
		Register myRegister;
		try { //the try statement is there because it throws an exception if the register doesn't exist
			  //register 1 is card coded in Store so we don't risk this here of course.
			myRegister = myStore.getRegister(1);
			
			//we are unlocking the register, thereby assigning the cashier
			//in this case it's the default admin cashier with the default admin password
			//this cashier is hard coded in Store
			System.out.println(myRegister.unlock(0, "masterpassword"));
			
			
			//Now that I'm logged in as an administrator, we can get started!
			//I am creating new cashiers here
			//I've commented this out, as I only had to to it once to get 
			//the data on to the text files
//			try {
//				myRegister.addCashier("asdf", "Kaleb", "Olson", true);
//				myRegister.addCashier("1234", "Riley", "Martinez", false);
//				myRegister.addCashier("01Q3EE@", "Nicole", "Burns", false);
//				
//				myRegister.addRegister(50.00);
//				myRegister.addRegister(100.00);
//				
//				
//			} catch (InsufficientRightsException e) {
//				System.out.println(e.getMessage());
//			} catch (IOException e) {
//				System.out.println(e.getMessage());
//			}
			
			//Now I'm going to print their information to test how it comes out
			//the toString for these files will ideally be how I save the information
			int i=1;
			for (Cashier c : myStore.getCashiers()) {
				System.out.println("Cashier number "+i+": "+c);
				i++;
			}
			
			//removing cashier 3
//			try {
//				myRegister.removeCashier(1);
//			} catch (InsufficientRightsException | IOException e) {
//				System.out.println(e.getMessage());
//			}
			
			//printing new list
			System.out.println("");
			i=1;
			for (Cashier c : myStore.getCashiers()) {
				System.out.println("Cashier number "+i+": "+c);
				i++;
			}
			
			System.out.println("");
			i=1;
			for (Register r : myStore.getRegisters()) {
				System.out.println("Register number "+i+": "+r);
				i++;
			}
			
			
			//notice when we run this program, it updates the txt files for these respective lists accordingly
			
			
			
			
		} catch (InvalidIDException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
