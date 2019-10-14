package com.abc;

public class Calculate {
	public static void main(String[] args) {
		


		double interest=0.0;
		
		double money = 3000.0;
		
		
		for(int i=0;i<4;i++) {
			interest = interest + money *0.001;
			money = money + money *0.001;
		}
		money = money +(100);
		interest = interest + money *0.001;
		money = money + money *0.001;
		for(int i=0;i<4;i++) {
			interest = interest + money *0.001;
			money = money + money *0.001;
		}
		for(int i=0;i<3;i++) {
			interest = interest + money *0.05;
			money = money + money *0.05;
		}
		System.out.println(interest);
		money = money +(100);
		interest = interest + money *0.05;
		money = money + money *0.05;
		
		money = money +(100);
		interest = interest + money *0.05;
		money = money + money *0.05;
		
		
		System.out.println(interest);
	}

}
