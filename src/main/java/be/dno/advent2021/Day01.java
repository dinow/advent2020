package be.dno.advent2021;


import be.dno.Day;

public class Day01 extends Day {

	public Day01(){
		fileName = "2021/day01.txt";
	 }

	@Override
	public String processPart1() {
		int numberOfIncrease = 0;
		Integer previousNumber = null;
		for(String line : lines){
			Integer lineValue = Integer.valueOf(line);
			if (previousNumber != null && lineValue > previousNumber){
				numberOfIncrease++;
			}
			previousNumber = lineValue;
		}
		return numberOfIncrease+"";
	}
	   
	@Override  
   	public String processPart2(){
		int numberOfIncrease = 0;
		Integer[] slidingWindow = new Integer[3];
		int indexSlidingWindow = 0;
		Integer previousSlidingSum = null;
		Integer currentSlidingSum = null;
		for(String line : lines){
			Integer lineValue = Integer.valueOf(line);
			slidingWindow[indexSlidingWindow] = lineValue;
			indexSlidingWindow++;
			if (indexSlidingWindow == 3){
				currentSlidingSum = slidingWindow[0]+slidingWindow[1]+slidingWindow[2];
				if (previousSlidingSum != null && currentSlidingSum > previousSlidingSum){
					numberOfIncrease++;
				}
				previousSlidingSum = currentSlidingSum;
				indexSlidingWindow=2;
				slidingWindow[0] = slidingWindow[1];
				slidingWindow[1] = slidingWindow[2];
			}
		}
		return numberOfIncrease+"";
	}
}
