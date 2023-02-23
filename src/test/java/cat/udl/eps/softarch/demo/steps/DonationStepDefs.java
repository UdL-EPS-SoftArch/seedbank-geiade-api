package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donation;
import cat.udl.eps.softarch.demo.domain.Donor;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class DonationStepDefs {
    public static Donation donation;
    public static Donor donor;
    @Given("^There is a donor registered with the username \"([^\"]*)\"$")
    public void thereIsADonor(String username){
        donor = new Donor();
        donor.setUsername(username);
    }

    @When("^I create a new donation$")
    public void createDonation(){
        /*Donor donor = new Donor();*/
        donation = new Donation();
        /*donation.setDonor(donor);*/
    }

    @And("^I assign a donor to the donation$")
    public void setDonor(){
        donation.setDonor(donor);
    }

    @Then("^The donor of the donation is \"([^\"]*)\"$")
    public void checkDonor(String username){
        assertThat(username, is(donation.getDonor().getUsername()));
    }
}
