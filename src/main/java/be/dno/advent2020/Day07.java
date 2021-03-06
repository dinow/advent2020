package be.dno.advent2020;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import be.dno.Day;

public class Day07 extends Day{

	private static final String MY_BAG = "shiny gold";
	private final Map<String, Day07Relation> mapContains = new HashMap<>();
	private final Set<String> superBags = new HashSet<>();

	@Override
	public void fillDataStruct() {
		
		
		boolean found = true;
		superBags.add(MY_BAG);

		for(String line : lines) {
			Day07Relation rel = getRelation(line);
			mapContains.put(rel.bagName, rel);
		}

		String[] alines = lines.toArray(new String[0]);
		while(found) {
			found = false;
			for(int i = 0; i < alines.length; i++) {
				String[] parts = alines[i].split("bags contain");
				if (parts.length <= 1) continue;
				String[] currentSuperBags = superBags.toArray(new String[0]);
				for (String superBag : currentSuperBags) {
					if (parts[1].contains(superBag)){
						superBags.add(parts[0].trim());
						found = true;
						alines[i] = "";
					}
				}
			}
		}

	}

	@Override
	public String processPart1() {
		return "" + ((superBags.size()-1));
	}

	@Override
	public String processPart2() {
		return ""+ (addRecurs(MY_BAG, 1) + mapContains.get(MY_BAG).getBagCount());
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
