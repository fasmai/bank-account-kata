package com.talan.kata.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.MockedStatic;

import com.talan.kata.bank.model.BankAccount;



public class BankAccountTest{

	/*
	 * � US 1:
			In order to save money
			As a bank client
			I want to make a deposit in my account
	 */
	@Test
    @DisplayName("Check account Sttatement after positive deposite")
	public void checkAccountStatement_AfterPositiveDeposit() throws RuntimeException{

		LocalDateTime date = LocalDateTime.of(2011, 1, 8, 19, 35, 30);

		BigDecimal amount = new BigDecimal("1000");
	    BigDecimal balance = new BigDecimal("2000");
	    
	    BankAccount ba = new BankAccount(1L, 1L, date, amount, balance);
	    
	    ba.deposite(new BigDecimal("4000"));
		ba.deposite(new BigDecimal("2000"));
		
		BigDecimal bd1 = new BigDecimal (8000);

		//assertTrue(bd1.compareTo(ba.getBalance()) == 0); 
		//assertThat(bd1).isEqualTo(ba.getBalance());
		
	   /* BigDecimal actual = new BigDecimal("8.0");
	    assertThat(actual).isEqualByComparingTo(new BigDecimal("8.00"));
	    assertThat(actual).isEqualByComparingTo("8.00");*/

	}
	
	@Test
    @DisplayName("Check account Sttatement after negative deposite")
	public void checkAccountStatement_AfternNgativeeDeposit(){

		LocalDateTime date = LocalDateTime.of(2011, 1, 8, 19, 35, 30);

		BigDecimal amount = new BigDecimal("1000");
	    BigDecimal balance = new BigDecimal("2000");
	    
	    BankAccount ba = new BankAccount(1L, 1L, date, amount, balance);
		
	    Exception exception = assertThrows(RuntimeException.class, () -> {
		    ba.deposite(new BigDecimal("-4000"));
	    });

	    String expectedMessage = "You can't deposite a negatif amount !";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	
	  /*
	   * � US 2:			
			In order to retrieve some or all of my savings
			As a bank client			
			I want to make a withdrawal from my account
	   */
	  @Test
      @DisplayName("Check Account Statement After normal withdraw")
	  public void checkAccountStatement_AfterNormalWithdraw() throws RuntimeException{
			
			LocalDateTime date = LocalDateTime.of(2011, 1, 8, 19, 35, 30);

			BigDecimal amount = new BigDecimal("1000");
		    BigDecimal balance = new BigDecimal("8000");
		    
		    BankAccount ba = new BankAccount(1L, 1L, date, amount, balance);

		    ba.withdraw(new BigDecimal("4000"));
			ba.withdraw(new BigDecimal("2000"));
			
			BigDecimal bd1 = new BigDecimal (2000);

			assertTrue(bd1.compareTo(ba.getBalance()) == 0);

	  }
	  
		@Test
	    @DisplayName("Check Account Statement After abnormal withdraw")
		public void checkAccountStatement_AfterAbnormalWithdraw(){

			LocalDateTime date = LocalDateTime.of(2011, 1, 8, 19, 35, 30);

			BigDecimal amount = new BigDecimal("1000");
		    BigDecimal balance = new BigDecimal("2000");
		    
		    BankAccount ba = new BankAccount(1L, 1L, date, amount, balance);
			
		    Exception exception = assertThrows(RuntimeException.class, () -> {
			    ba.withdraw(new BigDecimal("4000"));
		    });

		    String expectedMessage = "Your amount order is greater than your balance !";
		    String actualMessage = exception.getMessage();

		    assertTrue(actualMessage.contains(expectedMessage));
		}
		
	  /*
	   * � US 3:
			In order to check my operations
			As a bank client
			I want to see the history (operation, date, amount, balance) of my operations
	   */
	  @Test
      @DisplayName("Check Account Statement History")
	  public void checkAccountStatementHistory() throws RuntimeException {
		  
		  	LocalDateTime date1 = LocalDateTime.of(2011, 1, 8, 19, 35, 30);
		  	LocalDateTime date2 = LocalDateTime.of(2012, 1, 8, 19, 35, 30);
		  	LocalDateTime date3 = LocalDateTime.of(2013, 1, 8, 19, 35, 30);
		  	LocalDateTime date4 = LocalDateTime.of(2014, 1, 8, 19, 35, 30);
		  	LocalDateTime date5 = LocalDateTime.of(2015, 1, 8, 19, 35, 30);

		    BigDecimal amount = new BigDecimal("10000");
		    BigDecimal balance = new BigDecimal("20000");

		    BankAccount ba = new BankAccount(1L, 1L, date1, amount, balance);
		    
		    ba.withdraw(new BigDecimal("4000"));
		    ba.deposite(new BigDecimal("5000"));
		    ba.withdraw(new BigDecimal("3000"));
		    ba.deposite(new BigDecimal("5000"));
		    ba.withdraw(new BigDecimal("3000"));

		    try (MockedStatic<LocalDateTime> mocked = mockStatic(LocalDateTime.class, withSettings().defaultAnswer(Answers.CALLS_REAL_METHODS))) {

		    	LocalDateTime mockLocalDateTime = mock(LocalDateTime.class);
		        when(mockLocalDateTime.getMinute()).thenReturn(35);
		        
		        mocked.when(LocalDateTime::now).thenReturn(mockLocalDateTime);
		        
		        LocalDateTime result = LocalDateTime.now();
		        assertNotEquals(result, date1);
		        assertNotEquals(result, date2);
		        assertNotEquals(result, date3);
		        assertNotEquals(result, date4);
		        assertNotEquals(result, date5);
		    }

	        assertEquals(ba.printStatement().size(), 5);

	  }
}