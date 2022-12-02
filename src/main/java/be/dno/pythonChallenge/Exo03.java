package be.dno.pythonChallenge;

import java.util.HashMap;
import java.util.Map;


import be.dno.Day;

public class Exo03 extends Day {
   /**
    * http://www.pythonchallenge.com/pc/def/ocr.html
    */
   Map<String, Integer> resultMap = new HashMap<>();
	public Exo03(){
		this.fileName = "pythonChallenge/Exo3.txt";
	}

   @Override
   public void fillDataStruct(){
      for(String line : lines) {
         for (char c : line.toCharArray()){
            Integer count = resultMap.get(String.valueOf(c));
            if (count == null) count = 0;
            count++;
            resultMap.put(String.valueOf(c), count);
         }
      }
      //for (Entry<String, Integer> r : resultMap.entrySet()){
      //   System.out.println(r.getKey() + " -> " + r.getValue());
      //}
      for(String line : lines) {
         for (char c : line.toCharArray()){
            Integer count = resultMap.get(String.valueOf(c));
            if (count.intValue() == 1){
               System.out.println(c);
            }
         }
      }
   }

	

	@Override
	public String processPart1() {
		return "";
	}
	   
	
}
