package be.dno.advent2016;

import java.util.ArrayList;
import java.util.List;

import be.dno.Day;
import be.dno.Utils;

public class Day22 extends Day{

   private static class Node extends java.awt.Point {
      final int size;
      final int used;
      final int avail;
  
      public Node(int x, int y, int size, int used, int avail) {
         super(x, y);
         this.size = size;
         this.used = used;
         this.avail = avail;
      }
   }

   private List<Node> nodes = new ArrayList<>();

   public Day22(){
      fileName = "2016/day22.txt";
   }

   //root@ebhq-gridcenter# df -h
   //Filesystem              Size  Used  Avail  Use%
   ///dev/grid/node-x0-y0     88T   66T    22T   75%
   ///dev/grid/node-x0-y1     85T   65T    20T   76%
   public void fillDataStruct(){
      for(String line : lines){
         Integer[] lineData = Utils.extractNumbers(line);
         nodes.add(new Node(lineData[0], lineData[1], lineData[2], lineData[3], lineData[4]));
      }
   }

   @Override
   public String processPart1() {
      int cpt = 0;
      for(Node node_a : nodes){
         if (node_a.used > 0){
            for(Node node_b : nodes){
               if (!node_a.equals(node_b)){
                  if (node_b.avail >= node_a.used){
                     cpt++;
                  }
               }
            }
         }
      }
      return ""+cpt;
   }

   @Override
   public String processPart2() {
      return "";
   }


}
