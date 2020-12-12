package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;

public class Day06 implements Day{

	@Override
	public void run(String fileName) throws IOException {
		List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
		Set<String> groupSet = new HashSet<>();
		Set<String> overallSet  = new HashSet<>();
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		overallSet.addAll(Arrays.asList(alphabet.split("")));
		int cptPart1 = 0;
		int cptPart2 = 0;
		for(String line : contents) {
			if (line.isEmpty()){
				cptPart1 += groupSet.size();
				cptPart2 += overallSet.size();
				groupSet.clear();
				overallSet.clear();
				overallSet.addAll(Arrays.asList(alphabet.split("")));
			} else {
				groupSet.addAll(Arrays.asList(line.split("")));
				overallSet.retainAll(Arrays.asList(line.split("")));
			}
			
		}
		System.out.println("part1: "+ cptPart1);
		System.out.println("part2: "+ cptPart2);
	}

}
