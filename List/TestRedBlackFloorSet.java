import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
        double lo = -5000;
        double hi = 5000;
        int nums = 1000000;
        for (int i=0; i< nums; i++){
            double a = StdRandom.uniform(lo, hi);
        }
        RedBlackFloorSet rbFloorSet = new RedBlackFloorSet();


    }
}
