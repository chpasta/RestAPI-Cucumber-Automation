package cucumber.Options;

import addPlace.AddPlace;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/java/features"
        , plugin="json:target/jsonReports/cucumber-report.json"
        , glue={"stepDefinitions"})
public class TestRunner {
}


// For running on terminal:
//  mvn test "-DargLine=-Dcucumber.filter.tags='@AddPlace'"