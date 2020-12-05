package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class Day04 implements Day{

	@Override
	public void run(String fileName) throws IOException {
		List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
		int[] seats = new int[contents.size()];
		int i = 0;
		for(String line : contents) {
			double current = computeLine(line);
			seats[i] = (int) current;
			i++;
		}
		Arrays.sort(seats);
		System.out.println("Part 1 : "+seats[seats.length-1]);
		for (int j = 0; j < seats.length-1; j++) {
			if (seats[j+1] - seats[j] > 1) {
				System.out.println("Part 2 : "+(seats[j]+1));
			}
		}
	}

	private double computeLine(String line) {
		double seatId = 0;
		double row = 0;
		double column = 0;
		double min = 0, max = 127;
		for (int i = 0; i < 7; i++) {
			double middle = ((max-min) / 2);
			if (line.charAt(i) == 'F') {
				max = min + Math.floor(middle);
				
			} else if (line.charAt(i) == 'B') {
				min += Math.ceil(middle);
			}
		}
		row = min;
		min = 0;
		max = 7;
		for (int i = 7; i < 10; i++) {
			double middle = ((max-min) / 2);
			if (line.charAt(i) == 'L') {
				max = min + Math.floor(middle);
				
			} else if (line.charAt(i) == 'R') {
				min += Math.ceil(middle);
			}
		}
		column = Math.max(min,  max);
		seatId = (row * 8) + column;
		System.out.println(line+" -> " + seatId);
		return seatId;
	}

}
