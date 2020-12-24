package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import be.dno.Day;

public class Day20 implements Day{
   private final List<Day20Tile> tiles = new ArrayList<>();
   private final Map<Integer, Day20Tile> mtiles = new HashMap<>();
   private static final int SIZE = 10;
   private static final int PUZZLE_SIZE = 12;
   private final char[][] finalPicture = new char[PUZZLE_SIZE*(SIZE-2)][PUZZLE_SIZE*(SIZE-2)];
   private final Day20Tile[][] finalArray = new Day20Tile[PUZZLE_SIZE][PUZZLE_SIZE];
   private final Map<Day20Tile, Map<Integer, char[][]>> mapMatches = new HashMap<>();

   @Override
	public void fillDataStruct(String fileName) throws IOException {
		List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
      fillArrays(contents);
	}


   private void fillArrays(List<String> content){
      Integer currentNumber = Integer.valueOf(0);
      char[][] currentArr = new char[SIZE][SIZE];
      int linePos = 0;
      for (String line : content){
         if (line.startsWith("Tile")){
            currentNumber = Integer.valueOf(line.replaceAll("Tile", "").replaceAll(":", "").replaceAll(" ", ""));
         } else if (line.isEmpty()){
            Day20Tile day20Tile = new Day20Tile(currentNumber, currentArr);
            day20Tile.generateTransforms();
            tiles.add(day20Tile);
            currentNumber = null;
            currentArr = new char[SIZE][SIZE];
            linePos = 0;
         } else {
            currentArr[linePos] = line.toCharArray();
            linePos++;
         }
      }
      Day20Tile day20Tile = new Day20Tile(currentNumber, currentArr);
      day20Tile.generateTransforms();
      tiles.add(day20Tile);
      currentNumber = null;
      currentArr = new char[10][10];
      linePos = 0;
      for (Day20Tile t : tiles){
         mtiles.put(t.number, t);
      }
   }

   @Override
	public String processPart1(){
      Set<Integer> corners = new HashSet<>();
      while(corners.size() != 4){
         for(int main_tile_idx = 0; main_tile_idx < tiles.size(); main_tile_idx++){
            Day20Tile tile = tiles.get(main_tile_idx);
            if (corners.contains(tile.number)) continue;
            //System.out.println("Main tile : " + tile.number);
            //for each transformation of this one
            int matches = 0;
            for (int other_idx = 0; other_idx < tiles.size(); other_idx++){
               Day20Tile otherTile = tiles.get(other_idx);
               if (otherTile.number.equals(tile.number)) continue;
               List<char[][]> current_all_trans = tile.getAllTransformations();
               for(int current_all_trans_idx = 0; current_all_trans_idx < current_all_trans.size(); current_all_trans_idx++){
                  char[][] current_transformed = current_all_trans.get(current_all_trans_idx);
                  List<char[][]> other_transformeds = otherTile.getAllTransformations();
                  for(int other_transformeds_idx = 0; other_transformeds_idx < other_transformeds.size(); other_transformeds_idx++){
                     char[][] other_transformed = other_transformeds.get(other_transformeds_idx);
                     if (oneBorderMatch(current_transformed, other_transformed)){
                        Map<Integer, char[][]> neibourSet = mapMatches.get(tile);
                        if (neibourSet == null) neibourSet = new HashMap<>();
                        if (!neibourSet.containsKey(otherTile.number)){
                           neibourSet.put(otherTile.number, other_transformed);
                        }
                        mapMatches.put(tile, neibourSet);
                        //System.out.println("\tMatch with : " + otherTile.number);
                        matches++;
                        other_transformeds_idx = other_transformeds.size();
                        current_all_trans_idx = current_all_trans.size(); 
                     }
                  }
               }
            }
            if (matches == 2){
               //System.out.println("Add "+tile.number+" in corners");
               corners.add(tile.number);
               break;
            }
         }
      }
      long acc = 1;
      for (Integer i : corners){
         acc *= i;
      }
      return acc+"";
   }


