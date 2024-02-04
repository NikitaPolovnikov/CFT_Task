package org.example;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsAndStats<T> implements TxtFileWrite{

    private String s = "a";
    private Integer integer = 1;
    private Float aFloat = 1.00F;
    private PathsOfFiles pathsOfFiles;
    private List<Integer> integerList;
    private List<Float> floatList;
    private List<String> stringList;

    public void setPathsOfFiles(PathsOfFiles pathsOfFiles) {
        this.pathsOfFiles = pathsOfFiles;
    }

    public ResultsAndStats(List<String> stringList, List<Integer> integerList, List<Float> floatList) {
        this.stringList = stringList;
        this.integerList = integerList;
        this.floatList = floatList;
    }
    public void printShortStat(List list) {
        if (list.get(0).getClass() == s.getClass()) {
            System.out.println("Number of strings: " + list.size());
        } else if (list.get(0).getClass() == integer.getClass()) {
            System.out.println("Number of integers: " + list.size());
        } else if (list.get(0).getClass() == aFloat.getClass()) {
            System.out.println("Number of floats: " + list.size());
        }
    }
    public void printFullStat(List list){
        if (list.get(0).getClass() == s.getClass()){
            System.out.println("Number of strings: " + stringList.size());
            System.out.println("Max length of string: " + stringList.stream().map(String::length).max(Integer::compareTo).get());
            System.out.println("Min length of string: " + stringList.stream().map(String::length).min(Integer::compareTo).get());
        } else if (list.get(0).getClass() == integer.getClass()) {
            System.out.println(Main.integerList.stream().mapToInt(a -> a).summaryStatistics());
        } else if (list.get(0).getClass() == aFloat.getClass()) {
            System.out.println(Main.floatList.stream().mapToDouble(a -> a).summaryStatistics());
        }
    }
    @SuppressWarnings("unchecked")
    public void resultsAndStats(List<T>... list) throws IOException {
        for (List<T> ts : list) {
            if (!ts.isEmpty()) {
                if (ts.get(0).getClass() == s.getClass()) {
                    writeFile(pathsOfFiles.getPathString(), stringList, Main.option);
                } else if (ts.get(0).getClass() == integer.getClass()) {
                    writeFile(pathsOfFiles.getPathInt(), integerList.stream().map(a -> a.toString()).collect(Collectors.toList()), Main.option);
                } else if (ts.get(0).getClass() == aFloat.getClass()) {
                    writeFile(pathsOfFiles.getPathFloat(), floatList.stream().map(a -> a.toString()).collect(Collectors.toList()), Main.option);
                }
                if (Main.shortStat) {
                    printShortStat(ts);
                }
                if (Main.fullStat) {
                    printFullStat(ts);
                }
            }
        }
    }
}
