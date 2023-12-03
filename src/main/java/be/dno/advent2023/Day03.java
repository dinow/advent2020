package be.dno.advent2023;


import java.util.ArrayList;
import java.util.List;

import be.dno.Day;
import be.dno.MatrixElementChar;
import be.dno.Utils;

public class Day03 extends Day {

   MatrixElementChar[][] matrix;
   int matrixSize;

   List<MyNumber> numbers = new ArrayList<>();

   class MyNumber {
      long value;
      int fromIndex;
      int toIndex;
      int line;

      public MyNumber(long _v, int _f, int _t, int _l){
         this.value = _v;
         this.fromIndex = _f;
         this.toIndex = _t;
         this.line = _l;
      }
   }

   public Day03(){
      fileName = "2023/day03.txt";
   }

   @Override
   public void fillDataStruct() {
      matrix = Utils.getCharMatrix(lines);
      matrixSize = matrix.length-1;
      System.out.println("Matrix size : " + matrixSize);
   }


   @Override
   public String processPart1() {
      long totalScore = 0l;
      String currentNumber = "";
      boolean hasSymbol = false;
      for (int i = 0; i < matrix.length; i++){
         if (hasSymbol){
            totalScore += Long.valueOf(currentNumber);
            numbers.add(new MyNumber(Long.valueOf(currentNumber), (matrix.length)-currentNumber.length(), matrix.length-1, i-1));
            currentNumber = "";
            hasSymbol = false;
         }
         
         for (int j = 0; j < matrix[i].length; j++){
            
            if (matrix[i][j].getValue().matches("\\d+")){
               currentNumber += matrix[i][j].getValue();
               if (hasSymbol(matrix, i, j)){
                  hasSymbol = true;
               }
            }else{
               if (!currentNumber.isEmpty()){
                  if (hasSymbol){
                     totalScore += Long.valueOf(currentNumber);
                     numbers.add(new MyNumber(Long.valueOf(currentNumber), (j)-currentNumber.length(), j-1, i));
                  }
                  currentNumber = "";
                  hasSymbol = false;
               }
            }
         }
      }
      return totalScore+"";
   }

   private boolean hasSymbol(MatrixElementChar[][] matrix2, int i, int j) {
      if (i > 0){
         if (j > 0){
            if (isSymbol(matrix[i-1][j-1].getValue())) return true;
         }
         if (j < matrixSize){
            if (isSymbol(matrix[i-1][j+1].getValue())) return true;
         }
         if (isSymbol(matrix[i-1][j].getValue())) return true;
      }
      if (j > 0){
         if (i < matrixSize){
            if (isSymbol(matrix[i+1][j-1].getValue())) return true;
         }
         if (isSymbol(matrix[i][j-1].getValue())) return true;
      }
      if (j < matrixSize && i < matrixSize){
         if (isSymbol(matrix[i+1][j+1].getValue())) return true;
      }
      if (i < matrixSize){
         if (isSymbol(matrix[i+1][j].getValue())) return true;
      }
      if (j < matrixSize){
         if (isSymbol(matrix[i][j+1].getValue())) return true;
      }
      
      return false;
   }

   private boolean isSymbol(String str) {
      return (!str.equals(".") && !str.matches("\\d+"));
   }

   @Override
   public String processPart2() {
      long totalScore = 0l;
      for (int i = 0; i < matrix.length; i++){
         for (int j = 0; j < matrix[i].length; j++){
            if (matrix[i][j].getValue().equals("*")){
               //find adjacent numbers
               List<Long> adjacent = new ArrayList<>();
               for (MyNumber mn : numbers){
                  if (!(mn.line == i || mn.line == i-1 || mn.line == i+1)){
                     continue;
                  }
                  if (!(mn.fromIndex == j-1 || mn.fromIndex == j || mn.fromIndex == j+1 || mn.toIndex == j-1 || mn.toIndex == j || mn.toIndex == j+1)){
                     continue;
                  }
                  adjacent.add(mn.value);
                  if (adjacent.size() > 2) break;
               }
               if (adjacent.size() == 2){
                  totalScore +=adjacent.get(0) * adjacent.get(1);
               }
            }
         }
      }
      return totalScore+"";
   }
}
