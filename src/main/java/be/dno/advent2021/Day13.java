package be.dno.advent2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Point;


import be.dno.Day;
import be.dno.Utils;

public class Day13 extends Day {
	Map<Point, String> matrix = new HashMap<>();
	List<String> instructions = new ArrayList<>();

	public Day13(){
		fileName = "2021/day13.txt";
	}

	@Override
	public void fillDataStruct(){
		boolean isInstruction = false;
		for (String line : lines){
			if (line.isEmpty()){
				isInstruction = true;
			}
			if (!isInstruction){
				int[] coords = Utils.splitCouple(line, ",");
				matrix.put(new Point(coords[0], coords[1]), "#");
			}else if(!line.isEmpty()){
				instructions.add(line.replaceAll("fold along ", ""));
			}
		}
	}

	@Override
	public String processPart1(){ 
		String firstInstruction = instructions.get(0);
		processInstruction(firstInstruction);
		return matrix.size()+"";
	}

	@Override
	public String processPart2(){ 
		for(String instruction : instructions){
			processInstruction(instruction);
		}
		//get max x / max y
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		for (Point point : matrix.keySet()){
			if (point.getX() > maxX) maxX = (int)point.getX();
			if (point.getY() > maxY) maxY = (int)point.getY();
		}
		char[][] display = new char[maxY+1][maxX+1];
		Utils.initMatrix(display, ' ');
		for (Point point : matrix.keySet()){
			display[(int)point.getY()][(int)point.getX()] = '#';
		}
		Utils.printMatrix(display);
		return "";
	}

	private void processInstruction(String firstInstruction) {
		String[] instr = firstInstruction.split("=");
		int position = Integer.valueOf(instr[1]).intValue();
		Map<Point, String> copyOfMatrix = new HashMap<>();
		if (instr[0].equals("y")){
			for (Point point : matrix.keySet()){
				if (point.getY() < position){
					copyOfMatrix.put(new Point(point), "#");
				}
			}
			for (Point point : matrix.keySet()){
				if (point.getY() < position) continue;

				//transpose
				int distance = ((int)point.getY()) - position;
				int newY = position - distance;
				if (newY > -1){
					copyOfMatrix.put(new Point((int)point.getX(), newY), "#");
				}
			}
		}else{
			for (Point point : matrix.keySet()){
				if (point.getX() < position){
					copyOfMatrix.put(new Point(point), "#");
				}
			}
			for (Point point : matrix.keySet()){
				if (point.getX() < position) continue;

				//transpose
				int distance = ((int)point.getX()) - position;
				int newX = position - distance;
				if (newX > -1){
					copyOfMatrix.put(new Point(newX, (int)point.getY()), "#");
				}
			}
		}
		matrix = new HashMap<>(copyOfMatrix);
	}
}
