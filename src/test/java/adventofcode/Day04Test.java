package adventofcode;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import be.dno.advent2020.Day04;

public class Day04Test {

	@Test
	public void test() {
		try {
			new Day04().run("day04.txt");
		} catch (IOException e) {
			assertTrue(false);
		}
	}

}
