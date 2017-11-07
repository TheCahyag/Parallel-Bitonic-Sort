/**
 * File: Util.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class Util {
    public static double[] randomArray(int size){
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.random() * 1000;
        }
        return array;
    }

    /**
     * Prints usage message to System.out
     */
    public static void printUsage(){
        System.out.println("Usage: BitonicSort N R [-s|-p] [-o]");
    }

    /**
     * Print a primitive double array to System.out
     * @param array array to print
     */
    public static void printArray(double[] array){

//        StringBuilder result = new StringBuilder("[\n\t");
        System.out.println("[");
        for (int i = 0; i < array.length;) {
            System.out.print("\t");
            for (int j = 0; j < 4 && i < array.length; j++, i++) {
                System.out.printf("%-8.2f", array[i]);
            }
            System.out.println();
        }

        System.out.print("]\n");
    }
}


