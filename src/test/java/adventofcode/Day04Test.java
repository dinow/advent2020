package adventofcode;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import be.dno.advent2020.Day04;

public class Day04Test {
	public static void main(String[] args) throws Exception {
		//new Day03().run("day03.txt");
		new Day04().run("day04.txt");
	}
	@Test
	public void test() {
		try {
			new Day04().run("day04.txt");
		} catch (IOException e) {
			assertTrue(false);
		}
	}

}
