package be.dno.advent2022;

import java.util.ArrayList;
import java.util.List;

import be.dno.Day;

public class Day07 extends Day {

   Folder root;
   private static final long TOTAL_DISK_SIZE = 70000000l;
   private static final long NEEDED_SIZE = 30000000l;

   static class File{
      String name;
      long size;

      File(String name, long size){
          this.name = name;
          this.size = size;
      }

      @Override
      public String toString() {
         return this.name;
      }
  }

   static class Folder{
      String name;
      List<File> files;
      List<Folder> folders;
      Folder parent;

      Folder(String name, Folder parent){
          this.name    = name;
          this.parent  = parent;
          this.files   = new ArrayList<>();
          this.folders = new ArrayList<>();
      }

      public long getSize(){
         long size = 0;
         for (File f : this.files){
            size += f.size;
         }
         for (Folder f : this.folders){
            size += f.getSize();
         }
         return size;
      }

      @Override
      public String toString() {
         return this.name;
      }

  }

   public Day07(){
      fileName = "2022/day07.txt";
   }

   @Override
   public void fillDataStruct() {
      root = new Folder("/", null);
      Folder currentFolder = root;
      for (String line : lines){
         if (line.equals("$ cd /")){
            currentFolder = root;
         }else{
            if (line.equals("$ ls")) continue;
            if (line.startsWith("$")){
               if (line.endsWith("..")){
                  currentFolder = currentFolder.parent;
               }
               if (line.startsWith("$ cd ")){
                  String[] command = line.split(" ");
                  for (Folder f : currentFolder.folders){
                     if (f.name.equals(command[2])){
                        currentFolder = f;
                        break;
                     }
                  }
               }
            } else {
               String[] file = line.split(" ");
               if (file[0].equals("dir")){
                  currentFolder.folders.add(new Folder(file[1], currentFolder));
               } else {
                  currentFolder.files.add(new File(file[1], Long.valueOf(file[0])));
               }
            }
         }
      }
   }


   @Override
   public String processPart1() {
      int totalSize = 0;
      List<Folder> relevantFolders = new ArrayList<>();
      processAndStore(relevantFolders, root, 100000);
      for (Folder f : relevantFolders){
         totalSize += f.getSize();
      }
      return String.valueOf(totalSize);
   }

   private void processAndStore(List<Folder> relevantFolders, Folder root2, int limit) {
      if (root2.getSize() <= limit){
         relevantFolders.add(root2);
      }
      for (Folder f : root2.folders){
         processAndStore(relevantFolders, f, limit);
      }
   }

   private void processAndStore2(List<Folder> relevantFolders, Folder root2, long currentFreeSpace) {
      if (currentFreeSpace + root2.getSize() >= NEEDED_SIZE){
         relevantFolders.add(root2);
      }
      for (Folder f : root2.folders){
         processAndStore2(relevantFolders, f, currentFreeSpace);
      }
   }

   @Override
   public String processPart2() {
      long currentFreeSpace = TOTAL_DISK_SIZE - root.getSize();
      List<Folder> relevantFolders = new ArrayList<>();
      processAndStore2(relevantFolders, root, currentFreeSpace);
      long smallestSize = Long.MAX_VALUE;
      for (Folder f : relevantFolders){
         if (f.getSize() < smallestSize){
            smallestSize = f.getSize();
         }
      }
      return String.valueOf(smallestSize);
   }
}

