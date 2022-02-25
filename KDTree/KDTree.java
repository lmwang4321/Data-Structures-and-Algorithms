import java.util.List;

public class KDTree implements PointSet {
    private Node root;

    private class Node implements Comparable<Point>{
        int level;
        Node left;
        Node right;
        Point m_p;

        @Override
        public int compareTo(Point p){
            if (level % 2 == 0){
                return (int) (m_p.getX() - p.getX());
            }else{
                return (int) (m_p.getY() - p.getY());
            }
        }

        public Node(Point point){
            level = 0;
            left = null;
            right = null;
            m_p = point;
        }

        public void add(Node x){
            if (x == null){
                throw new IllegalArgumentException("NOOB! DON'T call add() with an empty node");
            }
            root = add(root, x);
        }

        public void updateLevel(Node x, int level){
            if (x == null) return;
            x.level = level;
            updateLevel(x.left, level+1);
            updateLevel(x.right, level+1);
        }
        private Node add (Node dest, Node x){
            if(dest == null) {return new Node(x.m_p);}
            int cmp = x.compareTo(dest.m_p);
            if (cmp < 0){
                dest.left = add(dest.left, x);
                // dest.left.level += 1;
            }else if (cmp > 0){
                dest.right = add(dest.right, x);
                // dest.right.level += 1;
            }
            updateLevel(dest, dest.level);
            return dest;
        }

        /*
        public Node get(Point key){
            return null;
        }

        public Node min(){
            return null;
        }
*/
    }

    public KDTree(List<Point> points){
        root = new Node(points.get(0));
        for (int i = 1; i < points.size(); i++){
            root.add(new Node(points.get(i)));
        }
    }

    @Override
    public Point nearest(double x, double y){
        Point target = new Point(x, y);
        return nearest(root, target, root.m_p);
    }

    private double axisDiff(Node x, Point target){
        if (x.level % 2 == 0){
            return Math.pow((target.getX()- x.m_p.getX()),2);
        }else{
            return Math.pow((target.getY() - x.m_p.getY()),2);
        }
    }

    private Point nearest(Node root, Point target, Point best){
        if (root == null){
            return best;
        }
        if (Point.distance(target, best) == 0){
            return best;
        }
        if (Point.distance(root.m_p, target) < Point.distance(best, target)){
            best = root.m_p;
        }

        int cmp = root.compareTo(target);
        if (cmp < 0){
            best = nearest(root.right, target, best);
        }
        else{
            best = nearest(root.left, target, best);
        }

        double best_possible = axisDiff(root, target);
        if (best_possible < Point.distance(best, target)){
            if (cmp < 0){
                best = nearest(root.left, target, best);
            }
            else{
                best = nearest(root.right, target, best);
            }
        }
        return best;




    }
    public static void main(String[] args){
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(2.2, 3.3);
        List<Point> pointList = List.of(p1,p2,p3,p4);
        KDTree kdTree = new KDTree(pointList);
        Point best = kdTree.nearest(3.5, 4.5);
        int a = 1;
    }
}
