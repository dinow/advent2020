package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

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
		while (pointer < instructions.length && instructions[pointer] != null) {
			prev_poiner = pointer;
			if (instructions[pointer].instruction.equals("nop")) {
				instructions[pointer].instruction = "jmp";
				if (!doesEndAfter(instructions, pointer)) {
					instructions[pointer].instruction = "nop";
				}
			}
			if (instructions[pointer].instruction.equals("jmp")) {
				instructions[pointer].instruction = "nop";
				if (!doesEndAfter(instructions, pointer)) {
					instructions[pointer].instruction = "jmp";
				}
			}
			pointer = newPointerPosition(instructions[pointer], pointer, false);
			instructions[prev_poiner] = null;
		}
		System.out.println("part2 : "+ acc);
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
	
	private boolean doesEndAfter(Day08Instruction[] instructions, int pointer) {
		Day08Instruction[] copy_instructions = Arrays.copyOf(instructions, instructions.length);
		int newPointer = newPointerPosition(copy_instructions[pointer], pointer, true);
		if (newPointer >= instructions.length || copy_instructions[newPointer] == null) return false;
		newPointer = newPointerPosition(copy_instructions[newPointer], newPointer, true);
		if (newPointer == instructions.length) {
			System.out.println("instruction found ! -> "+instructions[pointer].toString());
			return true;
		} else {
			return false;
		}
	}

	

}
