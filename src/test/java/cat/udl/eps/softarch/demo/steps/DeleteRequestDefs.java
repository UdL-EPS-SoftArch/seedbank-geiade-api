package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteRequestDefs {
    public static String newResourceUri;
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private PropagatorRepository propagatorRepository;
    @Autowired
    private RequestRepository requestRepository;

    @When("^I delete the request")
    public void deleteRequest() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        delete(newResourceUri)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("There is a request created with amount {int}, weight {int} and location {string}")
    public void thereIsARequestCreatedWithAmountWeightAndLocation(int amount, int weight, String location) {
        Request request = new Request();
        request.setAmount(amount);
        request.setWeight(new BigDecimal(weight));
        request.setLocation(location);
        request.setBy(propagatorRepository.findById("propagator").get());
        request = requestRepository.save(request);
        newResourceUri = "/requests/" + request.getId();
    }

    @And("I try to get the request")
    public void iTryToGetTheRequest() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get(newResourceUri)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
