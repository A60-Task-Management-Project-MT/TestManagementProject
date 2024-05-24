package com.company.oop.test.menagement.units;

import com.company.oop.test.menagement.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.company.oop.test.menagement.units.ParsingHelpers.tryParseEnum;
import static com.company.oop.test.menagement.units.ParsingHelpers.tryParseInt;

class ParsingHelpersTest {
    enum TestEnum {
        TEST_VALUE,
        ANOTHER_TEST_VALUE
    }

    @Test
    void tryParseEnum_should_parseCorrectly_when_valueToParseHasWhitespace() {
        Assertions.assertEquals(TestEnum.TEST_VALUE,
                tryParseEnum("test value", TestEnum.class, ""));
    }

    @Test
    void tryParseEnum_should_parseCorrectly_when_valueToParseHasMultipleWhitespaces() {
        Assertions.assertEquals(TestEnum.ANOTHER_TEST_VALUE,
                tryParseEnum("another test value", TestEnum.class, ""));
    }

    @Test
    void tryParseInteger_should_throwException_when_valueUnparsable() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tryParseInt("invalid-value", ""));
    }

    @Test
    void tryParseInteger_should_return_when_valueParsable() {
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> tryParseInt("1", "")),
                () -> Assertions.assertEquals(1, tryParseInt("1", ""))
        );
    }

}