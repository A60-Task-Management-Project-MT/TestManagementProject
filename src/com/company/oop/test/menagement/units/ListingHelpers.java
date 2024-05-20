package com.company.oop.test.menagement.units;

import com.company.oop.test.menagement.commands.CommandConstants;
import com.company.oop.test.menagement.models.contracts.Printable;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers {
    public static <T extends Printable> String elementsToString(List<T> elements) {
        List<String> result = new ArrayList<>();
        for (T element : elements) {
            result.add(element.getAsString());
        }
        return String.join(CommandConstants.JOIN_DELIMITER + System.lineSeparator(), result).trim();
    }
}
