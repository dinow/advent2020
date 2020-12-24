package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

import be.dno.Day;
public class Day10 implements Day {

	private long[] numbers;

	
	@Override
	public void fillDataStruct(String fileName) throws IOException {
		List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
		numbers = new long[contents.size()+2];
		for(int i = 0; i < contents.size(); i++) {
			numbers[i] = Long.parseLong(contents.get(i));
		}
		numbers[contents.size()] = 0;
		numbers[contents.size()+1] = Integer.MAX_VALUE;
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
