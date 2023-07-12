import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.FileNotFoundException;

public class CsvReader {
    //Stores the elements to be loaded into the data object and the columnNames for the table
    private ArrayList<String[]> elements = new ArrayList<String[]>();
    private JTable table = new JTable();
    //Stores the table data
    private Object[][] data;
    //Stores the number of columns for the loops
    private int columns;

    /*
     * Loads the table data from a file. Validates the file and displays
     * error messages in the console if the file is not found or null.
     */
    public void setTable(File file){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            String[] colNames = null;
            boolean firstLoop = true;   // Used to grab the first row for the column names
            while((line = br.readLine()) != null){
                // Split the line into an array using comma separation
                // since this program uses CSV files that closely match
                // a spreadsheet.
                String[] splitString = line.split(",");
                if (firstLoop){
                    // Split the CSV row into an array for the column names
                    colNames = splitString;
                    firstLoop = !firstLoop;
                }
                else {
                    // Add the rest of the split strings to the ArrayList
                    // for the table data. Find the number of columns to 
                    // create the object array.
                    elements.add(splitString);
                    columns = splitString.length;
                }
            }

            // Creates the object array to build the table with
            data = new Object[elements.size()][columns];
            for (int i = 0; i < elements.size(); i++) {
                for (int j = 0; j < columns; j++){
                    data[i][j] = elements.get(i)[j];
                }
            }
            // Format the cells of the table to align-right
            table.setModel(new DefaultTableModel(data, colNames));
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            render.setHorizontalAlignment(JLabel.RIGHT);
            for (int i = 0; i < columns; i++){
                table.getColumnModel().getColumn(i).setCellRenderer(render);
            }
        } 
        catch (FileNotFoundException ex) {
            System.out.println("CSVReader: File not found.");
        } 
        catch (IOException ex) {
            System.out.println("CSVReader: IO Error");
        }
        catch (NullPointerException ex){
            System.out.println("CSVReader: File was null");
        }
    }
    // returns a table for use in a swing application.
    public JTable getTable(File file) throws FileNotFoundException{
        if (file == null){
            String msg = "CSVReader: File not found";
            throw new FileNotFoundException(msg);
        }
        return table;
    }
}
