package be.dno;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static List<Integer> extractNumbersList(String input){
        List<Integer> ints = new ArrayList<>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(input);
        while(m.find()) {
            ints.add(Integer.valueOf(m.group()));
        }
        return ints;
    }

    public static MatrixElement[][] getIntMatrix(List<String> lines){
        MatrixElement[][] matrix = new MatrixElement[lines.size()][lines.get(0).length()];
        int i =  0;
        for (String line : lines){
            int j = 0;
            for (String c : line.split("|")){
                matrix[i][j] = new MatrixElement(Integer.valueOf(c).intValue());
                j++;
            }
            i++;
        }
        return matrix;
    }

    public static Integer[] extractNumbers(String input){
        return extractNumbersList(input).toArray(new Integer[]{});
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
    public static void initArray(int[] input, int c){
        for (int i = 0; i < input.length; i++){
            input[i] = c;
        }
    }
    public static void initArray(long[] input, long c){
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

    public static char[] copy(char[] in){
        char[] out = new char[in.length];
        for (int i = 0; i < out.length; i++){
            out[i] = in[i];
        }
        return out;
    }

    public static Integer[] copy(Integer[] in){
        Integer[] out = new Integer[in.length];
        for (int i = 0; i < out.length; i++){
            out[i] = in[i];
        }
        return out;
    }

    public static List<Integer[]> copy(List<Integer[]> in){
        List<Integer[]> out = new ArrayList<>();
        for(Integer[] i : in){
            out.add(copy(i));
        }
        return out;
    }

    public static int[] splitCouple(String input, String separator){
        String[] spl = input.split(separator);
        int[] data = new int[spl.length];
        for (int i = 0; i < spl.length; i++){
            data[i] = Integer.valueOf(spl[i].trim());
        }
        return data;
    }

    public static long[] splitCoupleL(String input, String separator){
        String[] spl = input.split(separator);
        long[] data = new long[spl.length];
        for (int i = 0; i < spl.length; i++){
            data[i] = Long.valueOf(spl[i].trim());
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

    public static void printMatrix(long[][] matrix) {
        for (int i = 0; i < matrix.length; i++){
            printArray(matrix[i]);
        }
        System.out.println("");
        
    }

    private static void printArray(long[] ls) {
        for (int i = 0; i < ls.length; i++){
            System.out.print(ls[i]);
        }
        System.out.println("");
    }

    public static void initMatrix(long[][] matrix, long l) {
        for (int i = 0; i < matrix.length; i++){
            initArray(matrix[i], l);
        }
    }
}
