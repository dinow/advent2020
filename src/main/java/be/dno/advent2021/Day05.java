package be.dno.advent2021;

import java.util.HashMap;
import java.util.Map;
import java.awt.Point;

import be.dno.Day;
import be.dno.Utils;

public class Day05 extends Day {

	Map<Point, Integer> matrix = new HashMap<>();

	public Day05(){
		fileName = "2021/day05.txt";
	}


	@Override
	public String processPart1() {	
		int cpt = 0;
		for (String line : lines){
			Integer[] values = Utils.extractNumbers(line);
			if (values[0].equals(values[2]) || values[1].equals(values[3])){
				fillLine(values, true);
			}
		}
		for (Integer value : matrix.values()){
			if (value >= 2){
				cpt++;
			}
		}
		return cpt+""; 
	}

	private void processCoord(int x, int y){
		Point coord = new Point(x, y);
		Integer value = matrix.get(coord);
		if (value == null){
			value = 0;
		}
		value++;
		matrix.put(coord, value);
	}

	private void fillLine(Integer[] values, boolean skipDiags){
			
		if (values[0].equals(values[2])){
			//couple 1,1 -> 1,3, iterate from values[1] to values [3]
			int startY = Math.min(values[1], values[3]);
			int endY   = Math.max(values[1], values[3]);
			for (int y = startY; y <= endY; y++){
				processCoord(values[0], y);
			}
		}else if (values[1].equals(values[3])){
			//couple 9,7 -> 7,7, iterate from values[0] to values [2]
			int startX = Math.min(values[0], values[2]);
			int endX   = Math.max(values[0], values[2]);
			for (int x = startX; x <= endX; x++){
				processCoord(x, values[1]);
			}
		}else if (!skipDiags){
			//An entry like 1,1 -> 3,3 covers points 1,1, 2,2, and 3,3.
			//An entry like 9,7 -> 7,9 covers points 9,7, 8,8, and 7,9.
			Point nextPoint = new Point(values[0], values[1]);
			while (nextPoint != null){
				processCoord((int)nextPoint.getX(), (int)nextPoint.getY());
				nextPoint = getNextPoint((int)nextPoint.getX(), (int)nextPoint.getY(), values[2], values[3]);
			}
		}
	}
	
	private Point getNextPoint(int x, int y, int targetX, int targetY){
		int nextX = x;
		int nextY = y;
		if (x < targetX) nextX = x+1;
		if (x > targetX) nextX = x-1;
		if (y < targetY) nextY = y+1;
		if (y > targetY) nextY = y-1;
		if (nextX == x && nextY == y) return null;
		return new Point(nextX, nextY);
	}

	@Override  
   	public String processPart2(){
		matrix.clear();
		int cpt = 0;
		for (String line : lines){
			fillLine(Utils.extractNumbers(line), false);
		}
		for (Integer value : matrix.values()){
			if (value >= 2){
				cpt++;
			}
		}
		return cpt+""; 
	}
}