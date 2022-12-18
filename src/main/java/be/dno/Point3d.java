package be.dno;

public class Point3d {
   private int x;
   private int y;
   private int z;
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
   
   public Point3d(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }
 
   @Override
   public int hashCode() {
      return (""+x+","+y+","+z).hashCode();
   }
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
         Point3d other = (Point3d) obj;
      if (x != other.x)
         return false;
      if (y != other.y)
         return false;
      if (z != other.z)
         return false;
      return true;
   }
   @Override
   public String toString() {
      return "Point [x=" + x + ", y=" + y + ", z="+z+"]";
   }
   public void setLocation(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }
   public int getZ() {
      return z;
   }
   public void setZ(int z) {
      this.z = z;
   }

   
}
