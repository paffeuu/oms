package service.reports;

import dnl.utils.text.table.TextTable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportSaver {
    public String saveReportToFile (String report) {
        try {
            String fileName = "report" +
                    LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace(":","")+ ".txt";
            PrintWriter printWriter = new PrintWriter(new File(fileName));
            String formattedReport = report.replace(" is ", ",");
            formattedReport = formattedReport.substring(0, formattedReport.length() - 1);
            printWriter.print(formattedReport);
            printWriter.close();
            return fileName;
        } catch (IOException ioex) {
            return null;
        }
    }

    public String saveReportToFile (TextTable report) {
        try {
            String fileName = "report" +
                    LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace(":","")+ ".txt";
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            report.toCsv(fos);
            return fileName;
        } catch (IOException ioex) {
            return null;
        }
    }
}
