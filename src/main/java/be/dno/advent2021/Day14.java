package be.dno.advent2021;

import java.util.HashMap;
import java.util.Map;

import be.dno.Day;

public class Day14 extends Day {
	String  template;
	Map<String, String> instructions;

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

	public Long processPolymer(int stepNums){
		Map<String, Long> hits = new HashMap<>();
		Long maxCount = Long.MIN_VALUE;
		Long minCount = Long.MAX_VALUE;

		LinkedElement startElement = null;
		LinkedElement previousElement = null;
		LinkedElement currentElement = null;
		LinkedElement nextElement = null;
		for(String letter: template.split("|")){
			if (startElement == null){
				startElement = new LinkedElement(letter);
				previousElement = startElement;
			} else {
				LinkedElement middleElement = new LinkedElement(letter);
				previousElement.next = middleElement;
				previousElement = middleElement;
			}
		}

		
		for (int step = 0; step < stepNums; step++){
			System.out.println("Processing step "+ step);
			currentElement = startElement;
			nextElement = currentElement.next;
			while(nextElement != null){
				String currentCouple = currentElement.value + nextElement.value;
				String toInsert = instructions.get(currentCouple);
				previousElement = currentElement;
				currentElement = nextElement;
				nextElement = nextElement.next;
				if (toInsert != null){
					LinkedElement newElement = new LinkedElement(toInsert);
					previousElement.next = newElement;
					newElement.next = currentElement;
				}
			}
		}

		nextElement = startElement;
		while(nextElement != null){
			Long cpt = hits.get(nextElement.value);
			if (cpt == null) cpt = Long.valueOf(0);
			cpt++;
			hits.put(nextElement.value, cpt);
			nextElement = nextElement.next;
		}
		for(Long cpt : hits.values()){
			if (maxCount < cpt) maxCount = cpt;
			if (minCount > cpt) minCount = cpt;
		}
		return (maxCount-minCount);
	}

	/*public Long processPolymer(int stepNums){
		Map<String, Long> hits = new HashMap<>();
		StringBuilder working = new StringBuilder();
		Long maxCount = Long.MIN_VALUE;
		Long minCount = Long.MAX_VALUE;
		for (int step = 0; step < stepNums; step++){
			System.out.println("Processing step "+ step);
			working.setLength(0);
			for(int i = 0; i < template.length()-1; i++){
				if (i == 0) working.append(template.charAt(i));
				String currentCouple = template.charAt(i)+""+template.charAt(i+1);
				String toInsert = instructions.get(currentCouple);
				if (toInsert == null) toInsert = "";
				working.append(toInsert).append(template.charAt(i+1));
			}

			template = working.toString();
		}
		for(String letter: template.split("|")){
			Long cpt = hits.get(letter);
			if (cpt == null) cpt = Long.valueOf(0);
			cpt++;
			
			hits.put(letter, cpt);
		}
		for(Long cpt : hits.values()){
			if (maxCount < cpt) maxCount = cpt;
			if (minCount > cpt) minCount = cpt;
		}
		return (maxCount-minCount);
	}*/

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