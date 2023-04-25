package stepDefinitions;


import java.io.IOException;
import io.cucumber.java.Before;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

        StepDefinition stepDefinition = new StepDefinition();

        if (StepDefinition.place_id == null) {
            stepDefinition.addPlacePayloadWithAccuracy(32,"Cavalca", "Portuguese", "Florida");
            stepDefinition.userCallWithHttpRequest("AddPlaceAPI", "POST");
            stepDefinition.verifyIfPlace_IdCreateMapsToUsing("Cavalca", "GetPlaceAPI");
        }

    }
}
