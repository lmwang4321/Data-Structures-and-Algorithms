import java.util.ArrayList;
//import java.util.List;
import edu.princeton.cs.algs4.*;
/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int exp = 7;
        int base = 1000;
        int ops = 10000;

        SLList<Integer> dataSize = new SLList<>();
        SLList<Double> time = new SLList<>();
        SLList<Integer> opsList = new SLList<>();
        for (int i = 0; i < exp; i++){
            int data = base * (int) Math.pow(2, i);
            dataSize.addLast(data);
        }
        SLList<Integer> test = new SLList<>();
        System.out.println("Timing table for getLast");
        for (int num: dataSize){
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j <= num; j++){
                test.addLast(j);
            }
            opsList.addLast(ops);
            double elapsed = sw.elapsedTime();
            time.addLast(elapsed);
        }
        printTimingTable(dataSize, time, opsList);

    }

}
