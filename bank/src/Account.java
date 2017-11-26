import java.text.DecimalFormat;

public abstract class Account {
	
	protected String firstName;
	protected String lastName;
	protected Double balance;
	protected Double interestRate;
	DecimalFormat rounding = new DecimalFormat("#0.00");
	


}
