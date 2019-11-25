import java.text.DecimalFormat;

public class Money {
	public static final double SALES_TAX = 0.07875;
	private double subtotal;
	private double total;
	DecimalFormat df = new DecimalFormat("$###,###.00");
	
	public Money() {
		subtotal = 0;
		total = 0;
	}
	
	public double addTax(double subtotal) {
		return subtotal * SALES_TAX;
	}
	
	/**
	public double calculateSubtotal(ArrayList<Product> cart) {
		double sum;
		for (int i; i < cart.size(); i++) {
			sum += cart.get(i).getPrice();
		}
		subtotal = sum;
		return subtotal;
	}
	*/
	
	/**
	public double calculateTotal(ArrayList<Product> cart) {
		calculateSubtotal(cart);
		addTax(subtotal);
		total = subtotal + addTax(subtotal);	
		return total;
	}
	*/
}
