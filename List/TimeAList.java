import java.util.ArrayList;
// import java.util.List;
import edu.princeton.cs.algs4.*;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        int exponent = 7;
        int thousands = 1000;

        AList<Integer> dataSize = new AList<>();
        AList<Double> time = new AList<>();

        for (int i = 0 ; i <= exponent; i++){
            int data_size = thousands * (int) Math.pow(2, i);
            dataSize.addLast(data_size);
        }
        System.out.println("Timing table for addLast (AList)");
        for (Integer num: dataSize){

            Stopwatch sw = new Stopwatch();
            AList aList = new AList();
            for (int j = 0; j <= num; j++){
                aList.addLast(j);
            }
            double timePast = sw.elapsedTime();
            time.addLast(timePast);
        }
        printTimingTable(dataSize, time, dataSize);

    }


}
