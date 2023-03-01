package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class RequestDefs {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PropagatorRepository propagatorRepository;

    @Given("^I create a new request with propagator with username \"([^\"]*)\"$")
    public void thereIsNoRegisteredUserWithUsername(String user) {
        Optional<Propagator> propagator = propagatorRepository.findById(user);
        if (propagator.isPresent()) {
            Request request = new Request();
            request.setBy(propagator.get());
            requestRepository.save(request);
        }

    }
}
