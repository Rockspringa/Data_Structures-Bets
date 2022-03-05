package edu.mooncoder.controller.managers;

import edu.mooncoder.controller.Manager;
import edu.mooncoder.model.domain.containers.Report;
import edu.mooncoder.model.domain.containers.reports.ResultsReport;
import edu.mooncoder.model.domain.containers.reports.SortReport;
import edu.mooncoder.model.domain.containers.reports.VerificationReport;
import edu.mooncoder.model.tools.types.ReportType;

public class ReportManager extends Manager {
    public static String[] getData(ReportType type) {
        String[] data = new String[4];
        Report report = switch (type) {
            case VERIFICATION_REPORT -> VerificationReport.getInstance();
            case RESULTS_REPORT -> ResultsReport.getInstance();
            case SORT_REPORT -> SortReport.getInstance();
        };

        data[0] = String.format("%d ms", report.getPromedioTime());
        data[1] = String.format("%d pasos", report.getPromedioPasos());
        data[2] = String.format("%d pasos", report.getMaxPasos());
        data[3] = String.format("%d pasos", report.getMinPasos());

        return data;
    }
}
