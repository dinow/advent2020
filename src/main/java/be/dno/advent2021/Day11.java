package be.dno.advent2021;

import be.dno.Day;

public class Day11 extends Day {

	private DumboOctopus[][] matrix_1;

	public Day11(){
		fileName = "2021/day11.txt";
	}

	@Override
	public void fillDataStruct() {
		matrix_1 = new DumboOctopus[lines.size()][lines.get(0).length()];
		int i =  0;
		for (String line : lines){
			int j = 0;
			for (String c : line.split("|")){
				matrix_1[i][j] = new DumboOctopus(c);
				j++;
			}
			i++;
		}
	}

	private String bringIt(int partNumber){
		int numberOfFlashes = 0;
		int maxSteps = partNumber == 1 ? 100 : Integer.MAX_VALUE;
		
		for (int step = 0; step < maxSteps; step++){
			//First, the energy level of each octopus increases by 1.
			for(int i = 0; i < matrix_1.length; i++){
				for (int j = 0; j < matrix_1[i].length; j++){
					matrix_1[i][j].increase();
				}
			}
			//Then, any octopus with an energy level greater than 9 flashes. 
			//This increases the energy level of all adjacent octopuses by 1, including octopuses that are diagonally adjacent. 
			//If this causes an octopus to have an energy level greater than 9, it also flashes. 
			//This process continues as long as new octopuses keep having their energy level increased beyond 9. (An octopus can only flash at most once per step.)
			boolean hasFlashed = true;
			while (hasFlashed){
				hasFlashed = false;
				for(int i = 0; i < matrix_1.length; i++){
					for (int j = 0; j < matrix_1[i].length; j++){
						if(matrix_1[i][j].flash()){
							hasFlashed = true;
							numberOfFlashes++;
							increaseAdjacent(i, j);
						}
					}
				}
			}
			
			if (partNumber == 2){
				int cptEnergy0 = 0;
				for(int i = 0; i < matrix_1.length; i++){
					for (int j = 0; j < matrix_1[i].length; j++){
						if(matrix_1[i][j].getEnergyLevel() == 0){
							cptEnergy0++;
						}
					}
				}
				if (cptEnergy0 == 100){
					return ""+(step+1);
				}
			}

			for(int i = 0; i < matrix_1.length; i++){
				for (int j = 0; j < matrix_1[i].length; j++){
					matrix_1[i][j].reset();
				}
			}
		}

		return numberOfFlashes+""; 
	}


	@Override
	public String processPart1() {
		return bringIt(1)+"";
	}

	@Override
	public String processPart2() {
		return bringIt(2)+"";
	}

	private void increaseAdjacent(int i, int j) {
		if (i - 1 > -1) matrix_1[i-1][j].increase();
		if (i + 1 < 10) matrix_1[i+1][j].increase();
		if (j - 1 > -1) matrix_1[i][j-1].increase();
		if (j + 1 < 10) matrix_1[i][j+1].increase();

		if (i - 1 > -1  && j - 1 > -1) matrix_1[i-1][j-1].increase();
		if (i + 1 < 10  && j + 1 < 10) matrix_1[i+1][j+1].increase();
		if (i + 1 < 10  && j - 1 > -1) matrix_1[i+1][j-1].increase();
		if (i - 1 > -1  && j + 1 < 10) matrix_1[i-1][j+1].increase();
	}

}

class DumboOctopus{
	private int energyLevel;
	private boolean flashed;

	public DumboOctopus(String _energy){
		this.flashed = false;
		this.energyLevel = Integer.valueOf(_energy);
	}

	public void increase(){
		if (!this.flashed){
			this.energyLevel++;
		}
	}

	public int getEnergyLevel(){
		return this.energyLevel;
	}

	public boolean flash(){
		if (!this.flashed && this.energyLevel > 9){
			this.flashed = true;
			this.energyLevel = 0;
			return true;
		}
		return false;
	}

	public void reset(){
		this.flashed = false;
	}

	public String toString(){ return this.energyLevel+"";}

}