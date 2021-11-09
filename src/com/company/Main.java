package com.company;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;


public class Main extends JFrame {
    public static void main(String[] args) {
        StartWind realTimeActSW = new StartWind();
        realTimeActSW.StartWind();                          // (добавить в конце рестарт)

    }
}

class StartWind {
    File file1 = new File("ArrayBoard.txt");
    File file2 = new File("TimeSecond.txt");
    File file3 = new File("ClickWayOrEat.txt");

    ArrayBoard firstArrayBoard = new ArrayBoard();
    public JPanel firstPanel;
    static JFrame mainWinJF = new JFrame();

    public void StartWind() {
        firstArrayBoard.NGBoard();
        mainWinJF.setTitle("REVERSI 3.0.5 ");
        mainWinJF.setSize(new Dimension(400, 600));
        mainWinJF.setLocation(1320, 100);
        mainWinJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWinJF.setSize(550, 550);
        Board replaceBoard = new Board();
        firstPanel = replaceBoard.Board(firstArrayBoard.bolvanchikArrayBoard);
        mainWinJF.add(firstPanel);
        try {
            if (!file1.exists()) {
                file1.createNewFile();
            }
            PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
            try {
                for (int i = 0; i < firstArrayBoard.bolvanchikArrayBoard.length; i++) {
                    for (int j = 0; j < firstArrayBoard.bolvanchikArrayBoard[i].length; j++) {
                        if ((j == 7)) {
                            out.print(firstArrayBoard.bolvanchikArrayBoard[i][j]);
                        } else {
                            out.print(firstArrayBoard.bolvanchikArrayBoard[i][j] + " ");
                        }
                    }
                    out.print("\n");
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            if (!file3.exists()) {
                file3.createNewFile();
            }
            PrintWriter out = new PrintWriter(file3.getAbsoluteFile());
            try {
                out.print("Line");
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            if (!file2.exists()) {
                file2.createNewFile();
            }
            PrintWriter out = new PrintWriter(file2.getAbsoluteFile());
            try {
                out.print("-1");
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JMenuBar mainWindMB = new JMenuBar();

        JMenu menu1 = new JMenu("File");
        JMenu menu4 = new JMenu("Exit");

        JMenuItem menuItem1Manual = new JMenuItem("Manual");
        JMenuItem menuItem1New = new JMenuItem("New Game(beta)");
        JMenuItem menuItem1Close = new JMenuItem("Close");
        JMenuItem menuItem4Exit = new JMenuItem("Exit");                                //!
        menu1.add(menuItem1Manual);
        menu1.add(menuItem1New);
        menu1.add(menuItem1Close);
        menu4.add(menuItem4Exit);

        mainWindMB.add(menu1);
        mainWindMB.add(menu4);

        mainWinJF.setJMenuBar(mainWindMB);
        mainWinJF.setVisible(true);


        menuItem1Manual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String urlString = "https://ru.wikipedia.org/wiki/%D0%A0%D0%B5%D0%B2%D0%B5%D1%80%D1%81%D0%B8";
                try {
                    Desktop.getDesktop().browse(new URL(urlString).toURI());
                } catch (IOException exception) {
                    exception.printStackTrace();
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                }
            }
        });
        menuItem1Close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuItem4Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    static class ListenerAction implements ActionListener {                                             //!!!!!!!!!!
        private final ArrayBoard secondArrayBoard = new ArrayBoard();
        JPanel secondPanel;

        public void actionPerformed(ActionEvent e) {
            mainWinJF.getContentPane().removeAll();
            Integer[][] arrBig = new Integer[8][8];                                                     // поиск обновленного массива чисел
            String[] timeLineArray = new String[]{null, null, null, null, null, null, null, null};
            Integer timeLine = 0;
            String line1 = null;
            String line2 = null;
            String line3 = null;
            String line4 = null;
            String line5 = null;
            String line6 = null;
            String line7 = null;
            String line8 = null;
            try {
                Scanner sc = new Scanner(new File("ArrayBoard.txt"));
                while (sc.hasNextLine()) {
                    String time = sc.nextLine();
                    if (timeLine == 0) line1 = time;
                    if (timeLine == 1) line2 = time;
                    if (timeLine == 2) line3 = time;
                    if (timeLine == 3) line4 = time;
                    if (timeLine == 4) line5 = time;
                    if (timeLine == 5) line6 = time;
                    if (timeLine == 6) line7 = time;
                    if (timeLine == 7) line8 = time;
                    timeLine++;
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            int[][] arr = new int[8][8];
            for (int ik = 0; ik <= 7; ik++) {
                if (ik == 0) {
                    String[] time = line1.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);
                }
                if (ik == 1) {
                    String[] time = line2.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);
                }
                if (ik == 2) {
                    String[] time = line3.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);
                }
                if (ik == 3) {
                    String[] time = line4.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);
                }
                if (ik == 4) {
                    String[] time = line5.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);
                }
                if (ik == 5) {
                    String[] time = line6.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);
                }
                if (ik == 6) {
                    String[] time = line7.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);
                }
                if (ik == 7) {
                    String[] time = line8.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);
                }
            }
            for (int i = 0; i < arr.length; i++) {
                if (i == 0) System.out.print("\n");
                System.out.print("");
                for (int j = 0; j < arr[i].length; j++) {
                }
            }
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    arrBig[i][j] = arr[i][j];
                }
            }                                                                                          //FINISH!

            String[] timeInString;                                             // определение нажатой кнопки
            TimeNumber bolvanchikTN = new TimeNumber();
            bolvanchikTN.TestString(e.getActionCommand());
            File file3 = new File("ClickWayOrEat.txt");

            timeInString = bolvanchikTN.ReplaceToArray();

            int tIS1 = Integer.parseInt(timeInString[0]);
            int tIS2 = Integer.parseInt(timeInString[1]);

            secondArrayBoard.ReplaceToNew(arr);                                                         // клик обработка
            String whiteOrBlack = null;
            try {
                Scanner timescTS = new Scanner(new File("TimeSecond.txt"));
                whiteOrBlack = timescTS.next();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }


            String newClickWay = "";                                                                              // ход или поиск пути
            String timeNewClick = "";
            boolean canClick = false;

            try {
                Scanner timescCW = new Scanner(new File("ClickWayOrEat.txt"));
                timeNewClick = timescCW.next();
                if (timeNewClick.equals("") || timeNewClick.equals("Line")) {
                    newClickWay = secondArrayBoard.NewClickWay(tIS2, tIS1, whiteOrBlack);                   //смотрю можно ли ходить если да --- 01010100010!!!44 если нет ---- лайн

                } else {
                    String[] arrayTimeNewClick = timeNewClick.split("");                                // сли нажал и проверяю возможность хода
                    for (int i = 0; i < arrayTimeNewClick.length; i++) {
                        if (arrayTimeNewClick[i].equals("!")) break;
                        if (arrayTimeNewClick[i].equals("0")) {
                            canClick = true;
                        }

                    }
                    if (canClick == true) {
                        secondArrayBoard.ThisClickWay(tIS2, tIS1, whiteOrBlack);

                    } else {
                        PrintWriter out = null;
                        try {
                            out = new PrintWriter(file3.getAbsoluteFile());
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        try {
                            System.out.println("\n ошибочка \n");
                            out.print("Line");
                        } finally {
                            out.close();
                        }
                    }
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }


            Board replaceBoardAfterClick = new Board();
            secondPanel = replaceBoardAfterClick.Board(secondArrayBoard.bolvanchikArrayBoard);

            Integer[][] newTimeArrayBoard = secondArrayBoard.bolvanchikArrayBoard;
            Board newTimeBoard = new Board();
            newTimeBoard.Board(newTimeArrayBoard);

            File file2 = new File("ArrayBoard.txt");
            try {
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                PrintWriter out = new PrintWriter(file2.getAbsoluteFile());
                try {
                    for (int i = 0; i < secondArrayBoard.bolvanchikArrayBoard.length; i++) {
                        for (int j = 0; j < secondArrayBoard.bolvanchikArrayBoard[i].length; j++) {
                            if ((j == 7)) {
                                out.print(secondArrayBoard.bolvanchikArrayBoard[i][j]);
                            } else {
                                out.print(secondArrayBoard.bolvanchikArrayBoard[i][j] + " ");
                            }
                        }
                        out.print("\n");
                    }
                } finally {
                    out.close();
                }
            } catch (IOException exce) {
                throw new RuntimeException(exce);
            }

            mainWinJF.add(secondPanel);
            mainWinJF.validate();
        }
    }
}

