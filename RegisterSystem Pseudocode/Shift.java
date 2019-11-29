import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Shift{
  Cashier cashier;
  String timein;
  String timeout;
  ArrayList<Event> events = new ArrayList<Event>(0);
  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
  LocalDateTime now = LocalDateTime.now();


  Shift(Cashier c){
    this.cashier=c;
    timein = dtf.format(now);
  }

  public void addEvent(Event e){
    events.add(e);
  }

  public ArrayList<Event> getEvents(){
    return events;
  }

  public void setTimein(){
    timein = dtf.format(now);
  }

  @Override
  public void setTimein(String date, String time){
    timein = date + " " + time;
  }

  public String getTimein(){
    return timein;
  }

  public void setTimeout(){
    timeout = dtf.format(now);
  }

  @Override
  public void setTimeout(String date, String time){
    timeout = date + " " + time;
  }

  public String getTimeout(){
    return timeout;
  }

}
