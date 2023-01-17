package org.example;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileHelper {
    public void removeNotExistFiles(ArrayList arrFiles) {
        for (int i = 1; i < arrFiles.size(); i++) {
            File file = new File(arrFiles.get(0).toString());

            if (!file.exists()) {
                System.out.println("Файл " + arrFiles.get(0).toString() + " не найден");
                arrFiles.remove(i);
                i--;
            }
        }

        if (arrFiles.size() == 1) {
            System.out.println("Укажите входные файлы и перезапустите программу");
            System.out.println("Нажмите любую клавишу для завершения работы...");
            System.exit(0);
        }

        if (arrFiles.size() == 0) {
            System.out.println("Укажите входной и выходные файлы и перезапустите программу");
            System.out.println("Нажмите любую клавишу для завершения работы...");
            System.exit(0);
        }
    }

   /* public void removeFile(String filename) {
        File file=new File(filename);

        if (filename != null) {
            file.delete();
        }
    }

    public void removeFileByMask(String mask) {
        File file=new File(mask);

        for (String temp: file.list()) {
           new File(temp).delete();
        }
    }*/
}
