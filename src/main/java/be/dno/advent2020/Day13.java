package be.dno.advent2020;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.stream;
import be.dno.Day;
public class Day13 extends Day{


	@Override
	public String processPart1() {
		int earliestTime = Integer.parseInt(lines.get(0));
		int minutesToWait = Integer.MAX_VALUE;
		int choosenBusLine = -1;
		for (String sBusLine : lines.get(1).split(",")){
			if (sBusLine.equals("x")) continue;
			int busLine = Integer.parseInt(sBusLine);
			int closestNextTime = ((int)Math.ceil((double)earliestTime/busLine) * busLine);
			if (closestNextTime - earliestTime < minutesToWait){
				minutesToWait = closestNextTime - earliestTime;
				choosenBusLine = busLine;
			}

		}
		return "" + (choosenBusLine*minutesToWait);
	}

	@Override
	public String processPart2() {
		String[] sbuses = lines.get(1).split(",");
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
		return "" +chineseRemainder(num, rem); 
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