class ArrayBoard {
    Integer[][] bolvanchikArrayBoard = new Integer[8][8];
    File file3 = new File("ClickWayOrEat.txt");

    void ReplaceToNew(int[][] time) {
        for (int i = 0; i < time.length; i++) {

            for (int j = 0; j < time[i].length; j++) {
                this.bolvanchikArrayBoard[i][j] = time[i][j];
            }
        }
    }

    void ThisClickWay(int firstM, int firstN, String blackOrWhite) throws FileNotFoundException {

        String scanFile = new Scanner(new File("ClickWayOrEat.txt")).next();
        String secondN = Character.toString(scanFile.charAt(11));
        String secondM = Character.toString(scanFile.charAt(12));
        boolean writeWhiteOrBlack = false;
        int mainBlackOrWhite = 1;
        int testBlackOrWhite = -1;
        if (blackOrWhite.equals("-1")) {            // white
            mainBlackOrWhite = -1;
            testBlackOrWhite = 1;
            writeWhiteOrBlack = true;
        }
        boolean timeBoo = false;
        if (firstM - 1 > -1) {
            if (this.bolvanchikArrayBoard[firstN][firstM - 1] == testBlackOrWhite) {
                for (int j = firstM - 1; j > -1; j--) {
                    if ((this.bolvanchikArrayBoard[firstN][j] == mainBlackOrWhite)) {
                        String strM = Integer.toString(j);
                        String strN = Integer.toString(firstN);
                        if ((strM.equals(secondM)) && (strN.equals(secondN))) {
                            timeBoo = true;
                            this.bolvanchikArrayBoard[firstN][j] = mainBlackOrWhite;              // исходное
                            this.bolvanchikArrayBoard[firstN][firstM] = mainBlackOrWhite;             //  последнее
                            for (int m = j; 8 > m; m++) {
                                this.bolvanchikArrayBoard[firstN][m] = mainBlackOrWhite;
                                if (m == firstM) break;
                            }
                        }
                    }
                    if (this.bolvanchikArrayBoard[firstN][j] == 9) break;
                }
            }
        }
        if (firstM + 1 < 8) {                                                                             //Left
            if (!timeBoo) {
                if (this.bolvanchikArrayBoard[firstN][firstM + 1] == testBlackOrWhite) {
                    for (int j = firstM + 1; j < 8; j++) {
                        if ((this.bolvanchikArrayBoard[firstN][j] == mainBlackOrWhite)) {
                            String strM = Integer.toString(j);
                            String strN = Integer.toString(firstN);
                            if ((strM.equals(secondM)) && (strN.equals(secondN))) {
                                timeBoo = true;
                                this.bolvanchikArrayBoard[firstN][j] = mainBlackOrWhite;                               // исходное
                                this.bolvanchikArrayBoard[firstN][firstM] = mainBlackOrWhite;                           //  последнее
                                for (int m = j; m > -1; m--) {
                                    this.bolvanchikArrayBoard[firstN][m] = mainBlackOrWhite;
                                    if (m == firstM) break;
                                }
                            }
                        }
                        if (this.bolvanchikArrayBoard[firstN][j] == 9) break;
                    }
                }
            }
        }
        if (firstN + 1 < 8) {                                                                             //UP
            if (!timeBoo) {
                if (this.bolvanchikArrayBoard[firstN + 1][firstM] == testBlackOrWhite) {
                    for (int i = firstN + 1; i < 8; i++) {
                        if ((this.bolvanchikArrayBoard[i][firstM] == mainBlackOrWhite)) {
                            String strM = Integer.toString(firstM);
                            String strN = Integer.toString(i);
                            if ((strM.equals(secondM)) && (strN.equals(secondN))) {
                                timeBoo = true;
                                this.bolvanchikArrayBoard[i][firstM] = mainBlackOrWhite;                               // исходное
                                this.bolvanchikArrayBoard[firstN][firstM] = mainBlackOrWhite;                           //  последнее
                                for (int n = i; n > -1; n--) {
                                    this.bolvanchikArrayBoard[n][firstM] = mainBlackOrWhite;
                                    if (n == firstN) break;
                                }
                            }
                        }
                        if (this.bolvanchikArrayBoard[i][firstM] == 9) break;
                    }
                }
            }
        }
        if (firstN - 1 > -1) {                                                                             //Down
            if (!timeBoo) {
                if (this.bolvanchikArrayBoard[firstN - 1][firstM] == testBlackOrWhite) {
                    for (int i = firstN - 1; i > -1; i--) {
                        if ((this.bolvanchikArrayBoard[i][firstM] == mainBlackOrWhite)) {
                            String strM = Integer.toString(firstM);
                            String strN = Integer.toString(i);
                            if ((strM.equals(secondM)) && (strN.equals(secondN))) {
                                timeBoo = true;
                                this.bolvanchikArrayBoard[i][firstM] = mainBlackOrWhite;                               // исходное
                                this.bolvanchikArrayBoard[firstN][firstM] = mainBlackOrWhite;                           //  последнее
                                for (int n = i; n < 8; n++) {
                                    this.bolvanchikArrayBoard[n][firstM] = mainBlackOrWhite;
                                    if (n == firstN) break;
                                }
                            }
                        }
                        if (this.bolvanchikArrayBoard[i][firstM] == 9) break;
                    }
                }
            }
        }
        if ((firstN + 1 < 8) && (firstM + 1 < 8)) {                                          // верх-влево?
            if (!timeBoo) {
                if (this.bolvanchikArrayBoard[firstN +1][firstM +1] == testBlackOrWhite) {
                    int i = firstN  + 1;
                    int j = firstM + 1;

                    while ((i  < 8) && (j  < 8)) {
                        if ((this.bolvanchikArrayBoard[i][j] == mainBlackOrWhite)) {
                            String strM = Integer.toString(j);
                            String strN = Integer.toString(i);
                            if ((strM.equals(secondM)) && (strN.equals(secondN))) {
                                timeBoo = true;
                                this.bolvanchikArrayBoard[i][j] = mainBlackOrWhite;                               // исходное
                                this.bolvanchikArrayBoard[firstN][firstM] = mainBlackOrWhite;
                                int m = j;
                                int n = i;
                                while ((-1 < n) && (-1 < m)) {
                                    System.out.println("Ход вверх-влево");
                                    this.bolvanchikArrayBoard[n][m] = mainBlackOrWhite;
                                    if ((n == firstN) && (m == firstM)) break;
                                    m--;
                                    n--;
                                }
                            }
                        }
                        if (this.bolvanchikArrayBoard[i][j] == 9) break;
                        i++;
                        j++;

                    }
                }
            }
        }
        if ((firstN - 1 > -1) && (firstM + 1 < 8)) {                                          // вниз-влево?
            if (!timeBoo) {
                if (this.bolvanchikArrayBoard[firstN - 1][firstM +1] == testBlackOrWhite) {
                    int i = firstN - 1;
                    int j = firstM + 1;

                    while ((i > -1) && (j  < 8)) {
                        if ((this.bolvanchikArrayBoard[i][j] == mainBlackOrWhite)) {
                            String strM = Integer.toString(j);
                            String strN = Integer.toString(i);
                            if ((strM.equals(secondM)) && (strN.equals(secondN))) {
                                timeBoo = true;
                                this.bolvanchikArrayBoard[i][j] = mainBlackOrWhite;                               // исходное
                                this.bolvanchikArrayBoard[firstN][firstM] = mainBlackOrWhite;
                                int m = j;
                                int n = i;
                                while ((n < 8) && (-1 < m)) {
                                    System.out.println("ход вниз-влево");
                                    this.bolvanchikArrayBoard[n][m] = mainBlackOrWhite;
                                    if ((n == firstN) && (m == firstM)) break;
                                    m--;
                                    n++;
                                }
                            }
                        }
                        if (this.bolvanchikArrayBoard[i][j] == 9) break;
                        i--;
                        j++;

                    }
                }
            }
        }
        if ((firstN - 1 > -1) && (firstM - 1 > -1)) {                                          // вниз-вправо
            if (!timeBoo) {
                if (this.bolvanchikArrayBoard[firstN - 1][firstM - 1] == testBlackOrWhite) {
                    int i = firstN - 1;
                    int j = firstM - 1;

                    while ((i > -1) && (j > -1)) {
                        if ((this.bolvanchikArrayBoard[i][j] == mainBlackOrWhite)) {
                            String strM = Integer.toString(j);
                            String strN = Integer.toString(i);
                            if ((strM.equals(secondM)) && (strN.equals(secondN))) {
                                timeBoo = true;
                                this.bolvanchikArrayBoard[i][j] = mainBlackOrWhite;                               // исходное
                                this.bolvanchikArrayBoard[firstN][firstM] = mainBlackOrWhite;
                                int m = j;
                                int n = i;
                                while ((n < 8) && (8 > m)) {
                                    System.out.println("Ход вниз-вправо");
                                    this.bolvanchikArrayBoard[n][m] = mainBlackOrWhite;
                                    if ((n == firstN) && (m == firstM)) break;
                                    m++;
                                    n++;
                                }
                            }
                        }
                        if (this.bolvanchikArrayBoard[i][j] == 9) break;
                        i--;
                        j--;

                    }
                }
            }
        }
        if ((firstN + 1 < 8) && (firstM - 1 > -1)) {                                          // верх-вправо
            if (!timeBoo) {
                if (this.bolvanchikArrayBoard[firstN + 1][firstM -1] == testBlackOrWhite) {
                    int i = firstN + 1;
                    int j = firstM - 1;

                    while ((i < 8 ) && (j  > -1)) {
                        if ((this.bolvanchikArrayBoard[i][j] == mainBlackOrWhite)) {
                            String strM = Integer.toString(j);
                            String strN = Integer.toString(i);
                            if ((strM.equals(secondM)) && (strN.equals(secondN))) {
                                timeBoo = true;
                                this.bolvanchikArrayBoard[i][j] = mainBlackOrWhite;                               // исходное
                                this.bolvanchikArrayBoard[firstN][firstM] = mainBlackOrWhite;
                                int m = j;
                                int n = i;
                                while ((n >-1 ) && (8> m)) {
                                    System.out.println("ход верх-вправо");
                                    this.bolvanchikArrayBoard[n][m] = mainBlackOrWhite;
                                    if ((n == firstN) && (m == firstM)) break;
                                    m++;
                                    n--;
                                }
                            }
                        }
                        if (this.bolvanchikArrayBoard[i][j] == 9) break;
                        i++;
                        j--;

                    }
                }
            }
        }
/*
        if (blackOrWhite.equals("1")) {                                                                     //!!!!!!!!!!!!!!!!!!!
            if (firstM + 1 < 8) {                                                                             //Left
                System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            //    if (!timeBoo) {
                    if (this.bolvanchikArrayBoard[firstN][firstM + 1] == -1) {
                        System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        for (int j = firstM + 1; j < 8; j++) {
                            if ((this.bolvanchikArrayBoard[firstN][j] == 1)) {
                                System.out.println("\n!!!!!!!!!!!!!");
                                String strM = Integer.toString(j);
                                String strN = Integer.toString(firstN);
                                System.out.println(strM);
                                System.out.println(secondM);
                                System.out.println(strN);
                                System.out.println(secondN);
                                if ((strM.equals(secondM)) && (strN.equals(secondN))) {
                                    System.out.println("\n!!!!!");
                                    timeBoo = true;
                                    this.bolvanchikArrayBoard[firstN][j] = 1;                               // исходное
                                    this.bolvanchikArrayBoard[firstN][firstM] = 1;                           //  последнее
                                    for (int m = j; m > -1; m--) {
                                        this.bolvanchikArrayBoard[firstN][m] = 1;
                                        if (m == firstM) break;
                                    }
                                }
                            }
                            if (this.bolvanchikArrayBoard[firstN][j] == 9) break;
                        }
                    }
           //     }
            }
            if (firstM - 1 > -1) {

                System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                if (this.bolvanchikArrayBoard[firstN][firstM - 1] == -1) {
                    System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    for (int j = firstM - 1; j > -1; j--) {
                        if ((this.bolvanchikArrayBoard[firstN][j] == 1)) {
                            String strM = Integer.toString(j);
                            String strN = Integer.toString(firstN);
                            if ((strM.equals(secondM)) && (strN.equals(secondN))) {
                                timeBoo = true;
                                this.bolvanchikArrayBoard[firstN][j] = 1;              // исходное
                                this.bolvanchikArrayBoard[firstN][firstM] = 1;             //  последнее
                                for (int m = j; 8 > m; m++) {
                                    this.bolvanchikArrayBoard[firstN][m] = 1;
                                    if (m == firstM) break;
                                }
                            }
                        }
                        if (this.bolvanchikArrayBoard[firstN][j] == 9) break;
                    }
                }

            }

            if (firstN + 1 < 8) {                                                                             //UP
                System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                if (!timeBoo) {
                    if (this.bolvanchikArrayBoard[firstN + 1][firstM] == -1) {
                        System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        for (int i = firstN + 1; i < 8; i++) {
                            if ((this.bolvanchikArrayBoard[i][firstM] == 1)) {
                                String strM = Integer.toString(firstM);
                                String strN = Integer.toString(i);
                                if ((strM.equals(secondM)) && (strN.equals(secondN))) {
                                    timeBoo = true;
                                    this.bolvanchikArrayBoard[i][firstM] = 1;                               // исходное
                                    this.bolvanchikArrayBoard[firstN][firstM] = 1;                           //  последнее
                                    for (int n = i; n > -1; n--) {
                                        this.bolvanchikArrayBoard[n][firstM] = 1;
                                        if (n == firstN) break;
                                    }
                                }
                            }
                            if (this.bolvanchikArrayBoard[i][firstM] == 9) break;
                        }
                    }
                }
            }
            if (firstN - 1 > -1) {                                                                             //Down
                System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                if (!timeBoo) {
                    if (this.bolvanchikArrayBoard[firstN - 1][firstM] == -1) {
                        System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        for (int i = firstN - 1; i > -1; i--) {
                            if ((this.bolvanchikArrayBoard[i][firstM] == 1)) {
                                String strM = Integer.toString(firstM);
                                String strN = Integer.toString(i);
                                if ((strM.equals(secondM)) && (strN.equals(secondN))) {
                                    timeBoo = true;
                                    this.bolvanchikArrayBoard[i][firstM] = 1;                               // исходное
                                    this.bolvanchikArrayBoard[firstN][firstM] = 1;                           //  последнее
                                    for (int n = i; n < 8; n++) {
                                        this.bolvanchikArrayBoard[n][firstM] = 1;
                                        if (n == firstN) break;
                                    }
                                }
                            }
                            if (this.bolvanchikArrayBoard[i][firstM] == 9) break;
                        }
                    }
                }
            }
        }




 */


        if (timeBoo) {
            File file3 = new File("TimeSecond.txt");

            try {
                if (!file3.exists()) {
                    file3.createNewFile();
                }
                try (PrintWriter out = new PrintWriter(file3.getAbsoluteFile())) {
                    if (writeWhiteOrBlack) {
                        out.print("1");
                    } else {
                        out.print("-1");
                    }

                }
            } catch (IOException ecept) {
                throw new RuntimeException(ecept);
            }
        }

        PrintWriter out = new PrintWriter(file3.getAbsoluteFile());
        try {
            out.print("Line");
        } finally {
            out.close();
        }
    }


