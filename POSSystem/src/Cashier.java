public class Cashier {
  private static int IDgenerator=0;
  private int cashierID;
  private String password;
  private String firstname;
  private String lastname;
  private boolean administrator;
  private Shift[] shifts = new Shift[1];
  private Shift shift;

  Cashier(String PW, String fn, String ln, boolean admin){
    cashierID = IDgenerator;
    IDgenerator++;
    password = PW;
    firstname = fn;
    lastname = ln;
    administrator = admin;
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
    shift = new Shift(this);
    Shift[] temp = new Shift[shifts.length+1];
    shifts = temp;
    shifts[shifts.length-1]=shift;
  }

  public Shift[] getShifts(){
    return shifts;
  }

  public Shift getShift(){
    return shifts[shifts.length-1];
  }

  public Shift getShift(int i){
    return shifts[i];
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
}
