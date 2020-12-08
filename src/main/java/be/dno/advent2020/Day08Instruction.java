package be.dno.advent2020;

public class Day08Instruction {
	public String instruction = null;
	public int value;
	
	public Day08Instruction(String line) {
		this.instruction = line.substring(0,  3);
		this.value = Integer.valueOf(line.split(" ")[1], 10);
	}

	@Override
	public String toString() {
		return "[instruction=" + instruction + ", value=" + value + "]";
	}
	
	
}
