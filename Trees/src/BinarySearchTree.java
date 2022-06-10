import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> implements BinaryTree<T>{

    protected Node<T> root;
    private int counter;
    private boolean foundIdx;
    /**
     * Tree height is zero indexed, i.e. if a node that has
     * no left or right child, its height is 0.
     * @return height of the BinarySearchTree
     */
    @Override
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node<T> tree) {
        if (tree == null){
            return -1;
        }
        return 1 + Math.max(getHeight(tree.getLeft()), getHeight(tree.getRight()));
    }

    @Override
    public int getSize() {
        return getSize(root);
    }

    private int getSize(Node<T> tree){
        if (tree == null) return 0;
        return 1 + getSize(tree.getLeft()) + getSize(tree.getRight());
    }

    /**
     * find the index of element in a tree in inorder
     * the tree is zero indexed meaning the index of left most
     * node will be 0
     * @return -1 if elem is not found in the tree
     */
    @Override
    public Node<T> elementAt(int i) {
         counter = i;
         return getNode(root);
    }

    private Node<T> getNode(Node<T> tree){
        if (tree == null) return null;

        Node<T> e = getNode(tree.getLeft());
        if (counter >= 0){
            counter = counter - 1;
            if (counter == -1){
                e = tree;
            }
            else{
                e = getNode(tree.getRight());
            }
        }
        return e;
    }

    @Override
    public List<Node<T>> preOrder() {
        List<Node<T>> res = new ArrayList<>();
        return preOrder(root, res);
    }

    private List<Node<T>> preOrder(Node<T> tree, List<Node<T>> res){
        if (tree == null) return res;
        res.add(tree);
        res = preOrder(tree.getLeft(), res);
        res = preOrder(tree.getRight(), res);
        return res;
    }

    private Node<T> elementAt(Node<T> tree, int count){
        Stack<Node<T>> stk = new Stack<>();
        Node<T> n = new Node<>();
        while (tree != null){
            stk.push(tree);
            tree = tree.getLeft();
        }

        while (count >= 0 && !stk.isEmpty()){
            n = stk.pop();
            count--;
            if (n.getRight() != null){
                Node<T> r = n.getRight();
                while (r != null){
                    stk.push(r);
                    r = r.getLeft();
                }
            }
        }
        return n;
    }

    @Override
    public List<Node<T>> inOrder() {
        List<Node<T>> res = new ArrayList<>();
        return inOrder(root, res);
    }

    private List<Node<T>> inOrder(Node<T> tree, List<Node<T>> res){
        if (tree == null) return res;
        res = inOrder(tree.getLeft(), res);
        res.add(tree);
        res = inOrder(tree.getRight(), res);
        return res;
    }

    @Override
    public List<Node<T>> postOrder() {
        List<Node<T>> res = new ArrayList<>();
        return postOrder(root, res);
    }

    private List<Node<T>> postOrder(Node<T> tree, List<Node<T>> res){
        if (tree == null) return res;
        res = inOrder(tree.getLeft(), res);
        res = inOrder(tree.getRight(), res);
        res.add(tree);
        return res;
    }

    @Override
    public Boolean isBalanced(Node<T> n) {
        if (n == null) return true;
        if (!isBalanced(n.getLeft())) return false;
        else if (!isBalanced(n.getRight())) return false;
        int leftHeight = getHeight(n.getLeft());
        int rightHeight = getHeight(n.getRight());
        if (Math.abs(leftHeight - rightHeight) > 1){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public List<Node<T>> getLeaves() {
        List<Node<T>> res = new ArrayList<>();
        return getLeaves(root, res);
    }

    @Override
    public int indexOf(Node<T> elem) {
        return indexOf(root, elem, 0);
    }

    private int indexOf(Node<T> tree, Node<T> elem, int idx){
        if (tree == null) {
            return idx;
        }

        idx = indexOf(tree.getLeft(), elem, idx);

        if (!foundIdx) {
            if (tree.equals(elem)) {
                foundIdx = true;
                return idx;
            } else {
                idx = indexOf(tree.getRight(), elem, idx);
                idx++;
            }
        }
        return idx;
    }

    private List<Node<T>> getLeaves(Node<T> tree, List<Node<T>> res){
        if (tree == null){
            return res;
        }
        if (tree.getLeft() == null && tree.getRight() == null){
            res.add(tree);
            return res;
        }
        res = getLeaves(tree.getLeft(), res);
        res = getLeaves(tree.getRight(), res);
        return res;
    }

    public Boolean contains(){
        return false;
    }

    private Boolean contains(Node<T> tree){
        return false;
    }

    public Node<T> search(T elem){
        return search(root, elem);
    }

    public Node<T> search(Node<T> tree, T elem){
        if (tree == null) return null;
        if (elem.equals(tree.getData())) return tree;
        else{
            if (elem.compareTo(tree.getData()) < 0){
                return search(tree.getLeft(), elem);
            }
            else{
                return search(tree.getRight(), elem);
            }
        }
    }

    public Boolean delete(T elem){
        Node<T> n = search(elem);
        if (n == null) return false;
        if (n.getLeft() == null){
            if (n.getParent().getLeft() == n){
                n.getParent().setLeft(n.getRight());
            }
            else if (n.getParent().getRight().equals(n)){
                n.getParent().setRight(n.getRight());
            }
            if (n.getRight() != null) {
                n.getRight().setParent(n.getParent());
                n.setRight(null);
            }
            n.setParent(null);
        }
        else if (n.getRight() == null){
            if (n.getParent().getLeft() == n){
                n.getParent().setLeft(n.getLeft());
            }
            else if (n.getParent().getRight() == n){
                n.getParent().setRight(n.getLeft());
            }
            if(n.getLeft() != null){
                n.getLeft().setParent(n.getParent());
                n.setLeft(null);
            }
            n.setParent(null);
        }
        else{
            Node<T> smallest = findSmallest(n.getRight());
            if(smallest.getParent() != n){
                smallest.getParent().setLeft(null);
            }
            smallest.setParent(n.getParent());
            if(n.getParent().getLeft() == n){
                n.getParent().setLeft(smallest);
            }
            else if(n.getParent().getRight() == n){
                n.getParent().setRight(smallest);
            }
            n.setParent(null);
            smallest.setLeft(n.getLeft());
            smallest.setRight(n.getRight());
            n.getLeft().setParent(smallest);
            n.getRight().setParent(smallest);
        }
        return true;
    }

    private Node<T> findSmallest(Node<T> tree){
        while (tree.getLeft() != null){
            tree = tree.getLeft();
        }
        return tree;
    }

    public void add(T elem){
        root = add(root, new Node<>(elem));
    }

    private Node<T> add(Node<T> tree, Node<T> elem){
        if (tree == null){
            return elem;
        }
        if (tree.compareTo(elem) >= 0) {
            tree.setLeft(add(tree.getLeft(), elem));
            tree.getLeft().setParent(tree);
        }
        else {
            tree.setRight(add(tree.getRight(), elem));
            tree.getRight().setParent(tree);
        }
        return tree;

    }

    public static void main(String[] args){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(20);
        bst.add(15);
        bst.add(14);
        bst.add(17);
        bst.add(16);
        bst.add(18);
        List<Node<Integer>> lst = bst.getLeaves();
        Node<Integer> t = bst.elementAt(5);
        int idx = bst.indexOf(t);
        int a = 1;
    }

}
