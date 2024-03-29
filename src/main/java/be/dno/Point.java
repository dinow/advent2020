package be.dno;

public class Point {
   private int x;
   private int y;
   public int getX() {
      return x;
   }
   public void setX(int x) {
      this.x = x;
   }
   public int getY() {
      return y;
   }
   public void setY(int y) {
      this.y = y;
   }
   public Point(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public int getManhattanDistance(Point that) {
      return Math.abs(Math.abs(this.getX()-that.getX()) + Math.abs(this.getY()-that.getY()));
   }

   @Override
   public int hashCode() {
      return (""+x+","+y).hashCode();
   }
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Point other = (Point) obj;
      if (x != other.x)
         return false;
      if (y != other.y)
         return false;
      return true;
   }
   @Override
   public String toString() {
      return "Point [x=" + x + ", y=" + y + "]";
   }
   public void setLocation(int x, int y) {
      this.x = x;
      this.y = y;
   }

   
}
