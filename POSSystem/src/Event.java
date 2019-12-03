public class Event {

  private Transaction t;
  private String type;

  Event(Transaction t, String type){
    this.t = t;
    this.type = type;
  }
  
  @Override 
  public String toString(){
    String s = (t+" | "+type);
    return s;
  }
  
}
