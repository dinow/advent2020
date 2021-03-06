package be.dno.advent2020;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import be.dno.Day;

public class Day19 extends Day{
   private final Set<String> messages = new HashSet<>();
   private final Map<String, String> rules = new HashMap<>();
   private final static String REGEX_AB = "^[ab\\|\\(\\)]+$";


   @Override
   public String processPart1() {
      fillRulesAndMessages(lines);
      int cpt= 0;
      String rule = rules.get("0");
      for (String message : messages){
         if (message.matches(rule)) cpt++;
      }
      return cpt+"";
   }

   @Override
   public String processPart2() {
      fillRulesAndMessages();
      int cpt= 0;
      String rule = rules.get("0");
      for (String message : messages){
         if (message.matches(rule)) cpt++;
      }
      return cpt+"";
   }

   private void fillRulesAndMessages(List<String> lines){
      //fill rules
      boolean message_reached = false;
      for(String line : lines){
         if (line.isEmpty()) {
            message_reached = true;
            continue;
         }


         if (message_reached){
            messages.add(line);
         } else {
            String[] kv = line.split(":");
            if (kv[1].contains("a")){
               rules.put(kv[0].trim(),"a");
            } else if (kv[1].contains("b")){
               rules.put(kv[0].trim(), "b");
            } else {
               rules.put(kv[0].trim(), kv[1]);
            }
         }
      }

      boolean replacementDone = true;
      while(replacementDone){
         replacementDone = false;
         for (String rule : rules.keySet()){
            String rule_value = rules.get(rule);
            StringBuilder replaced_value = new StringBuilder();
            if (rule_value.replace(" ", "").matches(REGEX_AB)) continue;
            replacementDone = true;
            rule_value += " ";
            for (String ruleNumber : rule_value.split(" ")){
               String cleanedRuleNumber = ruleNumber.replaceAll("\\)\\(", " ").replaceAll("\\(", "").replaceAll("\\)", "");
               if (ruleNumber.isEmpty()) continue;
               if (ruleNumber.replace(" ", "").matches(REGEX_AB)){
                  replaced_value.append(ruleNumber).append(" ");
               }else {
                  replacementDone = true;
                  replaced_value.append("( ").append(rules.get(cleanedRuleNumber)).append(" )") ;
               }
            }
            rules.put(rule, replaced_value.toString().replace("  ", " ").trim());
         }
      }

      for (String rule : rules.keySet()){
         String rule_value = rules.get(rule);
         rules.put(rule, "\\b" + rule_value.replaceAll(" ", "") + "\\b");
      }


      
   }
   private void fillRulesAndMessages(){

      //part 2
      /*
         8:  42 | 42 8
         11: 42 31 | 42 11 31
       */
      String rule_42 = "("+rules.get("42").replace("\\b","") + ")";
      String rule_31 = "("+rules.get("31").replace("\\b","") + ")";

      String masterRegex = "(" + rule_42 + "+)(";
		for (int i = 1; i < 10; i++) {
			masterRegex += "(";
			for (int j = 1; j <= i; j++) {
				masterRegex += rule_42;
			}
			for (int j = 1; j <= i; j++) {
				masterRegex += rule_31;
			}
			masterRegex += ")";
			if (i < 9) {
				masterRegex += "|";
			}
		}
		masterRegex = "^(" + masterRegex + "))$";
      rules.put("0", masterRegex);
   }

}
