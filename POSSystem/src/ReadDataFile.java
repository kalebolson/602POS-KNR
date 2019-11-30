import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadDataFile {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("transaction_data.txt"));
		
		ArrayList<String> transactionList = new ArrayList<String>();
		
		while (input.hasNextLine()) {
			transactionList.add(input.nextLine());
		}
		
		System.out.println(transactionList);
	}
}
