package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;


@SuppressWarnings("unchecked")
public class Main {
    static List <String> list;
    static int numberOfTxt;
    static List<Integer> integerList = new ArrayList<>();
    static List<Float> floatList = new ArrayList<>();
    static List<String> stringList = new ArrayList<>();
    static boolean option = false;
    static boolean fullStat = false;
    static boolean shortStat = false;

    public static void main(String[] args) throws  IOException {

        PathsOfFiles pathsOfFiles = new PathsOfFiles();

        for (String arg : args) {
            if (arg.endsWith(".txt"))
                numberOfTxt++;
        }

        String[][] arr = new String[numberOfTxt][];
        numberOfTxt = 0;


        for (int i = 0; i < args.length; i++) {

            if (args[i].endsWith(".txt")) {

                try {
                    Path path = Path.of(pathsOfFiles.findJarDir() + args[i]);
                    list = Files.readAllLines(path);
                    arr[numberOfTxt] = list.toArray(new String[0]);
                    numberOfTxt++;
                } catch (NoSuchFileException e) {
                    System.out.println("Invalid file name: " + args[i]);
                }
            } else if (args[i].equals("-p")) {
                pathsOfFiles.setPrefix(args[i + 1]);
            } else if (args[i].equals("-o")) {
                pathsOfFiles.setResultPath(args[i + 1] + "/");
            } else if (args[i].equals("-f")) {
                fullStat = true;
            } else if (args[i].equals("-s")) {
                shortStat = true;
            } else if (args[i].equals("-a")) {
                option = true;
            }
        }
        pathsOfFiles.setPathInt();
        pathsOfFiles.setPathFloat();
        pathsOfFiles.setPathString();

        int longestRow = 0;

        try {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length > longestRow) {
                    longestRow = arr[i].length;
                }
            }

            for (int j = 0; j < longestRow; j++) {
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].length > j) {

                        if (isNumericInt(arr[i][j])) {
                            integerList.add(Integer.parseInt(arr[i][j]));
                        } else if (isNumericFloat(arr[i][j])) {
                            floatList.add(Float.parseFloat(arr[i][j]));

                        } else if (!arr[i][j].equals("")) {
                            stringList.add(arr[i][j]);
                        }
                    }
                }
            }
        } catch (NullPointerException e){
            System.out.println("Check the specified files");
        }

        ResultsAndStats threeCollections = new ResultsAndStats(stringList, integerList, floatList);
        threeCollections.setPathsOfFiles(pathsOfFiles);
        threeCollections.resultsAndStats(stringList, integerList, floatList);
    }

    public static boolean isNumericInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isNumericFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}






