package be.dno.pythonChallenge;

import java.util.HashMap;
import java.util.Map;
import be.dno.Day;

public class Exo04 extends Day {
   /**
    * http://www.pythonchallenge.com/pc/def/equality.html
    */
   char[] bigText;
	public Exo04(){
		this.fileName = "pythonChallenge/Exo4.txt";
	}

   @Override
   public void fillDataStruct(){
      StringBuilder sb = new StringBuilder();
      for(String line : lines) {
         sb.append(line);
      }
      bigText = sb.toString().toCharArray();
   }

	private int[] getNeighbours(int index){
      int[] neighbours = new int[8];
      neighbours[0] = 98;
      if (index > 3){
         neighbours[0] = (int)bigText[index-4];
      }
      for (int i = 0; i < 6; i++){
         int nInd = index+((-3)+i);
         if (nInd >= index) nInd++;
         neighbours[i+1] = (int)bigText[nInd];
      }
      neighbours[7] = 98;
      if (index < bigText.length - 4){
         neighbours[7] = (int)bigText[index+4];
      }
      return neighbours;
   }

   public boolean isMin(int c){
      return c >= 97 && c <= 122;
   }
   public boolean isMaj(int c){
      return c >= 65 && c <= 90;
   }

	@Override
	public String processPart1() {
      String result = "";
		for (int i = 3; i < bigText.length - 3; i++){
         if (!isMin((int)bigText[i])){
            continue;
         }
         int[] neighbours = getNeighbours(i);
         if (isMin(neighbours[0]) && 
             isMaj(neighbours[1]) && 
             isMaj(neighbours[2]) &&
             isMaj(neighbours[3]) &&
             isMaj(neighbours[4]) &&
             isMaj(neighbours[5]) &&
             isMaj(neighbours[6]) &&
             isMin(neighbours[7])){
               result += String.valueOf(bigText[i]);
             }
      }
      return result;
	}
	   
	
}
