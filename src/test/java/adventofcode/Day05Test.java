package adventofcode;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import be.dno.advent2020.Day05;

public class Day05Test {
	public static void main(String[] args) throws Exception {
		//new Day03().run("day03.txt");
		new Day05().run("day05.txt");
	}
	@Test
	public void test() {
		try {
			new Day05().run("day05.txt");
		} catch (IOException e) {
			assertTrue(false);
		}
	}

}
