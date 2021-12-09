package be.dno.advent2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.awt.Point;

import be.dno.Day;

public class Day09 extends Day {

	HeightPoint[][] heightMap;
	List<Point> lowPoints = new ArrayList<>();
	public Day09(){
		fileName = "2021/day09.txt";
	}

	@Override
	public void fillDataStruct() {
		heightMap = new HeightPoint[lines.size()][lines.get(0).length()];
		int i = 0;
		for(String line : lines){
			int j = 0;
			for(String item : line.split("|")){
				heightMap[i][j] = new HeightPoint(item);
				j++;
			}
			i++;
		}
	}

	@Override
	public String processPart1() {
		int sum = 0;
		for (int i = 0; i < heightMap.length; i++){
			for (int j = 0; j < heightMap[i].length; j++){
				if (isLowestPoint(i, j)){
					lowPoints.add(new Point(i, j));
					sum += heightMap[i][j].getValue() + 1;
				}
			}
		}
		return sum+""; 
	}

	private boolean isLowestPoint(int i, int j) {
		Integer current = heightMap[i][j].getValue();
		Integer north = current + 1;
		Integer south = current + 1;
		Integer east  = current + 1;
		Integer west  = current + 1;
		if (i - 1 >= 0){
			north = heightMap[i-1][j].getValue();
		}
		if (i + 1 < heightMap.length){
			south = heightMap[i+1][j].getValue();
		}
		if (j - 1 >= 0){
			west = heightMap[i][j-1].getValue();
		}
		if (j + 1 < heightMap[i].length){
			east = heightMap[i][j+1].getValue();
		}

		return current < north && current < south && current < east && current < west;
	}

	@Override  
   	public String processPart2(){
		List<Integer> bassins = new ArrayList<>();

		for (Point lowPoint : lowPoints){
			Integer countItems = exploreBassin((int)lowPoint.getX(), (int)lowPoint.getY());
			bassins.add(countItems);
		}

		Collections.sort(bassins, Collections.reverseOrder());
		return (bassins.get(0) * bassins.get(1) * bassins.get(2))+""; 
	}

	private Integer exploreBassin(int i, int j) {
		//recursively count to every cardinal direction
		Integer size = startCount(i, j, 0);
	
		//System.out.println("Explored bassin from ["+i+"]["+j+"] == " + size);
		return size;
	}

	private Integer startCount(int i, int j, int currentBassinSize) {
		if (i < 0 || i >= heightMap.length){
			return currentBassinSize;
		}
		if (j < 0 || j >= heightMap[i].length) {
			return currentBassinSize;
		}
		Boolean isPartOfBassin = heightMap[i][j].isPartOfBassin();
		Boolean isNine = heightMap[i][j].getValue().intValue() == 9;

		if (!isPartOfBassin && !isNine){
			currentBassinSize++;
			heightMap[i][j].flagAsPartOfBassin();
			currentBassinSize = startCount(i-1, j  , currentBassinSize);
			currentBassinSize = startCount(i+1, j  , currentBassinSize);
			currentBassinSize = startCount(i  , j-1, currentBassinSize);
			currentBassinSize = startCount(i  , j+1, currentBassinSize);
		}
		
		return currentBassinSize;
	}
}

class HeightPoint {
	Integer value;
	Boolean partOfBassin;

	public HeightPoint(String val){
		this.value = Integer.valueOf(val);
		this.partOfBassin = false;
	}

	public Integer getValue(){
		return this.value;
	}

	public boolean isPartOfBassin(){
		return this.partOfBassin;
	}

	public void flagAsPartOfBassin(){
		this.partOfBassin = true;
	}
}
