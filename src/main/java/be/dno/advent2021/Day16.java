package be.dno.advent2021;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.dno.Day;

public class Day16 extends Day {
   private Map<String, String> hexToBin = new HashMap<>();
   private Map<String, String> binToHex = new HashMap<>();
   public static final int SUM = 0;
   public static final int PRODUCT = 1;
   public static final int MINIMUM = 2;
   public static final int MAXIMUM = 3;
   public static final int LITERAL_VALUE = 4;
   public static final int GREATHER_THAN = 5;
   public static final int LESS_THAN = 6;
   public static final int EQUALS_TO = 7;
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
      long lastResult = 0l;
      for (String line : lines){
         String binaryLine = convertToBinary(line);
         ArrayDeque<Character> adc = new ArrayDeque<>(binaryLine.length());
         for(char c : binaryLine.toCharArray()) {
            adc.add(Character.valueOf(c));
         }
         Packet packet = parseBinary(adc);
         lastResult = packet.sumVersions();
      }
      return lastResult+"";
   }
   
   @Override
   public String processPart2(){ 
     long lastResult = 0l;
      for (String line : lines){
         String binaryLine = convertToBinary(line);
         ArrayDeque<Character> adc = new ArrayDeque<>(binaryLine.length());
         for(char c : binaryLine.toCharArray()) {
            adc.add(Character.valueOf(c));
         }
         Packet packet = parseBinary(adc);
         lastResult = packet.compute();
      }
      return lastResult+"";
   }
   
   private int readNBits(ArrayDeque<Character> s, int bitCount) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bitCount; i++) {
         sb.append(s.removeFirst());
      }
      return Integer.valueOf(sb.toString(), 2);
   }
   
   private String readNBitsString(ArrayDeque<Character> s, int bitCount) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bitCount; i++) {
         sb.append(s.removeFirst());
      }
      return sb.toString();
   }
   
   private Packet parseBinary(ArrayDeque<Character> adc) {
      Packet packet = new Packet();
      packet.setVersion(readNBits(adc, 3));
      packet.setTypeId(readNBits(adc, 3));
         
      if (packet.getTypeId() == LITERAL_VALUE){
         StringBuilder sb = new StringBuilder();
         while(true) {
            String currentValue = readNBitsString(adc, 5);
            sb.append(currentValue.substring(1));
            if (currentValue.charAt(0)=='0') break;
         }
         packet.setLiteralValue(Long.valueOf(sb.toString(), 2).longValue());
         return packet;
      }
         
      //Not a literalValue
      packet.setLengthTypeId(readNBits(adc, 1));
      if (packet.getLengthTypeId() == 1) {
         packet.setNumberOfSubPacket(readNBits(adc, 11));
         for (int i = 0; i < packet.getNumberOfSubPacket(); i++) {
            packet.addSubPacket(parseBinary(adc));
         }
      }else {
         packet.setLengthOfSubPacket(readNBits(adc, 15));
         int targetBits = adc.size() - packet.getLengthOfSubPacket();
         while (adc.size() != targetBits) {
            packet.addSubPacket(parseBinary(adc));
         }
      }
      return packet;
   }

   private String convertToBinary(String line) {
      StringBuilder binary = new StringBuilder();
      for (String letter : line.split("|")){
         binary.append(hexToBin.get(letter));
      }
      return binary.toString();
   }

}

class Packet {
   int version;
   int typeId;
   int lengthTypeId;
   long literalValue;
   int lengthOfSubPacket;
   int numberOfSubPacket;
   List<Packet> subPackets = new ArrayList<>();
   
   public Packet() { 
      this.literalValue = -1l;
   }
   
   public long sumVersions() {
      if (this.literalValue > -1l) return this.version;
      long subSum = this.version;
      for (Packet p : this.subPackets) {
         subSum += p.sumVersions();
      }
      return subSum;
   }

   public int getLengthOfSubPacket() {
      return lengthOfSubPacket;
   }

   public int getNumberOfSubPacket() {
      return numberOfSubPacket;
   }

   public long compute() {
      if (this.typeId == Day16.SUM) {
         long sum = 0;
         for (Packet p : this.subPackets) {
            sum += p.compute();
         }
         return sum;
      }
      if (this.typeId == Day16.PRODUCT) {
         long sum = 1;
         for (Packet p : this.subPackets) {
            sum *= p.compute();
         }
         return sum;
      }
      if (this.typeId == Day16.MINIMUM) {
         long sum = Long.MAX_VALUE;
         for (Packet p : this.subPackets) {
            long l = p.compute();
            if (sum > l) sum = l;
         }
         return sum;
      }
      if (this.typeId == Day16.MAXIMUM) {
         long sum = Long.MIN_VALUE;
         for (Packet p : this.subPackets) {
            long l = p.compute();
            if (sum < l) sum = l;
         }
         return sum;
      }
      if (this.typeId == Day16.LITERAL_VALUE) {
         return this.literalValue;
      }
      if (this.typeId == Day16.EQUALS_TO) {
         return (this.subPackets.get(0).compute() == this.subPackets.get(1).compute()) ? 1 : 0;
      }
      if (this.typeId == Day16.GREATHER_THAN) {
         return (this.subPackets.get(0).compute() > this.subPackets.get(1).compute()) ? 1 : 0;
      }
      if (this.typeId == Day16.LESS_THAN) {
         return (this.subPackets.get(0).compute() < this.subPackets.get(1).compute()) ? 1 : 0;
      }
      return -1;
   }

   public void setLengthOfSubPacket(Integer valueOf) {
      this.lengthOfSubPacket = valueOf;
      
   }

   public void setNumberOfSubPacket(Integer valueOf) {
      this.numberOfSubPacket = valueOf;
      
   }

   public int getLengthTypeId() {
      return this.lengthTypeId;
   }

   public int getTypeId() {
      return this.typeId;
   }
   
   

   @Override
   public String toString() {
      return "Packet [version=" + version + ", typeId=" + typeId + ", lengthTypeId=" + lengthTypeId
            + ", literalValue=" + literalValue + ", lengthOfSubPacket=" + lengthOfSubPacket + ", numberOfSubPacket="
            + numberOfSubPacket + "]";
   }

   public void setVersion(int _version) {
      this.version = _version;
   }
   
   public void setTypeId(int _typeId) {
      this.typeId = _typeId;
   }
   
   public void setLengthTypeId(int _lengthTypeId) {
      this.lengthTypeId = _lengthTypeId;
   }
   
   public void setLiteralValue(long _literalValue) {
      this.literalValue = _literalValue;
   }
   
   public void addSubPacket(Packet packet) {
      this.subPackets.add(packet);
   }
   
   public long getLiteralValue() {
      return this.literalValue;
   }
}

