package be.dno;

import java.io.IOException;

public interface Day {
   public void fillDataStruct(String fileName) throws IOException;
   public long processPart1();
   public long processPart2();
}
