package firsttest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.talan.kata.bank.business.Order;
import com.talan.kata.bank.model.Client;
import com.talan.kata.bank.service.impl.BankAccount;

public class BankAccountTest{

	SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

	/*
	 * � US 1:
			In order to save money
			As a bank client
			I want to make a deposit in my account
	 */
	@Test
	public void checkAccountStatement_AfterDeposit() throws ParseException {

		Date date1 = ft.parse("2010-11-11 10:10:10");
	    Date date2 = ft.parse("2011-11-11 10:10:10");
	    Date date3 = ft.parse("2012-11-11 10:10:10");

	    // no need for Clinet class
		Client client1 = new Client(1);

	    BankAccount ba = new BankAccount(1, client1, date1, 10000.0, 2000);

		Order order1 = new Order(1, "Deposite", date2, 4000);
		Order order2 = new Order(2, "Deposite", date3, 3000);


		ba.order(order1);
		ba.order(order2);
		
		assertEquals(9000, ba.getBalance());
	}
	
	  /*
	   * � US 2:			
			In order to retrieve some or all of my savings
			As a bank client			
			I want to make a withdrawal from my account
	   */
	  @Test
	  public void checkAccountStatement_AfterWithdrawal() throws ParseException {
			
			Date date1 = ft.parse("2010-11-11 10:10:10");
		    Date date2 = ft.parse("2011-11-11 10:10:10");
		    Date date3 = ft.parse("2012-11-11 10:10:10");
		    
			Client client = new Client(1);

		    BankAccount ba = new BankAccount(1, client, date1, 10000.0, 20000);

			Order order1 = new Order(1, "Withdraw", date2, 4000);
			Order order2 = new Order(2, "Withdraw", date3, 3000);


			ba.order(order1);
			ba.order(order2);
			
			assertEquals(13000, ba.getBalance());

	  }
	  
	  /*
	   * � US 3:
			In order to check my operations
			As a bank client
			I want to see the history (operation, date, amount, balance) of my operations
	   */
	  @Test
	  public void checkAccountStatementHistory() throws ParseException {
		  
			Date date1 = ft.parse("2010-11-11 10:10:10");
		    Date date2 = ft.parse("2011-11-11 10:10:10");
		    Date date3 = ft.parse("2012-11-11 10:10:10");
		    Date date4 = ft.parse("2013-11-11 10:10:10");
		    Date date5 = ft.parse("2014-11-11 10:10:10");
		    
			Client client = new Client(1);

		    BankAccount ba = new BankAccount(1, client, date1, 10000.0, 20000);

			Order order1 = new Order(1, "Withdraw", date2, 4000);
			Order order2 = new Order(2, "Deposite", date3, 4000);
			Order order3 = new Order(3, "Withdraw", date4, 3000);
			Order order4 = new Order(2, "Deposite", date5, 5000);


			ba.order(order1);
			ba.order(order2);
			ba.order(order3);
			ba.order(order4);
			
			assertNotNull(ba.getHistory());


	  }
}