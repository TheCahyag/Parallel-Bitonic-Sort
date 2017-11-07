/**
 * File: BitonicSort.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class BitonicSort {

    /**
     * TODO
     * @param list
     * @return
     */
    private static Result serialBitonicSort(double[] list){
        Result result = new Result();

        result.setStartingArray(list);


        long startTime = System.nanoTime();
        result.setResultArray(SerialBitonicSort.sort(list));
        long endTime = System.nanoTime();

        result.setRuntime(endTime - startTime);
        return result;
    }

    private static Result parallelBitonicSort(double[] list){

        return null;
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
            Util.printUsage();
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
            Util.printUsage();
            return;
        }

        if (args.length > 2){
            // Parse options specified
            for (int i = OPTIONS_START; i < args.length; i++) {
                if (args[i].charAt(0) != '-'){
                    System.out.println("'" + args[i] + "' is not a recognized argument.");
                    Util.printUsage();
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
                            break;
                        // -o option
                        case 'o':
                            outputResult = true;
                            break;
                        // The option is invalid
                        default:
                            System.out.println("'" + args[i] + "' is not a recognized argument.");
                            Util.printUsage();
                            return;
                    }
                }
            }
        }

        /* End command parsing */

        if (parallel){

        } else {
            long totalTime = 0;
            for (int i = 0; i < timesToRepeat; i++) {
                Result result = serialBitonicSort(Util.randomArray(size));
                System.out.println("Runtime of array of size " + size + ": " + result.getRuntime() + " ns (" + (result.getRuntime() / 1000000) + " ms)");
                if (outputResult){
                    // Print resulting array
                    Util.printArray(result.getResultArray());
                }
                totalTime += result.getRuntime();
            }
            long avgTime = totalTime / timesToRepeat;
            System.out.println("Average runtime of " + timesToRepeat + " runs: " + avgTime + " ns (" + (avgTime / 1000000) + " ms)");
        }
    }
}
