package be.dno.advent2016;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import be.dno.Day;

public class Day07 extends Day {

   public Day07(){
      fileName = "2016/day07.txt";
   }

   @Override
   public String processPart1() {
      Pattern pattern = Pattern.compile("\\[.*?\\]");
      int tlsOk = 0;
      for (String line : lines){
         String newLine = line;
         Matcher m = pattern.matcher(line);
         boolean abbaInBracet = false;
         while(m.find()){
            if (hasABBA(m.group(0))){
               abbaInBracet = true;
            }
            newLine = newLine.replace(m.group(0), "-");
         }
         if (!abbaInBracet){
            if (hasABBA(newLine)){
               tlsOk++;
            }
         }
      }
      return ""+tlsOk;
   }

   private boolean hasABBA(String input){
      for (int i = 0; i < input.length() - 3; i++){
         if (   input.charAt(i)   == input.charAt(i+3) 
             && input.charAt(i+1) == input.charAt(i+2)
             && input.charAt(i)   != input.charAt(i+1)){
            return true;
         }
      }
      return false;
   }
   private boolean hasBAB(String input, char[] aba){
      for (int i = 0; i < input.length() - 2; i++){
         if (   input.charAt(i)   == aba[1] 
             && input.charAt(i+1) == aba[0]
             && input.charAt(i+2) == aba[1]){
            return true;
         }
      }
      return false;
   }
   private List<char[]> getABA(String input){
      List<char[]> abas = new ArrayList<>();
      for (int i = 0; i < input.length() - 2; i++){
         if (   input.charAt(i)   == input.charAt(i+2) 
             && input.charAt(i)   != input.charAt(i+1)){
               abas.add(new char [] {input.charAt(i), input.charAt(i+1), input.charAt(i+2)});
         }
      }
      return abas;
   }

   @Override
   public String processPart2() {
      Pattern pattern = Pattern.compile("\\[.*?\\]");
      int sslOk = 0;
      for (String line : lines){
         String newLine = line;
         Matcher m = pattern.matcher(line);
         List<String> inBracket = new ArrayList<>();
         while(m.find()){
            inBracket.add(m.group(0));
            newLine = newLine.replace(m.group(0), "---");
         }
         List<char[]> abas = getABA(newLine);
         if (!abas.isEmpty()){
            for (String bra : inBracket){
               for(char[] aba : abas){
               if (hasBAB(bra, aba)){
                  sslOk++;
                  break;
               }
            }
            }
         }
      }
      return ""+sslOk;
   }
}
