public class Store implements Reports(){
  SortedList(Inventory) inv;
  SortedList(Cashier) cashiers;
  SortedList(Transaction) transactions
  ArrayList(Register) registers;


  public void addCashier(String PW, String firstname, String lastname, Boolean admin){
    cashiers.add(new Cashier(PW,firstname,lastname,admin));
  }

  public void addCashier(String PW){
    cashiers.add(new Cashier(PW));
  }

  public void removeCashier(int ID){
    cashiers.remove(ID);
  }

  public Cashier getCashier(int ID){
    cashiers.get(ID);
  }

  public Transaction getTransaction(int ID){
    transactions.get(ID);
  }

  public Product getProduct(int UPC){
    inv.get(UPC);
  }

  public void addProduct(Product p){
    inv.add(p);
  }

  public void addTransaction(Transaction t){
    transactions.add(t);
  }

  public void addRegister(Register r){
    registers.add(r);
  }

}
