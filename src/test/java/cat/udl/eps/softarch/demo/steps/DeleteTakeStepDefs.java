package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class DeleteTakeStepDefs {
    public static String newResourceUri;
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private PropagatorRepository propagatorRepository;
    @Autowired
    private TakeRepository takeRepository;

    @When("^I delete a Take$")
    public void deleteTake() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        delete(newResourceUri)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("There is a take created with amount {int}, weight {int} and location {string}")
    public void thereIsATakeCreatedWithAmountWeightAndLocation(int amount, int weight, String location) {
        Take take = new Take();
        take.setAmount(amount);
        take.setWeight(new BigDecimal(weight));
        take.setLocation(location);
        take.setBy(propagatorRepository.findById("propagator").get());
        take = takeRepository.save(take);
        newResourceUri = "/takes/" + take.getId();
    }

    @And("I try to get the take")
    public void iTryToGetTheRequest() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get(newResourceUri)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("There is no take created with id {long}")
    public void thereIsNoTakesCreatedWithId(Long id) {
        Optional<Take> take = takeRepository.findById(id);
        Assert.assertTrue(take.isEmpty());
        newResourceUri = "/takes/" + id;
    }
}




