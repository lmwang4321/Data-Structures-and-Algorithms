import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V>{

    private static final int INITIAL_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75;

    private MyHashST<K,V>[] myHashST;
    private double loadFactor;
    private int tableSize; // size of hashMap;
    private int size = 0; // number of Key-value pairs;

    public MyHashMap(){
        this(INITIAL_SIZE, LOAD_FACTOR);
    }

    public MyHashMap(int initialSize){
        this(initialSize, LOAD_FACTOR);
    }

    public MyHashMap(int initialSize, double loadFactor){
        this.tableSize = initialSize;
        this.loadFactor = loadFactor;
        myHashST = (MyHashST<K, V>[]) new MyHashST[initialSize];
        for (int i = 0; i < tableSize; i++){
            myHashST[i] = new MyHashST<K, V>();
        }
    }

    private void resize(int chain){
        MyHashMap<K, V> temp = new MyHashMap<>(chain);
        for(int i = 0; i < tableSize; i++){
            for(K key : myHashST[i].keys()){
                temp.put(key, myHashST[i].get(key));
            }
        }

        this.tableSize = temp.tableSize;
        this.size = temp.size;
        this.myHashST = temp.myHashST;
    }

    @Override
    public void clear() {
        size = 0;
        myHashST = new MyHashST[INITIAL_SIZE];
    }

    @Override
    public boolean containsKey(K key) {
        for(int i = 0; i < tableSize; i++){
            for (K k : myHashST[i].keys()){
                if (k.equals(key)){
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public V get(K key) {

        int pos = hash(key);
        if (myHashST[pos] != null){
            if (myHashST[pos].get(key) != null){
                return myHashST[pos].get(key);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int hash = hash(key);
        if(!this.containsKey(key)) size++;
        if(myHashST[hash]==null) myHashST[hash] = new MyHashST<>();
        myHashST[hash].addLast(key, value);
        checkSize();
    }

    public void checkSize(){
        double curFac = size/tableSize;
        if( curFac > this.loadFactor ){
            resize(tableSize*2);
        }
    }
    @Override
    public Set<K> keySet() {
        HashSet<K> keys = new HashSet<>();
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("This method is not implemented yet lol.");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("This method is not implemented yet lol.");
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<K>{

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public K next() {
            return null;
        }
    }

    private int hash(K key, int cap){
        return (key.hashCode()&0x7fffffff)%cap;
    }

    private int hash(K key){
        int l = myHashST.length;
        return hash(key, l);
    }
    private static class MyHashST<K, V>{
        private int n; // number of key-value pairs;
        private Node first;

        private class Node{
            private K key;
            private V val;
            private Node next;

            public Node(K key, V val, Node next){
                this.key = key;
                this.val = val;
                this.next = next;
            }
        }

        public MyHashST(){}

        public V get(K key){
            for (Node x = first; x != null; x = x.next){
                if (x.key.equals(key)){
                    return x.val;
                }
            }
            return null;
        }

        public void addLast(K key, V val){
            first = addLast(first, key, val);
        }

        private Node addLast(Node x, K key, V val){
            if(x == null) {
                n++;
                return new Node(key, val, null);
            }
            if (x.key.equals(key)){
                x.val = val;
                return x;
            }
            x.next = addLast(x.next, key, val);
            return x;
        }

        public void delete(K key){
            first = delete(first, key);
        }

        private Node delete (Node x, K key){
            if (x==null) return null;
            if (key.equals(x.key)){
                n--;
                return x.next;
            }
            x.next = delete(x.next, key);
            return x;
        }

        public Iterable<K> keys(){
            ArrayList<K> arrayList = new ArrayList<>();
            for (Node x = first; x != null; x= x.next){
                arrayList.add(x.key);
            }
            return arrayList;
        }
    }


}
