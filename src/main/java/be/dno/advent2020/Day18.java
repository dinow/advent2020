package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import be.dno.Day;

public class Day18 implements Day{
   private static int currentPart = 0;

   private static Pattern innerMostParensRegex = Pattern.compile(
            //negative lookahead to ensure we find the last (
            // and then lazy dot quantifier .*?) to get the first right paren after that
            "\\((?!.*\\().*?\\)");

   @Override
   public void run(String fileName) throws IOException {
      List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
      long startTime = System.nanoTime();
      System.out.println("Part 1 : " + processPart1(contents));
      System.out.println("Part 2 : " + processPart2(contents));
      long endTime = System.nanoTime();
      long timeElapsed = endTime - startTime;
      System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
   }

   private long processPart1(List<String> contents) {
      //AtomicLong acc = new AtomicLong();
      long lineResults = 0l;
      for (String line : contents){
         lineResults+= processLine(new String(line), 1);
         //long redditResult = evaluateExpression(new String(line));
      }
      return lineResults;
   }

   private long processPart2(List<String> contents) {
      long lineResults = 0l;
      for (String line : contents){
         lineResults+= processLine(new String(line), 2);
      }
      return lineResults;
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


   private static long evaluateExpression(String expression) {
      //while the expression has any more parenthesis inside, find the innermost parenthetical expressions
      // and recursively call this function on that substring to evaluate them
      while (expression.contains("(")) {
          Matcher matcher = innerMostParensRegex.matcher(expression);
          matcher.find();
          String innerExpression = matcher.group().replaceAll("[()]", "");
          expression = matcher.replaceFirst(String.valueOf(evaluateExpression(innerExpression)));

      }

      List<String> symbols = new ArrayList<>(Arrays.asList(expression.split(" ")));

      //extra logic needed for part 2 only - handle addition operations first
      if (currentPart == 2) {
          //with parens already handled through the above recursion
          //go ahead and evaluate all plus operations from left to right
          while (symbols.contains("+")) {
              //note this wouldn't handle a string beginning or ending with + but that's ok
              int iOp = symbols.indexOf("+");
              int iNum1 = iOp - 1;
              int iNum2 = iOp + 1;
              //replace num1 with the summed value, and remove the 2 other symbols (+, and num2)
              symbols.set(iNum1, String.valueOf(Long.parseLong(symbols.get(iNum1)) + Long.parseLong(symbols.get(iNum2))));
              symbols.remove(iNum2);
              symbols.remove(iOp);
          }
      }

      //start with the first number and then evaluate from left to right
      long currNum = Long.parseLong(symbols.remove(0));
      String currOperation = null;
      for (String symbol : symbols) {
          //if the currOperation is null, the current symbol should be + or *
          if (currOperation == null) {
              currOperation = symbol;
          } else {
              //we already know what operation to do, so do it
              switch (currOperation) {
                  case "*":
                      currNum *= Long.parseLong(symbol);
                      break;
                  case "+":
                      currNum += Long.parseLong(symbol);
                      break;
                  default:
                      throw new RuntimeException("error parsing symbols");
              }
              //clear out the operation, the next symbol should be another one
              currOperation = null;
          }
      }

      return currNum;
  }

}
