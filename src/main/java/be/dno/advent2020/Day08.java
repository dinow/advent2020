package be.dno.advent2020;

import java.util.Arrays;

import be.dno.Day;

public class Day08 extends Day {

	private int acc = 0;
	Day08Instruction[] instructions;

	@Override
	public void fillDataStruct() {
		instructions = new Day08Instruction[lines.size()];
		for(int i = 0; i < instructions.length; i++) {
			instructions[i] = new Day08Instruction(lines.get(i));
		}
	}

	@Override
	public String processPart1() {
		int pointer = 0;
		int prev_poiner;
		acc = 0;
		while (instructions[pointer] != null) {
			prev_poiner = pointer;
			pointer = newPointerPosition(instructions[pointer], pointer, false);
			instructions[prev_poiner] = null;
		}
		return ""+acc;
	}

	@Override
	public String processPart2() {
		acc = 0;
		boolean endedGracely = false;
		int instructchangedPos = 0;
		while (endedGracely == false) {
			for(int i = instructchangedPos; i < instructions.length; i++) {
				if (instructions[i].instruction.equals("nop")) {
					instructions[i].instruction = "jmp";
					endedGracely = doesnEndGracely(instructions);
					if (endedGracely) {
						return ""+ acc;
					} else {
						//infinite loop
						instructions[i].instruction = "nop";
						i = instructions.length;
						instructchangedPos++;
					}
				} else if (instructions[i].instruction.equals("jmp")) {
					instructions[i].instruction = "nop";
					endedGracely = doesnEndGracely(instructions);
					if (endedGracely) {
						return ""+ acc;
					} else {
						//infinite loop
						instructions[i].instruction = "jmp";
						i = instructions.length;
						instructchangedPos++;
					}
				}
			}
		}
		return null;
	}


	private int newPointerPosition(Day08Instruction instruction, int pointer, boolean fakeInstruction) {
		int newPointer = pointer;
		if (instruction.instruction.equals("nop")) {
			newPointer++;
		} else if (instruction.instruction.equals("acc")) {
			if (!fakeInstruction) {
				acc+= instruction.value;
			}
			newPointer++;
		} else if (instruction.instruction.equals("jmp")) {
			newPointer+=instruction.value;
		}
		return newPointer;
	}

	private boolean doesnEndGracely(Day08Instruction[] instructions) {
		Day08Instruction[] copy_instructions = Arrays.copyOf(instructions, instructions.length);
		int pointer = 0;
		int prev_poiner;
		acc = 0;
		boolean whileCondition = true;
		boolean endedGracely = false;
		while (whileCondition) {
			prev_poiner = pointer;
			pointer = newPointerPosition(copy_instructions[pointer], pointer, false);
			copy_instructions[prev_poiner] = null;
			if (pointer == instructions.length) {
				whileCondition = false;
				endedGracely = true;
				break;
			}
			if (copy_instructions[pointer] == null) {
				whileCondition = false;
				break;
			}
		}
		return endedGracely;
	}



}
