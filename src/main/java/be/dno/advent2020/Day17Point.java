package be.dno.advent2020;

import java.util.Objects;

public class Day17Point {
   public int x;
   public int y;
   public int z;
   public int w;

   public Day17Point(int _x, int _y, int _z, int _w){
      this.x = _x;
      this.z = _z;
      this.y = _y;
      this.w = _w;
   }

   public String toString(){
      return "z="+z+",x="+x+",y="+y+",w="+w;
   }

   @Override
   public int hashCode() {
    return Objects.hash( this.x , this.y , this.z, this.w  );
   }

   @Override
   public boolean equals(Object o) {

       // If the object is compared with itself then return true
       if (o == this) {
           return true;
       }

       /* Check if o is an instance of Day17Point or not
         "null instanceof [type]" also returns false */
       if (!(o instanceof Day17Point)) {
           return false;
       }

       // typecast o to Day17Point so that we can compare data members
       Day17Point c = (Day17Point) o;

       // Compare the data members and return accordingly
       return x == c.x && y == c.y && z == c.z && w == c.w;
   }


}
