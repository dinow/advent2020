package be.dno.advent2021;

import java.util.HashMap;
import java.util.Map;

import be.dno.Day;

public class Day14 extends Day {
	String  template;
	Map<String, String> instructions;
	Map<String, Long> coupleHits;
	Map<String, Long> letterHits;

	public Day14(){
		fileName = "2021/day14.txt";
	}

	@Override
	public void fillDataStruct(){
		template = lines.get(0);
		instructions = new HashMap<>();
		for (String line : lines){
			String[] instr = line.split(" -> ");
			if (instr.length == 2) instructions.put(instr[0], instr[1]);
		}
	}
	
	public void addInMap(Map<String, Long> map, String key, Long value) {
		Long cpt = map.get(key);
		if (cpt == null) cpt = 0l;
		cpt+=value;
		map.put(key, cpt);
	}
	
	public Long processPolymer(int stepNums){
		
		Long maxCount = Long.MIN_VALUE;
		Long minCount = Long.MAX_VALUE;
		letterHits = new HashMap<>();
		coupleHits = new HashMap<>();
		//Fill with initial template
		for (int i = 0; i < template.length()-1; i++) {
			addInMap(coupleHits, String.valueOf(template.charAt(i)) + String.valueOf(template.charAt(i+1)), 1l);		
		}
		//Count letters of initial template
		for(String letter: template.split("|")){
			addInMap(letterHits, letter, 1l);
		}
		
		for (int step = 0; step < stepNums; step++){
			Map<String, Long> newCouples = new HashMap<>();
			
			//Go through all couples of current polymer
			for (String currentCouple : coupleHits.keySet()) {
				
				//Get number of instance of this couple in the polymer
				Long howManyInstances = coupleHits.get(currentCouple);
				
				//Check if there is a mapping for this couple
				if (instructions.containsKey(currentCouple)) {
					
					//Get the letter to Add
					String letterToAdd = instructions.get(currentCouple);
					
					//Build the two new string (e.g. CC->N == CN, NC)
					String n1 = currentCouple.charAt(0)+letterToAdd;
					String n2 = letterToAdd+currentCouple.charAt(1);
					addInMap(newCouples, n1, howManyInstances);
					addInMap(newCouples, n2, howManyInstances);
					
					//From CC to CNC there is one N more, count it
					addInMap(letterHits, letterToAdd, howManyInstances);
				} else {
					
					//No mapping, save the currents couple in the newCouple map
					addInMap(newCouples, currentCouple, howManyInstances);
				}
			}
			coupleHits = new HashMap<>(newCouples);
		}
		
		for(Long cpt : letterHits.values()){
			if (maxCount < cpt) maxCount = cpt;
			if (minCount > cpt) minCount = cpt;
		}
		return (maxCount-minCount);
	}

	@Override
	public String processPart1(){ 
		return processPolymer(10)+"";
	}
	
	@Override
	public String processPart2(){ 
		return processPolymer(40)+"";
	}
}

class LinkedElement {
	String value;
	LinkedElement next;

	public LinkedElement(String l){
		this.value = l;
	}

	public String toString(){
		return value;
	}
}
