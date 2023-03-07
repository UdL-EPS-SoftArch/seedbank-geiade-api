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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateTake {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropagatorRepository propagatorRepository;
    @Autowired
    private TakeRepository takeRepository;

    @When("I create a new valid Take with Propagator with username \"([^\"]*)\"$")
    public void createTake(String username) throws Exception{

    }






    @And("User {string} is the propagator")
    public void thereIsAValidPropagator(String name) {
        this.propagator = propagatorRepository.findById(name).orElseThrow();
    }
    @But("There is no Take")
    public void thereIsNoValidTake() {
        assertEquals(0, takeRepository.count());
    }


    private Take createValidTake(){
        Take take = new Take();
        take.setWeight(BigDecimal.TEN);
        take.setLocation("Lleida");
        take.setDate(ZonedDateTime.now());
        return take;
    }

    private Take takeWithoutPropagator() {
        Take take = new Take();
        take.setWeight(BigDecimal.TEN);
        take.setLocation("Lleida");
        take.setDate(ZonedDateTime.now());
        take.setBy(null);
        //posar atriuts take
        return take;
    }
}
