package com.architecture;

public class IntegerConverter {

    public static String toBinary(int num) {
        StringBuilder sb = new StringBuilder(32);
        for (int i = 31; i >= 0; i--) {
            int bit = (num >>> i) & 1;
            sb.append(bit);
        }
        return sb.toString();
    }

    public static String toOctal(int num) {
        StringBuilder sb = new StringBuilder(11);
        for (int i = 30; i >= 0; i -= 3) {
            int octDigit = (num >>> i) & 0b111;
            sb.append(octDigit);
        }
        return sb.toString();
    }

    public static String toHexadecimal(int num) {
        StringBuilder sb = new StringBuilder(8);
        for (int i = 28; i >= 0; i -= 4) {
            int hexDigit = (num >>> i) & 0b1111;
            if (hexDigit < 10) {
                sb.append((char) ('0' + hexDigit));
            }
            else {
                sb.append((char) ('A' + (hexDigit - 10)));
            }
        }
        return sb.toString();
    }

}
