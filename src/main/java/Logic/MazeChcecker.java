package Logic;

import DataAccess.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MazeChcecker {
    public static char[][] checkMaze(ArrayList<String> listMaze) {
        // check for empty maze
        if (listMaze.size() <= 0)
            return null;

        // check rectangular 2d grid
        int rowLength = listMaze.get(0).length();
        for (String row: listMaze) {
            if (row.length() != rowLength)
                return null;
        }

        // from ArrayList<String> to char[][]
        char[][]charMaze = new char[listMaze.size()][rowLength];
        for (int i = 0; i < charMaze.length; i++) {
            charMaze[i] = listMaze.get(i).toCharArray();
        }

        // check blocks ----------------------------------------------------
        HashSet<Character> blockTypes = new HashSet<Character>();
        blockTypes.add(Constants.BLOCK_BLOCKED);
        blockTypes.add(Constants.BLOCK_END);
        blockTypes.add(Constants.BLOCK_START);
        blockTypes.add(Constants.BLOCK_FREE);

        HashMap<Character, Integer> blockCount = new HashMap<Character, Integer>();
        for (char[] line : charMaze) {
            for (char block : line) {
                if (blockTypes.add(block) == true) { // unknown block type
                    return null;
                }
                Integer count = blockCount.get(block);
                if (count == null) {
                    blockCount.put(block, 1);
                } else {
                    blockCount.put(block, ++count);
                }
            }
        }

        if (blockCount.get(Constants.BLOCK_START) == null || blockCount.get(Constants.BLOCK_START) > 1)
            return null;
        if (blockCount.get(Constants.BLOCK_END) == null || blockCount.get(Constants.BLOCK_END) > 1)
            return null;

        return charMaze;
    }

    public static char[][] checkMaze(String[] stringMaze) {
        ArrayList<String> list = new ArrayList<>();
        for (String line : stringMaze) {
            list.add(line);
        }
        return checkMaze(list);
    }
}
