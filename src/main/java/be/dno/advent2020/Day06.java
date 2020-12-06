package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class Day06 implements Day{

	@Override
	public void run(String fileName) throws IOException {
		List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
		int[] answers = new int[26];
		int cptPart1 = 0;
		int cptPart2 = 0;
		int groupCount = 0;
		for (int i = 0; i < 26; i++){
			answers[i] = 0;
		}

		for(String line : contents) {
			
			if (line.isEmpty()){
				for (int i = 0; i < 26; i++){
					if (answers[i] > 0){
						cptPart1++;
					}
					if (answers[i] == groupCount){
						cptPart2++;
					}
				}
				for (int i = 0; i < 26; i++){
					answers[i] = 0;
				}
				groupCount = 0;
			} else{
				groupCount++;
			}
			for(char c : line.toCharArray()){
				if (c >= 'a' && c <= 'z'){
					answers[((int)c)-97] = answers[((int)c)-97] + 1;
				}
			}
		}
		System.out.println("part1: "+ cptPart1);
		System.out.println("part2: "+ cptPart2);
	}

}
