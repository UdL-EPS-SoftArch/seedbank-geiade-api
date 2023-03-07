package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CreateTake {
    @Autowired
    private PropagatorRepository propagatorRepository;
    @Autowired
    private TakeRepository takeRepository;
    @Autowired
    private StepDefs stepDefs;


    @Given("^There is a registered user with username \"([^\"]*)\"$")
    public void thereIsARegisteredUserWithUsernameAndPasswordAndEmail(String username) throws RuntimeException {
        if (!propagatorRepository.existsById(username)) {
            throw new RuntimeException("Unregistered propagator");
        }
    }

    @When("^I create a new valid Take with Propagator with username \"([^\"]*)\"$")
    public void createTake(String username) throws Exception {
        Optional<Propagator> propagator = propagatorRepository.findById(username);
        if (propagator.isPresent()) {
            Take take = createValidTake();
            take.setBy(propagator.get());
            takeRepository.save(take);
            takeRepository.findAll();
            stepDefs.result = stepDefs.mockMvc.perform(
                            post("/takes")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(stepDefs.mapper.writeValueAsString(takeRepository.findById(take.getId())))
                                    .accept(MediaType.APPLICATION_JSON)
                                    .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print());
        }
    }
    private Take createValidTake(){
        Take take = new Take();
        take.setWeight(BigDecimal.TEN);
        take.setLocation("Lleida");
        take.setDate(ZonedDateTime.now());
        return take;
    }
}
