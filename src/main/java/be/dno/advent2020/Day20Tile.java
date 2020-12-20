package be.dno.advent2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day20Tile {
   
   public char[][] array;
   public Integer number;

   public List<char[][]> transformations;

   public Day20Tile(Integer currentNumber, char[][] currentArr) {
      this.number = currentNumber;
      this.array = clone(currentArr);
   }

   public void generateTransforms(){
    transformations = new ArrayList<>();
    transformations.add(this.array);
    transformations.add(mirror(this.array));
    transformations.add(mirror2(this.array));
    
    char[][] rotated90 = clone(this.array);
    rotate90(rotated90);
    transformations.add(rotated90);
    transformations.add(mirror(rotated90));
    transformations.add(mirror2(rotated90));

    char[][] rotated180 = clone(this.array);
    rotate90(rotated180);
    rotate90(rotated180);
    transformations.add(rotated180);
    transformations.add(mirror(rotated180));
    transformations.add(mirror2(rotated180));

    char[][] rotated270 = clone(this.array);
    rotate90(rotated270);
    rotate90(rotated270);
    rotate90(rotated270);
    transformations.add(rotated270);
    transformations.add(mirror(rotated270));
    transformations.add(mirror2(rotated270));
   }

    public char[][] getInnerArray(){
        char[][] out = new char[this.array.length-2][this.array.length-2];
        for (int x = 1; x < this.array.length-1; x++){
            for (int y = 1; y < this.array.length-1; y++){
                out[x-1][y-1] = this.array[x][y];
            }
        }
        return out;
    }

    private char[][] clone(char[][] in){
        char[][] out = new char[in.length][in.length];
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[i].length; j++) {
                out[i][j] = in[i][j];
            }
        }
        return out;
    }

    private char[][] mirror(char[][] in) {
        char[][] out = new char[in.length][in.length];
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[i].length / 2; j++) {
                char temp = in[i][j];
                out[i][j] = in[i][in.length - 1 - j];
                out[i][in.length - 1 -j] = temp;
            }
        }
        return out;
    }
    private char[][] mirror2(char[][] in) {
        char[][] out = new char[in.length][in.length];
        for (int i = 0; i < in.length / 2; i++) {
            for (int j = 0; j < in[i].length; j++) {
                char temp = in[i][j];
                out[i][j] = in[in.length - 1 - i][j];
                out[in.length - 1 -i][j] = temp;
            }
        }
        return out;
    }

   private void rotate90(char arr[][]) { 
        transpose(arr); 
        reverseColumns(arr); 
   } 

    // After transpose we swap elements of 
    // column one by one for finding left 
    // rotation of matrix by 90 degree 
    private void reverseColumns(char arr[][]) { 
        for (int i = 0; i < arr[0].length; i++) 
            for (int j = 0, k = arr[0].length - 1; 
                 j < k; j++, k--) { 
                  char temp = arr[j][i]; 
                arr[j][i] = arr[k][i]; 
                arr[k][i] = temp; 
            } 
    } 
  
    // Function for do transpose of matrix 
    private void transpose(char arr[][]) { 
        for (int i = 0; i < arr.length; i++) 
            for (int j = i; j < arr[0].length; 
                 j++) { 
                  char temp = arr[j][i]; 
                arr[j][i] = arr[i][j]; 
                arr[i][j] = temp; 
            } 
    } 

   public List<char[][]> getAllTransformations() {
      return transformations;
   }
}