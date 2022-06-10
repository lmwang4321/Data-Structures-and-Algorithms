import jdk.jshell.spi.ExecutionControl;

import java.util.Stack;

public class AVL<T extends Comparable<T>> extends BinarySearchTree<T>{

    public AVL(){
        super();
    }

    public void leftRotate(Node<T> x){
        if(x.getParent()!= null && x.getParent().getRight() == x){
            x.getParent().setRight(x.getRight());
        }
        if (x.getParent() != null && x.getParent().getLeft() == x){
            x.getParent().setLeft(x.getRight());
        }
        x.getRight().setParent(x.getParent());
        x.setParent(x.getRight());
        x.setRight(x.getParent().getLeft());
        if (x.getRight() != null) x.getRight().setParent(x);
        x.getParent().setLeft(x);
    }

    public void rightRotate(Node<T> x){
        if(x.getParent()!= null && x.getParent().getRight() == x){
            x.getParent().setRight(x.getLeft());
        }
        if (x.getParent() != null && x.getParent().getLeft() == x){
            x.getParent().setLeft(x.getLeft());
        }
        x.getLeft().setParent(x.getParent());
        x.setParent(x.getLeft());
        x.setLeft(x.getLeft().getRight());
        if (x.getLeft() != null) x.getLeft().setParent(x);
        x.getParent().setRight(x);
    }

    public Node<T> child(Node<T> x){
        return null;
    }

    public Node<T> grandChild(Node<T> x){
        return null;
    }

    @Override
    public void add(T elem){
        Stack<String> stk = new Stack<>();
        this.add(elem);
        Node<T> n = search(elem);
        while(n != null){
            if (n.getParent() != null){
                if (n.getParent().getLeft() == n){
                    stk.push("left");
                }
                else{
                    stk.push("right");
                }
            }
            n = n.getParent();
            if (isBalanced(n)) {
                continue;
            }
            else{
                String child = stk.pop();
                String grandChild = stk.pop();
                if (child.equals("left") && grandChild.equals("left")){
                    rightRotate(n);
                }
                else if (child.equals("left") && grandChild.equals("right")){
                    rightRotate(n.getLeft());
                    leftRotate(n);
                }
                else if (child.equals("right") && grandChild.equals("left")){
                    leftRotate(n.getRight());
                    rightRotate(n);
                }
                else if (child.equals("right") && grandChild.equals("right")){
                    leftRotate(n);
                }
            }
        }
    }

    @Override
    public Boolean delete(T elem){
        return false;
    }

    public static void main(String[] args){
        AVL<Integer> bst = new AVL<>();
        bst.add(20);
        bst.add(15);
        bst.add(14);
        bst.add(13);
        bst.add(12);
        int a = 1;
    }
}
