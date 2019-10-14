package com.abc;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-6;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    /*@Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }*/

    /*@Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);
        //System.out.println("saving account amount of interested should paid: " + bank.totalInterestPaid());
        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }*/

    @Test
    public void maxi_savings_account() {
      Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, -10);
        //System.out.println(cal1.getTime());
        checkingAccount.deposit(3000.0,cal1.getTime());
        Calendar cal2 = Calendar.getInstance();
        checkingAccount.withdraw(100.0,cal2.getTime());
        assertEquals(30.03536063075663, bank.totalInterestPaid(), DOUBLE_DELTA);
        
        Bank bank2 = new Bank();
        Account checkingAccount2 = new Account(Account.MAXI_SAVINGS);
        bank2.addCustomer(new Customer("Bill2").openAccount(checkingAccount2));
        
        Calendar cal3 = Calendar.getInstance();
        cal3.add(Calendar.DATE, -10);
        //System.out.println(cal1.getTime());
        checkingAccount2.deposit(3000.0,cal3.getTime());
        Calendar cal4 = Calendar.getInstance();
        cal4.add(Calendar.DATE, 1);
        checkingAccount2.withdraw(100.0,cal4.getTime());

        assertEquals(181.5421286622945, bank2.totalInterestPaid(), DOUBLE_DELTA);
        
        Bank bank3 = new Bank();
        Account checkingAccount3 = new Account(Account.MAXI_SAVINGS);
        bank3.addCustomer(new Customer("Bill3").openAccount(checkingAccount3));
        
        Calendar cal5 = Calendar.getInstance();
        cal5.add(Calendar.DATE, -15);
        //System.out.println(cal1.getTime());
        checkingAccount3.deposit(3000.0,cal5.getTime());
        Calendar cal6 = Calendar.getInstance();
        cal6.add(Calendar.DATE, -10);
        checkingAccount3.deposit(100.0,cal6.getTime());
        Calendar cal7 = Calendar.getInstance();
        cal7.add(Calendar.DATE, -9);
        checkingAccount3.deposit(100.0,cal7.getTime());
      //18.345160045018
//27.60925337887835
        //188.98971604782224
        assertEquals(1125.841932943753, bank3.totalInterestPaid(), DOUBLE_DELTA);
        
        Bank bank4 = new Bank();
        Account checkingAccount4 = new Account(Account.MAXI_SAVINGS);
        bank4.addCustomer(new Customer("Bill4").openAccount(checkingAccount4));
        
        Calendar cal8 = Calendar.getInstance();
        cal8.add(Calendar.DATE, -15);
        //System.out.println(cal1.getTime());
        checkingAccount4.deposit(3000.0,cal8.getTime());
        Calendar cal9 = Calendar.getInstance();
        cal9.add(Calendar.DATE, -10);
        checkingAccount4.deposit(100.0,cal9.getTime());
        Calendar cal10 = Calendar.getInstance();
        cal10.add(Calendar.DATE, -9);
        checkingAccount4.deposit(100.0,cal10.getTime());
        
        Calendar cal11 = Calendar.getInstance();
        cal11.add(Calendar.DATE, -3);
        checkingAccount4.withdraw(100.0,cal11.getTime());
//362.339744655115
        
        assertEquals(369.26788648416994, bank4.totalInterestPaid(), DOUBLE_DELTA);
        
        
        Bank bank5 = new Bank();
        Account checkingAccount5 = new Account(Account.MAXI_SAVINGS);
        bank5.addCustomer(new Customer("Bill5").openAccount(checkingAccount5));
        
        Calendar cal14 = Calendar.getInstance();
        cal14.add(Calendar.DATE, -15);
        //System.out.println(cal1.getTime());
        checkingAccount5.deposit(3000.0,cal14.getTime());
        Calendar cal12 = Calendar.getInstance();
        cal12.add(Calendar.DATE, -10);
        checkingAccount5.deposit(100.0,cal12.getTime());
        Calendar cal13 = Calendar.getInstance();
        cal13.add(Calendar.DATE, -2);
        checkingAccount5.deposit(100.0,cal13.getTime());
        Calendar cal15 = Calendar.getInstance();
        cal15.add(Calendar.DATE, -1);
        checkingAccount5.deposit(100.0,cal15.getTime());
//525.598661942724
        assertEquals(906.9600247918532, bank5.totalInterestPaid(), DOUBLE_DELTA);
        
    }

}
