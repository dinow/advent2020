package be.dno.advent2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import be.dno.Day;

public class Day21 extends Day{
   //allergen -> Set<Line> -> Set<ingredients>
   private final Map<String, Set<Set<String>>> allergens = new HashMap<>();
   private final Map<String, Integer> allIngredients = new HashMap<>();
   private final Map<String, Set<String>> finalMap = new TreeMap<>();

   @Override
   public void fillDataStruct() {
      for (String line : lines){
         String[] content = line.split("contains");
         List<String> ingredients = Arrays.asList(content[0].replace("(","").replace(")","").split(" "));
         for(String allergen : content[1].replace("(","").replace(")","").replaceAll(" ", "").split(",")){
            if (!allergens.containsKey(allergen)) allergens.put(allergen, new HashSet<>());
            HashSet<String> line_all = new HashSet<>();
            line_all.addAll(ingredients);
            Set<Set<String>> mline = allergens.get(allergen);
            mline.add(line_all);
            allergens.put(allergen, mline);
         }
         for (String ingredient : ingredients){
            Integer cpt = 1;
            if (allIngredients.containsKey(ingredient)){
               cpt = allIngredients.get(ingredient) + 1;
            }
            allIngredients.put(ingredient, cpt);
         }
      }
   }

   @Override
   public String processPart1() {
      
      boolean allIsOne = false;
      while(allIsOne == false){
         for (String allergen : allergens.keySet()){
            Set<Set<String>> lines = allergens.get(allergen);
            Set<String> intersection = null;
            for (Set<String> line : lines){
               if (intersection == null) {
                  intersection = line;
               } else {
                  intersection.retainAll(line);
               }
            }
            finalMap.put(allergen, intersection);
         }
         allIsOne = true;
         Set<String> toRemove = new HashSet<>();
         for (String allergen : finalMap.keySet()){
            Set<String> ingredients = finalMap.get(allergen);
            if (ingredients.size() > 1) allIsOne = false;
            if (ingredients.size() == 1){
               //remove this ingredients from the other sets
               toRemove.addAll(ingredients);
            }
         }
         for (String allergen : finalMap.keySet()){
            Set<String> ingredients = finalMap.get(allergen);
            if (ingredients.size() > 1){
               ingredients.removeAll(toRemove);
               finalMap.put(allergen, ingredients);
            }
         }

      }

      for (Set<String> ingredients : finalMap.values()) {
         for (String ingredient : ingredients){
            allIngredients.remove(ingredient);
         }
      }
      int count = 0;
      for (Integer val : allIngredients.values()){
         count += val;
      }

      return ""+count;
   }

   @Override
   public String processPart2() {
      List<String> ingre = new ArrayList<>();
      for (Set<String> ingredients : finalMap.values()){
         ingre.addAll(ingredients);
      }
      return String.join(",", ingre);
   }

}
