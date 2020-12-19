package be.dno.advent2020;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class day16_reddit {

    static HashMap<String, ArrayList<Integer>> rules = new HashMap<>();
    static HashMap<String, ArrayList<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("C:\\git_workspace\\java_workspace\\advent2020\\src\\main\\java\\be\\dno\\advent2020\\day16.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) break;
            Scanner sc4 = new Scanner(line).useDelimiter(":");

            Scanner sc2 = new Scanner(line);
            ArrayList<Integer> nums = new ArrayList<>();
            while (sc2.hasNext()) {
                String str = sc2.next();
                if (str.contains("-")) {
                    Scanner sc3 = new Scanner(str).useDelimiter("-");
                    int low = sc3.nextInt();
                    int high = sc3.nextInt();
                    for (int i = low; i <= high; i++) nums.add(i);
                }
            }
            rules.put(sc4.next(), nums);
        }

        ArrayList<Integer> mine = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Scanner sc2 = new Scanner(line).useDelimiter(",");
            while (sc2.hasNextInt()) {
                mine.add(sc2.nextInt());
            }

            if (line.equals("")) break;
        }

        sc.nextLine();
        int count = 0;
        for (String rule : rules.keySet()) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < rules.size(); i++) list.add(i);
            map.put(rule, list);
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Scanner sc2 = new Scanner(line).useDelimiter(",");
            ArrayList<Integer> nums = new ArrayList<>();
            while (sc2.hasNextInt()) {
                nums.add(sc2.nextInt());
            }

            for (int i = 0; i < nums.size(); i++) {
                if (!contains(nums.get(i))) {
//                    count += nums.get(i);
                } else {
                    int position = i;
                    for (String rule : rules.keySet()) {
                        if (!rules.get(rule).contains(nums.get(i))) map.get(rule).remove(Integer.valueOf(position));
                    }
                }
            }
        }
        System.out.println(map.values().size());

        for (int i = 0; i < 20; i++) {
            for (String rule : map.keySet()) {
                if (map.get(rule).size() == 1) {
                    for (String rule2 : map.keySet()) {
                        if (!rule.equals(rule2)) {
                            map.get(rule2).remove(Integer.valueOf(map.get(rule).get(0)));
                        }
                    }
                }
            }
        }
        long count2 = 1;
        System.out.println(map.toString());
        for (String rule : map.keySet()) {
            if (rule.contains("departure")) {
                count2 *= mine.get(map.get(rule).get(0));
            }
        }
        System.out.println(count2);
    }

    static String oneposition (int i, int position) {
        for (String rule : rules.keySet()) {
            if (!map.containsValue(position) && !map.containsKey(rule) && rules.get(rule).contains(i)) {
                for (String rule2 : rules.keySet()) {
                    if (!map.containsKey(rule2) && !rule.equals(rule2) && rules.get(rule2).contains(i)) {
                        return "";
                    }
                }
                return rule;
            }
        }
        return "";
    }

    static boolean contains(int i) {
        for (String rule : rules.keySet()) {
            if (rules.get(rule).contains(i)) return true;
        }
        return false;
    }
}
