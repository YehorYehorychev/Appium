package steps;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/",
        glue = {"steps"},
        monochrome = false,
        dryRun = false,
        plugin = {
                "pretty",
				"html:target/htmlReports/report.html",
                "json:target/JsonReports/report.json",
                "junit:target/JunitReports/report.xml"},

        tags = "@smoke")
public class TestRunner {}