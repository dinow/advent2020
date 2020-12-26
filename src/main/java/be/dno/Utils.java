package be.dno;


public class Utils {

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
}
