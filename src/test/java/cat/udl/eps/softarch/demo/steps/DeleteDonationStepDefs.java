package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donation;
import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.repository.DonationRepository;
import cat.udl.eps.softarch.demo.repository.DonorRepository;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteDonationStepDefs {
    public static String newResourceUri;
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private DonationRepository donationRepository;

    @When("^I delete the donation")
    public void deleteRequest() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        delete(newResourceUri)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("There is a donation created with amount {int}, weight {int} and location {string}")
    public void thereIsADonationCreatedWithAmountWeightAndLocation(int amount, int weight, String location) {
        Donation donation = new Donation();
        donation.setAmount(amount);
        donation.setWeight(new BigDecimal(weight));
        donation.setLocation(location);
        donation.setBy(donorRepository.findById("userdonor").get());
        donation = donationRepository.save(donation);
        newResourceUri = "/donations/" + donation.getId();
    }

    @And("I try to get the donation")
    public void iTryToGetTheDonation() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get(newResourceUri)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("There is no donation created with id {long}")
    public void thereIsNoDonationsCreatedWithId(Long id) {
        Optional<Donation> donation = donationRepository.findById(id);
        Assert.assertTrue(donation.isEmpty());
        newResourceUri = "/donations/" + id;
    }
}
