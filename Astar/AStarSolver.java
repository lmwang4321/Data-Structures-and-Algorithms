package bearmaps.proj2c;

import bearmaps.proj2ab.ArrayHeapMinPQ;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>{

    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, Vertex> edgeTo;
    private LinkedList<Vertex> Solution;
    private int numStatesExplored;
    private SolverOutcome solverOutcome;
    private double timeSpent;
    private double SolutionWeight;
    private ArrayHeapMinPQ<Vertex> fringe;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        // instantiate fringe, distTo, and edgeTo
        fringe = new ArrayHeapMinPQ<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        Solution = new LinkedList<>();
        Solution = new LinkedList<>();

        // at start, add (Vertex start, h(start, goal)) to the fringe
        fringe.add(start, input.estimatedDistanceToGoal(start, end));
        numStatesExplored++;
        distTo.put(start, 0.0);
        edgeTo.put(null, start);

        Stopwatch sw = new Stopwatch();
        while(fringe.size()>0){
            // when next vertex to be dequeued is our target, we are done;
            if (fringe.getSmallest().equals(end)){
                timeSpent = sw.elapsedTime(); // record time elapsed until now;
                Vertex v = fringe.getSmallest();

                // append vertices into Solution;
                Solution.addFirst(v);
                while(edgeTo.get(v) != null){
                    v = edgeTo.get(v);
                    Solution.addFirst(v);
                }

                // register those private parameters
                solverOutcome = SolverOutcome.SOLVED;
                SolutionWeight = distTo.get(end);
                return;
            }

            // if time out
            if(sw.elapsedTime() > timeout){
                solverOutcome = SolverOutcome.TIMEOUT;
                timeSpent = sw.elapsedTime();
                return;
            }
            Vertex ver = fringe.removeSmallest();
            for(WeightedEdge<Vertex> w: input.neighbors(ver)){
                relax(w, fringe, input, end);
            }
        }
    }

    /**
     * at each neighbour of vertex,
     * @param edge
     * @param heapq
     * @param g
     * @param end
     */
    private void relax(WeightedEdge<Vertex> edge, ExtrinsicMinPQ<Vertex> heapq,
                       AStarGraph<Vertex> g, Vertex end){
        Vertex p = edge.from();
        Vertex q = edge.to();
        double w = edge.weight();
        if(!distTo.containsKey(q) || distTo.get(p) + w < distTo.get(q)){
            distTo.put(q, distTo.get(p) + w);
            edgeTo.put(q, p);

            if(heapq.contains(q)){
                heapq.changePriority(q, distTo.get(q)+g.estimatedDistanceToGoal(q, end));
            }else{
                heapq.add(q, distTo.get(q)+g.estimatedDistanceToGoal(q, end));
            }
        }
    }

    @Override
    public SolverOutcome outcome() {

        return solverOutcome;
    }

    @Override
    public List<Vertex> solution() {

        return Solution;
    }

    @Override
    public double solutionWeight() {

        return SolutionWeight;
    }

    @Override
    public int numStatesExplored() {

        return numStatesExplored;
    }

    @Override
    public double explorationTime() {

        return timeSpent;
    }
}
