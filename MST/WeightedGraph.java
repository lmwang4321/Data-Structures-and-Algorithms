import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WeightedGraph {
    private int numV;
    private int numE;
    private Map<Integer, List<Edge>> adj;

    public WeightedGraph(){
        numV = 0;
        numE = 0;
        adj = new HashMap<>();
    }

    public void addEdge(int a, int b, double weight){
        if (!adj.containsKey(a)){
            adj.put(a, new LinkedList<>());
        }
        if (!adj.containsKey(b)){
            adj.put(b, new LinkedList<>());
        }
        adj.get(a).add(new Edge(a, b, weight));
        adj.get(b).add(new Edge(b, a, weight));
        numE += 1;
    }

    public int getNumE(){
        return numE;
    }


    public Iterable<Edge> edgeFrom(int v){
        return adj.get(v);
    }

    public Iterable<Integer> allVertexes(){
        return adj.keySet();
    }

    public Iterable<Edge> allEdges(){
        List<Edge> lstOfEdges = new LinkedList<>();
        for (int v : allVertexes()){
            for (Edge e : edgeFrom(v)){
                lstOfEdges.add(e);
            }
        }
        return lstOfEdges;
    }

    public int getNumV(){
        return adj.size();
    }
}
