package com.payroll;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/** Finds the next pay date, skipping weekends & given holidays. */
public class NextPayDateCalculator {

    public enum Frequency { WEEKLY, BIWEEKLY, SEMIMONTHLY }

    /**
     * @param prevPayDate last processed pay date
     * @param freq        payroll frequency
     * @param holidays    set of YYYY-MM-DD dates to skip
     */
    public static LocalDate next(LocalDate prevPayDate,
                                 Frequency freq,
                                 Set<LocalDate> holidays) {

        LocalDate d;
        switch (freq) {
            case WEEKLY:
                d = prevPayDate.plusWeeks(1);
                break;
            case BIWEEKLY:
                d = prevPayDate.plusWeeks(2);
                break;
            case SEMIMONTHLY:
                if (prevPayDate.getDayOfMonth() <= 15)
                    d = prevPayDate.with(TemporalAdjusters.lastDayOfMonth());
                else
                    d = prevPayDate.plusMonths(1).withDayOfMonth(15);
                break;
            default:
                throw new IllegalArgumentException();
        }

        while (isWeekend(d) || holidays.contains(d)) {
            d = d.plusDays(1);
        }
        return d;
    }

    private static boolean isWeekend(LocalDate d) {
        return d.getDayOfWeek() == DayOfWeek.SATURDAY
                || d.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    /* Demo */
    public static void main(String[] args) {
        Set<LocalDate> holidays = Set.of(LocalDate.of(2025, 7, 4)); // July-4 holiday
        LocalDate prev = LocalDate.of(2025, 7, 15);                 // Tue
        System.out.println(next(prev, Frequency.SEMIMONTHLY, holidays));
        // -> Thu 31-Jul-2025
    }
}

