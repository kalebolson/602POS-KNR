import java.io.*;

// Example 
public class DataFile {
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("transaction_data.txt");
		
		
		try (PrintWriter output = new PrintWriter(new FileWriter(file, false))) {//I set this to false so it overwrites instead of appending the file
		
			output.print("Apples\t");
			output.print("2\t");
			output.print("0.5\t");
			output.println("1.00");
			
			output.close();
		}
	}
}
