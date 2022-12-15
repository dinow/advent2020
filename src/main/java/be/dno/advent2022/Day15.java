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

   private Map<Point, String> map;
   private Set<Point> beacons;
   private Set<Point> sensors;
   private Map<Point, Point> couples;
   //private static final int ANSWER_ROW = 10;
   private static final int ANSWER_ROW = 2_000_000;

   public Day15(){
      fileName = "2022/day15.txt";
   }

   @Override
   public void fillDataStruct() {
      map = new HashMap<>();
      couples = new HashMap<>();
      beacons = new HashSet<>();
      sensors = new HashSet<>();
      for(String line : lines){
         List<Integer> coords = Utils.extractNumbersList(line);
         Point sensor = new Point(coords.get(0), coords.get(1));
         Point beacon = new Point(coords.get(2), coords.get(3));
         couples.put(sensor, beacon);
         map.put(sensor, "S");
         map.put(beacon, "B");
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
         //System.out.println("Check sensor " + sensor.toString() + " and beacon " + beacon.toString());
         int manhattanDistance = sensor.getManhattanDistance(beacon);
         int startI = sensor.getX()-(manhattanDistance);
         int startJ = sensor.getY()-(manhattanDistance);
         int endI   = sensor.getX()+(manhattanDistance);
         int endJ   = sensor.getY()+(manhattanDistance);

         if (!(ANSWER_ROW <= endJ && ANSWER_ROW >= startJ)) continue;
         System.out.println("Check sensor " + sensor.toString() + " and beacon " + beacon.toString());
         //startJ = ANSWER_ROW;
         //endJ = ANSWER_ROW;

         //add in radius manhathan distance
         for(int i = startI; i <= endI; i++){
            //for(int j = startJ; j <= endJ; j++){
               //if (j != ANSWER_ROW) continue;
               Point p = new Point(i, ANSWER_ROW);
               if (sensors.contains(p)) continue;
               if (beacons.contains(p)) continue;
               if (sensor.getManhattanDistance(p)<=manhattanDistance){
                  //map.put(p, "X");
                  //System.out.println("add for col " + p.getX());
                  answerRow.add(p);
               }
            //}
         }
      }
      //4218988 too low
      return String.valueOf(answerRow.size());
   }

   @Override
   public String processPart2() throws Exception {
      return String.valueOf(0);
   }
}

