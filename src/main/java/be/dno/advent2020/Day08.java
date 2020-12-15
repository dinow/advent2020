package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import be.dno.Day;
public class Day08 implements Day {

	private int acc = 0;

	@Override
	public void run(String fileName) throws IOException {
		List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
		Day08Instruction[] instructions = new Day08Instruction[contents.size()];
		for(int i = 0; i < instructions.length; i++) {
			instructions[i] = new Day08Instruction(contents.get(i));
		}
		int pointer = 0;
		int prev_poiner;
		while (instructions[pointer] != null) {
			prev_poiner = pointer;
			pointer = newPointerPosition(instructions[pointer], pointer, false);
			instructions[prev_poiner] = null;
		}
		System.out.println("part1 : "+ acc);

		for(int i = 0; i < instructions.length; i++) {
			instructions[i] = new Day08Instruction(contents.get(i));
		}
		pointer = 0;
		acc = 0;

		boolean endedGracely = false;
		int instructchangedPos = 0;
		while (endedGracely == false) {
			for(int i = instructchangedPos; i < instructions.length; i++) {
				if (instructions[i].instruction.equals("nop")) {
					System.out.println("Change instruction ["+instructions[i]+"] to jmp");
					instructions[i].instruction = "jmp";
					endedGracely = doesnEndGracely(instructions);
					if (endedGracely) {
						System.out.println("part2 : "+ acc);
						i = instructions.length;
					} else {
						//infinite loop
						instructions[i].instruction = "nop";
						i = instructions.length;
						instructchangedPos++;
					}
				} else if (instructions[i].instruction.equals("jmp")) {
					System.out.println("Change instruction ["+instructions[i]+"] to nop");
					instructions[i].instruction = "nop";
					endedGracely = doesnEndGracely(instructions);
					if (endedGracely) {
						System.out.println("part2 : "+ acc);
						i = instructions.length;
					} else {
						//infinite loop
						instructions[i].instruction = "jmp";
						i = instructions.length;
						instructchangedPos++;
					}
				}
			}

		}

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
