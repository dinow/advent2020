package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class Day11 implements Day{

	private static char[][] SEATS;
	private static char[][] WORKING_SEATS;


	private enum VERTICAL_DIRECTION {
		NORTH, SOUTH, NONE;
    }
	
	private enum HORIZONTAL_DIRECTION {
		EAST, WEST, NONE;
    }

	@Override
	public void run(String fileName) throws IOException {
		List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
		SEATS = new char[contents.size()][contents.get(1).length()];
		WORKING_SEATS = new char[contents.size()][contents.get(1).length()];
		for(int i = 0; i < SEATS.length; i++) {
			String line = contents.get(i);
			for (int j = 0; j < SEATS[i].length; j++) {
				SEATS[i][j] = line.charAt(j);
			}
		}

		boolean alive = true;
		while(alive) {
			copyArray(0);
			alive = playLife(true, 4);
			copyArray(1);
		}
		System.out.println("Part 1 : " + countChars('#'));

		for(int i = 0; i < SEATS.length; i++) {
			String line = contents.get(i);
			for (int j = 0; j < SEATS[i].length; j++) {
				SEATS[i][j] = line.charAt(j);
			}
		}
		alive = true;
		while(alive) {
			copyArray(0);
			alive = playLife(false, 5);
			copyArray(1);
		}
		System.out.println("Part 2 : " + countChars('#'));
	}




	private void copyArray(int mode) {
		for(int i = 0; i < SEATS.length; i++) {
			for (int j = 0; j < SEATS[i].length; j++) {
				if (mode == 0) {
					WORKING_SEATS[i][j] = SEATS[i][j];
				} else {
					SEATS[i][j] = WORKING_SEATS[i][j];
				}
			}
		}
	}




	private boolean playLife(boolean adgacentMode, int occupThreshold) {
		boolean changeMade = false;
		for(int i = 0; i < SEATS.length; i++) {
			for (int j = 0; j < SEATS[i].length; j++) {
				changeMade |= processSeat(i, j, adgacentMode, occupThreshold);
			}
		}
		if (changeMade) {
			//printSeats();
		}
		return changeMade;
	}


	/**
	 * 
    	If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
    	If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
    	Otherwise, the seat's state does not change.
		Floor (.) never changes; seats don't move, and nobody sits on the floor.
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean processSeat(int i, int j, boolean adgacentMode, int occupThreshold) {
		char seat = SEATS[i][j];
		if (seat == '.') return false;
		int occupied = countInSight(i, j, adgacentMode);
		if (seat == 'L' && occupied == 0) {
			WORKING_SEATS[i][j] = '#';
			return true;
		}
		if (seat == '#' && occupied >= occupThreshold) {
			WORKING_SEATS[i][j] = 'L';
			return true;
		}
		return false;
	}

	private int countInSight(int i, int j, boolean adgacentMode) {
		int occupied = 0;
		for (VERTICAL_DIRECTION vertical : VERTICAL_DIRECTION.values()) {
			for (HORIZONTAL_DIRECTION horizontal : HORIZONTAL_DIRECTION.values()) {
				occupied += count(i, j, adgacentMode, vertical, horizontal);
			}
		}
		return occupied;
	}
	
	private int count(int i, int j, boolean adgacentMode, VERTICAL_DIRECTION vertical, HORIZONTAL_DIRECTION horizontal) {
		if (vertical.equals(VERTICAL_DIRECTION.NONE) && horizontal.equals(HORIZONTAL_DIRECTION.NONE)) return 0;
		int i1 = i, j1 = j;
		if (vertical.equals(VERTICAL_DIRECTION.NORTH)) i1 = i-1;
		if (vertical.equals(VERTICAL_DIRECTION.SOUTH)) i1 = i+1;
		if (horizontal.equals(HORIZONTAL_DIRECTION.EAST)) j1 = j+1;
		if (horizontal.equals(HORIZONTAL_DIRECTION.WEST)) j1 = j-1;
	
		while(indexesInBounds(i1, j1)) {
			if (SEATS[i1][j1] == '#') return 1;
			if (SEATS[i1][j1] == 'L') return 0;
			if (adgacentMode) {
				return 0;
			}
			if (vertical.equals(VERTICAL_DIRECTION.NORTH)) i1--;
			if (vertical.equals(VERTICAL_DIRECTION.SOUTH)) i1++;
			if (horizontal.equals(HORIZONTAL_DIRECTION.EAST)) j1++;
			if (horizontal.equals( HORIZONTAL_DIRECTION.WEST)) j1--;
		}
		return 0;
	}


	private boolean indexesInBounds(int i, int j) {
		if ( i < 0 || j < 0 ) return false;
		if ( i >= SEATS.length || j >= SEATS[0].length ) return false;
		return true;
	}


	private int countChars(char c) {
		int cpt = 0;
		for(int i = 0; i < SEATS.length; i++) {
			for (int j = 0; j < SEATS[i].length; j++) {
				if (SEATS[i][j] == c) cpt++;
			}
		}
		return cpt;
	}



	public void printSeats() {
		System.out.print("\n");
		for(int i = 0; i < WORKING_SEATS.length; i++) {
			for (int j = 0; j < WORKING_SEATS[i].length; j++) {
				System.out.print(WORKING_SEATS[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

}
