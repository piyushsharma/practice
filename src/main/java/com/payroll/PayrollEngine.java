package com.payroll;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PayrollEngine {

    private final List<TaxRule> rules;

    public PayrollEngine(List<TaxRule> rules) { this.rules = List.copyOf(rules); }

    /** returns map { "SS":254.20 , "MEDI":72.50 , "CA_SDI":45.00 } */
    public Map<String, BigDecimal> compute(BigDecimal gross, BigDecimal ytdBefore) {
        return rules.stream()
                .collect(Collectors.toMap(TaxRule::code,
                        r -> r.calculate(gross, ytdBefore).setScale(2)));
    }

    public static void main(String[] args) {
        var engine = new PayrollEngine(List.of(
                new SocialSecurityRule(),
                new MedicareRule(),
                new CASDIRule()));                 // <-- added in seconds during interview

        System.out.println(engine.compute(
                new BigDecimal("5000"),            // current pay
                new BigDecimal("50_000")));        // YTD wages
    }
}
