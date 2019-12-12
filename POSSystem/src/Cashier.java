import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Cashier {
  private static Scanner input;
  private static int IDgenerator;
  private int cashierID;
  private String password;
  private String firstname;
  private String lastname;
  private boolean administrator;
  private ArrayList<Shift> shifts = new ArrayList<Shift>();
  private Shift shift;

  Cashier(String PW, String fn, String ln, boolean admin){
	//this block is to update the running ID txt file
	try {
		input = new Scanner(new File("runningCashierIDgenerator.txt"));
		IDgenerator = input.nextInt();
	} catch (FileNotFoundException e) {}
	  
    cashierID = IDgenerator;
    IDgenerator++;
    password = PW;
    firstname = fn;
    lastname = ln;
    administrator = admin;
    
    //this block is to update the running ID txt file
    try (PrintWriter output = new PrintWriter(new FileWriter("runningCashierIDgenerator.txt", false))) {//I set this to false so it overwrites instead of appending the file
    	output.print(IDgenerator);
    	output.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
		
  }
 
  
  //this is for the reader to recreate cashiers that already exist when rewriting the text files
  Cashier(int ID, String PW, String fn, String ln, boolean admin, ArrayList<Shift> shifts){
	    cashierID = ID;
	    password = PW;
	    firstname = fn;
	    lastname = ln;
	    administrator = admin;
	    this.shifts = shifts;
	  }

  Cashier(int ID, String PW, String fn, String ln, boolean admin){
	  cashierID = ID;
	  password = PW;
	  firstname = fn;
	  lastname = ln;
	  administrator = admin;
	  shifts = new ArrayList<Shift>();
  }
  
  Cashier (String PW){
    cashierID = IDgenerator;
    IDgenerator++;
    password = PW;
  }

  public int getID(){
    return cashierID;
  }

  public void setShift(){
    shift = new Shift();
	shifts.add(shift);
  }

  public ArrayList<Shift> getShifts(){
    return shifts;
  }

  public Shift getShift(){
	  if (shifts.size()!=0)
		  return shifts.get(shifts.size()-1);
	  else
		  return null;
  }

  public Shift getShift(int i){
	  return shifts.get(i);
  }

  public void setPassword(String pw){
    this.password = pw;
  }

  public boolean checkPassword(String pw){
    if (pw.equals(this.password)){
      return true;
    }
    else {
      return false;
    }
  }

  public void setName(String f, String l){
    this.firstname=f;
    this.lastname=l;
  }

  public String getFirstName(){
    return firstname;
  }

  public String getLastName(){
    return lastname;
  }

  public Boolean isAdmin(){
    return administrator;
  }

  public void setAdmin(Boolean b){
    this.administrator=b;
  }
  
  @Override
  public String toString() {
	  String s = "";
	  s+=cashierID+" "+password+" "+firstname+" "+lastname+" "+administrator+" "+shifts.size()+" ";
	  for (Shift shift : shifts) {
		  s+=shift.toFile();
	  }
	  return s;
  }
  
}
