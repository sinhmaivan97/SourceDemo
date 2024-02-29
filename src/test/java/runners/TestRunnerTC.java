package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

@Test
@CucumberOptions(features = "src/test/resources/features",
        glue = {"steps", "cucumberHooks"},
        plugin = {"cucumberHooks.CucumberListener", "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-reports/cucumber-reports.html",
                "json:target/cucumber-reports/cucumber-reports.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        tags = "@Research"
//        tags = "@ChangeLanguage"
//        tags = "@Signin"
)
public class TestRunnerTC extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    public void deleteScreenshotsFiles() {
        try {
            System.out.println("================ BEFORE SUITE ================");
            String pathFolderallure = "allure-results";
            String pathFolerScreenshot = "extentReport/screenshots";

            File fileAllure = new File(pathFolderallure);
            File fileScreenshot = new File(pathFolerScreenshot);

            File[] listOfFilesAllure = fileAllure.listFiles();
            File[] listOfFilesScreenshot = fileScreenshot.listFiles();
            System.out.println("......................" + pathFolderallure);
            for (int i = 0; i < listOfFilesAllure.length; i++) {
                if (listOfFilesAllure[i].isFile()) {
                    new File(listOfFilesAllure[i].toString()).delete();
                }
            }
            for (int i = 0; i < listOfFilesScreenshot.length; i++) {
                if (listOfFilesScreenshot[i].isFile()) {
                    new File(listOfFilesScreenshot[i].toString()).delete();
                }
            }
            System.out.println("================ DELETE ================");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
