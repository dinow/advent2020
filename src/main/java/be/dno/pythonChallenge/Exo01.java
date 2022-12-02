package be.dno.pythonChallenge;

import java.math.BigDecimal;

import be.dno.Day;

public class Exo01 extends Day {

	public Exo01(){
		
	}

	

	@Override
	public String processPart1() {
		return new BigDecimal(Math.pow(2, 38)).toPlainString();
	}
	   
	
}
