package com.architecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class IntegerConverterTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/binary_test_data.csv", numLinesToSkip = 1)
    public void shouldConvertIntegerToBinary(int number, String expectedBinString) {
        Assertions.assertEquals(expectedBinString, IntegerConverter.toBinary(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/octal_test_data.csv", numLinesToSkip = 1)
    public void shouldConvertIntegerToOctal(int number, String expectedOctString) {
        Assertions.assertEquals(expectedOctString, IntegerConverter.toOctal(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/hexadecimal_test_data.csv", numLinesToSkip = 1)
    public void shouldConvertIntegerToHexadecimal(int number, String expectedHexString) {
        Assertions.assertEquals(expectedHexString, IntegerConverter.toHexadecimal(number));
    }

}
