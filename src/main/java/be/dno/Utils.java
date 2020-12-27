package be.dno;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static Integer[] extractNumbers(String input){
        List<Integer> ints = new ArrayList<>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(input);
        while(m.find()) {
            ints.add(Integer.valueOf(m.group()));
        }
        return ints.toArray(new Integer[]{});
    }

    public static void initMatrix(char[][] input, char c){
        for (int i = 0; i < input.length; i++){
            initArray(input[i], c);
        }
    }

    public static void initArray(char[] input, char c){
        for (int i = 0; i < input.length; i++){
            input[i] = c;
        }
    }
    public static void initArray(boolean[] input, boolean c){
        for (int i = 0; i < input.length; i++){
            input[i] = c;
        }
    }

    public static void printMatrix(Object[][] input){
        for (int i = 0; i < input.length; i++){
            printArray(input[i]);
        }
        System.out.println("");
    }
    public static void printMatrix(char[][] input){
        for (int i = 0; i < input.length; i++){
            printArray(input[i]);
        }
        System.out.println("");
    }

    public static void printArray(Object[] input){
        for (int i = 0; i < input.length; i++){
            System.out.print(input[i]);
        }
        System.out.println("");
    } 

    public static void printArray(char[] input){
        for (int i = 0; i < input.length; i++){
            System.out.print(input[i]);
        }
        System.out.println("");
    } 

    public static int[] splitCouple(String input, String separator){
        String[] spl = input.split(separator);
        int[] data = new int[spl.length];
        for (int i = 0; i < spl.length; i++){
            data[i] = Integer.valueOf(spl[i].trim());
        }
        return data;
    }

    public static long countCharInArray(char[][] input, char item){
        long cpt = 0l;
        for (int i = 0; i < input.length; i++){
            for (int j = 0; j < input[i].length; j++){
                if (input[i][j] == item){
                    cpt++;
                }
            }
        }
        return cpt;
    }

	public static void initMatrix(boolean[][] input, boolean b) {
        for (int i = 0; i < input.length; i++){
            initArray(input[i], b);
        }
	}
}
