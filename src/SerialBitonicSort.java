/**
 * File: SerialBitonicSort.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class SerialBitonicSort {
    /**
     * Sort a list using the bitonic sorting algorithm
     * @param list - list to sort
     * @return reference to the sorted list
     */
    public static double[] sort(double[] list){
        bitonicSort(list, 0, list.length, 1);
        return list;
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
    private static void bitonicSort(double list[], int low, int count, int direction){
        if (count > 1){
            int k = count / 2;
            bitonicSort(list, low, k, 1);
            bitonicSort(list, low + k, k, 0);
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
    private static void merge(double[] list, int low, int count, int direction){
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
    private static void compare(double[] list, int i, int j, int direction){
        if ((list[i] > list[j] && direction == 1) || (list[i] < list[j] && direction == 0)){
            // Swap elements
            double temp = list[i];
            list[i] = list[j];
            list[j] = temp;
        }
    }
}
