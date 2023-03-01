package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.DonationRepository;
import cat.udl.eps.softarch.demo.repository.DonorRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckDonorDonationsStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Given("^There is a registered Donor with username \"([^\"]*)\"$")
    public void thereIsARegisteredDonorWithUsername(String username){
        Assert.assertTrue(donorRepository.existsById(username));
    }

    @Then("^There is no Donations by the Donor with username \"([^\"]*)\"$")
    public void thereIsNoDonationsForDonor(String username){
        Donor donor = donorRepository.findById(username).get();
        Assert.assertTrue(donationRepository.findByBy(donor).isEmpty());
    }
}
