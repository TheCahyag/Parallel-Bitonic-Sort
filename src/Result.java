/**
 * File: Result.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class Result {
    private long runtime;
    private double[] startingArray;
    private double[] resultArray;

    public Result(){}

    public long getRuntime() {
        return runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public double[] getStartingArray() {
        return startingArray;
    }

    public void setStartingArray(double[] startingArray) {
        this.startingArray = new double[startingArray.length];
        for (int i = 0; i < startingArray.length; i++) {
            this.startingArray[i] = startingArray[i];
        }
    }

    public double[] getResultArray() {
        return resultArray;
    }

    public void setResultArray(double[] resultArray) {
        this.resultArray = resultArray;
    }
}