   private boolean isNorthOk(char[][] current_transformed, char[][] other_transformed) {
      return String.valueOf(current_transformed[0]).equals(String.valueOf(other_transformed[SIZE-1]));
   }
   private boolean isSouthOk(char[][] current_transformed, char[][] other_transformed){
      return String.valueOf(current_transformed[SIZE-1]).equals(String.valueOf(other_transformed[0]));
   }
   private boolean isWestOk(char[][] current_transformed, char[][] other_transformed){
      for (int i = 0; i < SIZE; i++){
         if (current_transformed[i][0] != other_transformed[i][SIZE-1]){
            return false;
         }
      }
      return true;
   }
   private boolean isEastOk(char[][] current_transformed, char[][] other_transformed){
      for (int i = 0; i < SIZE; i++){
         if (current_transformed[i][SIZE-1] != current_transformed[i][0]){
            return false;
         }
      }
      return true;
   }
   
   private boolean oneBorderMatch(char[][] current_transformed, char[][] other_transformed) {
      if (isNorthOk(current_transformed, other_transformed)) return true;
      if (isSouthOk(current_transformed, other_transformed)) return true;
      if (isWestOk(current_transformed, other_transformed))  return true;
      if (isEastOk(current_transformed, other_transformed))  return true;
      return false;
   }


   @Override
	public String processPart2() {
      //it's one of the border, I tried manually...
      finalArray[0][0] = mtiles.get(Integer.valueOf("3539"));
      solveSudoku();
   
      for (int tilex = 0; tilex < PUZZLE_SIZE; tilex++){
         for (int tiley = 0; tiley < PUZZLE_SIZE; tiley++){
            char[][] theTileArray = finalArray[tilex][tiley].getInnerArray();
            for (int x = 0; x < theTileArray.length; x++){
               for (int y = 0; y < theTileArray.length; y++){
                  finalPicture[(tilex*(theTileArray.length))+x][(tiley*(theTileArray.length))+y] = theTileArray[x][y];
               }
            }
         }
      }

      Day20Tile monsterTile = new Day20Tile(Integer.valueOf(42), finalPicture);
      monsterTile.generateTransforms();
      for (char[][] monster : monsterTile.getAllTransformations()){
         boolean isMonster = false;
         for (int i = 0; i < monster.length; i++){
            for (int j = 0; j < monster[i].length; j++){
               if (isSeaMonster(monster, i, j)){
                  //System.out.println("Monster found !");
                  isMonster = true;
               }
            }
         }


         if (isMonster){
            long acc = 0l;
            for (int i = 0; i < monster.length; i++){
               for (int j = 0; j < monster[i].length; j++){
                  if (monster[i][j] == '#'){
                     acc++;
                  }
               }
            }
            return ""+acc;
         }
      }
      return 0l+"";
   }

   private boolean isSeaMonster(char[][] monster, int i, int j) {
      if (j+19 >= monster.length) return false;
      if (i == 0) return false;
      if (i+1 >= monster.length) return false;
      //                  # 
      //#    ##    ##    ###
      // #  #  #  #  #  #   
      //01234567890123456789
      if (   monster[i][j+0] == '#' 
          && monster[i][j+5] == '#' 
          && monster[i][j+6] == '#' 
          && monster[i][j+11] == '#' 
          && monster[i][j+12] == '#' 
          && monster[i][j+17] == '#' 
          && monster[i][j+18] == '#' 
          && monster[i][j+19] == '#' 
          ){
             //middle found
            if (monster[i-1][j+18] == '#'){
               if (   monster[i+1][j+1] == '#'
                   && monster[i+1][j+4] == '#'
                   && monster[i+1][j+7] == '#'
                   && monster[i+1][j+10] == '#'
                   && monster[i+1][j+13] == '#'
                   && monster[i+1][j+16] == '#'
                   ){

                     monster[i][j+0]  = 'O';
                     monster[i][j+5]  = 'O';
                     monster[i][j+6]  = 'O';
                     monster[i][j+11] = 'O';
                     monster[i][j+12] = 'O';
                     monster[i][j+17] = 'O';
                     monster[i][j+18] = 'O';
                     monster[i][j+19] = 'O';
                     monster[i-1][j+18] = 'O';
                     monster[i+1][j+1]  = 'O';
                     monster[i+1][j+4]  = 'O';
                     monster[i+1][j+7]  = 'O';
                     monster[i+1][j+10] = 'O';
                     monster[i+1][j+13] = 'O';
                     monster[i+1][j+16] = 'O';

                      return true;
                   }
            }
          }
      return false;
   }

