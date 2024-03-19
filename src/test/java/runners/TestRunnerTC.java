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
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        tags = "@Research or @ChangeLanguage"
//        tags = "not @Signin"
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

            String pathFolerScreenshot = "extentReport/screenshots";
            File fileScreenshot = new File(pathFolerScreenshot);
            File[] listOfFilesScreenshot = fileScreenshot.listFiles();

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
