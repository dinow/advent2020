package be.dno.advent2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import be.dno.Day;

public class Day08 extends Day {
	
	public Day08(){
		fileName = "2021/day08.txt";
	}

	@Override
	public String processPart1() {
		int sum = 0;
		for (String line : lines){
			String[] lineSplit = line.split(" \\| ");
			String signalPattern = lineSplit[0];
			String outPutValue = lineSplit[1];
			Map<String, Integer> digits = processPattern(signalPattern, false);
			List<Integer> outputDigits = getOutputDigits(digits, outPutValue);
			for (Integer outDi : outputDigits){
				if (outDi.intValue() == 1 || outDi.intValue() == 4 || outDi.intValue() == 7 || outDi.intValue() == 8){
					sum++;
				}
			}
		}
		return sum+""; 
	}

	private List<Integer> getOutputDigits(Map<String, Integer> digits, String outPutValue) {
		List<Integer> result = new ArrayList<>();
		for (String outVal : outPutValue.split(" ")){
			Integer val = digits.get(sort(outVal));
			if (val != null){
				result.add(val);
			}
		}
		return result;
	}

	private Integer getOutputDigit(Map<String, Integer> digits, String outPutValue) {
		String result = "";
		for (String outVal : outPutValue.split(" ")){
			Integer val = digits.get(sort(outVal));
			if (val != null){
				result += ""+val;
			}
		}
		return Integer.valueOf(result);
	}

	private String sort(String input){
		char[] charArray = input.toCharArray();
		Arrays.sort(charArray);
		return new String(charArray);
	}

	private Map<String, Integer> processPattern(String signalPattern, boolean extendSearch) {
		Map<String, Integer> patToDigit = new HashMap<>();
		Map<Integer, String> digitToPat = new HashMap<>();
		String[] patterns = signalPattern.split(" ");
		//do the easy part
		Set<String> processed = new HashSet<>();
		for (String pattern : patterns){
			Integer mapped = null;
			if (pattern.length() == 2){
				//we have found the 1
				mapped = Integer.valueOf(1);
			} else if (pattern.length() == 3){
				//we have found the 7
				mapped = Integer.valueOf(7);
			} else if (pattern.length() == 4){
				//we have found the 4
				mapped = Integer.valueOf(4);
			} else if (pattern.length() == 7){
				//we have found the 8
				mapped = Integer.valueOf(8);
			}
			if (mapped != null){
				//System.out.println(pattern + " -> " + mapped);
				patToDigit.put(sort(pattern), mapped);
				digitToPat.put(mapped, pattern);
				processed.add(pattern);
			}
		}

		if (extendSearch){
			//3, 6 from 1 (and path to digits)
			String patternRef = digitToPat.get(Integer.valueOf(1));
			Map<String, Integer> uglyMappings = new HashMap<>();
			uglyMappings.put("302", Integer.valueOf(3));
			uglyMappings.put("511", Integer.valueOf(6));
			for (String pattern : patterns){
				Integer mapped = getMapping(processed, pattern, patternRef,  uglyMappings);
				if (mapped != null){
					patToDigit.put(sort(pattern), mapped);
					digitToPat.put(mapped, pattern);
					processed.add(pattern);
				}
			}

			//9, 0 from 3 (and path to digits)
			patternRef = digitToPat.get(Integer.valueOf(3));
			uglyMappings = new HashMap<>();
			uglyMappings.put("214", Integer.valueOf(0));
			uglyMappings.put("105", Integer.valueOf(9));
			for (String pattern : patterns){
				Integer mapped = getMapping(processed, pattern, patternRef,  uglyMappings);
				if (mapped != null){
					patToDigit.put(sort(pattern), mapped);
					digitToPat.put(mapped, pattern);
					processed.add(pattern);
				}
			}

			//2, 5 from 9 (and path to digits)
			patternRef = digitToPat.get(Integer.valueOf(9));
			uglyMappings = new HashMap<>();
			uglyMappings.put("124", Integer.valueOf(2));
			uglyMappings.put("015", Integer.valueOf(5));
			for (String pattern : patterns){
				Integer mapped = getMapping(processed, pattern, patternRef,  uglyMappings);
				if (mapped != null){
					patToDigit.put(sort(pattern), mapped);
					digitToPat.put(mapped, pattern);
					processed.add(pattern);
				}
			}

		}
		return patToDigit;
	}

	private Integer getMapping(Set<String> processed, String pattern, String patternRef, Map<String, Integer> uglyMappings){
		Integer mapped = null;
		if (!processed.contains(pattern)){
			String uniqueId = ""+ countAdded(patternRef, pattern) + countRemoved(patternRef, pattern) + countIdentical(patternRef, pattern)  + "";
			if (uglyMappings.containsKey(uniqueId)){
				mapped = uglyMappings.get(uniqueId);
			}
		}
		return mapped;
	}

	private int countAdded(String str1, String str2){
		int cpt = 0;
		for (String c : str2.split("|")){
			if (str1.indexOf(c) == -1){
				cpt++;
			}
		}
		return cpt;
	}

	private int countRemoved(String str1, String str2){
		int cpt = 0;
		for (String c : str1.split("|")){
			if (str2.indexOf(c) == -1){
				cpt++;
			}
		}
		return cpt;
	}

	private int countIdentical(String str1, String str2){
		int cpt = 0;
		for (String c : str1.split("|")){
			if (str2.indexOf(c) > -1){
				cpt++;
			}
		}
		return cpt;
	}

	@Override  
   	public String processPart2(){
		int sum = 0;
		for (String line : lines){
			String[] lineSplit = line.split(" \\| ");
			String signalPattern = lineSplit[0];
			String outPutValue = lineSplit[1];
			Map<String, Integer> digits = processPattern(signalPattern, true);
			Integer outputDigits = getOutputDigit(digits, outPutValue);
			//System.out.println(line + " -> " + outputDigits);
			sum += outputDigits;
		}
		return sum+""; 
	}
}