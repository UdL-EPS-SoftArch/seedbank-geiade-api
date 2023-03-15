package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class CreateRequestDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private TakeRepository takeRepository;
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
            Request request = createValidRequest();
            stepDefs.result = stepDefs.mockMvc.perform(
                            post("/requests")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .characterEncoding("utf-8")
                                    .content(stepDefs.mapper.writeValueAsString(request))
                                    .accept(MediaType.APPLICATION_JSON)
                                    .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print());
    }

    private Request createValidRequest () throws Exception{
        Request request = new Request();
        Propagator propagator = createValidPropagator();
        request.setBy(propagator);
        request.setAmount(1);
        request.setWeight(BigDecimal.ONE);
        request.setLocation("Lleida");
        request.setLastUpdate(ZonedDateTime.now());
        request.setFulfilledBy(createValidTake("Lleida"));
        return request;
    }
    private Take createValidTake(String location) throws Exception{
        Optional<Take> take1 = takeRepository.findByLocation(location);
        if (take1.isEmpty()){
            Take take = new Take();
            take.setWeight(BigDecimal.TEN);
            take.setAmount(10);
            take.setLocation(location);
            take.setDate(ZonedDateTime.now());
            takeRepository.save(take);
            return take;
        }
        return take1.get();
    }
    private Propagator createValidPropagator() {
        Propagator propagator = new Propagator();
        propagator.setUsername("propagator");
        propagator.setEmail("propagator@sample.app");
        propagator.setPassword("password");
        propagator.encodePassword();
        return propagator;
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
    @When("^I create a new request without Take with propagator with username \"([^\"]*)\"$")
    public void createNotValidRequest(String username) throws Exception {
        Request request = createRequestWithoutTake();
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/requests")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(stepDefs.mapper.writeValueAsString(request))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
    private Request createRequestWithoutTake (){
        Request request = new Request();
        request.setBy(createValidPropagator());
        request.setAmount(1);
        request.setWeight(BigDecimal.ONE);
        request.setLocation("Lleida");
        request.setLastUpdate(ZonedDateTime.now());
        return request;
    }
}
