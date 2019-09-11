package UI;

import DataAccess.FindPathInputReaderFile;
import Logic.AStarAlgorithm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PathFinderForm extends JFrame{
    private JPanel windowPanel;
    private JTextArea textArea;
    private JButton btnFileSelection;
    private JButton btnExecute;
    private JTextPane textPane;
    private JTabbedPane tabbedPane;
    private JScrollPane scrollPane;


    public PathFinderForm() {
        this.setContentPane(windowPanel);
        this.setTitle("Path Finder");
        this.setSize(400, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnFileSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[][] maze = new FindPathInputReaderFile().getMaze();
                showSolution(maze);
            }
        });

        btnExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[][] maze = Logic.MazeChcecker.checkMaze(textArea.getText().split("\n"));
                showSolution(maze);
            }
        });
    }

    private void showSolution(char[][] maze) {
        if (maze == null) {
            textPane.setText("");
            JOptionPane.showMessageDialog(null, "Incorrect format of maze.");
            return;
        }

        char[] path = new AStarAlgorithm(maze).getCharPath();
        String str = "Input: " + System.lineSeparator();
        for (char[] line: maze) {
            str += new String(line) + System.lineSeparator();
        }
        str += "Output: " + System.lineSeparator() + new String(path);
        textPane.setText(str);
    }
}
