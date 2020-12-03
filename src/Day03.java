package dno.advent;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class Day03 {
	public void run() throws Exception {
		List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream("day03.txt"), Charset.forName("UTF-8"));
		List<Day03Slope> slopes = new ArrayList<>();
		slopes.add(new Day03Slope(1, 1));
		slopes.add(new Day03Slope(1, 3));
		slopes.add(new Day03Slope(1, 5));
		slopes.add(new Day03Slope(1, 7));
		slopes.add(new Day03Slope(2, 1));
		int rows = contents.size();
		int cols = contents.get(0).length();
		System.out.println("Rows : " + rows + ", cols : " + cols);
		long cptTrees = 1;
		for (int i = 0; i < slopes.size(); i++) {
			char[][] playground = new char[rows][cols];

			for (int x = 0; x < rows; x++) {
				for (int y = 0; y < cols; y++) {
					playground[x][y] = contents.get(x).charAt(y);
				}
			}



			int posX = 0, posY = 0;
			

			while (posY <= rows) {
				posY += slopes.get(i).down;
				if (posY < rows) {
					posX += slopes.get(i).right;
					if (posX - cols >= 0) {
						posX = posX - cols;
					}
					playground[posY][posX] = 'O';
					if (contents.get(posY).charAt(posX) == '#') {
						slopes.get(i).addTree();
					}
				}

			}

			System.out.print("\n"+slopes.get(i).trees);
			cptTrees *= slopes.get(i).trees;
		}
		System.out.print("\n\n"+cptTrees);
	}
}
