package programming;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ScoreBoard extends JFrame implements Serializable {

    public static JTable table;
    public static JScrollPane pane;
    ArrayList<HighScore> S;
    Score c;

    public ScoreBoard() {
        this.setTitle("Scores");

        this.setSize(600, 600);
        this.setResizable(false);
        table = new JTable();
        pane = new JScrollPane(table);
        S = new ArrayList<HighScore>();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Num", "Name", "Score"});
        table.setModel(model);
        table.setRowHeight(15);
        c = Score.open();
        S = c.Scores;
        for (int i = 0; i < S.size(); i++) {
            Object[] data = {i + 1, S.get(i).getName(), S.get(i).getScore()};
            model.addRow(data);

        }
        this.add(pane);

    }

    public void addScoresToTable() {

        ArrayList<HighScore> hs = Score.getArrayList();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < hs.size(); i++) {
            model.addRow(new Object[]{i, hs.get(i).getName(), hs.get(i).getScore()});

        }
    }
}

class HighScore implements Serializable {

    private String Name;
    private int Score;

    public HighScore(String Name, int Score) {
        this.Name = Name;
        this.Score = Score;
    }

    public String getName() {
        return this.Name;
    }

    public int getScore() {
        return this.Score;
    }

    public void sort() {

    }

    @Override
    public String toString() {
        return (Name + ":         " + Score + "\n");
    }

}

class Score {

    public static ArrayList<HighScore> Scores;
    private static String ScoreFile = "Scores.scr";

    public Score() {
    }

    private Score(ArrayList<HighScore> S) {
        this.Scores = S;
    }

    public void sort() {
        int j;
        boolean flag = true;   // set flag to true to begin first pass
        HighScore temp;   //holding variable

        while (flag) {
            flag = false;    //set flag to false awaiting a possible swap
            for (j = 0; j < Scores.size() - 1; j++) {
                if (Scores.get(j).getScore() < Scores.get(j + 1).getScore()) // change to > for ascending sort
                {

                    temp = Scores.get(j);                //swap elements
                    Scores.add(j, Scores.get(j + 1));
                    Scores.remove(j + 1);
                    Scores.add(j + 1, temp);
                    Scores.remove(j + 2);
                    flag = true;              //shows a swap occurred  
                }
            }
        }
    }

    public static Score open() {
        //Read from file
        try {
            FileInputStream fis = new FileInputStream(ScoreFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            //-----------------------
            ArrayList<HighScore> S = (ArrayList<HighScore>) ois.readObject();
            Score Sc = new Score(S);
            //-----------------------
            ois.close();
            fis.close();

            return Sc;

        } catch (ClassNotFoundException ex) {
            //return empty Score
            return new Score(new ArrayList());
        } catch (IOException ex) {
            //return empty Score
            return new Score(new ArrayList());

        }

    }

    public void addScore(HighScore sc) {

        this.Scores.add(sc);
        sort();
        save();
    }

    public static ArrayList<HighScore> getArrayList() {
        return Scores;
    }

//    public void listAll() {
//        for (Object score : Scores) {
//            System.out.println(score.toString());
//        }
//    }
    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream(ScoreFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //----------------------
            oos.writeObject(this.Scores);
            //----------------------
            oos.close();
            fos.close();
        } catch (IOException e) {

        }
    }

}
