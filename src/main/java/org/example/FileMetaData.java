package org.example;

import java.io.*;

public class FileMetaData {
    public String line;
    public String linePrev;
    public String fileName;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLinePrev() {
        return linePrev;
    }

    public void setLinePrev(String linePrev) {
        this.linePrev = linePrev;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ICompare getComparer() {
        return comparer;
    }

    public void setComparer(ICompare comparer) {
        this.comparer = comparer;
    }

    public ICompare comparer;

    public void MergeLine(BufferedReader reader, BufferedWriter writer) throws IOException {
        if (linePrev == null || linePrev.contains(" ") ||linePrev.equals("")) {
            line = null;
            System.out.println("Файл " + fileName + " поврежден после элемента " + linePrev + ", дальнейшие данные не учитываются");
            System.exit(0);
        } else if (comparer.isNotCorrectOrder(linePrev, line)) {
            line = null;
            System.out.println("Файл " + fileName + " поврежден после элемента " + linePrev + ", дальнейшие данные не учитываются");
            System.exit(0);
        } else {
            writer.write(line);
            writer.newLine();
            linePrev = line;
            line = reader.readLine();
        }
    }
}
