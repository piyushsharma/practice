package com.payroll;

import java.math.BigDecimal;

public class CASDIRule implements TaxRule {
    private static final BigDecimal RATE = new BigDecimal("0.009");
    private static final BigDecimal CAP  = new BigDecimal("168600");

    @Override
    public String code() {
        return "CA_SDI";
    }

    @Override
    public BigDecimal calculate(BigDecimal gross, BigDecimal ytdBefore) {
        var remaining = CAP.subtract(ytdBefore).max(BigDecimal.ZERO);
        return gross.min(remaining).multiply(RATE);
    }
}