package be.dno.advent2020;


public class Day23Cup {
   public int number;
   public Day23Cup next;

   public Day23Cup(String number){
      this.number = Integer.valueOf(number);
   }
   public Day23Cup(int number){
      this.number = number;
   }

   public String toString(){ 
      return String.valueOf(this.number); 
   }

   public int toInt(){
      return this.number;
   }

}
