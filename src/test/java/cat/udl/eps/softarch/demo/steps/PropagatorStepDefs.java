package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PropagatorStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private PropagatorRepository propagatorRepository;

    @Given("^There is no registered propagator with username \"([^\"]*)\"$")
    public void thereIsNoRegisteredPropagatorWithUsername(String username) {
        Assert.assertFalse("Propagator \""
                        + username + "\"shouldn't exist",
                propagatorRepository.existsById(username));
    }

    @Given("^There is a registered propagator with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredPropagatorWithUsernameAndPasswordAndEmail(String username, String password, String email) {
        if (!propagatorRepository.existsById(username)) {
            Propagator propagator = new Propagator();
            propagator.setEmail(email);
            propagator.setUsername(username);
            propagator.setPassword(password);
            propagator.encodePassword();
            propagatorRepository.save(propagator);
        }
    }

    @When("^I register a new propagator with username \"([^\"]*)\", email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iRegisterANewPropagatorWithUsernameEmailAndPassword(String username, String email, String password) throws Throwable {
        Propagator propagator = new Propagator();
        propagator.setUsername(username);
        propagator.setEmail(email);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/propagators")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new JSONObject(
                                        stepDefs.mapper.writeValueAsString(propagator)
                                ).put("password", password).toString())
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^It has been created a propagator with username \"([^\"]*)\" and email \"([^\"]*)\", the password is not returned$")
    public void itHasBeenCreatedAPropagatorWithUsername(String username, String email) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/propagators/{username}", username)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @And("^It has not been created a propagator with username \"([^\"]*)\"$")
    public void itHasNotBeenCreatedAPropagatorWithUsername(String username) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/propagators/{username}", username)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andExpect(status().isNotFound());
    }
}
