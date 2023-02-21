package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.DonationEntity;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class DonationStepDefs {
    public static DonationEntity donation;

    @When("^I create a new donation$")
    public void createDonation(){
        donation = new DonationEntity();
    }
    @Then("^The status is \"([^\"]*)\"$")
    public void checkStatus(String status){
        assertThat(status, is(donation.getDonationStatus().getDescription()));

    }
}
