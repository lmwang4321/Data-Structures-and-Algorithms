import java.util.List;

public interface BinaryTree<T extends Comparable<T>> {

    int getHeight();

    int getSize();

    public Node<T> search(T elem);

    Node<T> elementAt(int i);

    List<Node<T>> preOrder();

    List<Node<T>> inOrder();

    List<Node<T>> postOrder();

    Boolean isBalanced(Node<T> n);

    List<Node<T>> getLeaves();

    int indexOf(Node<T> elem);
}
