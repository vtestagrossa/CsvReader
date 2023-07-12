import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;

public class ReaderApp {
    public static void main(String[] args) throws Exception {
        try {
            // Initialize the CsvReader, file, and JFileChooser
            CsvReader reader = new CsvReader();
            File file = new File(System.getProperty("user.dir"));
            JFileChooser path = new JFileChooser();
            path.setCurrentDirectory(file);

            // open the dialog to select the file and set the path to file
            // then set the table from the csv file.
            path.showOpenDialog(null);
            file = path.getSelectedFile();
            reader.setTable(file);

            // Assign the table to the values built by CsvReader
            // set the tables size, create the frame, and scrollpane
            // then add the table to the scrollpane.
            JTable table = reader.getTable(file);
            table.setPreferredScrollableViewportSize(null);
            JFrame frame = new JFrame("CSV Reader App");
            JScrollPane scrollPane = new JScrollPane(table);

            //Add the scrollpane tothe frame and set the size, close operation,
            //and visibility
            frame.add(scrollPane);
            frame.setSize(800, 260);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (FileNotFoundException ex) {
            System.out.println("Main App: No file was selected. Closing Program...");
            System.exit(-1);
        }
    }
}
