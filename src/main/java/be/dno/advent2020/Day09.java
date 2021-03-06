package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import be.dno.Day;
public class Day09 extends Day {

	private static int WINDOW_SIZE = 25;
	long invalidNumberPart1 = Long.parseLong("0");
	private long[] numbers;
	
	@Override
	public void fillDataStruct() {
		numbers = new long[lines.size()];
		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = Long.parseLong(lines.get(i));
		}
	}

	@Override
	public String processPart1() {
		
		for (int i = WINDOW_SIZE; i < numbers.length; i++) {
			if (!isSumOfTwo(i)) {
				invalidNumberPart1 = numbers[i];
				return ""+ invalidNumberPart1;
			}
		}
		return null;
	}

	@Override
	public String processPart2() {
		for(int i = 0; i < numbers.length; i++) {
			long sum = numbers[i];
			for(int j = i+1; j < numbers.length; j++) {
				sum += numbers[j];
				if (sum == invalidNumberPart1) {
					long[] subArr = Arrays.copyOfRange(numbers, i, j);
					Arrays.sort(subArr);
					return ""+(subArr[0]+ subArr[subArr.length-1]);
				}
			}
		}
		return null;
	}

	public void run(String fileName) throws IOException {
		List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
		numbers = new long[contents.size()];
		long invalidNumberPart1 = Long.parseLong("0");
		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = Long.parseLong(contents.get(i));
		}
		for (int i = WINDOW_SIZE; i < numbers.length; i++) {
			if (!isSumOfTwo(i)) {
				invalidNumberPart1 = numbers[i];
				break;
			}
		}
		System.out.println("Part1: "+ invalidNumberPart1);
		for(int i = 0; i < numbers.length; i++) {
			long sum = numbers[i];
			for(int j = i+1; j < numbers.length; j++) {
				sum += numbers[j];
				if (sum == invalidNumberPart1) {
					long[] subArr = Arrays.copyOfRange(numbers, i, j);
					Arrays.sort(subArr);
					System.out.println(subArr[0]+ subArr[subArr.length-1]);
				}
			}
		}
	}

	private boolean isSumOfTwo(int currentIdx) {
		long currentNumber = numbers[currentIdx];
		for (int i = currentIdx-WINDOW_SIZE; i < currentIdx; i++) {
			for (int j = currentIdx-WINDOW_SIZE; j < currentIdx; j++) {
				if (j == i) continue;
				if (numbers[i] + numbers[j] == currentNumber) return true;
			}
		}
		return false;
	}
	
	


}
