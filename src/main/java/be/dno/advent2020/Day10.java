package be.dno.advent2020;

import java.util.Arrays;

import be.dno.Day;

public class Day10 extends Day {

	private long[] numbers;

	
	@Override
	public void fillDataStruct() {
		numbers = new long[lines.size()+2];
		for(int i = 0; i < lines.size(); i++) {
			numbers[i] = Long.parseLong(lines.get(i));
		}
		numbers[lines.size()] = 0;
		numbers[lines.size()+1] = Integer.MAX_VALUE;
		Arrays.sort(numbers);
		numbers[numbers.length-1] = numbers[numbers.length-2] + 3;
	}

	@Override
	public String processPart1() {
		int oneDiffs = 0, threeDiffs = 0;
		for (int i = 1; i < numbers.length; i++) {
			long diff = numbers[i] - numbers[i-1];
			if (diff == 1l) oneDiffs++;
			else if (diff == 3l) threeDiffs++;
		}
		return "" + (oneDiffs * threeDiffs);
	}

	@Override
	public String processPart2() {
		return ""+getPathCount();
	}
	
	private long getPathCount() {
        long[] pathCount = new long[numbers.length];
        pathCount[0] = 1;
        for (int i = 1; i < numbers.length; i++) {
            for (int j = i - 3; j < i; j++) {
                if (j >= 0 && numbers[i] - numbers[j] <= 3) {
                    pathCount[i] += pathCount[j];
                }
            }
        }
        return pathCount[pathCount.length - 1];
    }

	
}
