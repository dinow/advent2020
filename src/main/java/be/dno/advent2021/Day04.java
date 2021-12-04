package be.dno.advent2021;


import java.util.ArrayList;
import java.util.List;

import be.dno.Day;
import be.dno.Utils;

public class Day04 extends Day {
	private List<BingoGrid> grids;
	private Integer[] numbers;
	public Day04(){
		fileName = "2021/day04.txt";
	}

	@Override
	public void fillDataStruct() {
		int lineIndex = 0;
		List<String> currentGrid = new ArrayList<>();
		grids = new ArrayList<>();
		for(String line : lines){
			if (lineIndex == 0){
				numbers = Utils.extractNumbers(line);
			}else if (!line.isEmpty()){
				currentGrid.add(line);
			}
			if (currentGrid.size() == 5){
				BingoGrid bingo = new BingoGrid();
				bingo.init(currentGrid);
				grids.add(bingo);
				currentGrid.clear();
			}
			lineIndex++;
		}
	}

	@Override
	public String processPart1() {
		int score = 0;
		for (int i = 0; i < numbers.length; i++){
			for (BingoGrid grid : grids){
				grid.play(numbers[i]);
				if (grid.hasWon()){
					score = grid.computeScore(numbers[i]);
					i = numbers.length;
					break;
				}
			}
		}
		return score+""; 
	}
	   
	@Override  
   	public String processPart2(){
		int score = 0;
		for (int i = 0; i < numbers.length; i++){
			for (BingoGrid grid : grids){
				if (!grid.hasAlreadyWon()){
					grid.play(numbers[i]);
					if (grid.hasWon()){
						score = grid.computeScore(numbers[i]);
					}
				}
			}
		}
		return score+""; 
	}
}

class GridEntry{
	private int value;
	private boolean flagged;

	public GridEntry(int _value){
		this.value = _value;
		this.flagged = false;
	}

	public int getValue() { return this.value;}
	public boolean isFlagged(){ return this.flagged; }
	public void flag(int _value){ 
		if (this.value == _value){
			this.flagged = true;
		}  
	}
}

class BingoGrid {
	private GridEntry[][] grid;
	private boolean alreadyWon = false;

	public BingoGrid(){
		grid = new GridEntry[5][5];
	}

	public void play(int number){
		for (int i = 0; i < this.grid.length; i++){
			for (int j = 0; j < this.grid[i].length; j++){
				this.grid[i][j].flag(number);
			}
		}
	}

	public boolean hasAlreadyWon() { return this.alreadyWon; }

	public boolean hasWon(){
		//find if we hava a full row flagged
		for (int i = 0; i < this.grid.length; i++){
			int nbFlagged = 0;
			for (int j = 0; j < this.grid[i].length; j++){
				if (this.grid[i][j].isFlagged()){
					nbFlagged++;
				}else{
					break; //no need to investigate more
				}
			}
			if (nbFlagged == this.grid[i].length){
				this.alreadyWon = true;
				return true;
			}
		}

		//find if we hava a flagged column
		for (int i = 0; i < this.grid.length; i++){
			int nbFlagged = 0;
			for (int j = 0; j < this.grid[i].length; j++){
				if (this.grid[j][i].isFlagged()){
					nbFlagged++;
				}else{
					break; //no need to investigate more
				}
			}
			if (nbFlagged == this.grid[i].length){
				this.alreadyWon = true;
				return true;
			}
		}

		return false;
	}

	public int computeScore(int lastNumber){
		int score = 0;
		for (int i = 0; i < this.grid.length; i++){
			for (int j = 0; j < this.grid[i].length; j++){
				if (!this.grid[i][j].isFlagged()){
					score += this.grid[i][j].getValue();
				}
			}
		}
		System.out.println("Grid has won with ["+lastNumber+"], score is ["+score+"]");
		return score * lastNumber;
	}

	public void init(List<String> fiveLines){
		int lineIdx = 0;
		for (String line : fiveLines){
			Integer[] columns = Utils.extractNumbers(line);
			for (int j = 0; j < columns.length; j++){
				this.grid[lineIdx][j] = new GridEntry(columns[j]);
			}
			lineIdx++;
		}
	}
}