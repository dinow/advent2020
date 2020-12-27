package be.dno.advent2015;

import be.dno.Day;

public class Day24 extends Day {
   private int[] number;
   private long minQE   = Long.MAX_VALUE;
   private long minPack = Long.MAX_VALUE;
   private int groupWeight;
   private boolean part1check = true;

   @Override
   public void fillDataStruct() {
      number = new int[lines.size()];
      for (int i = 0; i < lines.size(); i++){
         number[i] = Integer.parseInt(lines.get(i).trim());
      }
      
   }

   @Override
   public String processPart1() {
      
      int totalWeight = 0;
      for (int i = 0; i < number.length; i++){
         totalWeight += number[i];
      }
      groupWeight = totalWeight/3;


      int combLength = 1;
      while (combLength <= minPack && part1check){
         printCombination(number, number.length, combLength); 
         combLength++;
      }
      
      return ""+minQE;
   }


   /* arr[]  ---> Input Array 
    data[] ---> Temporary array to store current combination 
    start & end ---> Staring and Ending indexes in arr[] 
    index  ---> Current index in data[] 
    r ---> Size of a combination to be printed */
    private void combinationUtil(int arr[], int data[], int start, int end, int index, int r) { 
       if (r > minPack) return;
        // Current combination is ready to be printed, print it 
        if (index == r) { 
         int sum = 0;
         long QE = 1;
         for (int j=0; j<r; j++) {
            sum += data[j];
            if (sum > groupWeight) break;
			   QE *= data[j];
            if (r > minPack) break;
            if (sum == groupWeight){
               if (r <= minPack){
                  if (r < minPack) System.out.println("Found a new min length : " + r);
                  minPack = r;
                  if (QE < minQE){
                     minQE = QE;
                     System.out.println("new min QE: " + minQE);
                  }
               }
            }
         }
         return;
        } 
  
        // replace index with all possible elements. The condition 
        // "end-i+1 >= r-index" makes sure that including one element 
        // at index will make a combination with remaining elements 
        // at remaining positions 
        for (int i=start; i<=end && end-i+1 >= r-index; i++) { 
            data[index] = arr[i]; 
            combinationUtil(arr, data, i+1, end, index+1, r); 
        } 
    } 
  
    // The main function that prints all combinations of size r 
    // in arr[] of size n. This function mainly uses combinationUtil() 
    private void printCombination(int arr[], int n, int r) { 
        // A temporary array to store all combination one by one 
        int data[]=new int[r]; 
  
        // Print all combination using temprary array 'data[]' 
        combinationUtil(arr, data, 0, n-1, 0, r); 
    } 

   @Override
   public String processPart2() {
      int totalWeight = 0;
      for (int i = 0; i < number.length; i++){
         totalWeight += number[i];
      }
      groupWeight = totalWeight/4;


      int combLength = 1;
      while (combLength <= minPack && part1check){
         printCombination(number, number.length, combLength); 
         combLength++;
      }
      
      return ""+minQE;
   }
    
}
