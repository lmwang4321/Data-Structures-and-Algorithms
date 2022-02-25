import java.util.*;

public class Prim {
    private final ExtrinsicMinPQ<Integer> pq;
    private final Map<Integer, Edge> edgeTo;
    private final Map<Integer, Double> distTo;
    private final Map<Integer, Boolean> marked;
    private final List<Edge> mst;

    public Prim(WeightedGraph G){
        pq = new ArrayHeapMinPQ<>();
        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        marked = new HashMap<>();
        mst = new ArrayList<>();


        pq.add(0, 0.0);
        for (int i = 0; i < G.getNumV(); i++){
            distTo.put(i, Double.POSITIVE_INFINITY);
            marked.put(i, false);
        }
        distTo.put(0, 0.0);

        while (!pq.isEmpty()){
            int v = pq.removeSmallest();
            if (marked.get(v)){
                continue;
            }
            marked.put(v, true);
            for (Edge e : G.edgeFrom(v)){
                if (marked.get(e.other(v))){
                    continue;
                }
                int w = e.other(v);
                if (e.getWeight() < distTo.get(w)){
                    distTo.remove(w);
                    distTo.put(w, e.getWeight());
                    edgeTo.remove(w);
                    edgeTo.put(w, e);
                    if (pq.contains(w)){
                        pq.changePriority(w, distTo.get(w));
                    }
                    else{
                        pq.add(w, distTo.get(w));
                    }
                }
            }

        }

        for (int v : G.allVertexes()){
            mst.add(edgeTo.get(v));
        }

    }

    public static void main(String[] args){
        WeightedGraph G = new WeightedGraph();
        G.addEdge(0, 7, 0.16);
        G.addEdge(0, 2, 0.26);
        G.addEdge(0, 4, 0.38);
        G.addEdge(0, 6, 0.58);
        G.addEdge(4, 6, 0.93);
        G.addEdge(4, 5, 0.35);
        G.addEdge(4, 7, 0.37);
        G.addEdge(5, 1, 0.32);
        G.addEdge(5, 7, 0.28);
        G.addEdge(1, 7, 0.19);
        G.addEdge(1, 2, 0.36);
        G.addEdge(1, 3, 0.29);
        G.addEdge(3, 2, 0.17);
        G.addEdge(3, 6, 0.52);
        G.addEdge(2, 7, 0.34);
        G.addEdge(2, 6, 0.4);
        Prim p = new Prim(G);
        System.out.println(p.mst);




    }
}
