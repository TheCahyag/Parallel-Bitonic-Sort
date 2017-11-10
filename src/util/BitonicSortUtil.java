package util;

/**
 * File: util.BitonicSortUtil.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class BitonicSortUtil {
    public static double[] randomArray(int size){
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.random() * 100;
        }
        return array;
    }

    /**
     * Prints usage message to System.out
     */
    public static void printUsage(){
        System.out.println("Usage: BitonicSort N R [-s|-p[XX]] [-o]");
    }

    /**
     * Print a primitive double array to System.out
     * @param array array to print
     */
    public static void printArray(double[] array){
        System.out.println("[");
        for (int i = 0; i < array.length;) {
            System.out.print("\t");
            for (int j = 0; j < 4 && i < array.length; j++, i++) {
                System.out.printf("%8.2f", array[i]);
            }
            System.out.println();
        }
        System.out.print("]\n");
    }

    /**
     * TODO
     * @param list
     * @param low
     * @param count
     * @param direction - int representing whether to sort ascending or descending
     *                  1 for ascending
     *                  0 for descending
     */
    public static void bitonicSort(double list[], int low, int count, int direction){
        if (count > 1){
            int k = count / 2;
            bitonicSort(list, low, k, 1);
            bitonicSort(list, low + k, k, 0);
            merge(list, low, count, direction);
        }
    }

    /**
     *
     * @param list
     * @param low
     * @param count
     * @param direction
     */
    public static void mergeNodes(double list[], int low, int count, int direction){
        if (count > 1){
            int k = count / 2;
            mergeNodes(list, low, k, 1);
            mergeNodes(list, low + k, k, 0);
            merge(list, low, count, direction);
        }
    }

    /**
     * TODO
     * @param list - reference to the list
     * @param low
     * @param count
     * @param direction - int representing whether to sort ascending or descending
     *                  1 for ascending
     *                  0 for descending
     */
    public static void merge(double[] list, int low, int count, int direction){
        if (count > 1){
            int k = count / 2;
            for (int i = low; i < low + k; i++) {
                compare(list, i, i + k, direction);
            }
            merge(list, low, k, direction);
            merge(list, low + k, k, direction);
        }
    }

    /**
     * Compare two elements in a list and swap them if a certain condition is met
     * @param list - list containing the elements
     * @param i - element 1
     * @param j - element 2
     * @param direction - int representing whether to sort ascending or descending
     *                  1 for ascending
     *                  0 for descending
     */
    public static void compare(double[] list, int i, int j, int direction){
        if ((list[i] > list[j] && direction == 1) || (list[i] < list[j] && direction == 0)){
            // Swap elements
            double temp = list[i];
            list[i] = list[j];
            list[j] = temp;
        }
    }
}


