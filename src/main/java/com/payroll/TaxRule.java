package com.payroll;

import java.math.BigDecimal;

public interface TaxRule {

    String code();                         // e.g. "SS", "MEDI", "CA_SDI"
    BigDecimal calculate(BigDecimal gross, BigDecimal ytdBefore);
}
