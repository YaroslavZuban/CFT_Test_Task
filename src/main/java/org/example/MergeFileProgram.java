package org.example;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class MergeFileProgram {
    private ArgumentHelper argumentHelper = new ArgumentHelper();

    private FileHelper fileHelper = new FileHelper();

    private String tempFileMask = "Temp*.txt";

    public void Run(String[] args) {
        var arrFiles = argumentHelper.ExtractNameFiles(args);

        var arrArgs = argumentHelper.ExtractArguments(args);

        fileHelper.removeNotExistFiles(arrFiles);

        ICompare comparer = new CompareFactory().CreateComparerByArguments(arrArgs);

        String pathInNext;
        String pathOut = arrFiles.get(0).toString();
        String tempFile = null;

        tempFile = mergeFiles(arrFiles, comparer);
        File head = new File(tempFile);

        File temp = new File(pathOut);
        temp.delete();

        head.renameTo(temp);

        System.out.println("Сортировка выполнена, результат находится в файле " + pathOut);
    }

    private String mergeFiles(List arrFiles, ICompare comparer) {
        String pathInNext;
        int k = 0;
        String pathIn;
        String tempFile = null;


        for (int i = 0; i < arrFiles.size() - 1; i++) {
            if (arrFiles.size() == 2) {
                pathIn = arrFiles.get(1).toString();
                tempFile = "temp" + k + ".txt";
                k++;


                try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(pathIn))) {
                        FileMetaData File1 = new FileMetaData();

                        File1.fileName = pathIn;
                        File1.comparer = comparer;

                        File1.line = reader.readLine();

                        File1.linePrev = File1.line;

                        while (File1.line != null) {
                            File1.MergeLine(reader, writer);
                        }

                        pathIn = tempFile;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                pathIn = arrFiles.get(1).toString();

                pathInNext = arrFiles.get(i + 1).toString();


                tempFile = "temp" + k + ".txt";
                k++;

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                    try (BufferedReader reader1 = new BufferedReader(new FileReader(pathIn))) {

                        FileMetaData File1 = new FileMetaData();
                        FileMetaData File2 = new FileMetaData();

                        File1.fileName = pathIn;
                        File2.fileName = pathInNext;

                        File1.comparer = comparer;
                        File2.comparer = comparer;

                        try (BufferedReader reader2 = new BufferedReader(new FileReader(pathInNext))) {
                            File1.line = reader1.readLine();
                            File2.line = reader2.readLine();

                            File1.linePrev = File1.line;
                            File2.linePrev = File2.line;


                            while (File1.line != null || File2.line != null) {
                                if (File1.line == null) {
                                    File2.MergeLine(reader2, writer);
                                } else if (File2.line == null) {
                                    File1.MergeLine(reader1, writer);
                                } else if (File1.line.equals("") ||File1.line.equals(" ")) {
                                    System.out.println("Не верный формат файла "+pathIn);
                                    System.exit(0);
                                } else if (File2.line.equals("") ||File2.line.equals(" ")) {
                                    System.out.println("Не верный формат файла "+pathInNext);
                                    System.exit(0);
                                } else if ( comparer.isNextValid(File1.line, File2.line)) {
                                    File1.MergeLine(reader1, writer);
                                } else {
                                    File2.MergeLine(reader2, writer);
                                }
                            }
                        }

                        pathIn = tempFile;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return tempFile;
    }
}
