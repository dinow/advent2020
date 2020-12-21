package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.stream;
import org.apache.commons.io.IOUtils;
import be.dno.Day_old;
public class Day13 implements Day_old{


	@Override
	public void run(String fileName) throws IOException {
		List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
		int earliestTime = Integer.parseInt(contents.get(0));
		int minutesToWait = Integer.MAX_VALUE;
		int choosenBusLine = -1;
		for (String sBusLine : contents.get(1).split(",")){
			if (sBusLine.equals("x")) continue;
			int busLine = Integer.parseInt(sBusLine);
			int closestNextTime = ((int)Math.ceil((double)earliestTime/busLine) * busLine);
			if (closestNextTime - earliestTime < minutesToWait){
				minutesToWait = closestNextTime - earliestTime;
				choosenBusLine = busLine;
			}

		}
		System.out.println("part 1 : " + (choosenBusLine*minutesToWait));
		String[] sbuses = contents.get(1).split(",");
		List<Long> iBuses = new ArrayList<>();
		List<Long> iRems = new ArrayList<>();
		int idx = 0;
		for (String bus : sbuses){
			if (!bus.equals("x")){
				iBuses.add(Long.valueOf(bus));
				iRems.add(Long.valueOf(bus) - idx);
			}
			idx++;
		}

		long[] buses = iBuses.stream().mapToLong(Long::longValue).toArray();
		long[] rems = iRems.stream().mapToLong(Long::longValue).toArray();

		long num[] = buses; 
        long rem[] = rems; 
		System.out.println("part 2 : " +chineseRemainder(num, rem)); 
		
		System.out.println ( (600689120448303l/1000000000)*7);
	}



	public long chineseRemainder(long[] n, long[] a) {
 
        long prod = stream(n).reduce(1, (i, j) -> i * j);
 
        long p, sm = 0;
        for (int i = 0; i < n.length; i++) {
            p = prod / n[i];
            sm += a[i] * mulInv(p, n[i]) * p;
        }
        return sm % prod;
    }
 
    private long mulInv(long a, long b) {
        long b0 = b;
        long x0 = 0;
        long x1 = 1;
 
        if (b == 1)
            return 1;
 
        while (a > 1) {
            long q = a / b;
            long amb = a % b;
            a = b;
            b = amb;
            long xqx = x1 - q * x0;
            x1 = x0;
            x0 = xqx;
        }
 
        if (x1 < 0)
            x1 += b0;
 
        return x1;
    }
	
}
