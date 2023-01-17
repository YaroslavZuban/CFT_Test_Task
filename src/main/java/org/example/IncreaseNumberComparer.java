package org.example;

public class IncreaseNumberComparer implements ICompare {
    public Boolean isNotCorrectOrder(String line1, String line2) {
        return Integer.parseInt(line1) > Integer.parseInt(line2);
    }

    public Boolean isNextValid(String line1, String line2) {
        return Integer.parseInt(line1) < Integer.parseInt(line2);
    }
}
