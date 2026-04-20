package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            // 🔥 Create unique report name (prevents overwrite)
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = "test-output/ExtentReport_" + timestamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            // 🔥 UI Config
            spark.config().setReportName("Smart QA Automation Report");
            spark.config().setDocumentTitle("Execution Report");
            spark.config().setEncoding("utf-8");
            spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // 🔥 System Info (INTERVIEW GOLD)
            extent.setSystemInfo("Framework", "Hybrid Selenium + Playwright");
            extent.setSystemInfo("Execution", "Parallel");
            extent.setSystemInfo("Author", "Rishikesh");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }

        return extent;
    }
}