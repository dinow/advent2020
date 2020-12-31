package be.dno.advent2016;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

import be.dno.Day;

public class Day14 extends Day{

   String input;
   int repeatHash;
   Set<String> keys = new HashSet<>();
   Map<String, String> hashes = new HashMap<>();

   public Day14(){
      fileName = "2016/day14.txt";
   }

   public void fillDataStruct(){
      input = super.lines.get(0);
   }

   @Override
   public String processPart1() {
      int idx = 0;
      repeatHash = 1;
      String md5Hex;
      while (keys.size() != 64){
         md5Hex = genHash(input+idx);
         if(isKey(idx, md5Hex, input)){
            keys.add(md5Hex);
         }
         idx++;
      }
      return ""+(idx-1);
   }

   
   @Override
   public String processPart2() {
      int idx = 0;
      repeatHash = 2016;
      String md5Hex;
      keys.clear();
      hashes.clear();
      while (keys.size() != 64){
         md5Hex = genHash(input+idx);
         if(isKey(idx, md5Hex, input)){
            keys.add(md5Hex);
         }
         idx++;
      }
      return ""+(idx-1);
   }

   private char repeatingChar(String input, int goal){
      char prevC = ' ';
      int cpt = 0;
      for(char c : input.toCharArray()){
         if(prevC != c){
            prevC = c;
            cpt = 1;
         } else {
            cpt ++;
            if (cpt == goal) return c;
         }
      }
      return ' ';
   }

   private char repeatingChar(String input, int goal, char prevC){
      int cpt = 0;
      for(char c : input.toCharArray()){
         if(prevC != c){
            cpt = 0;
         } else {
            cpt ++;
            if (cpt == goal) return c;
         }
      }
      return ' ';
   }


   private boolean isKey(int idx, String md5Hex, String input){
      boolean isOk = false;
      char rc = repeatingChar(md5Hex, 3);
      String md5HexSub;
      if (rc != ' '){
         //compute the next 1_000 hashes to get one right
         for(int i = 1; i <= 1_000; i++){
            md5HexSub = genHash(input+(idx+i));
            char src = repeatingChar(md5HexSub, 5, rc);
            if (src != ' '){
               isOk = true;
               break;
            }
         }
      }
      return isOk;
   }

   private String genHash(String in){
      if (hashes.containsKey(in+"_"+repeatHash)){
         return hashes.get(in+"_"+repeatHash);
      }
      String result = in;
      for (int i = 0; i <= repeatHash; i++){
         result = DigestUtils.md5Hex(result).toLowerCase();
      }
      hashes.put(in+"_"+repeatHash, result);
      return result;
   }

}
