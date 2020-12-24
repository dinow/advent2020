package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import java.awt.geom.Point2D;

import be.dno.Day;

public class Day24 implements Day{

   private Set<Point2D> blackPoints = new HashSet<>();
   private List<String> lines;

   @Override
   public void fillDataStruct(String fileName) throws IOException {
      lines = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
   }

   @Override
   public String processPart1() {

      for(String line : lines){
         processLinePart1(line);
      }

      return ""+blackPoints.size();
   }

   @Override
   public String processPart2() {
      for (int i = 0; i < 100; i++){
         flipTiles();
         //System.out.println("Day "+(i+1)+" ["+blackPoints.size()+"]");
      }
      return ""+blackPoints.size();
   }

   private void flipTiles(){
      double maxY = Double.MIN_VALUE;
      double minY = Double.MAX_VALUE;
      double maxX = Double.MIN_VALUE;
      double minX = Double.MAX_VALUE;
      for(Point2D point : blackPoints){
         if (point.getY() > maxY) maxY = point.getY();
         if (point.getY() < minY) minY = point.getY();
         if (point.getX() > maxX) maxX = point.getX();
         if (point.getX() < minX) minX = point.getX();
      }
      maxY+=1;
      maxX+=1;
      minY-=1;
      minX-=1;

      Set<Point2D> futureBlacks = new HashSet<>();
      Set<Point2D> futureWhites = new HashSet<>();

      //Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
      for(Point2D point : blackPoints){
         int blacks = countBlacks(point);
         if (blacks == 0 || blacks > 2) futureWhites.add(point);
      }

      //Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
      for (double x = minX; x <= maxX; x++){
         for (double y = minY; y <= maxY; y++){
            Point2D point = new Point2D.Double(x, y);
            if (!blackPoints.contains(point)){
               int blacks = countBlacks(point);
               if (blacks == 2) futureBlacks.add(point);
            }
         }
      }


      //actual flip
      blackPoints.removeAll(futureWhites);
      blackPoints.addAll(futureBlacks);

   }

   private int countBlacks(Point2D point){
      int cpt = 0;
      if (blackPoints.contains(new Point2D.Double(point.getX()+1, point.getY()  ))) cpt++;
      if (blackPoints.contains(new Point2D.Double(point.getX()-1, point.getY()  ))) cpt++;
      if (blackPoints.contains(new Point2D.Double(point.getX()  , point.getY()+1))) cpt++;
      if (blackPoints.contains(new Point2D.Double(point.getX()  , point.getY()-1))) cpt++;
      if (blackPoints.contains(new Point2D.Double(point.getX()-1, point.getY()+1))) cpt++;
      if (blackPoints.contains(new Point2D.Double(point.getX()+1, point.getY()-1))) cpt++;
      return cpt;
   }

   private void processLinePart1(String line){
      String workingLine = line;
      Point2D referenceTile = new Point2D.Double(0, 0);
      while(!workingLine.isEmpty()){
         double x = referenceTile.getX();
         double y = referenceTile.getY();
         int instrLength = 2;
         if (workingLine.startsWith("e")){
            x++;
            instrLength = 1;
         } else if (workingLine.startsWith("w")){
            x--;
            instrLength = 1;
         } else if (workingLine.startsWith("se")){
            y++;
         } else if (workingLine.startsWith("sw")){
            x--;
            y++;
         } else if (workingLine.startsWith("nw")){
            y--;
         } else if (workingLine.startsWith("ne")){
            x++;
            y--;
         } else {
            System.out.println("Uknlown command ["+workingLine+"]");
         }

         workingLine = workingLine.substring(instrLength, workingLine.length());
         referenceTile.setLocation(x, y);
      }
      Point2D copyTile = new Point2D.Double(referenceTile.getX(), referenceTile.getY());
      if (blackPoints.contains(copyTile)){
         blackPoints.remove(copyTile);
      } else {
         blackPoints.add(copyTile);
      }
   }

}