   public boolean solveSudoku() {
      for(int row=0;row<PUZZLE_SIZE;row++){
         for(int col=0;col<PUZZLE_SIZE;col++){
            if(finalArray[row][col]==null){
               for(Day20Tile tile : tiles){
                  for (char[][] trans : tile.getAllTransformations()){
                     if(isAllowed(row, col, tile.number, trans)){
                        finalArray[row][col] = new Day20Tile(tile.number, trans);
                        if(solveSudoku()){
                           return true;
                        } else {
                           finalArray[row][col] = null;
                        }
                     }
                  }
               }
               return false;
            }
         }
      }
      return true;
   }

   private boolean isAllowed(int row, int col, Integer number, char[][] arr) {
      if (isPartOfImage(number)) return false;
      Day20Tile north = null;
      Day20Tile south = null;
      Day20Tile west  = null;
      Day20Tile east  = null;
      if (row == 0 && col == 0){
         south = finalArray[row+1][col];
         east  = finalArray[row][col+1];
      } else if (row == 0 && col == (PUZZLE_SIZE - 1)){
         south = finalArray[row+1][col];
         west  = finalArray[row][col-1];
      } else if (row == (PUZZLE_SIZE - 1) && col == 0){
         north = finalArray[row-1][col];
         east  = finalArray[row][col+1];
      } else if (row == (PUZZLE_SIZE - 1) && col == (PUZZLE_SIZE - 1)){
         north = finalArray[row-1][col];
         west  = finalArray[row][col-1];
      } else if (row == 0 && col != 0 && col != (PUZZLE_SIZE - 1)) {
         south = finalArray[row+1][col];
         west  = finalArray[row][col-1];
         east  = finalArray[row][col+1];
      } else if (row == (PUZZLE_SIZE - 1)  && col != 0 && col != (PUZZLE_SIZE - 1)) {
         north = finalArray[row-1][col];
         west  = finalArray[row][col-1];
         east  = finalArray[row][col+1];
      } else if (col == 0 && row != 0 && row != (PUZZLE_SIZE - 1)){
         north = finalArray[row-1][col];
         south = finalArray[row+1][col];
         east  = finalArray[row][col+1];
      } else if (col == (PUZZLE_SIZE - 1) && row != 0 && row != (PUZZLE_SIZE - 1)){
         north = finalArray[row-1][col];
         south = finalArray[row+1][col];
         west  = finalArray[row][col-1];
      } else {
         //inside
         north = finalArray[row-1][col];
         south = finalArray[row+1][col];
         west  = finalArray[row][col-1];
         east  = finalArray[row][col+1];
      }
      boolean allOk = true;
      if (north != null) allOk &= isNorthOk(arr, north.array);
      if (south != null) allOk &= isSouthOk(arr, south.array);
      if (west != null)  allOk &= isWestOk(arr,  west.array);
      if (east != null)  allOk &= isEastOk(arr,  east.array);
      return allOk;
   }

   private boolean isPartOfImage(Integer number) {
      for (int i = 0; i < PUZZLE_SIZE; i++) {
         for (int j = 0; j < PUZZLE_SIZE; j++) {
            if (finalArray[i][j] != null){
               if (number.equals(finalArray[i][j].number)) return true;
            }
         }
      }
      return false;
   }

}
