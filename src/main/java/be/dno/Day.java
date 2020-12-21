package be.dno;

import java.io.IOException;

public interface Day {
   public void fillDataStruct(String fileName) throws IOException;
   public String processPart1();
   public String processPart2();
}
