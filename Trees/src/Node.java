/**
 * Node class that has type Comparable<T>
 * Class implements Comparable<Node<T>>
 * @param <T>
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;
    private T data;

    public Node(){}

    public Node(T data){
        this.data = data;
    }

    public Node(Node<T> o){
        this.left = o.left;
        this.right = o.right;
        this.parent = o.parent;
        this.data = o.data;
    }

    public T getData() {
        return data;
    }

    public Node<T> getLeft(){
        return left;
    }

    public Node<T> getRight(){
        return right;
    }

    public Node<T> getParent(){
        return parent;
    }

    public void setLeft(Node<T> left){
        this.left = left;
    }

    public void setRight(Node<T> right){
        this.right = right;
    }

    public void setParent(Node<T> parent){
        this.parent = parent;
    }

    @Override
    public int compareTo(Node<T> t) {
        return data.compareTo(t.getData());
    }

    public boolean equals(Node<T> t){
        return data.equals(t.getData());
    }
}