    String NewClickWay(int m, int n, String blackOrWhite) throws FileNotFoundException {            // е: камон, если бы я не прочитал файл я бы тут не оказался
        String timeArrToWay = "";
        int wayUpIsOpen = 9;
        int wayDownIsOpen = 9;
        int wayLeftIsOpen = 9;
        int wayRightIsOpen = 9;
        int wayURIsOpen = 9;
        int wayRDIsOpen = 9;
        int wayDLIsOpen = 9;
        int wayLUIsOpen = 9;

        int mainBlackOrWhite = 1;
        int testBlackOrWhite = -1;

        if (blackOrWhite.equals("-1")) {            // white
            mainBlackOrWhite = -1;
            testBlackOrWhite = 1;

        }


        if (this.bolvanchikArrayBoard[n][m] == mainBlackOrWhite) {                                    //??
            if (m - 1 > -1) {
                if (this.bolvanchikArrayBoard[n][m - 1] == testBlackOrWhite) {
                    for (int j = m - 1; j > -1; j--) {
                        if (this.bolvanchikArrayBoard[n][j] == mainBlackOrWhite) break;
                        if (this.bolvanchikArrayBoard[n][j] == 9) {
                            System.out.println("     доступен шаг влево");
                            wayLeftIsOpen = 0;
                            break;
                        }
                    }
                }
            }
            if (n - 1 > -1) {
                if (this.bolvanchikArrayBoard[n - 1][m] == testBlackOrWhite) {
                    for (int i = n - 1; i > -1; i--) {
                        if (this.bolvanchikArrayBoard[i][m] == mainBlackOrWhite) break;
                        if (this.bolvanchikArrayBoard[i][m] == 9) {
                            System.out.println("     доступен шаг вверх");
                            wayUpIsOpen = 0;
                            break;
                        }

                    }
                }
            }
            if (m + 1 < 8) {
                if (this.bolvanchikArrayBoard[n][m + 1] == testBlackOrWhite) {
                    for (int j = m + 1; j < 8; j++) {
                        if (this.bolvanchikArrayBoard[n][j] == mainBlackOrWhite) break;
                        if (this.bolvanchikArrayBoard[n][j] == 9) {
                            System.out.println("     доступен шаг вправо");
                            wayRightIsOpen = 0;
                            break;
                        }
                    }
                }
            }
            if (n + 1 < 8) {
                if (this.bolvanchikArrayBoard[n + 1][m] == testBlackOrWhite) {
                    for (int i = n + 1; i < 8; i++) {
                        if (this.bolvanchikArrayBoard[i][m] == mainBlackOrWhite) break;
                        if (this.bolvanchikArrayBoard[i][m] == 9) {
                            System.out.println("     доступен шаг вниз");
                            wayDownIsOpen = 0;
                            break;
                        }
                    }
                }
            }
            if (m - 1 > -1) {
                if (this.bolvanchikArrayBoard[n][m - 1] == testBlackOrWhite) {
                    for (int j = m - 1; j > -1; j--) {
                        if (this.bolvanchikArrayBoard[n][j] == mainBlackOrWhite) break;
                        if (this.bolvanchikArrayBoard[n][j] == 9) {
                            System.out.println("     доступен шаг влево");
                            wayLeftIsOpen = 0;
                            break;
                        }
                    }
                }
            }
            if ((n + 1 < 7) && (m - 1 > -1)) {                                                     // UP-RIGHT
                if (this.bolvanchikArrayBoard[n + 1][m - 1] == testBlackOrWhite) {
                    int i = n + 1;
                    int j = m - 1;
                    while ((i < 8) && (j > -1)) {
                        if (this.bolvanchikArrayBoard[i][j] == mainBlackOrWhite) break;
                        if (this.bolvanchikArrayBoard[i][j] == 9) {
                            System.out.println("     доступен шаг влево-вниз");
                            wayDLIsOpen = 0;
                            break;
                        }
                        i++;
                        j--;
                    }
                }
            }
            if ((n + 1 < 7) && (m + 1 < 7)) {                                          // LEFT_UP
                if (this.bolvanchikArrayBoard[n + 1][m + 1] == testBlackOrWhite) {
                    int i = n + 1;
                    int j = m + 1;
                    while ((i < 8) && (j < 8)) {
                        if (this.bolvanchikArrayBoard[i][j] == mainBlackOrWhite) break;
                        if (this.bolvanchikArrayBoard[i][j] == 9) {
                            System.out.println("     доступен шаг Вправо-вниз");
                            wayDLIsOpen = 0;
                            break;
                        }
                        i++;
                        j++;
                    }
                }
            }
            if ((n - 1 > -1) && (m + 1 < 7)) {                                          // Down-left
                if (this.bolvanchikArrayBoard[n - 1][m + 1] == testBlackOrWhite) {
                    int i = n - 1;
                    int j = m + 1;
                    while ((i > -1) && (j < 8)) {
                        if (this.bolvanchikArrayBoard[i][j] == mainBlackOrWhite) break;
                        if (this.bolvanchikArrayBoard[i][j] == 9) {
                            System.out.println("     доступен шаг Вправо-Вверх");
                            wayDLIsOpen = 0;
                            break;
                        }
                        i--;
                        j++;
                    }
                }
            }
            if ((n - 1 > -1) && (m - 1 > -1)) {                                          // RIGHT-Down
                if (this.bolvanchikArrayBoard[n - 1][m - 1] == testBlackOrWhite) {
                    int i = n - 1;
                    int j = m - 1;
                    while ((i > -1) && (j > -1)) {
                        if (this.bolvanchikArrayBoard[i][j] == mainBlackOrWhite) break;
                        if (this.bolvanchikArrayBoard[i][j] == 9) {
                            System.out.println("     доступен шаг Вверх-Влево");
                            wayDLIsOpen = 0;
                            break;
                        }
                        i--;
                        j--;

                    }
                }
            }
            timeArrToWay = Integer.toString(wayUpIsOpen) + Integer.toString(wayDownIsOpen) + Integer.toString(wayLeftIsOpen) + Integer.toString(wayRightIsOpen)
                    + Integer.toString(wayURIsOpen) + Integer.toString(wayRDIsOpen) + Integer.toString(wayDLIsOpen) + Integer.toString(wayLUIsOpen) + "!!!" + n + m + "&&&";
        }


        PrintWriter out = new PrintWriter(file3.getAbsoluteFile());
        try {
            if (timeArrToWay.equals("")) {
                out.print("Line");
            } else out.print(timeArrToWay);

        } finally {
            out.close();
        }
        return timeArrToWay;
    }

