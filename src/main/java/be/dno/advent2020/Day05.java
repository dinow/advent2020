package be.dno.advent2020;

import java.util.Arrays;
import be.dno.Day;

public class Day05 extends Day{
	private int[] seats;

	private double computeLine(String line) {
		return Integer.parseInt(line.replaceAll("[FL]", "0").replaceAll("[BR]", "1"), 2);
	}

	@Override
	public void fillDataStruct() {
		seats = new int[lines.size()];
		int i = 0;
		for(String line : lines) {
			double current = computeLine(line);
			seats[i] = (int) current;
			i++;
		}
		Arrays.sort(seats);
	}

	@Override
	public String processPart1() {
		return ""+seats[seats.length-1];
	}

	@Override
	public String processPart2() {
		for (int j = 0; j < seats.length-1; j++) {
			if (seats[j+1] - seats[j] > 1) {
				return ""+(seats[j]+1);
			}
		}
		return "null";
	}

}
