package edu.mooncoder.model.domain.containers.reports;

import edu.mooncoder.model.domain.containers.Report;

public class SortReport extends Report {
    private static Report report;

    public static void clear() {
        report = null;
    }

    public static Report getInstance() {
        if (report == null) {
            report = new VerificationReport();
        } return report;
    }

}
