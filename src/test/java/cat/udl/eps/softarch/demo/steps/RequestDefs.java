package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.ZoneId;
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

    @Given("^There is a registered propagator with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredUserWithUsernameAndPasswordAndEmail(String username, String password, String email) {
        if (!propagatorRepository.existsById(username)) {
            Propagator propagator = createValidPropagator();
            propagatorRepository.save(propagator);
        }
    }
    @Given("^I create a new request with propagator with username \"([^\"]*)\"$")
    public void createRequest(String username) throws Exception {
        Optional<Propagator> propagator = propagatorRepository.findById(username);
        if (propagator.isPresent()) {
            Request request = new Request();
            request.setBy(propagator.get());
            requestRepository.save(request);
        }
        Request request = createValidRequest();
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/requests")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(request))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
    private Propagator createValidPropagator(){
        Propagator propagator = new Propagator();
        propagator.setEmail("propagator@sample.app");
        propagator.setUsername("propagator");
        propagator.setPassword("password");
        propagator.encodePassword();
        propagatorRepository.save(propagator);
        return propagator;
    }
    private Take createValidTake(){
        Take take = new Take();
        take.setId(1L);
        take.setLastUpdate(ZonedDateTime.of(2018, 01, 01, 0, 0, 0, 0, ZoneId.of("CET")));
        return take;
    }
    private Request createValidRequest() {
        Request request = new Request();
        request.setLastUpdate(ZonedDateTime.of(2018, 01, 01, 0, 0, 0, 0, ZoneId.of("CET")));
        request.setBy(createValidPropagator());
        request.setFulfilledBy(createValidTake());
        return request;
    }
}
