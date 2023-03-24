package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CreateTakeStepDefs {

    @Autowired
    private PropagatorRepository propagatorRepository;
    @Autowired
    private TakeRepository takeRepository;
    @Autowired
    private StepDefs stepDefs;

    @When("^I create a new valid Take with Propagator$")
    public void createTake() throws Exception {
        Take take = createValidTakeWithPropagator("Lleida");
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/takes")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .characterEncoding("utf-8")
                                .content(stepDefs.mapper.writeValueAsString(take))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @Then("There is 1 take created$")
    public void thereIsOnetakeCreated() throws Exception {
        Assert.assertEquals(1, takeRepository.count());
    }

    @When("I create 5 takes")
    public void thereAreVariousTakesCreated() throws Exception {
        List<String> locations = getLocations();
        for (int i = 0; i < 5; i++) {
            Take take = createValidTakeWithPropagator(locations.get(i));
            stepDefs.result = stepDefs.mockMvc.perform(
                            post("/takes")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .characterEncoding("utf-8")
                                    .content(stepDefs.mapper.writeValueAsString(take))
                                    .accept(MediaType.APPLICATION_JSON)
                                    .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print());
        }
    }

    @When("I create an invalid take")
    public void CreateAnInvalidTake() throws Exception {
        Take take = createTakeMissingSomeAttributes("Brno");
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/takes")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .characterEncoding("utf-8")
                                .content(stepDefs.mapper.writeValueAsString(take))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    /*
    @When("I create a new valid Take with Donor")
    public void CreateANewValidTakeWithDonor() throws Exception {
        Take take = createValidTakeWithDonor("Lleida");
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/takes")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .characterEncoding("utf-8")
                                .content(stepDefs.mapper.writeValueAsString(take))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
     */

    @Then("There are 5 take created$")
    public void thereAreFiveTakeCreated() throws Exception {
        Assert.assertEquals(5, takeRepository.count());
    }
    @Then("There is 0 take created$")
    public void thereIsNoTakeCreated() {
        Assert.assertEquals(0, takeRepository.count());
    }

    private Take createTakeMissingSomeAttributes(String location) {
        Take take = new Take();
        take.setWeight(BigDecimal.TEN);
        take.setLocation(location);
        take.setDate(ZonedDateTime.now());
        take.setBy(createValidPropagator());
        return take;
    }

    private Take createValidTakeWithPropagator(String location) {
        Take take = new Take();
        take.setWeight(BigDecimal.TEN);
        take.setAmount(10);
        take.setLocation(location);
        take.setDate(ZonedDateTime.now());
        take.setBy(createValidPropagator());
        return take;
    }

    private Propagator createValidPropagator() {
       return propagatorRepository.findById("propagator").get();
    }

    private List<String> getLocations() {
        List<String> locations = new ArrayList<>();
        locations.add("Els Alamús");
        locations.add("Girona");
        locations.add("Tarragona");
        locations.add("Lleida");
        locations.add("Barcelona");

        return locations;
    }
}



