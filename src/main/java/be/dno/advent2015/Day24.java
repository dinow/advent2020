package be.dno.advent2015;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;


import be.dno.Day;

public class Day24 implements Day{
   private int[] number;
   private int groupWeight = 0;;

   @Override
   public void fillDataStruct(String fileName) throws IOException {
      List<String> lines = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
      number = new int[lines.size()];
      for (int i = 0; i < lines.size(); i++){
         number[i] = Integer.parseInt(lines.get(i).trim());
      }
      
   }

   @Override
   public String processPart1() {
      long minQE   = Long.MAX_VALUE;
      long minPack = Long.MAX_VALUE;

      for (int i = 0; i < number.length; i++){
         groupWeight += number[i];
      }

      Arrays.sort(number);

      Set<Integer> group1 = new HashSet<>();
      Set<Integer> group2 = new HashSet<>();
      Set<Integer> group3 = new HashSet<>();


      return "";
   }

   @Override
   public String processPart2() {
      return "";
   }
}
