public class Edge implements Comparable<Edge>{

    private final int v;
    private final int w;
    private final double weight;

    public Edge (int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight(){
        return this.weight;
    }

    public int either(){
        return v;
    }
    public int other(int o){
        if (v == o) return w;
        if (w == o) return v;
        else {
            throw new IllegalArgumentException("Noob! this vertex doesn't exists in this Edge!");
        }
    }

    @Override
    public int compareTo(Edge other){
        int res = (int) (this.weight - other.weight);
        return res;
    }

    @Override
    public String toString(){
        return String.format("%d-%d %.2f",v, w, weight);
    }
}
