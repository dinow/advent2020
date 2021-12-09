package be.dno.advent2021;

import be.dno.Day;
import be.dno.Utils;

public class Day07 extends Day {
	
	public Day07(){
		fileName = "2021/day07.txt";
	}

	@Override
	public void fillDataStruct() {
		
	}

	@Override
	public String processPart1() {
		Integer[] crabs = Utils.extractNumbers(lines.get(0));
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < crabs.length; i++){
			if (min > crabs[i]){
				min = crabs[i];
			}
			if (max < crabs[i]){
				max = crabs[i];
			}
		}
		int minFuel = Integer.MAX_VALUE;
		for (int i = min; i <= max; i++){
			int curFuel = 0;
			for (int j = 0; j < crabs.length; j++){
				int startPos = Math.min(crabs[j], i);
				int endPos   = Math.max(crabs[j], i);
				curFuel += (endPos - startPos);
				if (curFuel > minFuel) break;
			}
			if (minFuel > curFuel){
				minFuel = curFuel;
			}
		}
		return minFuel+""; 
	}

	public int dumbSum(int start, int end){
		int sum = 0;
		for (int i = 1; i <= (end-start); i++){
			sum += i;
		}
		return sum;
	}

	@Override  
   	public String processPart2(){
		Integer[] crabs = Utils.extractNumbers(lines.get(0));
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < crabs.length; i++){
			if (min > crabs[i]){
				min = crabs[i];
			}
			if (max < crabs[i]){
				max = crabs[i];
			}
		}
		int minFuel = Integer.MAX_VALUE;
		for (int i = min; i <= max; i++){
			int curFuel = 0;
			for (int j = 0; j < crabs.length; j++){
				int startPos = Math.min(crabs[j], i);
				int endPos   = Math.max(crabs[j], i);
				curFuel += dumbSum(startPos, endPos);
				if (curFuel > minFuel) break;
			}
			if (minFuel > curFuel){
				minFuel = curFuel;
			}
		}
		return minFuel+""; 
	}
}