
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
    private ArrayList<PriorityNode> items;
    HashMap<T, Integer>itemMap;

    public ArrayHeapMinPQ(){
        items = new ArrayList<>();
        items.add(null);
        itemMap = new HashMap<>();

    }

    @Override
    public void add(T item, double priority) {
        PriorityNode node = new PriorityNode(item, priority);
        items.add(node);
        int ret = swim(items.size()-1);
        itemMap.put(item, ret);
    }

    @Override
    public boolean contains(T item){
        return itemMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (items.size() <= 1){
            throw new NoSuchElementException("heap is empty!");
        }
        return items.get(1).getItem();
    }

    @Override
    public T removeSmallest() {
        if(items.size() == 1){
            throw new NoSuchElementException("heap is empty!");
        }
        T min = items.get(1).getItem();
        exch(1, items.size()-1);
        items.remove(items.size()-1);
        sink(1);
        itemMap.remove(min);
        return min;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void changePriority(T item, double priority) {
        int idx = itemMap.get(item);
        items.get(idx).setPriority(priority);
        double parentPrior = items.get(parent(idx)).getPriority();
        double leftChildPrior = items.get(leftChild(idx)).getPriority();
        double rightChildPrior = items.get(rightChild(idx)).getPriority();

        if(parentPrior > priority){
            swim(idx);
        }

        if(priority > leftChildPrior || priority > rightChildPrior){
            sink(idx);
        }
    }

    private void exch(int i, int j){
        PriorityNode item = items.get(i);
        items.set(i, items.get(j));
        items.set(j, item);
        itemMap.put(items.get(j).item, j);
        itemMap.put(items.get(i).item, i);
    }

    private boolean greater(int i, int j){
        int cmp = items.get(i).compareTo(items.get(j));
        return cmp > 0;
    }

    private int parent(int n){
        if (!hasParent(n)){
            return n;
        }
        return n/2;
    }

    private int leftChild(int n){
        return 2*n<=items.size()-1?n*2:n;
    }

    private int rightChild(int n){
        return 2*n+1<=items.size()-1?n*2+1:n;
    }

    private int swim(int n){
        while(n>1 && greater(parent(n), n)){
            exch(n, parent(n));
            n = parent(n);
        }
        return n;
    }

    private boolean hasParent(int k){
        return k > 1;
    }
    private void sink(int n){
        while (n <= items.size()/2){
            int j = leftChild(n);
            if (j < items.size()-1 && greater(j, j+1)){
                j++;
            }
            if(!greater(n, j)) break;
            exch(n, j);
            n = j;
        }
    }

    private class PriorityNode implements Comparable<ArrayHeapMinPQ.PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(ArrayHeapMinPQ.PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((ArrayHeapMinPQ.PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

    public static void main(String[] args){
        ArrayHeapMinPQ<Integer> heapq = new ArrayHeapMinPQ<>();
        heapq.add(1, 1);
        heapq.add(2, 2);
        heapq.add(3, 3);
        heapq.add(4,4);
        heapq.add(5,5);
        heapq.add(6,6);
        heapq.removeSmallest();
        heapq.changePriority(6, 0);
        int a = 1;
    }
}