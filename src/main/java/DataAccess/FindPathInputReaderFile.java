package DataAccess;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindPathInputReaderFile extends AbstractFindPathInputReader {
    @Override
    public char[][] getMaze() {
        ArrayList<String> lines = new ArrayList<>();
        final JFileChooser fileChooser = new JFileChooser() {
            @Override
            protected JDialog createDialog( Component parent ) throws HeadlessException {
                JDialog jDialog = super.createDialog( parent );
                jDialog.setAlwaysOnTop( true );
                return jDialog;
            }
        };
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt files", "txt", "text"));
        int returnVal = fileChooser.showOpenDialog( null );
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Scanner scn = new Scanner(file);
                while (scn.hasNextLine()) {
                    lines.add(scn.nextLine());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("No file choosed.");
            return null;
        }

        char[][] maze = Logic.MazeChcecker.checkMaze(lines);
        return maze;
    }
}
