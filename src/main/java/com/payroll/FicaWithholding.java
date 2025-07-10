package com.payroll;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Calculates SS + Medicare withheld for a single payroll run. */
public class FicaWithholding {


    private static final BigDecimal SS_RATE       = new BigDecimal("0.062");
    private static final BigDecimal MEDICARE_RATE = new BigDecimal("0.0145");
    private static final BigDecimal SS_WAGE_BASE_2025 = new BigDecimal("176100");

    /** Immutable input row. */
    public static class PayrollRun {
        private final BigDecimal gross;
        private final BigDecimal ytdBefore;
        private final LocalDate payDate;

        public PayrollRun(BigDecimal gross, BigDecimal ytdBefore, LocalDate payDate) {
            this.gross = gross;
            this.ytdBefore = ytdBefore;
            this.payDate = payDate;
        }

        public BigDecimal gross() { return gross; }
        public BigDecimal ytdBefore() { return ytdBefore; }
        public LocalDate payDate() { return payDate; }
    }

    /** Return-value container. */
    public static class Result {
        private final BigDecimal ss;
        private final BigDecimal medicare;

        public Result(BigDecimal ss, BigDecimal medicare) {
            this.ss = ss;
            this.medicare = medicare;
        }

        public BigDecimal ss() { return ss; }
        public BigDecimal medicare() { return medicare; }
    }

    public static Result compute(PayrollRun run) {
        BigDecimal remainingCap = SS_WAGE_BASE_2025.subtract(run.ytdBefore());
        if (remainingCap.signum() < 0) remainingCap = BigDecimal.ZERO;

        BigDecimal ssTaxable = run.gross().min(remainingCap);
        BigDecimal ss        = ssTaxable.multiply(SS_RATE);
        BigDecimal medicare  = run.gross().multiply(MEDICARE_RATE);

        return new Result(round(ss), round(medicare));
    }

    private static BigDecimal round(BigDecimal x) {
        return x.setScale(2, RoundingMode.HALF_UP);
    }


    /* Demo */
    public static void main(String[] args) {
        PayrollRun run = new PayrollRun(new BigDecimal("5000"),
                new BigDecimal("172000"),
                LocalDate.of(2025, 12, 15));
        Result r = compute(run);
        System.out.println("SS: " + r.ss() + "  Medicare: " + r.medicare());
        // SS covers only $4 100 of wages  → $254.20
        // Medicare on full $5 000         → $72.50
    }
}
