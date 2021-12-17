package be.dno.advent2021;


import be.dno.Day;

public class Day17 extends Day {
   public static final int target_min_x = 207;
   public static final int target_max_x = 263;
   public static final int target_min_y = -115;
   public static final int target_max_y = -63;

   /*public static final int target_min_x = 20;
   public static final int target_max_x = 30;
   public static final int target_min_y = -10;
   public static final int target_max_y = -5;*/

   @Override
   public String processPart1(){ 
      int highestPosition = Integer.MIN_VALUE;

      for (int x = 0; x < target_max_x; x++){
         for (int y = target_min_y; y < 1000; y++){
            int veolcity_x = x;
            int veolcity_y = y;
            int current_x = 0;
            int current_y = 0;

            int currentShotHighest = Integer.MIN_VALUE;
            //System.out.println("Fire from "+veolcity_x+","+veolcity_y);
            while(true){
               //The probe's x position increases by its x velocity
               current_x += veolcity_x;

               //The probe's y position increases by its y velocity.
               current_y += veolcity_y;

               currentShotHighest = Math.max(currentShotHighest, current_y);

               //Due to drag, the probe's x velocity changes by 1 toward the value 0; 
               //that is, it decreases by 1 if it is greater than 0, increases by 1 if it is less than 0, or does not change if it is already 0.
               if (veolcity_x > 0){
                  veolcity_x-=1;
               }

               //Due to gravity, the probe's y velocity decreases by 1
               veolcity_y-=1;

               if (current_x >= target_min_x && current_x <= target_max_x && current_y >= target_min_y && current_y <= target_max_y){
                  highestPosition = Math.max(highestPosition, currentShotHighest);
                  break;
               }

               //test if missed
               if (current_y < target_min_y){
                  //System.out.println("missed !");
                  break;
               }
            }
         }
      }
      return highestPosition+"";
   }

   @Override
   public String processPart2(){ 
     long hits = 0l;

      for (int x = 0; x <= target_max_x; x++){
         for (int y = target_min_y; y < 1000; y++){
            int veolcity_x = x;
            int veolcity_y = y;
            int current_x = 0;
            int current_y = 0;

            //System.out.println("Fire from "+veolcity_x+","+veolcity_y);
            while(true){
               //The probe's x position increases by its x velocity
               current_x += veolcity_x;

               //The probe's y position increases by its y velocity.
               current_y += veolcity_y;

               //Due to drag, the probe's x velocity changes by 1 toward the value 0; 
               //that is, it decreases by 1 if it is greater than 0, increases by 1 if it is less than 0, or does not change if it is already 0.
               if (veolcity_x > 0){
                  veolcity_x-=1;
               }

               //Due to gravity, the probe's y velocity decreases by 1
               veolcity_y-=1;

               if (current_x >= target_min_x && current_x <= target_max_x && current_y >= target_min_y && current_y <= target_max_y){
                  //System.out.println("It's ok !");
                  hits++;
                  break;
               }

               //test if missed
               if (current_y < target_min_y){
                  break;
               }
            }
         }
      }
      return hits+"";
   }
   
}
