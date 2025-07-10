package com.payroll;

import java.math.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class PayrollExamples {

    /* ---------- 1. Annual 401(k) Deferral Cap ---------- */
    public static class DeferralCalculator {
        private static final BigDecimal EMP_CAP_2025 = new BigDecimal("23500");

        /**
         * Clip desired deferral so year‑to‑date never exceeds the annual cap.
         */
        public static BigDecimal defer(BigDecimal desired,
                                                 BigDecimal ytdDeferred) {
            BigDecimal remaining = EMP_CAP_2025.subtract(ytdDeferred).max(BigDecimal.ZERO);
            return desired.min(remaining).setScale(2, RoundingMode.HALF_UP);
        }
    }

    /* ---------- 2. Multi‑state Wage Allocation ---------- */
    public static class StateAllocator {
        /**
         * Pro‑rates gross wages by work‑days per state.
         *
         * @param gross       total wages for the run
         * @param daysInState map like { "CA":12, "NY":8 }
         * @return map of state‑wise wages rounded to cents
         */
        public static Map<String, BigDecimal> allocate(
                BigDecimal gross,
                Map<String, Integer> daysInState) {

            int totalDays = daysInState.values().stream().mapToInt(Integer::intValue).sum();
            return daysInState.entrySet().stream()
                    .collect(java.util.stream.Collectors.toMap(
                            Map.Entry::getKey,
                            e -> gross.multiply(BigDecimal.valueOf(e.getValue()))
                                    .divide(BigDecimal.valueOf(totalDays), 2, RoundingMode.HALF_UP)
                    ));
        }
    }

    /* ---------- 3. Tax Table Loader (CSV stub) ---------- */
    public static class TaxTableLoader {

        public record Bracket(BigDecimal upper, BigDecimal rate) {
        }

        /**
         * Very small CSV helper: filingStatus,upper,rate
         */
        public static Map<String, List<Bracket>> load(Path csv) throws java.io.IOException {

            Map<String, List<Bracket>> map = new HashMap<>();
            try (Stream<String> lines = Files.lines(csv)) {
                lines.skip(1).forEach(l -> {
                    String[] p = l.split(",");
                    map.computeIfAbsent(p[0], k -> new ArrayList<>())
                            .add(new Bracket(new BigDecimal(p[1]), new BigDecimal(p[2])));
                });
            }
            return map;
        }
    }

    /* ---------- 4. Audit‑friendly Rounding ---------- */
    public static class RoundingDemo {
        public static java.lang.String explain(BigDecimal gross, BigDecimal rate) {
            BigDecimal calc = gross.multiply(rate);
            BigDecimal four = calc.setScale(4, RoundingMode.HALF_UP);
            BigDecimal two = four.setScale(2, RoundingMode.HALF_UP);
            return String.format("""
                            calc: %s * %s = %s
                            round‑4‑places -> %s
                            round‑2‑places -> %s
                            """, gross.toPlainString(), rate.toPlainString(), calc.toPlainString(),
                    four.toPlainString(), two.toPlainString());
        }
    }

    /* ---------- 5. Pay‑date Business‑day Shift ---------- */
    public static class PayCalendar {
        public static java.time.LocalDate nextBusiness(java.time.LocalDate date,
                                                       java.util.Set<java.time.LocalDate> holidays) {
            java.time.LocalDate d = date;
            while (d.getDayOfWeek() == java.time.DayOfWeek.SATURDAY
                    || d.getDayOfWeek() == java.time.DayOfWeek.SUNDAY
                    || holidays.contains(d)) {
                d = d.plusDays(1);
            }
            return d;
        }
    }

    /* ---------- 6. Pretax vs Post‑tax Net Calculator ---------- */
    public static class NetCalculator {

        public interface Rule {
            BigDecimal apply(BigDecimal base);
        }

        /**
         * Simple percent‑of‑gross deduction rule.
         */
        public static class PercentRule implements Rule {
            private final BigDecimal pct;

            public PercentRule(String pct) {
                this.pct = new BigDecimal(pct);
            }

            public BigDecimal apply(BigDecimal base) {
                return base.multiply(pct);
            }
        }

        /**
         * Computes net given three ordered lists of rules.
         */
        public static BigDecimal computeNet(
                BigDecimal gross,
                java.util.List<Rule> pretax,
                java.util.List<Rule> taxes,
                java.util.List<Rule> postTax) {

            BigDecimal taxable = gross;
            for (Rule r : pretax) taxable = taxable.subtract(r.apply(gross));
            for (Rule r : taxes) taxable = taxable.subtract(r.apply(taxable));
            for (Rule r : postTax) taxable = taxable.subtract(r.apply(gross));
            return taxable.setScale(2, RoundingMode.HALF_UP);
        }
    }

    /* ---------- 7. Accrual Engine (PTO) ---------- */
    public static class AccrualEngine {
        private static final BigDecimal RATE = BigDecimal.ONE
                .divide(new BigDecimal("30"), 4, RoundingMode.HALF_UP);

        static class Bal {
            BigDecimal hours = BigDecimal.ZERO;
        }

        private final Map<String, Bal> db = new java.util.concurrent.ConcurrentHashMap<>();

        public void addHours(String emp, BigDecimal worked) {
            Bal b = db.computeIfAbsent(emp, k -> new Bal());
            b.hours = b.hours.add(worked.multiply(RATE))
                    .setScale(2, RoundingMode.HALF_UP);
        }

        public BigDecimal getBalance(String emp) {
            return db.getOrDefault(emp, new Bal()).hours;
        }
    }

    /* ---------- 8. Tax Strategy Interfaces & Rules ---------- */
    public interface TaxRule {
        String code();

        BigDecimal calculate(BigDecimal gross, BigDecimal ytd);
    }

    public static class SocialSecurityRule implements TaxRule {
        private static final BigDecimal RATE = new BigDecimal("0.062");
        private static final BigDecimal CAP = new BigDecimal("176100");

        public String code() {
            return "SS";
        }

        public BigDecimal calculate(BigDecimal gross, BigDecimal ytd) {
            BigDecimal remaining = CAP.subtract(ytd).max(BigDecimal.ZERO);
            return gross.min(remaining).multiply(RATE);
        }
    }

    public static class MedicareRule implements TaxRule {
        private static final BigDecimal RATE = new BigDecimal("0.0145");

        public String code() {
            return "MEDI";
        }

        public BigDecimal calculate(BigDecimal gross, BigDecimal ytd) {
            return gross.multiply(RATE);
        }
    }

    public static class CASdiRule implements TaxRule {
        private static final BigDecimal RATE = new BigDecimal("0.012");

        public String code() {
            return "CA_SDI";
        }

        public BigDecimal calculate(BigDecimal gross, BigDecimal ytd) {
            return gross.multiply(RATE);
        }
    }

    /* ---------- 9. Payroll Engine using TaxRule plug‑ins ---------- */
    public static class PayrollEngine {
        private final java.util.List<TaxRule> rules;

        public PayrollEngine(java.util.List<TaxRule> rules) {
            this.rules = java.util.List.copyOf(rules);
        }

        /**
         * returns map of code -> tax amount rounded to 2 decimals
         */
        public Map<String, BigDecimal> compute(BigDecimal gross,
                                                                   BigDecimal ytd) {
            return rules.stream().collect(java.util.stream.Collectors.toMap(
                    TaxRule::code,
                    r -> r.calculate(gross, ytd).setScale(2, RoundingMode.HALF_UP)
            ));
        }
    }

    /* ---------- 10. Concurrency / Idempotence Smoke Test ---------- */
    public static class PayrollEngineConcurrency {
        public static void run() throws Exception {
            PayrollEngine engine = new PayrollEngine(java.util.List.of(
                    new SocialSecurityRule(), new MedicareRule(), new CASdiRule()));

            Callable<Map<String, BigDecimal>> task =
                    () -> engine.compute(new BigDecimal("5000"), BigDecimal.ZERO);

            ExecutorService ex = Executors.newFixedThreadPool(4);
            List<Future<Map<String, BigDecimal>>> fs = ex.invokeAll(java.util.Collections.nCopies(4, task));

            Map<String, BigDecimal> first = fs.getFirst().get();
            for (Future<Map<String, BigDecimal>> f : fs) {
                if (!first.equals(f.get())) throw new AssertionError("Non‑idempotent result!");
            }
            ex.shutdown();
            System.out.println("Concurrency smoke‑test ✅ identical maps: " + first);
        }
    }

    /* ---------- Demo main ---------- */
    public static void main(String[] args) throws Exception {

        // 1. Deferral cap demo
        BigDecimal deferral = DeferralCalculator.defer(
                new BigDecimal("4000"), new BigDecimal("22000"));
        System.out.println("Deferral clip: " + deferral); // 1500.00

        // 2. State allocation demo
        Map<String, Integer> days = Map.of("CA", 12, "NY", 8);
        System.out.println("State allocation: " +
                StateAllocator.allocate(new BigDecimal("5000"), days));

        // 4. Rounding explanation
        System.out.println(RoundingDemo.explain(new BigDecimal("923.345"),
                new BigDecimal("0.0145")));
    }
}