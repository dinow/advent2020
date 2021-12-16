package be.dno.advent2021;

import java.util.HashMap;
import java.util.Map;

import be.dno.Day;

public class Day16 extends Day {
   private Map<String, String> hexToBin = new HashMap<>();
   private Map<String, String> binToHex = new HashMap<>();
   public static final int LITTERAL_VALUE = 4;
   public Day16(){
      fileName = "2021/day16.txt";
   }

   @Override
   public void fillDataStruct(){
      hexToBin.put("0","0000");
      hexToBin.put("1","0001");
      hexToBin.put("2","0010");
      hexToBin.put("3","0011");
      hexToBin.put("4","0100");
      hexToBin.put("5","0101");
      hexToBin.put("6","0110");
      hexToBin.put("7","0111");
      hexToBin.put("8","1000");
      hexToBin.put("9","1001");
      hexToBin.put("A","1010");
      hexToBin.put("B","1011");
      hexToBin.put("C","1100");
      hexToBin.put("D","1101");
      hexToBin.put("E","1110");
      hexToBin.put("F","1111");

      binToHex.put("0000", "0");
      binToHex.put("0001", "1");
      binToHex.put("0010", "2");
      binToHex.put("0011", "3");
      binToHex.put("0100", "4");
      binToHex.put("0101", "5");
      binToHex.put("0110", "6");
      binToHex.put("0111", "7");
      binToHex.put("000", "0");
      binToHex.put("001", "1");
      binToHex.put("010", "2");
      binToHex.put("011", "3");
      binToHex.put("100", "4");
      binToHex.put("101", "5");
      binToHex.put("110", "6");
      binToHex.put("111", "7");
      binToHex.put("1000", "8");
      binToHex.put("1001", "9");
      binToHex.put("1010", "A");
      binToHex.put("1011", "B");
      binToHex.put("1100", "C");
      binToHex.put("1101", "D");
      binToHex.put("1110", "E");
      binToHex.put("1111", "F");
   }

   @Override
   public String processPart1(){ 
      long sumOfVersionNumbers = 0l;
      for (String line : lines){
         String binaryLine = convertToBinary(line);
         sumOfVersionNumbers = computeVersionNumbers(binaryLine, sumOfVersionNumbers, Integer.MAX_VALUE, Integer.MAX_VALUE);
      }
      return sumOfVersionNumbers+"";
   }

   private long computeVersionNumbers(String binaryLine, long sumOfVersions, int bitLimit, int howManySub) {
      String packetVersion = binaryLine.substring(0, 3);
      String typeId        = binaryLine.substring(3, 6);
      int packetVersionValue = Integer.valueOf(binToHex.get(packetVersion)).intValue();
      int typeIdValue        = Integer.valueOf(binToHex.get(typeId)).intValue();
      sumOfVersions += packetVersionValue;
      System.out.println("I got a packet version ["+packetVersionValue+"] with typeId ["+typeIdValue+"] - current total is " + sumOfVersions);
      if (typeIdValue != LITTERAL_VALUE){
         String lengthTypeId = binaryLine.substring(6, 7);
         if (lengthTypeId.equals("0")){
            //If the length type ID is 0, then the next 15 bits are a number that 
            //represents the total length in bits of the sub-packets contained by this packet.
            String totalLengthOfSubPackets = binaryLine.substring(7, (7+15));
            int totalLengthOfSubPacketsValue = Integer.valueOf(totalLengthOfSubPackets, 2);
            sumOfVersions = computeVersionNumbers(binaryLine.substring((7+15)), sumOfVersions, totalLengthOfSubPacketsValue, Integer.MAX_VALUE);
         } else {
            //If the length type ID is 1, then the next 11 bits are a number that 
            //represents the number of sub-packets immediately contained by this packet.
            String numberOfSubPackets = binaryLine.substring(7, (7+11));
            int numberOfSubPacketsValue = Integer.valueOf(numberOfSubPackets, 2);
            sumOfVersions = computeVersionNumbers(binaryLine.substring((7+11)), sumOfVersions, Integer.MAX_VALUE, numberOfSubPacketsValue);
         }
      } else {
         int startPos = 0;
         String remaining = binaryLine.substring(6, binaryLine.length());
         String flag = remaining.substring(startPos,startPos+1);
         StringBuilder literalValue = new StringBuilder();
         literalValue.append(remaining.substring(startPos+1, startPos+5));
         //read bytes 5 to 5
         while(flag.equals("1")){
            startPos+=5;
            flag = remaining.substring(startPos,startPos+1);
            literalValue.append(remaining.substring(startPos+1, startPos+5));
         }
         Long literalValueLong = Long.valueOf(literalValue.toString(), 2);
         System.out.println("Literal value was " + literalValueLong);
      
         //Check if there is still something to process
         if (startPos == 0) startPos = 5;
         String endOfPacket = remaining.substring(startPos);

         if (!endOfPacket.isEmpty() && !endOfPacket.replace("0", "").isEmpty()){
            sumOfVersions = computeVersionNumbers(endOfPacket, sumOfVersions, Integer.MAX_VALUE, Integer.MAX_VALUE);
         }
      }
      return sumOfVersions;
   }

   private String convertToBinary(String line) {
      StringBuilder binary = new StringBuilder();
      for (String letter : line.split("|")){
         binary.append(hexToBin.get(letter));
      }
      return binary.toString();
   }

}

