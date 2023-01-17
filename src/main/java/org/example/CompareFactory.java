package org.example;

import java.util.List;

public class CompareFactory {
    public ICompare createComparerByArguments(List arrArgs) {
        ICompare comparer = null;

        if (arrArgs.contains("-i")) {
            if (arrArgs.contains("-a")) {
                comparer = new IncreaseNumberComparer();
            } else if (arrArgs.contains("-d")) {
                comparer = new DecreaseNumberComparer();
            } else {
                comparer = new IncreaseNumberComparer();
            }
        } else if (arrArgs.contains("-s")) {
            if (arrArgs.contains("-a")) {
                comparer = new IncreaseStringComparer();
            } else if (arrArgs.contains("-d")) {
                comparer = new DecreaseStringComparer();
            } else {
                comparer = new IncreaseStringComparer();
            }
        }

        return comparer;
    }
}

