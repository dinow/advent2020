package be.dno.advent2020;

import be.dno.Day;

public class Day18 extends Day{

   @Override
	public String processPart1() {
      long lineResults = 0l;
      for (String line : lines){
         lineResults+= processLine(new String(line), 1);
      }
      return ""+lineResults;
   }

   @Override
	public String processPart2() {
      long lineResults = 0l;
      for (String line : lines){
         lineResults+= processLine(new String(line), 2);
      }
      return ""+lineResults;
   }

   private long processLine(String line, int mode) {
      long lineResult = 0l;
      String workingLine = line.replaceAll(" ", "");
      int position = 0;
      int nextOp;
      boolean hasOperator = true;
      String toEvaluate;
      while (hasOperator){
         nextOp = getNextOperatorPosition(workingLine, position);
         if (nextOp == -1){
            hasOperator = false;
            lineResult = Long.parseLong(workingLine);
         } else {
            int closingP = workingLine.indexOf(")");
            if (closingP != -1){
               int openingP = workingLine.substring(0, closingP).lastIndexOf("(");
               toEvaluate = workingLine.substring(openingP+1, closingP);
               toEvaluate = evalAndReplace(toEvaluate, mode);
               workingLine = workingLine.substring(0, openingP) + toEvaluate + workingLine.substring(closingP+1, workingLine.length());
            } else {
               workingLine = evalAndReplace(workingLine, mode);
            }
         }

      }
      //System.out.println(line + " = " + lineResult);
      return lineResult;
   }

   //Contains no ( nor )
   private String evalAndReplace(String workingLine, int mode){
      if (mode == 2){
         //first compute +, then *
         int plusPos = workingLine.indexOf("+");
         if (plusPos == -1) return evalAndReplace(workingLine, 1); //Only * then no brainer
         int overallMult = workingLine.indexOf("*");
         if (overallMult == -1) return evalAndReplace(workingLine, 1); //Only + then no brainer
         int lastMultPos = workingLine.substring(0, plusPos).lastIndexOf("*");
         int nextMultPos = getNextOperatorPosition(workingLine, plusPos+1);
         if (nextMultPos == -1) nextMultPos = workingLine.length();
         String toEvaluate = workingLine.substring(lastMultPos+1, nextMultPos);
         long evalResult = evaluate(toEvaluate);
         workingLine = evalAndReplace(workingLine.substring(0, lastMultPos+1) + evalResult + workingLine.substring(nextMultPos, workingLine.length()), mode);
      } else {
         int nextOp = getNextOperatorPosition(workingLine, 0);
         if (nextOp != -1){
            int nextNextOp = getNextOperatorPosition(workingLine, nextOp+1);
            if (nextNextOp == -1) nextNextOp = workingLine.length();
            String toEvaluate = workingLine.substring(0, nextNextOp);
            long evalResult = evaluate(toEvaluate);
            workingLine = evalAndReplace(evalResult + workingLine.substring(nextNextOp, workingLine.length()), mode);
         }
      }
      return workingLine;
   }

   private long evaluate(String toEvaluate) {
      String left = toEvaluate.substring(0, getNextOperatorPosition(toEvaluate, 0));
      String right = toEvaluate.substring(getNextOperatorPosition(toEvaluate, 0)+1, toEvaluate.length());
      int operatorPos = getNextOperatorPosition(toEvaluate,0);
      String operator =toEvaluate.substring(operatorPos, operatorPos+1);
      Long result = 0l;
      if (operator.equals("+")){
         result = Long.parseLong(left) + Long.parseLong(right);
      } else {
         result = Long.parseLong(left) * Long.parseLong(right);
      }
      return result;
   }

   private int getNextOperatorPosition(String line, int previousPosition) {
      int positionPlus = line.indexOf("+", previousPosition);
      int positionMult = line.indexOf("*", previousPosition);
      if (positionMult == -1) return positionPlus;
      if (positionPlus == -1) return positionMult;
      return Math.min(positionPlus, positionMult);
   }

}
