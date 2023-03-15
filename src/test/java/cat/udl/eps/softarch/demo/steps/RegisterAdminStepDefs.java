package cat.udl.eps.softarch.demo.steps;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.softarch.demo.domain.Admin;
import cat.udl.eps.softarch.demo.repository.AdminRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class RegisterAdminStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private AdminRepository adminRepository;

    @Given("^There is no registered admin with username \"([^\"]*)\"$")
    public void thereIsNoRegisteredAdminWithUsername(String admin) {
        Assert.assertFalse("Admin \""
                        +  admin + "\"shouldn't exist",
                adminRepository.existsById(admin));
    }

    @Given("^There is a registered admin with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredAdminWithUsernameAndPasswordAndEmail(String username, String password, String email) {
        if (!adminRepository.existsById(username)) {
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setUsername(username);
            admin.setPassword(password);
            admin.encodePassword();
            adminRepository.save(admin);
        }
    }

    @And("^I can login admin with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iCanLoginAdminWithUsernameAndPassword(String username, String password) throws Throwable {
        AuthenticationStepDefs.currentUsername = username;
        AuthenticationStepDefs.currentPassword = password;

        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/identity", username)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @And("^I cannot login admin with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iCannotLoginAdminWithUsernameAndPassword(String username, String password) throws Throwable {
        AuthenticationStepDefs.currentUsername = username;
        AuthenticationStepDefs.currentPassword = password;

        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/identity", username)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @When("^I register a new admin with username \"([^\"]*)\", email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iRegisterANewAdminWithUsernameEmailAndPassword(String username, String email, String password) throws Throwable {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setEmail(email);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/admins")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new JSONObject(
                                        stepDefs.mapper.writeValueAsString(admin)
                                ).put("password", password).toString())
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^It has been created a admin with username \"([^\"]*)\" and email \"([^\"]*)\", the password is not returned$")
    public void itHasBeenCreatedAAdminWithUsername(String username, String email) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/admins/{username}", username)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @And("^It has not been created a admin with username \"([^\"]*)\"$")
    public void itHasNotBeenCreatedAAdminWithUsername(String username) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/admins/{username}", username)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andExpect(status().isNotFound());
    }
}