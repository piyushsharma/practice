package com.payroll;

import java.math.BigDecimal;

public class SocialSecurityRule implements TaxRule {
    private static final BigDecimal RATE = new BigDecimal("0.062");
    private static final BigDecimal CAP  = new BigDecimal("176100");

    @Override
    public String code() {
        return "SS";
    }

    @Override
    public BigDecimal calculate(BigDecimal gross, BigDecimal ytdBefore) {
        var remaining = CAP.subtract(ytdBefore).max(BigDecimal.ZERO);
        return gross.min(remaining).multiply(RATE);
    }
}
