package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgumentHelper {
    private List<String> arguments = Arrays.asList("-i", "-s", "-a", "-d");

    public ArrayList ExtractArguments(String[] args) {
        var arrArgs = new ArrayList();

        for (String e : args) {
            if (arguments.contains(e)) {
                arrArgs.add(e);
            }
        }

        CheckArgs(arrArgs);
        return arrArgs;
    }

    public ArrayList ExtractNameFiles(String[] args) {
        var arrFiles = new ArrayList();

        for (String e : args) {

            if (e.contains(".txt")) {
                arrFiles.add(e);
            }
        }

        return arrFiles;
    }

    private void CheckArgs(ArrayList arrArgs) {
        for (Object e : arrArgs) {
            if (!arguments.contains(e) &&!e.toString().contains(".txt")) {
                System.out.println("Неизвестный аргумент " + e);
                System.out.println("Доступные аргументы:");
                System.out.println("-i для сортировки чисел, -s для сортировки строк");
                System.out.println("-a для сортировки по возрастанию, -d для сортировки по убыванию");
                System.out.println("Нажмите любую клавишу для завершения работы...");
                System.exit(0);
                System.out.println();
            }
        }

        if (arrArgs.size() == 0) {
            System.out.println("Укажите аргументы и перезапустите программу");
            System.out.println("Нажмите любую клавишу для завершения работы...");
            System.exit(0);
        }
    }

}
