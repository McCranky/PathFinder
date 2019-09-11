import Logic.AStarAlgorithm;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class TestPath {

    @Test
    public void findPath_whenThereIsPossiblePath_findsTheShortestPath() {
        String[] stringMaze = {""};
        AStarAlgorithm alg = new AStarAlgorithm(new char[][]{   {'.', '.', '.', '.', '#', '.', '.', '.'},
                                                                {'.', 'S', '.', '.', '#', '.', '.', '.'},
                                                                {'.', '.', '.', '.', '#', '.', '#', 'X'},
                                                                {'.', '.', '.', '.', '.', '.', '#', '.'}});
        char[] path = {'d','r', 'r', 'd', 'r', 'r', 'u', 'u', 'r', 'r', 'd', '\u0000'};
        char[] result = alg.getCharPath();
        assertArrayEquals(path, result);
    }

    @Test
    public void findPath_whenThereIsNoPath_returnsThereIsNoPathText() {
        String[] stringMaze = {""};
        AStarAlgorithm alg = new AStarAlgorithm(new char[][]{   {'.', '.', '.', '.', '#', '.', '.', '.'},
                                                                {'.', 'S', '.', '.', '#', '.', '.', '#'},
                                                                {'.', '.', '.', '.', '#', '.', '#', 'X'},
                                                                {'.', '.', '.', '.', '.', '.', '#', '.'}});
        char[] path = "There is no path.".toCharArray();
        char[] result = alg.getCharPath();
        assertArrayEquals(path, result);
    }
}
