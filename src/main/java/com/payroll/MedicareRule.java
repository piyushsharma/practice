package com.payroll;

import java.math.BigDecimal;

public class MedicareRule implements TaxRule {
    private static final BigDecimal RATE = new BigDecimal("0.0145");

    @Override
    public String code() {
        return "MEDI";
    }

    @Override
    public BigDecimal calculate(BigDecimal gross, BigDecimal ignore) {
        return gross.multiply(RATE);
    }
}
