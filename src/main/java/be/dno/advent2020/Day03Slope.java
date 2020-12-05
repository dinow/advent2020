package be.dno.advent2020;

public class Day03Slope {
	public int down;
	public int right;
	public int trees = 0;
	public Day03Slope(int down, int right) {
		super();
		this.down = down;
		this.right = right;
	}
	public void addTree() {
		this.trees = this.trees + 1;
	}
}
