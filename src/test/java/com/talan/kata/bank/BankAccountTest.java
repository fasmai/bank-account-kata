package com.talan.kata.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import com.talan.kata.bank.model.BankAccount;

public class BankAccountTest {

	/*
	 * � US 1: In order to save money As a bank client I want to make a deposit in
	 * my account
	 */
	@Test
	@DisplayName("Check account Statement after positive deposite")
	public void checkAccountStatement_AfterPositiveDeposit() throws RuntimeException {

		// given
		LocalDateTime date = LocalDateTime.of(2011, 1, 8, 19, 35, 30);

		BankAccount bankAccount = new BankAccount(1L, 1L, date, BigDecimal.valueOf(2000));

		try (MockedStatic<LocalDateTime> mocked = mockStatic(LocalDateTime.class)) {

			mocked.when(LocalDateTime::now).thenReturn(date);
			// when
			bankAccount.deposite(BigDecimal.valueOf(4000));
			bankAccount.deposite(BigDecimal.valueOf(2000));
		}

		// then
		assertThat(bankAccount.getHistory()).hasSize(2);

		assertThat(bankAccount.getHistory().get(0).getAmount()).isEqualTo(BigDecimal.valueOf(4000));
		assertThat(bankAccount.getHistory().get(0).getDateOfOrder()).isEqualTo(date);
		assertThat(bankAccount.getHistory().get(0).getBalance()).isEqualTo(BigDecimal.valueOf(6000));

		assertThat(bankAccount.getHistory().get(1).getAmount()).isEqualTo(BigDecimal.valueOf(2000));
		assertThat(bankAccount.getHistory().get(1).getDateOfOrder()).isEqualTo(date);
		assertThat(bankAccount.getHistory().get(1).getBalance()).isEqualTo(BigDecimal.valueOf(8000));

		assertThat(bankAccount.getHistory()).allSatisfy(order -> {
			assertThat(order.getClientId()).isEqualTo(1L);
		});

		assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(8000));

	}

	@Test
	@DisplayName("Check account Sttatement after negative deposite")
	public void checkAccountStatement_AfternNgativeeDeposit() {

		LocalDateTime date = LocalDateTime.now();

		BankAccount bankAccount = new BankAccount(1L, 1L, date, BigDecimal.valueOf(2000));

		Exception exception = assertThrows(RuntimeException.class, () -> {
			bankAccount.deposite(BigDecimal.valueOf(-4000));
		});

		String expectedMessage = "You can't deposite a negatif amount !";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

		assertThat(bankAccount.getHistory()).hasSize(0);

		assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(2000));
	}

	/*
	 * � US 2: In order to retrieve some or all of my savings As a bank client I
	 * want to make a withdrawal from my account
	 */
	@Test
	@DisplayName("Check Account Statement After normal withdraw")
	public void checkAccountStatement_AfterNormalWithdraw() throws RuntimeException {

		LocalDateTime date = LocalDateTime.of(2011, 1, 8, 19, 35, 30);

		BankAccount bankAccount = new BankAccount(1L, 1L, date, BigDecimal.valueOf(8000));

		try (MockedStatic<LocalDateTime> mocked = mockStatic(LocalDateTime.class)) {

			mocked.when(LocalDateTime::now).thenReturn(date);

			bankAccount.withdraw(BigDecimal.valueOf(4000));
			bankAccount.withdraw(BigDecimal.valueOf(2000));
		}

		assertThat(bankAccount.getHistory()).hasSize(2);

		assertThat(bankAccount.getHistory().get(0).getAmount()).isEqualTo(BigDecimal.valueOf(4000));
		assertThat(bankAccount.getHistory().get(0).getDateOfOrder()).isEqualTo(date);
		assertThat(bankAccount.getHistory().get(0).getBalance()).isEqualTo(BigDecimal.valueOf(4000));

		assertThat(bankAccount.getHistory().get(1).getAmount()).isEqualTo(BigDecimal.valueOf(2000));
		assertThat(bankAccount.getHistory().get(1).getDateOfOrder()).isEqualTo(date);
		assertThat(bankAccount.getHistory().get(1).getBalance()).isEqualTo(BigDecimal.valueOf(2000));

		assertThat(bankAccount.getHistory()).allSatisfy(order -> {
			assertThat(order.getClientId()).isEqualTo(1L);
		});

		assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(2000));

	}

	@Test
	@DisplayName("Check Account Statement After abnormal withdraw")
	public void checkAccountStatement_AfterAbnormalWithdraw() {

		LocalDateTime date = LocalDateTime.of(2011, 1, 8, 19, 35, 30);

		BankAccount bankAccount = new BankAccount(1L, 1L, date, BigDecimal.valueOf(2000));

		Exception exception = assertThrows(RuntimeException.class, () -> {
			bankAccount.withdraw(BigDecimal.valueOf(4000));
		});

		String expectedMessage = "Your amount order is greater than your balance !";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

		assertThat(bankAccount.getHistory()).hasSize(0);

		assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(2000));
	}

	/*
	 * � US 3: In order to check my operations As a bank client I want to see the
	 * history (operation, date, amount, balance) of my operations
	 */
	@Test
	@DisplayName("Check Account Statement History")
	public void checkAccountStatementHistory() throws RuntimeException {

		// given
		LocalDateTime firstDate = LocalDateTime.of(2018, 10, 10, 19, 20, 20);
		LocalDateTime secondDate = LocalDateTime.of(2020, 10, 10, 19, 20, 20);
		LocalDateTime thirdDate = LocalDateTime.of(2021, 10, 10, 19, 20, 20);

		BankAccount bankAccount = new BankAccount(1L, 1L, LocalDateTime.now(), BigDecimal.valueOf(20000));

		try (MockedStatic<LocalDateTime> mocked = mockStatic(LocalDateTime.class)) {

			mocked.when(LocalDateTime::now).thenReturn(firstDate).thenReturn(secondDate).thenReturn(thirdDate);
			// when
			bankAccount.withdraw(BigDecimal.valueOf(4000));
			bankAccount.deposite(BigDecimal.valueOf(5000));
			bankAccount.withdraw(BigDecimal.valueOf(3000));
			bankAccount.deposite(BigDecimal.valueOf(8000));
			bankAccount.withdraw(BigDecimal.valueOf(3000));

		}

		// then
		assertThat(bankAccount.getHistory()).hasSize(5);

		assertThat(bankAccount.getHistory().get(0).getAmount()).isEqualTo(BigDecimal.valueOf(4000));
		assertThat(bankAccount.getHistory().get(0).getDateOfOrder()).isEqualTo(firstDate);
		assertThat(bankAccount.getHistory().get(0).getBalance()).isEqualTo(BigDecimal.valueOf(16000));

		assertThat(bankAccount.getHistory().get(1).getAmount()).isEqualTo(BigDecimal.valueOf(5000));
		assertThat(bankAccount.getHistory().get(1).getDateOfOrder()).isEqualTo(secondDate);
		assertThat(bankAccount.getHistory().get(1).getBalance()).isEqualTo(BigDecimal.valueOf(21000));

		assertThat(bankAccount.getHistory().get(2).getAmount()).isEqualTo(BigDecimal.valueOf(3000));
		assertThat(bankAccount.getHistory().get(2).getDateOfOrder()).isEqualTo(thirdDate);
		assertThat(bankAccount.getHistory().get(2).getBalance()).isEqualTo(BigDecimal.valueOf(18000));

		assertThat(bankAccount.getHistory().get(3).getAmount()).isEqualTo(BigDecimal.valueOf(8000));
		assertThat(bankAccount.getHistory().get(3).getDateOfOrder()).isEqualTo(thirdDate);
		assertThat(bankAccount.getHistory().get(3).getBalance()).isEqualTo(BigDecimal.valueOf(26000));

		assertThat(bankAccount.getHistory().get(4).getAmount()).isEqualTo(BigDecimal.valueOf(3000));
		assertThat(bankAccount.getHistory().get(4).getDateOfOrder()).isEqualTo(thirdDate);
		assertThat(bankAccount.getHistory().get(4).getBalance()).isEqualTo(BigDecimal.valueOf(23000));

		assertThat(bankAccount.getHistory()).allSatisfy(order -> {
			assertThat(order.getClientId()).isEqualTo(1L);
		});

		assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(23000));

	}
}