package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donation;
import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.DonorRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class CreateDonationStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private DonorRepository donorRepository;
    
    @Given("^There is a registered donor with username \"([^\"]*)\"$")
    public void thereIsARegisteredDonorWithUsername(String username) throws RuntimeException {
        if (!donorRepository.existsById(username)) {
            throw new RuntimeException("Unregistered donor");
        }
    }

    @When("^I create a new valid donation with donor$")
    public void createDonation() throws Exception {
        Donation donation = createValidDonation();
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/donations")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .characterEncoding("utf-8")
                                .content(stepDefs.mapper.writeValueAsString(donation))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("^I create a donation without donor$")
    public void createDonationWithoutDonor() throws Exception {
        Donation donation = createNotValidDonation();
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/donations")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .characterEncoding("utf-8")
                                .content(stepDefs.mapper.writeValueAsString(donation))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
    @When("^I create a donation with donor and take$")
    public void createDonationWithDonorAndTake() throws Exception {
        Donation donation = createValidDonationWithDonorAndTake();
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/donations")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .characterEncoding("utf-8")
                                .content(stepDefs.mapper.writeValueAsString(donation))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    private Donation createValidDonation() {
        Donation donation = new Donation();
        donation.setAmount(89);
        donation.setWeight(new BigDecimal("11.34"));
        donation.setDate(ZonedDateTime.now());
        donation.setLocation("Lleida");
        donation.setBy(createValidDonor());
        return donation;
    }

    private Donor createValidDonor() {
        Donor donor = new Donor();
        donor.setUsername("userdonor");
        donor.setEmail("userdonor@sample.app");
        donor.setPassword("password");
        donor.encodePassword();
        return donor;
    }
    private Donation createNotValidDonation() {
        Donation donation = new Donation();
        donation.setAmount(89);
        donation.setWeight(new BigDecimal("11.34"));
        donation.setDate(ZonedDateTime.now());
        donation.setLocation("Lleida");
        return donation;
    }
    private Take createValidTake(){
        Take take = new Take();
        take.setWeight(BigDecimal.TEN);
        take.setAmount(10);
        take.setLocation("Mollerussa");
        take.setDate(ZonedDateTime.now());
        take.setPropagator(createValidPropagator());
        return take;
    }
    private Propagator createValidPropagator() {
        Propagator propagator = new Propagator();
        propagator.setUsername("propagator");
        propagator.setEmail("propagator@sample.app");
        propagator.setPassword("password");
        propagator.encodePassword();
        return propagator;
    }
    private Donation createValidDonationWithDonorAndTake() {
        Donation donation = new Donation();
        donation.setAmount(89);
        donation.setWeight(new BigDecimal("11.34"));
        donation.setDate(ZonedDateTime.now());
        donation.setLocation("Lleida");
        donation.setBy(createValidDonor());
        donation.setTakenBy(createValidTake());
        return donation;

    }
}