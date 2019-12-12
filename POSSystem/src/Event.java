public class Event {

  private int transactionID;
  private String type;

  Event(int t, String type){
    this.transactionID = t;
    this.type = type;
  }
  
  @Override 
  public String toString(){
    String s = ("Type: " + type + "\t Transaction: " + transactionID);
    return s;
  }
  
  public int getTransID() {
	  return transactionID;
  }

  public void setTransID(int ID) {
	  transactionID =  ID;
  }
  
  public String getType() {
	  return type;
  }
  
  public void setType(String type) {
	  this.type = type;
  }
}
