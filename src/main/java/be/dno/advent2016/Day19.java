package be.dno.advent2016;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import be.dno.Day;

public class Day19 extends Day{
   int nbElf;
   int[] elfs;
   public Day19(){
      fileName = "2016/day19.txt";
   }

   public void fillDataStruct(){
      nbElf = Integer.parseInt(lines.get(0));
      elfs = new int[nbElf];
      Arrays.fill(elfs, 1);
   }



   @Override
   public String processPart1() {
      int i = 0;
      boolean gameIsOn = true;
      while(gameIsOn){
         if (elfs[i] == 0){
            i++;
            if (i >= nbElf) i = 0;
            continue;
         }
         int nextIdx = nextFilledIndex(i);
         if (nextIdx == -1) break;
         elfs[i] += elfs[nextIdx];
         elfs[nextIdx] = 0;
         i++;
         if (i >= nbElf) i = 0;
      }
      return ""+(i+1);
   }


   private int nextFilledIndex(int currIndex){
      int nextIdx = currIndex+1;
      if (nextIdx >= nbElf) nextIdx = 0;
      while(elfs[nextIdx] == 0){
         nextIdx++;
         if (nextIdx >= nbElf) nextIdx = 0;
         if (nextIdx == currIndex) return -1;
      }
      return nextIdx;
   }

   

   
   @Override
   public String processPart2() {
      Deque<Integer> stack2a = new ArrayDeque<Integer>(nbElf/2+1);
      Deque<Integer> stack2b = new ArrayDeque<Integer>(nbElf/2+1);
      // populate stack
      for (int i = 0 ; i < nbElf; i++) {
         if (i < nbElf/2) { //split the list in half...
            stack2a.add(i+1);
         } else {
            stack2b.add(i+1);
         } // and if/else
      } // end elfadd
    
      while (stack2a.size()+stack2b.size() > 1) { // snag presents.
         if ( (stack2a.size() +  stack2b.size()) % 2 != 0) { // steal middle
            stack2a.removeLast(); // odd number, take from left (i.e. end of first stack
         }else {
            stack2b.removeFirst();  // even number take top of stack
         };
         stack2b.add(stack2a.pop());  //move current head to end..,
         stack2a.add(stack2b.pop());  // rebalance
      }
      return "" + stack2a.peek();
   }

}
