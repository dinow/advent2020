package be.dno.advent2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import be.dno.Day;

public class Day10 extends Day {

	private Set<String>         openings  = new HashSet<>();
	private Set<String>         closings  = new HashSet<>();
	private Set<String>         processed = new HashSet<>();
	private Map<String, String> mappings  = new HashMap<>();

	public Day10(){
		fileName = "2021/day10.txt";
	}

	@Override
	public void fillDataStruct() {
		openings.add("(");
		openings.add("[");
		openings.add("{");
		openings.add("<");
		closings.add(")");
		closings.add("]");
		closings.add("}");
		closings.add(">");
		mappings.put(")", "(");
		mappings.put("}", "{");
		mappings.put("]", "[");
		mappings.put(">", "<");
		mappings.put("(", ")");
		mappings.put("[", "]");
		mappings.put("{", "}");
		mappings.put("<", ">");
	}


	@Override
	public String processPart1() {
		int syntaxErrorCode = 0;
		for (String line : lines){
			Stack<String> stack = new Stack<>();
			for(String c : line.split("|")){
				if (stack.isEmpty() || openings.contains(c)){
					stack.add(c);
				} else if (closings.contains(c)) {
					if(stack.lastElement().equals(mappings.get(c))){
						stack.pop();
					}else{
						//error
						//System.out.println(line + " - Expected " + stack.lastElement() + ", but found " + c+ " instead.");
						if (c.equals(")")) syntaxErrorCode+=3;
						if (c.equals("]")) syntaxErrorCode+=57;
						if (c.equals("}")) syntaxErrorCode+=1197;
						if (c.equals(">")) syntaxErrorCode+=25137;
						processed.add(line);
						break;
					}
				}

			}
			if (stack.isEmpty()) processed.add(line);
			

		}
		return syntaxErrorCode+""; 
	}

	@Override  
   	public String processPart2(){
		
		List<Long> scores = new ArrayList<>();
		for (String line : lines){
			if (processed.contains(line)) continue;
			Stack<String> stack = new Stack<>();
			for(String c : line.split("|")){
				if (stack.isEmpty() || openings.contains(c)){
					stack.add(c);
				} else if (closings.contains(c)) {
					if(stack.lastElement().equals(mappings.get(c))){
						stack.pop();
					}
				}
			}
			if (!stack.isEmpty()){
				Long score = 0l;
				while(!stack.isEmpty()){
					String c = stack.pop();
					if (c.equals("(")) score = (score*5) + 1;
					if (c.equals("[")) score = (score*5) + 2;
					if (c.equals("{")) score = (score*5) + 3;
					if (c.equals("<")) score = (score*5) + 4;
				}
				scores.add(score);
			}
			

		}
		Collections.sort(scores);
		return scores.get((scores.size()/2))+""; 
	}
}
