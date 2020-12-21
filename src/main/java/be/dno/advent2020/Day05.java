package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import be.dno.Day_old;

public class Day05 implements Day_old{

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
		return Integer.parseInt(line.replaceAll("[FL]", "0").replaceAll("[BR]", "1"), 2);
	}

}
