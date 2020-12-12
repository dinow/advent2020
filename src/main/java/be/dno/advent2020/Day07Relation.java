package be.dno.advent2020;

import java.util.HashMap;
import java.util.Map;

public class Day07Relation {
	public String bagName = null;
	public Map<String, Integer> subBags = new HashMap<>();
	public int getBagCount() {
		int sum = 0;
		for (Integer val : subBags.values()) {
			sum += val;
		}
		System.out.println(bagName+" contains "+sum+" bags");
		return sum;
	}
}
