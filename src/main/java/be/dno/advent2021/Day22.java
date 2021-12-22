package be.dno.advent2021;

import java.util.HashMap;
import java.util.Map;

import be.dno.Day;
import be.dno.Utils;

public class Day22 extends Day {

   private Map<Point3D, Boolean> cubes;
   
   public Day22(){
      fileName = "2021/day22.txt";
   }

   @Override
   public void fillDataStruct(){
      cubes = new HashMap<>();
   }

   @Override
   public String processPart1(){ 
      for (String line : lines){
         String action = line.substring(0, 3).trim();
         Boolean status = action.equals("on");

         Integer[] rangeX = getRange(line.substring(line.indexOf("x=")+2, line.indexOf(",y=")));
         Integer[] rangeY = getRange(line.substring(line.indexOf("y=")+2, line.indexOf(",z=")));
         Integer[] rangeZ = getRange(line.substring(line.indexOf("z=")+2, line.length()));

         long lowerX = Math.min(rangeX[0], rangeX[1]);
         long upperX = Math.max(rangeX[0], rangeX[1]);
         long lowerY = Math.min(rangeY[0], rangeY[1]);
         long upperY = Math.max(rangeY[0], rangeY[1]);
         long lowerZ = Math.min(rangeZ[0], rangeZ[1]);
         long upperZ = Math.max(rangeZ[0], rangeZ[1]);
         if (lowerX < -50 || upperX > 50 || lowerY < -50 || upperY > 50 || lowerY < -50 || upperY > 50) continue;
         for (long x = lowerX; x <= upperX; x++){
            for (long y = lowerY; y <= upperY; y++){
               for (long z = lowerZ; z <= upperZ; z++){
                  Point3D cube = new Point3D(x, y, z);
                  if (status){
                     if (!cubes.containsKey(cube)){
                        cubes.put(cube, status);
                     }
                  }else{
                     cubes.remove(cube);
                  }
               }
            }
         }
      }
      return cubes.size()+"";
   }
   @Override
   public String processPart2(){ 
      //
      return "";
   }

   private Integer[] getRange(String substring) {
      Integer[] range = new Integer[2];
      String[] s = substring.replace("..", "T").split("T");
      range[0] = Integer.valueOf(s[0]);
      range[1] = Integer.valueOf(s[1]);
      return range;
   }
}

class Point3D{
   long x;
   long y;
   long z;

   public Point3D(long _x, long _y, long _z){
      this.x = _x;
      this.y = _y;
      this.z = _z;
   }

   

   @Override
   public String toString() {
      return "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
   }



   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (x ^ (x >>> 32));
      result = prime * result + (int) (y ^ (y >>> 32));
      result = prime * result + (int) (z ^ (z >>> 32));
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Point3D other = (Point3D) obj;
      if (x != other.x)
         return false;
      if (y != other.y)
         return false;
      if (z != other.z)
         return false;
      return true;
   }

   

}