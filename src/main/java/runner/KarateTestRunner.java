package runner;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@KarateOptions( features={"classpath:features/"},
        tags = {"~@ignore"})

public class KarateTestRunner {

    public void testParallel() {
        Results results = Runner.parallel(getClass(), 1, "cucumber-html-reports/Run");
        generateReport(results.getReportDir());
        //assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
    }

    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("cucumber-html-reports/Run"), "ASCEND - DATABRICKS SERVICE");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
        //Create Run.json to Score DevSecOps CI/CD
        FileCreate.createJson("cucumber-html-reports/Run");
    }
}
