package cucumberHooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import constants.GlobalConstants;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CucumberListener implements EventListener {
    private ExtentSparkReporter spark;
    private ExtentReports extent;

    Map<String, ExtentTest> feature = new HashMap<String, ExtentTest>();
    ExtentTest scenario;
    ExtentTest step;

    public static int count_totalTCs;
    public static int count_passedTCs;
    public static int count_skippedTCs;
    public static int count_failedTCs;

    public CucumberListener() {
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, this::runStarted);
        publisher.registerHandlerFor(TestRunFinished.class, this::runFinished);
        publisher.registerHandlerFor(TestSourceRead.class, this::featureRead);
        publisher.registerHandlerFor(TestCaseStarted.class, this::ScenarioStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::ScenarioFinished);
        publisher.registerHandlerFor(TestStepStarted.class, this::stepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::stepFinished);
    }

    private void runStarted(TestRunStarted event) {
        spark = new ExtentSparkReporter("extentReport" + File.separator + "reports" + File.separator + "ExtentReport.html");
        extent = new ExtentReports();
        // Create extent report instance with spark reporter
        extent.attachReporter(spark);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle(GlobalConstants.getGlobalConstants().getREPORT_TITLE());
        spark.config().setReportName(GlobalConstants.getGlobalConstants().getREPORT_TITLE());
        extent.setSystemInfo("Framework Name", GlobalConstants.getGlobalConstants().getREPORT_TITLE());
        extent.setSystemInfo("Author", GlobalConstants.getGlobalConstants().getAUTHOR());
        System.out.println("Extent Reports is installed.");
    }

    private void runFinished(TestRunFinished event) {
        extent.flush();
    }

    private void featureRead(TestSourceRead event) {
        String featureSource = event.getUri().toString();
        String featureName = featureSource.split(".*/")[1];

        if (feature.get(featureSource) == null) {
            feature.putIfAbsent(featureSource, extent.createTest(featureName));
        }
    }

    private void ScenarioStarted(TestCaseStarted event) {
        String featureName = event.getTestCase().getUri().toString();
        scenario = feature.get(featureName).createNode(event.getTestCase().getName());
        count_totalTCs = count_totalTCs + 1;
    }

    private void ScenarioFinished(TestCaseFinished event) {
        String featureName = event.getTestCase().getUri().toString();

        scenario = feature.get(featureName).createNode(event.getTestCase().getName());

        if (Objects.equals(event.getResult().getStatus().toString(), "PASSED")) {
            count_passedTCs = count_passedTCs + 1;
        } else if (Objects.equals(event.getResult().getStatus().toString(), "SKIPPED")) {
            count_skippedTCs = count_skippedTCs + 1;
        } else {
            count_failedTCs = count_failedTCs + 1;
        }
    }

    private void stepStarted(TestStepStarted event) {

        String stepName = "";
        String keyword = "";

        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();
            stepName = steps.getStep().getText();
            keyword = steps.getStep().getKeyword();
        } else {
            HookTestStep hoo = (HookTestStep) event.getTestStep();
            stepName = hoo.getHookType().name();
        }
        step = scenario.createNode(Given.class, keyword + " " + stepName);
    }
    private void stepFinished(TestStepFinished event) {
    }
}
