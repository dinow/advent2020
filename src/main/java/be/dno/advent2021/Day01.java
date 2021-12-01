package be.dno.advent2021;


import be.dno.Day;

public class Day01 extends Day {
	private Integer[] depths;

	public Day01(){
		fileName = "2021/day01.txt";
	 }

	 @Override
	public void fillDataStruct() {
		depths = new Integer[lines.size()];
		int i = 0;
		for(String line : lines) {
			depths[i] = Integer.valueOf(line);
			i++;
		}
	}

	@Override
	public String processPart1() {
		int numberOfIncrease = 0;
		for (int i = 1; i < depths.length; i++){
			if (depths[i] > depths[i-1]){
				numberOfIncrease++;
			}
		}
		return numberOfIncrease+"";
	}
	   
	@Override  
   	public String processPart2(){
		int numberOfIncrease = 0;
		Integer nextSlidingSum = null;
		Integer currentSlidingSum = null;
		currentSlidingSum = depths[0]+depths[1]+depths[2];
		for (int i = 2; i < depths.length-1; i++){
			nextSlidingSum = depths[i-1]+depths[i]+depths[i+1];
			if (nextSlidingSum > currentSlidingSum){
				numberOfIncrease++;
			}
			currentSlidingSum = nextSlidingSum;
		}
		return numberOfIncrease+"";
	}
}
