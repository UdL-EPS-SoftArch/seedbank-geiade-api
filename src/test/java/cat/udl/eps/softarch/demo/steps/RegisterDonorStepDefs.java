package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.repository.DonorRepository;
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

public class RegisterDonorStepDefs {

  @Autowired
  private StepDefs stepDefs;

  @Autowired
  private DonorRepository donorRepository;

  @Given("^There is no registered donor with username \"([^\"]*)\"$")
  public void thereIsNoRegisteredDonorWithUsername(String donor) {
    Assert.assertFalse("Donor \""
                    +  donor + "\"shouldn't exist",
                    donorRepository.existsById(donor));
  }

  @Given("^There is a registered donor with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
  public void thereIsARegisteredDonorWithUsernameAndPasswordAndEmail(String username, String password, String email) {
    if (!donorRepository.existsById(username)) {
      Donor donor = new Donor();
      donor.setEmail(email);
      donor.setUsername(username);
      donor.setPassword(password);
      donor.encodePassword();
      donorRepository.save(donor);
    }
  }

  @And("^I cannot login donor with username \"([^\"]*)\" and password \"([^\"]*)\"$")
  public void iCannotLoginDonorWithUsernameAndPassword(String username, String password) throws Throwable {
    AuthenticationStepDefs.currentUsername = username;
    AuthenticationStepDefs.currentPassword = password;

    stepDefs.result = stepDefs.mockMvc.perform(
        get("/identity", username)
            .accept(MediaType.APPLICATION_JSON)
            .with(AuthenticationStepDefs.authenticate()))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @When("^I register a new donor with username \"([^\"]*)\", email \"([^\"]*)\" and password \"([^\"]*)\"$")
  public void iRegisterANewDonorWithUsernameEmailAndPassword(String username, String email, String password) throws Throwable {
    Donor donor = new Donor();
    donor.setUsername(username);
    donor.setEmail(email);

    stepDefs.result = stepDefs.mockMvc.perform(
            post("/donors")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new JSONObject(
                            stepDefs.mapper.writeValueAsString(donor)
                    ).put("password", password).toString())
                    .accept(MediaType.APPLICATION_JSON)
                    .with(AuthenticationStepDefs.authenticate()))
            .andDo(print());
  }

  @And("^It has been created a donor with username \"([^\"]*)\" and email \"([^\"]*)\", the password is not returned$")
  public void itHasBeenCreatedADonorWithUsername(String username, String email) throws Throwable {
    stepDefs.result = stepDefs.mockMvc.perform(
            get("/donors/{username}", username)
                    .accept(MediaType.APPLICATION_JSON)
                    .with(AuthenticationStepDefs.authenticate()))
            .andDo(print())
            .andExpect(jsonPath("$.email", is(email)))
            .andExpect(jsonPath("$.password").doesNotExist());
  }

  @And("^It has not been created a donor with username \"([^\"]*)\"$")
  public void itHasNotBeenCreatedADonorWithUsername(String username) throws Throwable {
    stepDefs.result = stepDefs.mockMvc.perform(
            get("/donors/{username}", username)
                    .accept(MediaType.APPLICATION_JSON)
                    .with(AuthenticationStepDefs.authenticate()))
            .andExpect(status().isNotFound());
  }
}
