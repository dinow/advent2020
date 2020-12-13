package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

public class Day07 implements Day {
	private static final String MY_BAG = "shiny gold";
	private final Map<String, Day07Relation> mapContains = new HashMap<>();
	@Override
	public void run(String fileName) throws IOException {
		List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
		Set<String> superBags = new HashSet<>();
		boolean found = true;
		superBags.add(MY_BAG);

		for(String line : contents) {
			Day07Relation rel = getRelation(line);
			mapContains.put(rel.bagName, rel);
		}

		String[] lines = contents.toArray(new String[0]);
		while(found) {
			found = false;
			for(int i = 0; i < lines.length; i++) {
				String[] parts = lines[i].split("bags contain");
				if (parts.length <= 1) continue;
				String[] currentSuperBags = superBags.toArray(new String[0]);
				for (String superBag : currentSuperBags) {
					if (parts[1].contains(superBag)){
						superBags.add(parts[0].trim());
						found = true;
						lines[i] = "";
					}
				}
			}
		}
		System.out.println("Part 1 : "+ (superBags.size()-1));
		int sum2 = addRecurs(MY_BAG, 1) + mapContains.get(MY_BAG).getBagCount();
		System.out.println("Part 2 : "+ (sum2));
	}

	private int addRecurs(String currentBag, int currentMult) {
		Day07Relation rel = mapContains.get(currentBag);
		int sum = 0;
		for(String subBag : rel.subBags.keySet()) {
			int nbMult = rel.subBags.get(subBag) * currentMult;
			sum += (mapContains.get(subBag).getBagCount() * nbMult);
			sum += addRecurs(subBag, nbMult);
		}
		return sum;
	}

	private Day07Relation getRelation(String line) {
		Day07Relation dayRel = new Day07Relation();
		String bagName = line.split("bags contain")[0].trim();
		dayRel.bagName = bagName;
		String rightParts[] = line.split("bags contain")[1].split(",");
		for(String rightPart : rightParts) {
			String cleanRight = rightPart.replaceAll("bags", "bag").replaceAll("bag", "").replaceAll("\\.", "").trim();
			if (!cleanRight.equals("no other")) {
				String subBagName = cleanRight.substring(cleanRight.indexOf(" ")).trim();
				String count = cleanRight.substring(0, cleanRight.indexOf(" ")).trim();
				dayRel.subBags.put(subBagName, Integer.parseInt(count));
			}
		}
		return dayRel;
	}

}
