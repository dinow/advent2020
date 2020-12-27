package be.dno.advent2020;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import be.dno.Day;

public class Day06 extends Day{
	private final Set<String> groupSet = new HashSet<>();
	private final Set<String> overallSet  = new HashSet<>();
	private final String alphabet = "abcdefghijklmnopqrstuvwxyz";

	@Override
	public void fillDataStruct() {
		overallSet.addAll(Arrays.asList(alphabet.split("")));
	}

	@Override
	public String processPart1() {
		int cptPart1 = 0;
		for(String line : lines) {
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
		for(String line : lines) {
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
