//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.nio.file.StandardOpenOption.CREATE;
public class DataStreams {

    public static ArrayList<String> matchingLines = new ArrayList<>();

    public static JPanel BackPanel =new JPanel();

    public static JPanel originPanel= new JPanel();
    public static JPanel resultPanel = new JPanel();
    public static FullScreenFrame GUIFrame= new FullScreenFrame("File Searcher");
    public static JTextArea originalFileArea= new JTextArea(20,30);
    public static JTextArea processedFileArea=new JTextArea(20,30);
   public static JLabel FileName = new JLabel("<NO FILE>");
  public static  JLabel SearchBarLabel = new JLabel("Enter search string here:");
  public static JTextField SearchBar = new JTextField(15);
    public static JScrollPane resultScroller = new JScrollPane(processedFileArea);
  public static File selectedFile;

public static boolean StreamedAFile;



  public static JFileChooser chooser = new JFileChooser();

  public static File workingDirectory = new File(System.getProperty("user.dir"));
public static JScrollPane inputScroller =new JScrollPane(originalFileArea);
  public static JButton FileButton = new JButton("Select a file");

  public static JButton searchButton = new JButton("SEARCH");

  public static JLabel resultLabel = new JLabel("OUTPUT:");
    public static void main(String[] args) {
originPanel.setLayout(new BoxLayout(originPanel,BoxLayout.Y_AXIS));
resultPanel.setLayout(new BoxLayout(resultPanel,BoxLayout.Y_AXIS));
chooser.setCurrentDirectory(workingDirectory);

GUIFrame.add(BackPanel);

BackPanel.add(originPanel);
BackPanel.add(resultPanel);


originPanel.add(FileButton);
originPanel.add(FileName);
        originPanel.add(inputScroller);
originPanel.add(SearchBarLabel);
originPanel.add(SearchBar);
originPanel.add(searchButton);
resultPanel.add(resultLabel);
resultPanel.add(resultScroller);
        ActionListener FileListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int control = chooser.showOpenDialog(GUIFrame);
                if (control == JFileChooser.APPROVE_OPTION){
                    selectedFile = chooser.getSelectedFile();
                    try {
                        Stream<String> streamedFile = Files.lines(selectedFile.toPath());
                       streamedFile.forEach(line -> originalFileArea.append("\n"+line));
                       StreamedAFile=true;
                       FileName.setText(selectedFile.getName());
                    }catch (IOException Exception) {JOptionPane.showMessageDialog(GUIFrame,"File error");}



                }

            }
        };

        ActionListener SearchListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StreamedAFile)
                 try {
                     Stream<String> fileToSearch =Files.lines(selectedFile.toPath());
                   List matchingLinesList = fileToSearch.filter(line -> line.contains(SearchBar.getText())).collect(Collectors.toList());
                   matchingLinesList.forEach(line ->processedFileArea.append("\n"+(String) line));
                 }catch (IOException exception){JOptionPane.showMessageDialog(GUIFrame,"File error");}
            }
        };
FileButton.addActionListener(FileListener);
searchButton.addActionListener(SearchListener);


        GUIFrame.setVisible(true);


    }
}