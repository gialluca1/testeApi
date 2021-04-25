package runner;

import java.io.File;

public class RESTRunner {

	public static void main(String[] args) {

		KarateTestRunner karateTestRunner = new KarateTestRunner();

		// generate report dir name
		String reportDirName = "Run";
		String reportRoot = "cucumber-html-reports/";
		String reportDir = reportRoot + reportDirName + "/";
		reportDir = reportDir.replace(" ", "");

		try {
			karateTestRunner.testParallel();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		String reportAbsPath = new File(reportDir).getAbsolutePath();

//		// abrir o relatório no final
		String report = reportAbsPath + "/cucumber-html-reports/overview-features.html";

		System.out.println("REPORT GENERATED: ");
		System.out.println(report);
	}
}