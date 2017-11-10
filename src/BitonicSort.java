import java.util.ArrayList;

import static util.BitonicSortUtil.*;

/**
 * File: BitonicSort.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class BitonicSort {

    private static int PROCESSING_NODES = 4;

    /**
     * TODO
     * @param list
     * @return
     */
    private static Result serialBitonicSort(double[] list){
        Result result = new Result();

        result.setStartingArray(list);
        result.setResultArray(list);

        long startTime = System.nanoTime();
        bitonicSort(list, 0, list.length, 1); // Sort the array
        long endTime = System.nanoTime();

        result.setRuntime(endTime - startTime);
        return result;
    }

    private static Result parallelBitonicSort(double[] list){
        Result result = new Result();

        result.setStartingArray(list);
        result.setResultArray(list);

        long startTime = System.nanoTime();

        ProcessingNode[] processingNodes = new ProcessingNode[PROCESSING_NODES];
        int totalCount = 0;
        for (int i = 0; i < PROCESSING_NODES - 1; i++) {
            // Create the given number of processing nodes and section of the list that is being sorted
            processingNodes[i] = new ProcessingNode(list, (list.length / PROCESSING_NODES) * i, list.length / PROCESSING_NODES);
            totalCount += list.length / PROCESSING_NODES;
        }
        // This last node will take all the remaining values, which should be around
        // list.length / processing_nodes, but could be more or less because of integer division
        processingNodes[PROCESSING_NODES - 1] =
                new ProcessingNode(list, (list.length / PROCESSING_NODES) * (PROCESSING_NODES - 1), list.length - totalCount);

        for (int i = 0; i < PROCESSING_NODES; i++) {
            // Run all the processing nodes
            new Thread(processingNodes[i]).start();
        }

        for (int i = 0; i < PROCESSING_NODES; i++) {
            while (!processingNodes[i].isDone()){
                // Wait for the node to finish processing data
            }
        }

        // Merge nodes together
        mergeNodes(list, 0, list.length, 1);

        long endTime = System.nanoTime();

        result.setRuntime(endTime - startTime);
        return result;
    }

    public static void main(String[] args) {
        int size, timesToRepeat;
        boolean parallel = true, outputResult = false;

        /* Start command parsing */

        // Required args
        final int SIZE_ARG = 0;
        final int REPEAT_ARG = 1;


        // Options
        final int OPTIONS_START = 2;

        if (args.length == 0){
            // No arguments present
            System.out.println("Not enough arguments.");
            printUsage();
            return;
        }

        try {
            // Parse the first argument as the size of the array
            size = Integer.parseInt(args[SIZE_ARG]);
            // Parse the second argument as the number of times to run
            timesToRepeat = Integer.parseInt(args[REPEAT_ARG]);
        } catch (NumberFormatException e){
            // Argument wasn't an integer
            System.out.println("'" + args[SIZE_ARG] + "' argument is not an integer.");
            printUsage();
            return;
        }

        if (args.length > 2){
            // Parse options specified
            for (int i = OPTIONS_START; i < args.length; i++) {
                if (args[i].charAt(0) != '-'){
                    System.out.println("'" + args[i] + "' is not a recognized argument.");
                    printUsage();
                    return;
                } else {
                    switch (args[i].charAt(1)){
                        // -s option
                        case 's':
                            parallel = false;
                            break;
                        // -p option
                        case 'p':
                            parallel = true;
                            String numString = "";
                            if (args[i].length() < 4){
                                // Number specified with -p option is less then 3 digits
                                if (args[i].length() > 2){
                                    // Number specified with -p option
                                    for (int j = 2; j < args[i].length(); j++) {
                                        numString = numString.concat(args[i].charAt(j) + "");
                                    }
                                    try {
                                        PROCESSING_NODES = Integer.parseInt(numString);
                                    } catch (NumberFormatException e){
                                        System.out.println("'" + args[i] + "' does not contain a valid integer less than 100.");
                                        printUsage();
                                        return;
                                    }
                                }
                            } else {
                                System.out.println("Processing Node number must be less than 100.");
                                printUsage();
                                return;
                            }
                            System.out.println("Using " + PROCESSING_NODES + " processing nodes...");
                            break;
                        // -o option
                        case 'o':
                            outputResult = true;
                            break;
                        // The option is invalid
                        default:
                            System.out.println("'" + args[i] + "' is not a recognized argument.");
                            printUsage();
                            return;
                    }
                }
            }
        }

        /* End command parsing */

        if (parallel){
            // Run parallel execution
            long totalTime = 0;
            for (int i = 0; i < timesToRepeat; i++) {
                Result result = parallelBitonicSort(randomArray(size));
                System.out.println("Parallel runtime of array of size " + size + ": " + result.getRuntime() + " ns (" + (result.getRuntime() / 1000000) + " ms)");
                if (outputResult){
                    // Print resulting array
                    printArray(result.getResultArray());
                }
                totalTime += result.getRuntime();
            }
            long avgTime = totalTime / timesToRepeat;
            System.out.println("Average runtime of " + timesToRepeat + " run(s): " + avgTime + " ns (" + (avgTime / 1000000) + " ms)");
        } else {
            // Run serial execution
            long totalTime = 0;
            for (int i = 0; i < timesToRepeat; i++) {
                Result result = serialBitonicSort(randomArray(size));
                System.out.println("Serial runtime of array of size " + size + ": " + result.getRuntime() + " ns (" + (result.getRuntime() / 1000000) + " ms)");
                if (outputResult){
                    // Print resulting array
                    printArray(result.getResultArray());
                }
                totalTime += result.getRuntime();
            }
            long avgTime = totalTime / timesToRepeat;
            System.out.println("Average runtime of " + timesToRepeat + " run(s): " + avgTime + " ns (" + (avgTime / 1000000) + " ms)");
        }
    }
}
