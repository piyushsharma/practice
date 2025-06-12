package com.tosort;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * Created by Piyush Sharma on 2/27/15.
 */
/* Problem Statement:
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
 */

/* Notes =>
 - All rational numbers => terminating decimal or repeating decimal
 - Any number that cannot be expressed as a ratio of two integers
 is said to be irrational. Their decimal representation neither
 terminates nor infinitely repeats but extends forever without
 regular repetition. Examples of such irrational numbers are the
 square root of 2 and pi.
 - A fraction in lowest terms with a prime denominator other than 2 or 5
 (i.e. coprime to 10) always produces a repeating decimal.
 The length of the repetend (period of the repeating decimal)
 of 1/p is equal to the order of 10 modulo p.
 - If 10 is a primitive root modulo p,
  the repetend length is equal to p − 1;
  if not, the repetend length is a factor of p − 1.
  This result can be deduced from Fermat's little theorem,
  which states that 10p−1 = 1 (mod p).

In Simpler Terms (Refer: http://eli.thegreenplace.net/2009/02/25/project-euler-problem-26/)
There are 3 kinds of rational numbers:
1) Expansion is finite (for instance 1/4 = 0.25): those are numbers of the form 2^alpha5^beta
2) Expansion is repetitive, from the start (like 1/7 = 0.(142857)). This is for numbers co-prime to 10.
3) Expansion is repetitive, after some initial non-repetitive section.
This is for numbers that have both factors co-prime to 10 and are divisible by 2 or 5. (for example 1/14 = 0.0(714285))

Useful links:
http://mathworld.wolfram.com/DecimalExpansion.html   (Must understand this to understand the solution)
http://mathworld.wolfram.com/MultiplicativeOrder.html

To find the longest cycle it's sufficient to look just at the primes
- because other numbers will just have the same cycles after some initial non-repetitive section.

If r=p/q has a finite decimal expansion (i.e.,  r is a regular number), then

r	=	(a_1)/(10)+(a_2)/(10^2)+...+(a_n)/(10^n)

	=	(a_1*10^(n-1)+a_2*10^(n-2)+...+a_n)/(10^n)

	=	(a_1*10^(n-1)+a_2*10^(n-2)+...+a_n)/(2^n·5^n).

Factoring possible common multiples gives:
 r= p/(2^n5^n),

=> A useful way to think about a≡b (mod n) is "a and b have the same remainder when divided by n".
=> The statement x ≡ k  (mod n) means that x−kn is an integer,
(note this requires n≠0). Equivalently, x = mn + k for some integer m.

We have to solve the discrete logarithm:
        10^k ≡ 1  (mod n)
k is the cycle length for n, for numbers co-prime to 10.


When the denominator of a fraction m/n has the form n=(n`)(2^alpha)(5^beta) with (n`,10) = 1,
i.e. n` is coprime to 10, then the period begins after  max(alpha,beta) terms and
the length of the period is the exponent to which 10 belongs (mod n`),
i.e., the number k such that 10^k = 1 (mod n`).
*/

public class FractionToRecurringDecimal {

    public static String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) {
            return null;
        }

        if (numerator % denominator == 0) {
            BigDecimal num = new BigDecimal(numerator);
            BigDecimal denom = new BigDecimal(denominator);
            DecimalFormat format = new DecimalFormat("#.###########################################");
            return format.format(num.divide(denom));
        }

         /* If the expansion is finite, denominator will be of form 2^alpha*5^beta */
        int den = denominator;
        while (den % 2 == 0)
            den = den / 2;
        while (den % 5 == 0)
            den = den / 5;

        /* If this condition holds, it is a terminating decimal */
        if (den == 1 || den == -1) {
            BigDecimal num = new BigDecimal(numerator);
            BigDecimal denom = new BigDecimal(denominator);
            DecimalFormat format = new DecimalFormat("#.###########################################");
            return format.format(num.divide(denom));
        }

        String solution = "";
        if((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
            solution += "-";
            if(numerator < 0) {
                numerator = -numerator;
            } else {
                denominator = -denominator;
            }
        }

        /* Get gcd and reduce the fraction */
        int gcd = Math.abs(getGCD(numerator, denominator));
        numerator = numerator / gcd;
        denominator = denominator / gcd;

        /* For repeating decimals :: */
        HashMap<Integer, Integer> modMap = new HashMap<Integer, Integer>();
        String decimalString = "";
        solution += Integer.toString(numerator / denominator) + ".";

        numerator = numerator % denominator;
        int index = 0;
        while (true) {
            int r = numerator % denominator;
            /* if the mod is already present, repeating decimal starts, so you want the string before that */
            if (null != modMap.get(r)) {
                int beforeRepeatingStarts = modMap.get(r);
                solution += decimalString.substring(0, beforeRepeatingStarts) +
                        "(" + decimalString.substring(beforeRepeatingStarts) + ")";
                break;
            }
            modMap.put(r, index);
            /* Proceed exactly like you would when dividing things by hand */
            decimalString += Integer.toString((r*10) / denominator);
            ++index;
            numerator = r * 10;
        }
        return solution;
    }


    /* The Euclidean algorithm is based on the principle that the greatest common divisor of two numbers does not
    change if the larger number is replaced by its difference with the smaller number.

    For example, 21 is the GCD of 252 and 105 (252 = 21 × 12 and 105 = 21 × 5), and the same number 21 is also the
    GCD of 105 and 147 = 252 − 105.

    Since this replacement reduces the larger of the two numbers, repeating this process gives successively
    smaller pairs of numbers until one of the two numbers reaches zero. When that occurs, the other number
    (the one that is not zero) is the GCD of the original two numbers.
    */
    public static int getGCD(int a, int b) {
        int x;
        if (a == b)
            return a;

        /* Make 'a' greater than 'b' always */
        if (b > a) {
            x = b;
            b = a;
            a = x;
        }
        while (a != 0 && b != 0) {
            /* Make the bigger number smaller */
            a = a % b;
            /* Swap again, so that a is the bigger number again */
            x = a;
            a = b;
            b = x;
        }
        return a == 0 ? b : a;
    }

    public static void main(String args[]) {
        System.out.println(fractionToDecimal(1, 14));
        System.out.println(fractionToDecimal(-2147483648, 1));
        System.out.println(fractionToDecimal(1, -2147483648));
        System.out.println(fractionToDecimal(-1, -2147483648));
        System.out.println(fractionToDecimal(-2147483648, -1));
        System.out.println(fractionToDecimal(-2147483648, -1999));
        System.out.println(fractionToDecimal(2147483647, 370000));
        System.out.println(fractionToDecimal(2147483647, 1));
    }
}