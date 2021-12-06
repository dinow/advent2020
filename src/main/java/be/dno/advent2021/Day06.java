package be.dno.advent2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.text.Utilities;

import be.dno.Day;
import be.dno.Utils;

public class Day06 extends Day {
	List<Integer> fishes;
	public Day06(){
		fileName = "2021/day06.txt";
	}

	@Override
	public void fillDataStruct() {
		fishes = Utils.extractNumbersList(lines.get(0));
	}

	public Long smartTank(int limit){
		Long[] numberCounter = new Long[9];
		for (int i = 0; i < numberCounter.length; i++){
            numberCounter[i] = Long.valueOf(0);
        }

		for (Integer fish : fishes){
			numberCounter[fish.intValue()]++;
		}

		for (int day = 0; day < limit; day++){
			Long newFishesToCreate = numberCounter[0];
			for (int i = 0; i < numberCounter.length-1; i++){
				numberCounter[i] = numberCounter[i+1];
			}
			numberCounter[8] = newFishesToCreate;
			numberCounter[6] += newFishesToCreate;
		}

		Long total = Long.valueOf(0);
		for (int i = 0; i < numberCounter.length; i++){
            total += numberCounter[i];
        }
		return total;
	}

	@Override
	public String processPart1() {	
		return smartTank(80)+""; 
	}

	@Override  
   	public String processPart2(){
		return smartTank(256)+""; 
	}
}