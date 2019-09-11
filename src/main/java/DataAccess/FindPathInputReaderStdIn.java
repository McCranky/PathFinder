package DataAccess;

import java.util.ArrayList;
import java.util.Scanner;

public class FindPathInputReaderStdIn extends AbstractFindPathInputReader {
    @Override
    public char[][] getMaze() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter empty line for end.");
        String inputLine = "";
        ArrayList<String> lines = new ArrayList<>();

        while (true) {
            inputLine = scn.nextLine();
            if (inputLine.isEmpty())
                break;
            lines.add(inputLine);
        }

        char[][] maze = Logic.MazeChcecker.checkMaze(lines);
        return maze;
    }
}
