package com.abc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount,Date date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount,date));
        }
    }
    
    public void transfer(Account account, double amount) {
    	this.withdraw(amount,DateProvider.getInstance().now());
    	account.deposit(amount,DateProvider.getInstance().now());
    }

public void withdraw(double amount,Date date) {
	boolean check=false;
	double total=sumTransactions();
	
	if(total<amount) {
		check=true;
	}
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } 
    else
    if(check==true){
    	throw new IllegalArgumentException("You don't have enough balance");
    }else{
        transactions.add(new Transaction(-amount,date));
    }
}

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
	        case SAVINGS:
	        	double sbalance = 0.0;
	        	double sinterest =0.0;
	        	Date defa = transactions.get(0).getTransactionDate();
            	for(Transaction t:transactions) {
            		if(defa.compareTo(t.getTransactionDate())==0) {
            			sbalance=sbalance+t.amount;
            		}else {
            			int day= (int)(t.getTransactionDate().getTime()-defa.getTime())/(1000 * 60 * 60 * 24);
	        			for(int i=0;i<day-1;i++) {
		            		if(sbalance<=1000) {
		            			sinterest= sinterest+sbalance*0.001;
		            			sbalance = sbalance+sbalance*0.001;
		            		}else {
		            			sinterest= sinterest+sbalance*0.002;
		            			sbalance = sbalance+sbalance*0.002;
		            		}
	            		}
	            		sbalance = sbalance +t.amount;
	            		if(sbalance<=1000) {
	            			sinterest= sinterest+sbalance*0.001;
	            			sbalance = sbalance+sbalance*0.001;
	            		}else {
	            			sinterest= sinterest+sbalance*0.002;
	            			sbalance = sbalance+sbalance*0.002;
	            		}
            		}
            		defa=t.getTransactionDate();
            	}
            	Calendar cal2 = Calendar.getInstance();
	        	int remaindays2 = (int)(cal2.getTime().getTime()-transactions.get(transactions.size()-1).getTransactionDate().getTime())/(1000 * 60 * 60 * 24);
	        	for(int i=0;i<remaindays2;i++) {
            		if(sbalance<=1000) {
            			sinterest= sinterest+sbalance*0.001;
            			sbalance = sbalance+sbalance*0.001;
            		}else {
            			sinterest= sinterest+sbalance*0.002;
            			sbalance = sbalance+sbalance*0.002;
            		}
        		}
	        	return sinterest;
	        	/*if (amount <= 1000)
	                return amount * 0.001;
	            else
	                return 1 + (amount-1000) * 0.002;*/

	        	
	//      case SUPER_SAVINGS:
	//            if (amount <= 4000)
	//                return 20;
	        case MAXI_SAVINGS:
	        	boolean check=false;
	        	double balance = 0.0;
	        	double interest =0.0;
	        	boolean check2=false;
	        	int recordday=0;
	        	Calendar defaults = Calendar.getInstance();
	        	defaults.setTime(transactions.get(0).getTransactionDate());
	        	for(Transaction t : transactions) {
	        		int difference = (int)(t.getTransactionDate().getTime()-defaults.getTime().getTime())/(1000 * 60 * 60 * 24);
	        		System.out.println(difference);
	        		if(difference==0) {
	        			balance = balance + transactions.get(0).amount;
	        		}else {
	        			
	        			if(t.amount<0) {
	        				if(recordday+difference>10) {
	        					int difference2 = 10 -recordday;
	        					for(int i=0;i<difference2-1;i++) {
		    						interest= interest+balance * 0.001;
			        				balance = balance + balance * 0.001;
		    					}
	        					int difference3 =difference-difference2;
		    					for(int i=0;i<difference3;i++) {
		    						interest= interest+balance * 0.05;
			        				balance = balance + balance * 0.05;
		    					}
		    					balance = balance +t.amount;
			        			interest= interest+balance * 0.001;
			        			balance = balance +balance * 0.001;
	        				}else {
		        				if(difference<=10) {	
		        					//System.out.println(difference);
		        					//System.out.println("run here1");
			    					for(int i=0;i<difference-1;i++) {
			    						interest= interest+balance * 0.001;
				        				balance = balance + balance * 0.001;
			    					}
			    					balance = balance +t.amount;
				        			interest= interest+balance * 0.001;
				        			balance = balance +balance * 0.001;
				        			defaults.setTime(t.getTransactionDate());
			    				}else {
			    					int difference2 = difference-9;
			    					//System.out.println("run here2");
			    					for(int i=0;i<9;i++) {
			    						interest= interest+balance * 0.001;
				        				balance = balance + balance * 0.001;
			    					}			        			
			    					for(int i=0;i<difference2-1;i++) {
			    						interest= interest+balance * 0.05;
				        				balance = balance + balance * 0.05;
			    					}
			    					balance = balance +t.amount;
				        			interest= interest+balance * 0.001;
				        			balance = balance +balance * 0.001;
				        			defaults.setTime(t.getTransactionDate());
			    				}
	        				}
	        				check=false;
	        				check2=false;
	        				recordday=0;	
	        			}else {	
	        				
		    				if(difference+recordday<10) {
		    					//System.out.println("run here3");
		    					for(int i=0;i<difference-1;i++) {
		    						interest= interest+balance * 0.001;
			        				balance = balance + balance * 0.001;
		    					}
		    					balance = balance +t.amount;
			        			interest= interest+balance * 0.001;
			        			balance = balance +balance * 0.001;
			        			recordday=recordday+difference;
			        			defaults.setTime(t.getTransactionDate());
		    				}else {
		    					//System.out.println(t.amount);
		    					if(check2==false) {
		    						System.out.println("run here4");
		    						int difference2 = 10-recordday-1;
			    					for(int i=0;i<difference2;i++) {
			    						interest= interest+balance * 0.001;
				        				balance = balance + balance * 0.001;
			    					}
			    					int difference3 = difference-1-difference2;
			    					for(int i=0;i<difference3;i++) {
			    						interest= interest+balance * 0.05;
				        				balance = balance + balance * 0.05;
			    					}
			    					balance = balance +t.amount;
			    					interest= interest+balance * 0.05;
			        				balance = balance + balance * 0.05;
			        				System.out.println(interest);
			    					check2=true;
		    					}else {
		    						System.out.println("run here5");
			    					balance = balance +t.amount;
			    					for(int i=0;i<difference;i++) {
			    						interest= interest+balance * 0.05;
				        				balance = balance + balance * 0.05;
			    					}
			    					
		    					}
		    					recordday=recordday+difference;
			        			defaults.setTime(t.getTransactionDate());
		    				}				
		    				check=true;
	        			}
	        		}
	        	}
	        	
	        	Calendar cal = Calendar.getInstance();
	        	int remaindays = (int)(cal.getTime().getTime()-transactions.get(transactions.size()-1).getTransactionDate().getTime())/(1000 * 60 * 60 * 24);
	        	//System.out.println(remaindays);
	        	if(remaindays!=0) {
		        	if(check2==true) {
		        		System.out.println("run here 6");
    					for(int i=0;i<remaindays-1;i++) {
    						interest= interest+balance * 0.05;
	        				balance = balance + balance * 0.05;
    					}
		        	}else {
		        		if(recordday+remaindays<10) {
		        			System.out.println("run here 7");
							for(int i=0;i<remaindays-1;i++) {
								interest= interest+balance * 0.001;
		        				balance = balance + balance * 0.001;
							}
		        		}else {
		        			System.out.println("run here 8");
		        			int difference2 = 10-recordday-1;
	    					for(int i=0;i<difference2;i++) {
	    						interest= interest+balance * 0.001;
		        				balance = balance + balance * 0.001;
	    					}
	    					//System.out.println(difference2);
	    					for(int i=0;i<remaindays-difference2;i++) {
	    						interest= interest+balance * 0.05;
		        				balance = balance + balance * 0.05;
	    					}
		        		}
		        	}
	        	}
	        	//System.out.println(interest);
	        	return interest;
	        default:
	        	double balance2 = 0.0;
	        	double interest2 =0.0;
	        	Date defa2 = transactions.get(0).getTransactionDate();
            	for(Transaction t:transactions) {
            		if(defa2.compareTo(t.getTransactionDate())==0) {
            			balance2=balance2+t.amount;
            		}else {
            			int day= (int)(t.getTransactionDate().getTime()-defa2.getTime())/(1000 * 60 * 60 * 24);
	        			for(int i=0;i<day-1;i++) {
	        				interest2= interest2+balance2*0.001;
	            			balance2 = balance2+balance2*0.001;
	            		}
	        			balance2 = balance2 +t.amount;
	        			interest2= interest2+balance2*0.001;
            			balance2 = balance2+balance2*0.001;
            		}
            		defa=t.getTransactionDate();
            	}
            	Calendar cal3 = Calendar.getInstance();
	        	int remaindays3 = (int)(cal3.getTime().getTime()-transactions.get(transactions.size()-1).getTransactionDate().getTime())/(1000 * 60 * 60 * 24);
	        	for(int i=0;i<remaindays3;i++) {
	        		interest2= interest2+balance2*0.001;
        			balance2 = balance2+balance2*0.001;
        		}
	            return balance2;
        }
    }

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions) {
            amount += t.amount;
        }
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
