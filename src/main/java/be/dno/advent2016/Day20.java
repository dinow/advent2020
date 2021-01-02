package be.dno.advent2016;

import java.util.Comparator;

import be.dno.Day;
import be.dno.Utils;

public class Day20 extends Day{

   public Day20(){
      fileName = "2016/day20.txt";
   }

   private static long MIN = 0l;
   private static long MAX = 4294967295l;

   public void fillDataStruct(){
      lines.sort(new Comparator<String>(){

		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
      });
   }

   @Override
   public String processPart1() {
      long minValue = MIN;
      boolean allOk = false;
      while(!allOk){
         allOk = true;
         for(String line : lines){
            long[] data = Utils.splitCoupleL(line, "-");
            if (minValue >= data[0] && minValue < data[1]){
               minValue = data[1];
               allOk = false;
            } else{
               allOk &= (minValue < data[0]) || (minValue > data[1]);
               if (!allOk) {
                  System.out.println("["+minValue+"] rejected by ["+line+"]");
                  break;
               }
            }
         }
         if (!allOk){
            minValue++;
         }
      }
      
      return ""+minValue;
   }

   @Override
   public String processPart2() {
      long cpt = 0;
      long minValue = MIN;
      boolean allOk = false;
      while(minValue <= MAX){
         allOk = true;
         for(String line : lines){
            long[] data = Utils.splitCoupleL(line, "-");
            if (minValue >= data[0] && minValue < data[1]){
               minValue = data[1];
               allOk = false;
            } else{
               allOk &= (minValue < data[0]) || (minValue > data[1]);
               if (!allOk) {
                  break;
               }
            }
         }
         minValue++;
         if (allOk){
            cpt++;
         }
      }
      
      
      return ""+cpt;
   }

}
