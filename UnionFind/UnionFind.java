import java.util.ArrayList;

public class UnionFind {
    int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        // set all the parents to be -1 to symbolize that they are disjoint
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid vertex. */
    private void validate(int v1) {
        // TODO
        if(v1 < 0 || v1 > parent.length){
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        int root = findWithPathCompression(v1);
        return -1 * parent[root];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean isConnected(int v1, int v2) {
        // TODO
        int v1Root = findWithPathCompression(v1);
        int v2Root = findWithPathCompression(v2);
        return v1Root == v2Root;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Connecting a
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void connect(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        if (isConnected(v1, v2)) return;
        int v1Root = findWithPathCompression(v1);
        int v2Root = findWithPathCompression(v2);
        int newRoot = sizeOf(v1Root) + sizeOf(v2Root);
        if (sizeOf(v1) > sizeOf(v2)){
            parent[v2Root] = v1Root;
            parent[v1Root] = -newRoot;
        }else{
            parent[v1Root] = v2Root;
            parent[v2Root] = -newRoot;
        }
    }

    /* Returns the root of the set v1 belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int v1) {
        // TODO
        validate(v1);
        if(parent(v1) <0){
            return v1;
        }
        return (parent(v1));
    }

    public int findWithPathCompression(int v1){
        validate(v1);
        int root = v1;
        while(parent(root) > 0){
             root = parent(root);
        }
        while (parent(v1) > 0){
            int newp = parent(v1);
            parent[v1] = root;
            v1 = newp;
        }
        return v1;

    }
    public static void main(String[] args){
        UnionFind uf = new UnionFind(10);
        uf.connect(1, 2);
        uf.connect(3,4);
        uf.connect(5,6);
        uf.connect(4,6);
        uf.connect(1, 3);
        int a = 1;
    }

}
