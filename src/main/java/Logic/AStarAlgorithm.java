package Logic;

import DataAccess.Constants;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStarAlgorithm {
    private final List<Node> open;
    private final List<Node> closed;
    private final List<Node> path;
    private final char[][] maze;
    private Node current;
    private Point startPoint;
    private Point endPoint;

    public AStarAlgorithm(char[][] maze) {
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();
        this.path = new ArrayList<>();
        this.maze = maze;
        this.startPoint = getPoint(Constants.BLOCK_START);
        this.endPoint = getPoint(Constants.BLOCK_END);
        this.current = new Node(null, this.startPoint.x, this.startPoint.y, 0, 0);
    }

    /**
     * Find point based on block type (look at DataAccess.Constants under BLOCK_*)
     * @param blockType
     * @return (Point | null) the point
     */
    private Point getPoint(char blockType) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == blockType) {
                    return new Point(j,i);
                }
            }
        }
        return null;
    }

    public char[] getCharPath() {
        List<Node> nodePath = findPath();
        if (nodePath == null)
            return "There is no path.".toCharArray();
        char[] charPath = new char[nodePath.size()];
        for (int i = 0; i < charPath.length - 1; i++) {
            charPath[i] = Node.getDirection(nodePath.get(i), nodePath.get(i + 1));
        }
        return charPath;
    }

    /**
     * Find path from startPoint to endPoint
     * @return (List<Node> | null) the path
     */
    private List<Node> findPath() {
        this.closed.add(this.current);
        addNeigborsToOpenList();
        while (this.current.getX() != this.endPoint.x || this.current.getY() != this.endPoint.y) {
            if (this.open.isEmpty()) {
                return null; // no path found
            }
            // get first node (lowest cost) and add it to the closed
            this.current = this.open.get(0);
            this.open.remove(0);
            this.closed.add(this.current);
            addNeigborsToOpenList();
        }
        this.path.add(0, this.current);
        while (this.current.getX() != this.startPoint.x || this.current.getY() != this.startPoint.y) {
            this.current = this.current.getParent();
            this.path.add(0, this.current);
        }
        return this.path;
    }

    /**
     * Check if given node is in given list
     * @param list (List<Node>)
     * @param node (Node)
     * @return (bool) found
     */
    private  boolean findNeighborInList(List<Node> list, Node node) {
        return list.stream().anyMatch((n) -> (n.getX() == node.getX() && n.getY() == node.getY()));
    }

    /**
     * distance between this.current and xend/yend
     * @param dx
     * @param dy
     * @return (int) distance
     */
    private double distance(int dx, int dy) {
        return Math.abs(this.current.getX() + dx - this.endPoint.x) + Math.abs(this.current.getY() + dy - this.endPoint.y); // Manhattan distance
    }

    private void addNeigborsToOpenList() {
        Node node;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (true && x != 0 && y != 0) {
                    continue; // because diagonal movements are not allowed
                }
                node = new Node(this.current, this.current.getX() + x, this.current.getY() + y, this.current.getG(), this.distance(x, y));
                if ((x != 0 || y != 0)
                        && this.current.getX() + x >= 0 && this.current.getX() + x < this.maze[0].length // check boundaries
                        && this.current.getY() + y >= 0 && this.current.getY() + y < this.maze.length
                        && this.maze[this.current.getY() + y][this.current.getX() + x] != Constants.BLOCK_BLOCKED // check if square is walkable
                        && !findNeighborInList(this.open, node) && !findNeighborInList(this.closed, node)) { // if not already done
                    node.setG(node.getParent().getG() + 1.); // Horizontal/vertical cost = 1.0
                    this.open.add(node);
                }
            }
        }

        Collections.sort(this.open);
    }
}
