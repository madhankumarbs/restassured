package resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportFileName = "Report.html";
    private static String macPath = System.getProperty("user.dir")+ "/TestReport";
    private static String windowsPath = System.getProperty("user.dir")+ "\\TestReport";
    private static String macReportFileLoc = macPath + "/" + reportFileName;
    private static String winReportFileLoc = windowsPath + "\\" + reportFileName;
    private static String os;
    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }
 
    //Create an extent report instance
    public static ExtentReports createInstance() {
        String platform = getCurrentPlatform();
        String fileName = getReportFileLocation(platform);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Regression Test Results");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Regression Test Results");
 
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
 
        return extent;
    }
 
    //Select the extent report file location based on platform
    private static String getReportFileLocation (String platform) {
        String reportFileLocation = null;
       
        if (platform == "WINDOWS")
        {
            reportFileLocation = winReportFileLoc;
            createReportPath(windowsPath);
            System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
            
        }else if (platform == "LINUX" ||platform == "MAC" )
        {
            reportFileLocation = macReportFileLoc;
            createReportPath(macPath);
            System.out.println("ExtentReport Path for MAC: " + macPath + "\n");
        }
        return reportFileLocation;
    }
 
    //Create the report path if it does not exist
    private static void createReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }
 
    //Get current platform
    private static String getCurrentPlatform () {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                os = "WINDOWS";
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
            	os = "LINUX";
            } else if (operSys.contains("mac")) {
            	os = "MAC";
            }
        }
        return os;
    }
    

}