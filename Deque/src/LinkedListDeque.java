public class LinkedListDeque<T> {
    private class TNode{
        TNode pre;
        T val;
        TNode next;
        public TNode(TNode p, T v, TNode n){
            pre = p;
            val = v;
            next = n;
        }
        public TNode(){
            pre = this;
            val = null;
            next = this;
        }

        public TNode(T item){
            pre = this;
            val = item;
            next = this;
        }
    }
    private int size;
    private TNode sentinel = new TNode();

    private TNode findLastNode(){
        TNode temp = sentinel;
        for (int i = 0; i<size; i++){
            temp = temp.next;
        }
        return temp;
    }
    public void addFirst(T item){
        TNode new_node = new TNode();
        /*
        TNode last_node = findLastNode();
        new_node.val = item;
        new_node.pre = last_node;
        new_node.next = sentinel;
        sentinel.pre = new_node;
        last_node.next = new_node;

         */
        new_node.val = item;
        new_node.next = sentinel.next;
        new_node.pre = sentinel;
        sentinel.next = new_node;
        new_node.next.pre = new_node;
        size++;
    }

    public void addLast(T item){
        TNode new_node = new TNode(item);
        TNode last_node = findLastNode();
        last_node.next = new_node;
        new_node.pre = last_node;
        sentinel.pre = new_node;
        new_node.next = sentinel;
        size++;
    }
    public boolean isEmpty(){
        return size==0;
    }

    public T removeFirst(){
        T temp = sentinel.next.val;
        /*
        TNode tempNode = new TNode();
        tempNode.pre = tempNode;
        tempNode.next = sentinel;
        */

        TNode deletedNode = sentinel.next;

        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;

        deletedNode.pre = null;
        deletedNode.next = null;

        size--;
        return temp;
    }
    public T removeLast(){
        TNode deletedNode = sentinel.pre;

        T temp = sentinel.pre.val;

        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        deletedNode.pre = null;
        deletedNode.next = null;
        size--;
        return temp;
    }

    public T get(int i){
        TNode temp = sentinel;
        while (i>=0){
            temp = temp.next;
            i--;
        }
        return temp.val;
    }
}
