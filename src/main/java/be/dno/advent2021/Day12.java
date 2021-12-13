package be.dno.advent2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import be.dno.Day;

public class Day12 extends Day {

	private Map<String, Set<String>> caves;
	private Map<String, Integer> caveVisits;

	private Integer pathCount = 0;
	


	public Day12(){
		fileName = "2021/day12.txt";
	}

	private void addCouple(String _start, String _end){
		Set<String> start = caves.get(_start);
		if (start == null) start = new HashSet<>();
		start.add(_end);
		caves.put(_start, start);
	
		Set<String> end = caves.get(_end);
		if (end == null) end = new HashSet<>();
		end.add(_start);
		caves.put(_end, end);
	}

	@Override
	public void fillDataStruct(){
		pathCount   = 0;
		caveVisits  = new HashMap<>();
		caves 	    = new HashMap<>();
		for (String line : lines){
			String[] data = line.split("-");
			addCouple(data[0], data[1]);
			addCouple(data[1], data[0]);
		}
	}

	@Override
	public String processPart1(){ 
		findPaths("start", "end", new LinkedList<>(), 1);
		return pathCount+"";
	}

	@Override
	public String processPart2(){ 
		findPaths("start", "end", new LinkedList<>(), 2);
		return pathCount+"";
	}

	private void findPaths(String from, String to, List<String> visited, int mode) {
        visited.add(from);
		Integer count = caveVisits.get(from);
		if (count == null) count = 0;
		count++;
		caveVisits.put(from, count);
        if (from.equals(to)) {
            pathCount++;
            return;
        } else {
            for (String conn : caves.get(from)) {
                if (!canVisit(conn, visited, mode)) {
                    continue;
                }
                List<String> currentPath = new LinkedList<>(visited);
                findPaths(conn, to, visited, mode);
                visited = new LinkedList<>(currentPath);
            }
        }
        return;
    }

	private boolean canVisit(String conn, List<String> visited, int mode) {
        if (conn.equals("start")) { return false; }
        if (conn.toUpperCase().equals(conn) || !visited.contains(conn)) { return true; }

		if (mode == 1) return false;

		List<String> smallVisited = new ArrayList<>();
		Set<String>  distinctVisited = new HashSet<>();
		for (String v : visited){
			if (v.toLowerCase().equals(v)){
				smallVisited.add(v);
				distinctVisited.add(v);
			}
		}
		return smallVisited.size() == distinctVisited.size();



        
    }
}
