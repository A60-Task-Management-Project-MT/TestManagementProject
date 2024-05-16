package com.company.oop.test.menagement.units;

import com.company.oop.test.menagement.exceptions.InvalidUserInputException;

public class ParsingHelpers {

    public static int tryParseInt(String valueToParse, String errorMessage) {
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type, String errorMessage) {
        try {
            return Enum.valueOf(type, valueToParse.replace(" ", "_").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUserInputException(String.format(errorMessage, valueToParse));
        }
    }
}
