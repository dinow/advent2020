package be.dno.advent2022;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import be.dno.Day;


public class Day13 extends Day {

   class Signal {

   }

   public Day13(){
      fileName = "2022/day13.txt";
   }

   @Override
   public void fillDataStruct() {

   }

   @Override
   public String processPart1() throws Exception {
      String line1 = null;
      String line2 = null;
      int index = 1;
      int answer = 0;
      for (String line : lines){
         if (line.isEmpty()){
            line1 = null;
            line2 = null;
            index++;
            continue;
         }
         if (line1 == null){
            line1 = line;
         } else {
            line2 = line;
            boolean comparison = compare(line1, line2);
            if (comparison){
               answer+=index;
            }
         }
      }
      return String.valueOf(answer);
   }

   private Boolean compareJson(JsonArray array1, JsonArray array2) {
      int idx = 0;
      for (int i = 0; i < array1.size(); i++){
         idx++;
         if (i >= array2.size()) return false;
         if (array1.get(i).isJsonArray() && array2.get(i).isJsonPrimitive()){
            JsonArray j2 = new JsonArray();
            j2.add(array2.get(i));
            Boolean b = compareJson(array1.get(i).getAsJsonArray(), j2);
            if (b == null) continue;
            return b.booleanValue();
         }
         if (array1.get(i).isJsonPrimitive() && array2.get(i).isJsonArray()){
            JsonArray j1 = new JsonArray();
            j1.add(array1.get(i));
            Boolean b = compareJson(j1, array2.get(i).getAsJsonArray());
            if (b == null) continue;
            return b.booleanValue();
         }
         if (array1.get(i).isJsonArray() && array2.get(i).isJsonArray()){
            Boolean b = compareJson(array1.get(i).getAsJsonArray(), array2.get(i).getAsJsonArray());
            if (b == null) continue;
            return b.booleanValue();
         }
         int leftValue  = array1.get(i).getAsInt();
         int rightValue = array2.get(i).getAsInt();
         if (leftValue == rightValue) continue;
         if (leftValue < rightValue) return true;
         if (leftValue > rightValue) return false;
      }
      if (array2.size() > idx) return true;
      return null;
   }

   private boolean compare(String line1, String line2) {
      Gson g = new Gson();
      JsonArray array1 = g.fromJson(line1, JsonArray.class);
      JsonArray array2 = g.fromJson(line2, JsonArray.class);

      return compareJson(array1, array2).booleanValue();
   }

   @Override
   public String processPart2() throws Exception {
      Gson g = new Gson();
      List<JsonArray> packets = new ArrayList<>();
      for (String line : lines){
         if (!line.isEmpty()){
            packets.add(g.fromJson(line, JsonArray.class));
         }
      }
      packets.add(g.fromJson("[[2]]", JsonArray.class));
      packets.add(g.fromJson("[[6]]", JsonArray.class));

      Collections.sort(packets, new Comparator<JsonArray>() {
         @Override
         public int compare(JsonArray array1, JsonArray array2) {
            Boolean result = compareJson(array1, array2);
            if (result == null) return 0;
            return result.booleanValue() ? -1 : 1;
         }
      });

      int idx2 = 0;
      int idx6 = 0;
      int idx = 0;
      for (JsonArray j : packets){
         if (j.toString().equals("[[2]]")) idx2 = idx+1;
         if (j.toString().equals("[[6]]")) idx6 = idx+1;
         idx++;
      }

      return String.valueOf(idx2*idx6);
   }
}

