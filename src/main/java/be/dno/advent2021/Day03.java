package be.dno.advent2021;


import java.util.ArrayList;
import java.util.List;

import be.dno.Day;
import be.dno.Utils;

public class Day03 extends Day {
	char[] gammaRate;
	char[] epsilonRate;
	char[] o2GeneratorRating;
	char[] co2ScrubberRating;
	int[] count1;
	int[] count0;

	public Day03(){
		fileName = "2021/day03.txt";
	}

	private int countChar(List<String> inputs, char c, int i){
		int count = 0;
		for(String line : inputs){
			char[] diagLine = line.toCharArray();
			if (diagLine[i] == c){
				count++;
			}
		}
		return count;
	}


	@Override
	public String processPart1() {
		int length = lines.get(0).toCharArray().length;
		gammaRate = new char[length];
		epsilonRate = new char[length];
		count1 = new int[length];
		count0 = new int[length];
		Utils.initArray(count0, 0);
		Utils.initArray(count1, 0);
		for(String line : lines){
			char[] diagLine = line.toCharArray();
			for (int i = 0; i < diagLine.length; i++){
				if (diagLine[i] == '1'){
					count1[i]=count1[i]+1;
				}else{
					count0[i]=count0[i]+1;
				}
			}
		}
		for (int i = 0; i < count0.length; i++){
			if (count0[i]>count1[i]){
				gammaRate[i]='0';
				epsilonRate[i]='1';
			}else{
				gammaRate[i]='1';
				epsilonRate[i]='0';
			}
		}
		return (Integer.parseInt(String.valueOf(gammaRate), 2) * Integer.parseInt(String.valueOf(epsilonRate), 2))+""; //4006064
	}
	   
	@Override  
   	public String processPart2(){
		String o2GeneratorRating;
		String co2ScrubberRating;
		int length = lines.get(0).toCharArray().length;

		
		List<String> okList = List.copyOf(lines);
		for (int i = 0; i < length; i++){
			int cpt1 = countChar(okList, '1', i);
			int cpt0 = countChar(okList, '0', i);
			char toKeep = cpt1 >= cpt0 ? '1' : '0';
			//filterout okList1
			List<String> newList = new ArrayList<>();
			for(String itemInOkList : okList){
				if (itemInOkList.toCharArray()[i] == toKeep){
					newList.add(itemInOkList);
				}
			}
			okList = List.copyOf(newList);
			if (okList.size() == 1) break;
		}
		o2GeneratorRating = okList.iterator().next();
		System.out.println("o2GeneratorRating: "+o2GeneratorRating);


		okList = List.copyOf(lines);
		for (int i = 0; i < length; i++){
			int cpt1 = countChar(okList, '1', i);
			int cpt0 = countChar(okList, '0', i);
			char toKeep = cpt0 <= cpt1 ? '0' : '1';
			//filterout okList1
			List<String> newList = new ArrayList<>();
			for(String itemInOkList : okList){
				if (itemInOkList.toCharArray()[i] == toKeep){
					newList.add(itemInOkList);
				}
			}
			okList = List.copyOf(newList);
			if (okList.size() == 1) break;
		}
		co2ScrubberRating = okList.iterator().next();
		System.out.println("co2ScrubberRating: "+co2ScrubberRating);
		
		return (Integer.parseInt(o2GeneratorRating, 2) * Integer.parseInt(co2ScrubberRating, 2))+""; //5941884
	}
}
