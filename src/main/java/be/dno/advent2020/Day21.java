package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import be.dno.Day;

public class Day21 implements Day{

   @Override
   public void fillDataStruct(String fileName) throws IOException {
      List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
   }

   @Override
   public long processPart1() {
      return 0l;
   }


   @Override
   public long processPart2() {
      return 0l;
   }

}
