import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Shift{
  private String timein;
  private String timeout = "Undefined Undefined";
  private ArrayList<Event> events = new ArrayList<Event>(0);
  private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
  private LocalDateTime now = LocalDateTime.now();


  Shift(){
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

  public void setTimein(String date, String time){
    timein = date + " " + time;
  }
  public void setTimein(String datetime){
	    timein = datetime;
	  }

  public String getTimein(){
    return timein;
  }

  public void setTimeout(){
    timeout = dtf.format(now);
  }

  public void setTimeout(String date, String time){
    timeout = date + " " + time;
  }
  public void setTimeout(String datetime){
	    timeout = datetime;
	  }

  public String getTimeout(){
    return timeout;
  }
  
  public String toFile() {
	  String s = "";
	  s+=timein+" ";
	  s+=timeout+" ";
	  s+=events.size()+" ";
	  for (Event e : events) {
		  s+=e.getTransID()+" ";
		  s+=e.getType()+" ";
	  }
	  return s;
  }

}
