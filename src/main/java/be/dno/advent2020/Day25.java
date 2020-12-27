package be.dno.advent2020;

import be.dno.Day;

public class Day25 extends Day{

   private Long cardPublicKey;
   private Long doorPublicKey;
   private final static int SUBJECT_NUMBER = 7;

   @Override
   public void fillDataStruct() {
      String input = lines.get(0);
      cardPublicKey = Long.valueOf(input.split(";")[0]);
      doorPublicKey = Long.valueOf(input.split(";")[1]);
   }

   @Override
   public String processPart1() {
      long cardLoopNumber = getLoopSize(cardPublicKey);
      return ""+transform(doorPublicKey,cardLoopNumber);
   }

   public long getLoopSize(Long publicKey){
      long loopIdx = 0l;
      long value = 1l;
      while (value != publicKey.longValue()){
         value *= SUBJECT_NUMBER;
         value = value % 20201227;
         loopIdx++;
      }
      return loopIdx;
   }

   public long transform(long subjectNumber, long loopSize){
      long value = 1;
      for (long l = 0l; l < loopSize; l++){
         value *= subjectNumber;
         value = value % 20201227;
      }
      return value;
   }

}
