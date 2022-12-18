package be.dno;

public class PointLong {
   private long x;
   private long y;
   public long getX() {
      return x;
   }
   public void setX(long x) {
      this.x = x;
   }
   public long getY() {
      return y;
   }
   public void setY(long y) {
      this.y = y;
   }
   public PointLong(long x, long y) {
      this.x = x;
      this.y = y;
   }

   public long getManhattanDistance(Point that) {
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
         PointLong other = (PointLong) obj;
      if (x != other.x)
         return false;
      if (y != other.y)
         return false;
      return true;
   }
   @Override
   public String toString() {
      return "PointLong [x=" + x + ", y=" + y + "]";
   }
   public void setLocation(long x, long y) {
      this.x = x;
      this.y = y;
   }

   
}
