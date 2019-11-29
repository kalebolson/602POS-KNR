public class Event {

  Transaction t;
  String type;

  Event(Transaction t, String type){
    this.t = t;
    this.type = type;
  }

  public toString(){
    System.out.println(t+" | "+type)
  }
  
}
