package Logic;

import java.awt.*;

public class Node implements Comparable {
    private Node parent;
    private Point point;
    private double g;
    private double h;

    Node(Node parent, int x, int y, double g, double h) {
        this.parent = parent;
        this.point = new Point(x, y);
        this.g = g;
        this.h = h;
    }

    public Node getParent() {
        return parent;
    }

    public double getG() {
        return g;
    }

    public double getH() {
        return h;
    }

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }

    public void setG(double g) {
        this.g = g;
    }

    public static char getDirection(Node from, Node to) {
        if (from.getX() < to.getX())
            return 'r';
        else if (from.getX() > to.getX())
            return 'l';
        else if (from.getY() < to.getY())
            return 'd';
        else
            return 'u';

    }

    // To be sorted by F value (g + h)
    @Override
    public int compareTo(Object o) {
        Node node = (Node) o;
        return (int)((this.g + this.h) - (node.g + node.h));
    }
}
