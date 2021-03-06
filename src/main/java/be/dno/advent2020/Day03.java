package be.dno.advent2020;

import java.util.ArrayList;
import java.util.List;


import be.dno.Day;

public class Day03 extends Day {
	@Override
	public String processPart1() {
		List<Day03Slope> slopes = new ArrayList<>();
		slopes.add(new Day03Slope(1, 3));
		int rows = lines.size();
		int cols = lines.get(0).length();
		long cptTrees = 1;
		for (int i = 0; i < slopes.size(); i++) {
			char[][] playground = new char[rows][cols];

			for (int x = 0; x < rows; x++) {
				for (int y = 0; y < cols; y++) {
					playground[x][y] = lines.get(x).charAt(y);
				}
			}

			int posX = 0, posY = 0;
			
			while (posY <= rows) {
				posY += slopes.get(i).down;
				if (posY < rows) {
					posX += slopes.get(i).right;
					if (posX - cols >= 0) {
						posX = posX - cols;
					}
					playground[posY][posX] = 'O';
					if (lines.get(posY).charAt(posX) == '#') {
						slopes.get(i).addTree();
					}
				}

			}
			cptTrees *= slopes.get(i).trees;
		}
		return ""+cptTrees;
	}
	   
	@Override  
   	public String processPart2(){
		List<Day03Slope> slopes = new ArrayList<>();
		slopes.add(new Day03Slope(1, 1));
		slopes.add(new Day03Slope(1, 3));
		slopes.add(new Day03Slope(1, 5));
		slopes.add(new Day03Slope(1, 7));
		slopes.add(new Day03Slope(2, 1));
		int rows = lines.size();
		int cols = lines.get(0).length();
		long cptTrees = 1;
		for (int i = 0; i < slopes.size(); i++) {
			char[][] playground = new char[rows][cols];

			for (int x = 0; x < rows; x++) {
				for (int y = 0; y < cols; y++) {
					playground[x][y] = lines.get(x).charAt(y);
				}
			}

			int posX = 0, posY = 0;
			
			while (posY <= rows) {
				posY += slopes.get(i).down;
				if (posY < rows) {
					posX += slopes.get(i).right;
					if (posX - cols >= 0) {
						posX = posX - cols;
					}
					playground[posY][posX] = 'O';
					if (lines.get(posY).charAt(posX) == '#') {
						slopes.get(i).addTree();
					}
				}

			}
			cptTrees *= slopes.get(i).trees;
		}
		return ""+cptTrees;
	}
}
