package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import be.dno.Day;
public class Day06 implements Day{
	private List<String> contents;
	private final Set<String> groupSet = new HashSet<>();
	private final Set<String> overallSet  = new HashSet<>();
	private final String alphabet = "abcdefghijklmnopqrstuvwxyz";

	@Override
	public void fillDataStruct(String fileName) throws IOException {
		contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
		overallSet.addAll(Arrays.asList(alphabet.split("")));
	}

	@Override
	public String processPart1() {
		int cptPart1 = 0;
		for(String line : contents) {
			if (line.isEmpty()){
				cptPart1 += groupSet.size();
				groupSet.clear();
			} else {
				groupSet.addAll(Arrays.asList(line.split("")));
			}
			
		}
		return cptPart1+"";
	}

	@Override
	public String processPart2() {
		int cptPart2 = 0;
		for(String line : contents) {
			if (line.isEmpty()){
				cptPart2 += overallSet.size();
				overallSet.clear();
				overallSet.addAll(Arrays.asList(alphabet.split("")));
			} else {
				overallSet.retainAll(Arrays.asList(line.split("")));
			}
			
		}
		return cptPart2+"";
	}

}
