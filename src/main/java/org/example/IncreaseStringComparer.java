package org.example;

public class IncreaseStringComparer implements ICompare {
    public Boolean isNotCorrectOrder(String line1, String line2) {
        return line1.compareTo(line2) > 0;
    }

    public Boolean isNextValid(String line1, String line2) {
        return line1.compareTo(line2) < 0;
    }
}
