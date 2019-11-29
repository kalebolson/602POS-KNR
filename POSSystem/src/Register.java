import java.util.Scanner;

public class Register {
  Store store;
  int registerID;
  Money cashValue;
  Cashier currentCashier;
  Scanner sc = new Scanner(System.in);

  Register(int ID, Money initCash, Store store){
    registerID = ID;
    cashValue = initCash;
    this.store = store;
  }

  public void menuSelect(String entry){
    switch (entry){
      case "sale": makeSale();
      case "return": makeReturn();
      case "add cashier":addCashier();
      case "remove cashier":removeCashier();
      case "run report": report();
      case "logout": {
        currentCashier.getShift().setTimeout();
        lock();
      }
    }
  }

  public void lock(){
    boolean locked = true;
    int ID;
    String PW;

    while (locked){
      System.out.println("ID: ");
      ID = sc.nextInt();
      System.out.println("Password: ");
      PW = sc.nextLine();
      if (store.getCashier(ID).checkPassword(PW)){
        locked = false;
        currentCashier = store.getCashier(ID);
        currentCashier.setShift();
      } else {
        System.out.println("Invalid Credentials")
      }
    }
  }

  public void addCash(Money input){
    cashValue+=input;
  }

  public void removeCash(Money input){
    if (input<=cashValue){
      cashValue-=input;
    } else {
      System.out.println("Insufficient Funds");
    }
  }

  public Money getValue(){
    return cashValue;
  }

  public int getID(){
    return this.registerID;
  }

  public void makeSale(){
    boolean saleOpen = true;
    Transaction t;
    int[] UPCs = new int[0];
    int[] temp;
    while (saleOpen){
      System.out.println("add, remove, or complete: ")
      Switch (sc.nextLine()){
        case "add":{
          System.out.println("Enter UPC");
          int entry = sc.nextInt();
          t.addProduct(entry);
          temp = new int[UPCs.length+1];
          for (int i=0;i<UPCs.length;i++){
            temp[i]=UPCs[i];
          }
          temp[temp.length-1]=entry;
        }
        case "remove":{
          System.out.println("Enter UPC");
          int entry = sc.nextInt();
          t.removeProduct(entry);
          temp = new int[UPCs.length-1];
          int rmindex;
          for (int i=0;i<UPCs.length;i++){
            if (UPCs[i]==entry){
              rmindex = i;
            }
          }
          for (int i=0;i<rmindex;i++){
            temp[i] = UPCs[i];
          }
          for (int i=rmindex;i<temp.length;i++){
            temp[i]=UPCs[i+1]
          }
        }
        case "complete":{
          System.out.println(t.getSubTotal());
          System.out.println(t.getTotal());
          Money payment = t.getTotal();
          addCash(payment);
          for (int i : UPCs){
            store.getProduct(i).sell();
          }
          store.addTransaction(t);
          saleOpen=false;
        }
      }
    }
  }

  public void makeReturn(){
    Transaction t;
    t = store.getTransaction(ID);
    Money prevTotal = t.getTotal();
    int[] UPCs = new int[0];
    int[] temp;
    boolean returnOpen = true;
    while (returnOpen){
      System.out.println("all, partial, or complete");
      String entry = sc.nextLine();
      switch (entry){
        case "all": {
          ////this logic will help return products to inventory if not handled in transaction class
          //temp = new int[t.getItems().length];
          //int i2=0;
          //for (Product i : t.getItems()){
          //  temp[i2]=i.getUPC();
          //  i2++;
          //}
          t.returnAlltoInventory();
        }
        case "partial":{
          System.out.println("Enter UPC:");
          int entry = sc.nextInt();
          //you might need to add the same inventory logic as in the "all" case
          t.returnProductToInventory(UPC);
        }
        case "complete":{
          ////in case you need to utilize inventory, as mentioned earlier
          for (int i : UPCs){
            store.getProduct(i).return();
            Money refund = prevTotal = getTotal();
            removeCash(refund);
            returnOpen = false;
          }
        }
      }
    }
  }

  public void addCashier(){
    System.out.println("enter password");
    String PW = sc.nextLine();
    System.out.println("enter first name");
    String firstname = sc.nextLine();
    System.out.println("enter last name");
    String lastname = sc.nextLine();
    System.out.println("is admin? (true/false)");
    boolean admin = sc.nextBoolean();

    if currentCashier.isAdmin()
      store.addCashier(PW,firstname,lastname,admin);
    else
      System.out.println("Insufficient privileges");
  }
  @Override
  public void addCashier(){
    System.out.println("enter password");
    String PW = sc.nextLine();

    if currentCashier.isAdmin()
      store.addCashier(PW);
    else
      System.out.println("Insufficient privileges");
  }

    removeCashier(int ID){
      if currentCashier.isAdmin(){
        store.removeCashier(ID);
      }
    }

  public String report(){
    if currentCashier.isAdmin(){
      String report;
      System.out.println("Inventory, CashierX, CashierZ");
      String entry = sc.nextLine;
      Switch (entry){
        case "Inventory": report = inventoryReport();
        case "CashierX": {
          System.out.println("Enter cashier ID");
          int entry = sc.nextInt();
          report = cashierXReport(entry);
        }
        case "CashierZ": report = cashierZReport();
      }
    } else {report = "Insufficient privileges"};
  }

  public String inventoryReport(){
    return "";
  }

  public String CashierReportX(int ID){
    Cashier c = store.getCashier(ID);
    String s = "";
    s+=c.getID()+" | "+c.getFirstName()+" | "+c.getLastName()+"\n";
    ArrayList<Event> events = c.getShift().getEvents();
    for (int i=0;i<events.size();i++){
      s+=events[i]+"\n";
    }
  }

  public String CashierReportZ(){
    String s = "";
    SortedList cashiers = store.getCashiers();
    int[] IDs = cashiers.getIDs();
    for (int i : IDs){
      s+=CashierReportX(i);
    }
  }
}
