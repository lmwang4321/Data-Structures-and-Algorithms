import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    /** Removes all of the mappings from this map. */
    private Node root;

    private class Node{
        private K m_key;
        private V m_value;
        private Node left;
        private Node right;
        private int m_size;
        public Node(K key, V value, int size){
            m_key = key;
            m_value = value;
            left = null;
            right = null;
            m_size = size;
        }

    }

    @Override
    public void clear(){
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        if (key == null){
            throw new IllegalArgumentException("NOOB! DON'T call containsKey() with empty key!");
        }
        return containsKey(root, key);
    }

    private boolean containsKey(Node x, K key){
        if (x == null)      {return false;}
        int cmp = key.compareTo(x.m_key);
        if (cmp < 0 )       {return containsKey(x.left, key);}
        else if (cmp > 0)   {return containsKey(x.right, key);}
        else                {return true;}
    }
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        if (root == null) {
            throw new NoSuchElementException("tree is empty, nothing to get");
        }
        if (key == null) {
            throw new IllegalArgumentException("NOOB! DON'T call get() with empty key");
        }
        return get(root, key);
    }

    private V get (Node x, K key){
        if (x == null) return null;
        if (x.m_key == key)     {return x.m_value;}
        int cmp = key.compareTo(x.m_key);
        if (cmp < 0 )           {return get(x.left, key);}
        else if (cmp > 0)       {return get(x.right, key);}
        else                    {return x.m_value;} // GOT U!
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size(){ return size(root);}

    private int size(Node x){
        if (x == null) return 0;
        return x.m_size;
    }
    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        if (key == null) throw new IllegalArgumentException("NOOB! DON'T call put() with empty key");
        root = put(root, key, value);
    }

    private Node put (Node x, K key, V value){
        if (x == null) return new Node(key, value, 1);
        if (x != null)
        {
            int cmp = key.compareTo(x.m_key);
            if (cmp < 0){
                x.left = put(x.left, key, value);
            }
            else if(cmp > 0){
                x.right = put(x.right, key, value);
            }
            else{
                x.m_value = value;
            }
            x.m_size = 1 + size(x.left) + size(x.right);
        }
        return x;
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator(){
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K>{
        List<K> q;
        int idx;

        public BSTMapIterator(){
            q = new ArrayList<>();
            idx = 0;
            inOrderTraversal(root);
        }
        @Override
        public boolean hasNext(){
            return idx < q.size();
        }

        @Override
        public K next(){
            return q.get(idx++);
        }

        public void inOrderTraversal(Node x){
            if ( x == null ) return;
            inOrderTraversal(x.left);
            q.add(x.m_key);
            inOrderTraversal(x.right);
        }
    }

    public static void main(String[] args){
        BSTMap<Integer, Integer> bstMap = new BSTMap<>();
        bstMap.put(1, 1);
        bstMap.put(2,1);
        bstMap.put(3,1);
        for (int k:bstMap){
            System.out.println(k);
        }
        int a = 1;
    }
}
