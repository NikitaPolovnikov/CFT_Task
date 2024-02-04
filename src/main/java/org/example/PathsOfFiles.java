package org.example;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsOfFiles {
    private String resultPath = "";
    private String jarDir;
    private String prefix = "";
    private Path pathString;
    private Path pathInt;
    private Path pathFloat;

    public Path getPathString() {
        return pathString;
    }

    public Path getPathInt() {
        return pathInt;
    }

    public Path getPathFloat() {
        return pathFloat;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setPathString() {
        this.pathString = Paths.get(resultPath + prefix + "strings.txt");
    }

    public void setPathInt() {
        this.pathInt = Paths.get(resultPath + prefix + "integers.txt");
    }

    public void setPathFloat() {
        this.pathFloat = Paths.get(resultPath + prefix + "floats.txt");
    }

    public String findJarDir(){
        try {
            jarDir = Main
                    .class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
        } catch (
                URISyntaxException e){
            System.out.println("Invalid directory or file name");
        }
        jarDir = jarDir.substring(jarDir.indexOf("/")+1);
        return jarDir.substring(0, jarDir.lastIndexOf("/") + 1);
    }

}
