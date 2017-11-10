import static util.BitonicSortUtil.*;

/**
 * File: ProcessingNode.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class ProcessingNode implements Runnable {

    private volatile Boolean done = false;
    private double[] list;
    private int start;
    private int count;

    public ProcessingNode(double[] list, int start, int count) {
        this.list = list;
        this.start = start;
        this.count = count;
    }

    public Boolean isDone() {
        return done;
    }

    @Override
    public void run() {
        bitonicSort(this.list, this.start, this.count, 1);
        this.done = true;
    }
}
