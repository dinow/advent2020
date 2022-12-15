package be.dno.advent2022;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import be.dno.Point;

import be.dno.Day;
import be.dno.Utils;


public class Day15 extends Day {

   private Set<Point> beacons;
   private Set<Point> sensors;
   private Map<Point, Point> couples;
   //private static final int ANSWER_ROW = 10;
   private static final int ANSWER_ROW = 2_000_000;
   //private static final int MAX_P2 = 20;
   private static final int MAX_P2 = 4_000_000;

   public Day15(){
      fileName = "2022/day15.txt";
   }

   @Override
   public void fillDataStruct() {
      couples = new HashMap<>();
      beacons = new HashSet<>();
      sensors = new HashSet<>();
      for(String line : lines){
         List<Integer> coords = Utils.extractNumbersList(line);
         Point sensor = new Point(coords.get(0), coords.get(1));
         Point beacon = new Point(coords.get(2), coords.get(3));
         couples.put(sensor, beacon);
         sensors.add(sensor);
         beacons.add(beacon);
      }
   }

   @Override
   public String processPart1() throws Exception {
      Set<Point> answerRow = new HashSet<>();
      for (Entry<Point, Point> couple : couples.entrySet()){
         Point sensor = couple.getKey();
         Point beacon = couple.getValue();
         int manhattanDistance = sensor.getManhattanDistance(beacon);
         int startI = sensor.getX()-(manhattanDistance);
         int startJ = sensor.getY()-(manhattanDistance);
         int endI   = sensor.getX()+(manhattanDistance);
         int endJ   = sensor.getY()+(manhattanDistance);

         if (!(ANSWER_ROW <= endJ && ANSWER_ROW >= startJ)) continue;
         //System.out.println("Check sensor " + sensor.toString() + " and beacon " + beacon.toString());

         for(int i = startI; i <= endI; i++){
            Point p = new Point(i, ANSWER_ROW);
            if (sensors.contains(p)) continue;
            if (beacons.contains(p)) continue;
            if (sensor.getManhattanDistance(p)<=manhattanDistance){
               answerRow.add(p);
            }
         }
      }
      return String.valueOf(answerRow.size());
   }

   @Override
   public String processPart2() throws Exception {
      isCovered(new Point(8, 17));
      Point answer = new Point(1,1);
      Point actualAnswer = new Point(1,1);
      boolean found = false;
      for (Entry<Point, Point> couple : couples.entrySet()){
         //go through external border, for each point, check if within range of other sensors
         Point sensor = couple.getKey();
         Point beacon = couple.getValue();
         int manhattanDistance = sensor.getManhattanDistance(beacon);
         answer.setLocation(sensor.getX(), sensor.getY()-(manhattanDistance+1));
         Point endPoint = new Point(answer.getX()-1, answer.getY()+1);
         boolean cont = true;
         boolean topRight     = true;
         boolean bottomRight  = false;
         boolean bottomLeft   = false;
         boolean topLeft      = false;
         while(cont){
            if (endPoint.equals(answer)){
               cont = false;
            }

            if (!isCovered(answer)){
               if (answer.getX() > actualAnswer.getX()){
                  actualAnswer = new Point(answer.getX(), answer.getY());
               }
            }
            
            if (topRight && answer.getY() >= sensor.getY() && answer.getX() >= sensor.getX()){
               topRight    = false;
               bottomRight = true;
               bottomLeft  = false;
               topLeft     = false;
            } else if (bottomRight && answer.getY() >= sensor.getY() && answer.getX() <= sensor.getX()){
               topRight    = false;
               bottomRight = false;
               bottomLeft  = true;
               topLeft     = false;
            } else if (bottomLeft && answer.getY() <= sensor.getY()){
               topRight    = false;
               bottomRight = false;
               bottomLeft  = false;
               topLeft     = true;
            }

            int nextY = answer.getY();
            int nextX = answer.getX();

            if (topRight){
               nextX++;
               nextY++;
            }
            if (bottomRight){
               nextX--;
               nextY++;
            }
            if (bottomLeft){
               nextX--;
               nextY--;
            }
            if (topLeft){
               nextX++;
               nextY--;
            }
            answer.setLocation(nextX, nextY);
         }
         if(found) break;
      }
      long rep = 4_000_000l;
      rep*= actualAnswer.getX();
      rep += actualAnswer.getY();
      return String.valueOf(rep);
   }

   private boolean isCovered(Point answer) {
      if (answer.getX() < 0      || answer.getY() < 0     ) return true;
      if (answer.getX() > MAX_P2 || answer.getY() > MAX_P2) return true;
      for (Entry<Point, Point> couple : couples.entrySet()){
         Point sensor = couple.getKey();
         Point beacon = couple.getValue();
         int manhattanDistanceBeacon = sensor.getManhattanDistance(beacon);
         int manhattanDistanceAnswer = sensor.getManhattanDistance(answer);
         if (manhattanDistanceAnswer <= manhattanDistanceBeacon){
            return true;
         }
      }
      return false;
   }
}

