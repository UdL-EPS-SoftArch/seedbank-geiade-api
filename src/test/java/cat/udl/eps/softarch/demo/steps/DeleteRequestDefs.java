package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteRequestDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private RequestRepository requestRepository;
    @Given("^There is a Request created with id \"([^\"]*)\"$")
    public void thereIsARequestCreatedWithId(Long id) throws RuntimeException {
        if (!requestRepository.existsById(id)) {
            throw new RuntimeException("Unexisting Request");
        }
    }
    @When("^I delete request with id \"([^\"]*)\"$")
    public void deleteRequest(String id) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        delete("/requests", id)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
