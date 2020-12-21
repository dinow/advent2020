package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import be.dno.Day_old;
public class Day14 implements Day_old{

   Map<Long, String> memory = new HashMap<>();
   String mask = null;
   String result = null;

   @Override
   public void run(String fileName) throws IOException {
      List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));

      for(String line : contents){
         if (line.startsWith("mask")){
            mask = line.split(" = ")[1].trim();
         } else {
            Long memoryPos = Long.valueOf(line.substring(4, line.indexOf("]")));
            String binValue = StringUtils.leftPad(Integer.toBinaryString(Integer.valueOf(line.split(" = ")[1].trim())), 36, "0") ;
            memory.put(memoryPos, applyMask(binValue, mask));
         }
      }
      long part1 = 0l;
      for(String value : memory.values()){
         part1 += Long.parseLong(value, 2);
      }
      System.out.println("Part 1 : " + part1);

      memory.clear();
      for(String line : contents){
         System.out.println(line);
         if (line.startsWith("mask")){
            mask = line.split(" = ")[1].trim();
         } else {
            Integer memoryPos = Integer.valueOf(line.substring(4, line.indexOf("]")));
            String binValue = StringUtils.leftPad(Integer.toBinaryString(memoryPos), 36, "0");
            System.out.println(binValue);
            System.out.println("");
            Integer value = Integer.valueOf(line.split(" = ")[1].trim());
            result = applyMask2(binValue, mask);
            System.out.println(result);
            System.out.println("");
            //Generate as many binary Strings as there is X in the mask (2 ^ nbX)
            int count = (int)mask.chars().filter(ch -> ch == 'X').count();
            int[] generator = new int[count];
            for (int i = 0; i < count; i++){
               generator[i] = 0;
            }
            generateAllBinaryStrings(value, count, generator, 0);
            //memory.put(memoryPos, applyMask(binValue, mask));
         }
      }
      long part2 = 0l;
      for(String value : memory.values()){
         part2 += Long.parseLong(value, 2);
      }
      System.out.println("Part 2 : " + part2);
   }

   private void printTheArray(Integer value, int arr[], int n) {
      String binInt = "";
      char[] chars = result.toCharArray();
      int ai = 0;
      for (int i = 0; i < chars.length; i++){
         if (chars[i]=='X') binInt += ""+arr[ai++];
         else binInt += chars[i]+"";
      }
      binInt = StringUtils.leftPad(binInt, 36, "0");
      System.out.println(binInt);
      memory.put(Long.parseLong(binInt, 2), Integer.toBinaryString(value));
}

   private void generateAllBinaryStrings(Integer value, int n, int arr[], int i){
      if (i == n){
         printTheArray(value, arr, n);
         return;
      }

      // First assign "0" at ith position
      // and try for all other permutations
      // for remaining positions
      arr[i] = 0;
      generateAllBinaryStrings(value, n, arr, i + 1);

      // And then assign "1" at ith position
      // and try for all other permutations
      // for remaining positions
      arr[i] = 1;
      generateAllBinaryStrings(value, n, arr, i + 1);
   }

   private String applyMask(String value, String mask){
      String result = "";
      for (int i = 0; i < 36; i++){
         if (mask.charAt(i) == 'X') result += value.charAt(i);
         if (mask.charAt(i) == '1') result += (value.charAt(i) == '1' || mask.charAt(i) == '1') ? "1" : "0";
         if (mask.charAt(i) == '0') result += (value.charAt(i) == '1' && mask.charAt(i) == '1') ? "1" : "0";
      }
      return result;
   }
   private String applyMask2(String value, String mask){
      String result = "";
      for (int i = 0; i < 36; i++){
         if (mask.charAt(i) == 'X') result += "X";
         if (mask.charAt(i) == '1') result += "1";
         if (mask.charAt(i) == '0') result += value.charAt(i);
      }
      return result;
   }
   

}
