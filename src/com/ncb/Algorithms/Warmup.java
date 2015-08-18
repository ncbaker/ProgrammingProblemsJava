package com.ncb.Algorithms;

import java.math.BigInteger;

/**
 * Created by nathanb on 8/18/2015.
 */
public class Warmup {
    /* https://www.hackerrank.com/challenges/extra-long-factorials */
    static void factProblem() {
        short num = new java.util.Scanner(System.in).nextShort();
        System.out.println(fact(num));
    }
    static BigInteger fact(int i)
    {
        BigInteger b = BigInteger.valueOf(i);
        return i <= 1 ? b : b.multiply(fact(i - 1));
    }
}
