package UI;

import DataAccess.FindPathInputReaderFile;
import DataAccess.FindPathInputReaderStdIn;
import Logic.AStarAlgorithm;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean GUI = false;
        if (GUI) {
            PathFinderForm pfForm = new PathFinderForm();
            pfForm.setVisible(true);
        } else {
            char[][] maze;
            Scanner scn = new Scanner(System.in);
            System.out.println("Path Finder for maze");
            System.out.println("Choose maze input method:");
            System.out.println("[1] StdIn");
            System.out.println("[2] File");
            System.out.print("Option: ");
            int option = 1;
            option = scn.nextInt();

            if (option == 1) {
                maze = new FindPathInputReaderStdIn().getMaze();
            } else {
                maze = new FindPathInputReaderFile().getMaze();
            }

            if (maze == null) {
                System.out.println("Maze in incorrect format or not given.");
            } else {
                AStarAlgorithm alg = new AStarAlgorithm(maze);
                char[] path = alg.getCharPath();

                String str = "Input: " + System.lineSeparator();
                for (char[] line: maze) {
                    str += new String(line) + System.lineSeparator();
                }
                str += "Output: " + System.lineSeparator() + new String(path);
                System.out.println(str);
            }
        }


    }
}