    void NGBoard() {
        for (int i = 0; i < this.bolvanchikArrayBoard.length; i++) {              //заполнение доски мусором без нуллов
            for (int j = 0; j < this.bolvanchikArrayBoard[i].length; j++) {
                this.bolvanchikArrayBoard[i][j] = 9;
            }
        }
        this.bolvanchikArrayBoard[3][4] = 1;
        this.bolvanchikArrayBoard[4][3] = 1;
        this.bolvanchikArrayBoard[3][3] = -1;
        this.bolvanchikArrayBoard[4][4] = -1;

    }
}


class TimeNumber {
    private String numberOfButton = "";

    void TestString(String time) {
        numberOfButton = time;
        System.out.print("\n" + "NUMBER " + numberOfButton);
    }

    String[] ReplaceToArray() {
        String[] timeStr;
        timeStr = numberOfButton.split("");
        return timeStr;
    }
}

class Board {
    String whiteWay = "WhitePoint.png";
    String blackWay = "BlackPoint.png";
    String noWay = "noPoint.png";
    Icon whiteWayICON = new ImageIcon(whiteWay);
    Icon blackWayICON = new ImageIcon(blackWay);
    Icon noWayICON = new ImageIcon(noWay);

    JPanel Board(Integer[][] bolvanchikBoard) {
        JPanel panelBoard = new JPanel();
        panelBoard.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < bolvanchikBoard.length; i++) {
            for (int j = 0; j < bolvanchikBoard[i].length; j++) {
                JButton bottonTime = new JButton(Integer.toString(i) + j);
                bottonTime.setBackground(new Color(80, 50, 90));
                StartWind.ListenerAction timeOutStr = new StartWind.ListenerAction();
                bottonTime.addActionListener(timeOutStr);
                bottonTime.setFocusPainted(false);
                if (bolvanchikBoard[i][j] == null) {
                    System.out.print("как такое возможно?");
                    continue;
                }
                if (bolvanchikBoard[i][j] == -1) {
                    bottonTime.setIcon(whiteWayICON);
                }
                if (bolvanchikBoard[i][j] == 1) {
                    bottonTime.setIcon(blackWayICON);
                }
                if (bolvanchikBoard[i][j] == 9) {
                    //  bottonTime.setIcon(noWayICON);
                }
                panelBoard.add(bottonTime);
            }
        }
        return panelBoard;
    }
}
