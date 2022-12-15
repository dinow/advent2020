package be.dno.advent2022;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import be.dno.Point;

import be.dno.Day;
import be.dno.Utils;


public class Day15 extends Day {

   private Map<Point, String> map;
   private Set<Point> answerRow;
   private Set<Point> beacons;
   private Set<Point> sensors;
   private static final int ANSWER_ROW = 10;

   public Day15(){
      fileName = "2022/day15.txt";
   }

   @Override
   public void fillDataStruct() {
      map = new HashMap<>();
      answerRow = new HashSet<>();
      beacons = new HashSet<>();
      sensors = new HashSet<>();
      for(String line : lines){
         List<Integer> coords = Utils.extractNumbersList(line);
         Point sensor = new Point(coords.get(0), coords.get(1));
         Point beacon = new Point(coords.get(2), coords.get(3));
         map.put(sensor, "S");
         map.put(beacon, "B");
         sensors.add(sensor);
         beacons.add(beacon);
         //if (sensor.getY() == ANSWER_ROW){
         //   System.out.println("sensor for col " + sensor.getX());
         //   answerRow.add(sensor);
         //}
         //if (beacon.getY() == ANSWER_ROW){
         //   System.out.println("beacon for col " + beacon.getX());
         //   answerRow.add(beacon);
         //}
         int manhattanDistance = sensor.getManhattanDistance(beacon);
         int startI = sensor.getX()-(manhattanDistance);
         int startJ = sensor.getY()-(manhattanDistance);
         int endI   = sensor.getX()+(manhattanDistance);
         int endJ   = sensor.getY()+(manhattanDistance);
         //add in radius manhathan distance
         for(int i = startI; i <= endI; i++){
            for(int j = startJ; j <= endJ; j++){
               Point p = new Point(i, j);
               if (sensors.contains(p)) continue;
               if (beacons.contains(p)) continue;
               if (sensor.getManhattanDistance(p)<=manhattanDistance){
                  //map.put(p, "X");
                  if (p.getY() == ANSWER_ROW){
                     //System.out.println("add for col " + p.getX());
                     answerRow.add(p);
                  }
               }
            }
         }
      }
   }

   @Override
   public String processPart1() throws Exception {
      return String.valueOf(answerRow.size());
   }

   @Override
   public String processPart2() throws Exception {
      return String.valueOf(0);
   }
}

