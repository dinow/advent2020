package be.dno.advent2021;

import be.dno.Day;
import be.dno.MatrixElement;
import be.dno.Utils;

public class Day11 extends Day {

	private DumboOctopus[][] matrix_1;

	public Day11(){
		fileName = "2021/day11.txt";
	}

	@Override
	public void fillDataStruct() {
		matrix_1 = DumboOctopus.fromGeneric(Utils.getIntMatrix(lines));
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
						if(matrix_1[i][j].getIntValue() == 0){
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
					matrix_1[i][j].unFlag();
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

class DumboOctopus extends MatrixElement{

	public DumboOctopus(int intValue) {
		super(intValue);
	}

	public static DumboOctopus[][] fromGeneric(MatrixElement[][] gen){
		DumboOctopus[][] newMatrix = new DumboOctopus[gen.length][gen[0].length];
		for(int i = 0; i < gen.length; i++){
			for (int j = 0; j < gen[i].length; j++){
				newMatrix[i][j] = new DumboOctopus(gen[i][j].getIntValue());
			}
		}
		return newMatrix;
	}

	public void increase(){
		if (!super.isFlagged()){
			super.addIntValue(1);
		}
	}

	public boolean flash(){
		if (!super.isFlagged() && super.getIntValue() > 9){
			super.flag();
			super.setIntValue(0);
			return true;
		}
		return false;
	}

}