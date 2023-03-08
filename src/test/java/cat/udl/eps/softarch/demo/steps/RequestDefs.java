package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class RequestDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PropagatorRepository propagatorRepository;

    @Given("^There is a registered propagator with username \"([^\"]*)\"$")
    public void thereIsARegisteredUserWithUsernameAndPasswordAndEmail(String username) throws RuntimeException {
        if (!propagatorRepository.existsById(username)) {
            throw new RuntimeException("Unregistered propagator");
        }
    }

    @When("^I create a new request with propagator with username \"([^\"]*)\"$")
    public void createRequest(String username) throws Exception {
        Optional<Propagator> propagator = propagatorRepository.findById(username);
        if (propagator.isPresent()) {
            Request request = new Request();
            request.setBy(propagator.get());
            request.setLastUpdate(ZonedDateTime.now());
            stepDefs.result = stepDefs.mockMvc.perform(
                            post("/requests")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(stepDefs.mapper.writeValueAsString(request))
                                    .accept(MediaType.APPLICATION_JSON)
                                    .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print());
        }
    }
}
