package org.IrvinCampos.stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
//        write a code that will give you place id
//        execute this code only when place id
        StepDefinitions stepDefinitions = new StepDefinitions();
        stepDefinitions.setup();
        if (StepDefinitions.place_id == null) {
            stepDefinitions.add_place_payload_with("Cozy", "English", "Madrid");
            stepDefinitions.user_calls_with_post_http_request("addPlaceAPI", "POST");
            stepDefinitions.verify_place_id_created_maps_to_using_get_place_api("Cozy", "getPlaceAPI");
        }
    }
}
