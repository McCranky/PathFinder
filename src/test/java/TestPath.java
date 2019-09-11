import Logic.AStarAlgorithm;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;


public class TestPath {

    @Test
    public void doTest() {
        String[] stringMaze = {""};
        AStarAlgorithm alg = new AStarAlgorithm(new char[][]{   {'.', '.', '.', '.', '#', '.', '.', '.'},
                                                                {'.', 'S', '.', '.', '#', '.', '.', '.'},
                                                                {'.', '.', '.', '.', '#', '.', '#', 'X'},
                                                                {'.', '.', '.', '.', '.', '.', '#', '.'}});
        char[] path = {'d','r', 'r', 'd', 'r', 'r', 'u', 'u', 'r', 'r', 'd', '\u0000'};
        char[] result = alg.getCharPath();
        assertArrayEquals(path, result);
    }
}
