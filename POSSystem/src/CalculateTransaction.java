import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CalculateTransaction {
	public static final BigDecimal SALES_TAX_RATE = new BigDecimal("0.07875");
	private BigDecimal subtotal = new BigDecimal("0.00");
	private BigDecimal total = new BigDecimal("0.00");
	DecimalFormat df = new DecimalFormat("$###,###.00");
	
	public CalculateTransaction() {
		subtotal = new BigDecimal("0.00");
		total = new BigDecimal("0.00");
		
	}
	
	public BigDecimal addTax() {
		BigDecimal salesTax = subtotal.multiply(SALES_TAX_RATE);
		salesTax = salesTax.setScale(2, RoundingMode.HALF_UP);
		return salesTax;
	}
	
	public BigDecimal calculateSubtotal(ArrayList<Product> cart) {
		BigDecimal sum = null;
		for (int i= 0; i < cart.size(); i++) {
			sum = sum.add(new BigDecimal(cart.get(i).getPrice()));
		}
		sum = sum.setScale(2, RoundingMode.HALF_UP);
		subtotal = sum;
		return subtotal;
	}
	
	
	public BigDecimal calculateTotal(ArrayList<Product> cart) {
		calculateSubtotal(cart);
		total = subtotal.add(addTax());	
		total = total.setScale(2, RoundingMode.HALF_UP);
		return total;
	}
	
}
