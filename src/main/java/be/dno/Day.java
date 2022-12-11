package be.dno;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;

public abstract class Day {
   protected List<String> lines;
   protected String fileName;
   public void readLines() throws IOException {
      if (fileName != null){
         lines = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
      }
   }
   public void fillDataStruct(){}
   public String processPart1() throws Exception{ return "";}
   public String processPart2() throws Exception{ return "";}
}
