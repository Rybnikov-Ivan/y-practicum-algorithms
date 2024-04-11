package ru.ypracticum.sprint5.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class I1 {

    private static BigInteger getCountTrees(BigInteger n) {
        return factorial(n.multiply(BigInteger.TWO)).divide(factorial(n).multiply(factorial(n.add(BigInteger.ONE))));
    }

    private static BigInteger factorial(BigInteger num) {
        if (num.equals(BigInteger.ZERO) || num.equals(BigInteger.ONE))
            return BigInteger.ONE;

        return factorial(num.subtract(BigInteger.ONE)).multiply(num);
    }

    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            BigInteger n = BigInteger.valueOf(Long.parseLong(reader.readLine()));
            System.out.println(getCountTrees(n));
        }
    }
}
