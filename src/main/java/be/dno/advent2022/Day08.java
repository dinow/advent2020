package be.dno.advent2022;

import be.dno.Day;
import be.dno.MatrixElement;

public class Day08 extends Day {

   MatrixElement[][] trees;
   public Day08(){
      fileName = "2022/day08.txt";
   }

   @Override
   public void fillDataStruct() {
      trees = new MatrixElement[lines.size()][lines.size()];
      int i = 0;
      int j = 0;
      for (String line : lines){
         for (char c : line.toCharArray()){
            trees[i][j] = new MatrixElement(Integer.valueOf(String.valueOf(c)));
            j++;
         }
         j=0;
         i++;
      }
   }


   @Override
   public String processPart1() {
      int nbVisibles = 0;
      for (int i = 0; i < trees.length; i++){
         for (int j = 0; j < trees[i].length; j++){
            if ( (i == 0 || i == trees.length-1) || (j == 0 || j == trees[i].length-1)){
               trees[i][j].flag();
            } else {
               //check if visible up/down/left/right
               //up
               boolean visible = true;
               for (int k = i-1; k >= 0; k--){
                  if (trees[k][j].getIntValue() >= trees[i][j].getIntValue()){
                     visible = false;
                     break;
                  }
               }
               if (visible){
                  trees[i][j].flag();
               }
               if (!visible){
                  //down
                  visible = true;
                  for (int k = i+1; k < trees[i].length; k++){
                     if (trees[k][j].getIntValue() >= trees[i][j].getIntValue()){
                        visible = false;
                        break;
                     }
                  }
                  if (visible){
                     trees[i][j].flag();
                  }
               }
               if (!visible){
                  //left
                  visible = true;
                  for (int k = j-1; k >= 0; k--){
                     if (trees[i][k].getIntValue() >= trees[i][j].getIntValue()){
                        visible = false;
                        break;
                     }
                  }
                  if (visible){
                     trees[i][j].flag();
                  }
               }
               if (!visible){
                  //right
                  visible = true;
                  for (int k = j+1; k < trees[i].length; k++){
                     if (trees[i][k].getIntValue() >= trees[i][j].getIntValue()){
                        visible = false;
                        break;
                     }
                  }
                  if (visible){
                     trees[i][j].flag();
                  }
               }
            }
         }
      }
      for (int i = 0; i < trees.length; i++){
         for (int j = 0; j < trees[i].length; j++){
            if (trees[i][j].isFlagged()){
               nbVisibles++;
            }
         }
      }
      return String.valueOf(nbVisibles);
   }

   @Override
   public String processPart2() {
      int viewingDistance = Integer.MIN_VALUE;
      for (int i = 1; i < trees.length-1; i++){
         for (int j = 1; j < trees[i].length-1; j++){
            int currentViewingDist = 0;
            int viewU = 0;
            int viewD = 0;
            int viewL = 0;
            int viewR = 0;
            //up
            for (int k = i-1; k >= 0; k--){
               viewU++;
               if (trees[k][j].getIntValue() >= trees[i][j].getIntValue()){
                  break;
               }
            }

            //down
            for (int k = i+1; k < trees[i].length; k++){
               viewD++;
               if (trees[k][j].getIntValue() >= trees[i][j].getIntValue()){
                  break;
               }
            }

            //left
            for (int k = j-1; k >= 0; k--){
               viewL++;
               if (trees[i][k].getIntValue() >= trees[i][j].getIntValue()){
                 break;
               }
            }

            //right
            for (int k = j+1; k < trees[i].length; k++){
               viewR++;
               if (trees[i][k].getIntValue() >= trees[i][j].getIntValue()){
                  break;
               }
            }
            currentViewingDist = viewD*viewL*viewR*viewU;
            if (currentViewingDist > viewingDistance){
               viewingDistance = currentViewingDist;
            }
         }
      }
      return String.valueOf(viewingDistance);
   }
}

