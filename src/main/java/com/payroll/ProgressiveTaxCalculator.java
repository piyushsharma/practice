package com.payroll;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Computes marginal tax from a progressive-bracket table. */
public class ProgressiveTaxCalculator {

    /** One tax bracket; {@code upper == null} means "∞". */
    public static class Bracket {
        private final BigDecimal upper;
        private final BigDecimal rate;

        public Bracket(BigDecimal upper, BigDecimal rate) {
            this.upper = upper;
            this.rate = rate;
        }

        public BigDecimal upper() { return upper; }
        public BigDecimal rate() { return rate; }
    }

    /**
     * @param income   gross income for the period
     * @param brackets ordered lowest → highest; last one may have {@code upper == null}
     * @return tax owed, rounded to cents
     */
    public static BigDecimal computeTax(BigDecimal income, List<Bracket> brackets) {
        BigDecimal remaining = income;
        BigDecimal prevUpper = BigDecimal.ZERO;
        BigDecimal tax       = BigDecimal.ZERO;

        for (Bracket b : brackets) {
            BigDecimal slice;
            if (b.upper() == null) {                 // top bracket
                slice = remaining;
            } else {
                BigDecimal width = b.upper().subtract(prevUpper);
                slice = remaining.min(width);
            }
            tax        = tax.add(slice.multiply(b.rate()));
            remaining  = remaining.subtract(slice);
            if (remaining.signum() <= 0) break;
            prevUpper  = b.upper();
        }
        return tax.setScale(2, RoundingMode.HALF_UP);
    }



    /* Demo */
    public static void main(String[] args) {
        List<Bracket> table = List.of(
                new Bracket(new BigDecimal("10000"), new BigDecimal("0.10")),
                new Bracket(new BigDecimal("40000"), new BigDecimal("0.15")),
                new Bracket(null,                     new BigDecimal("0.25"))
        );
        System.out.println(computeTax(new BigDecimal("55000"), table)); // 8250.00
    }
}
