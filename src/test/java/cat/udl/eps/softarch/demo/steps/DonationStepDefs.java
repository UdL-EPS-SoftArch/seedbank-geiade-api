package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donation;
import cat.udl.eps.softarch.demo.domain.Donor;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;


public class DonationStepDefs {
    public static Donation donation;

    @When("^I create a new donation$")
    public void createDonation(){
        /*Donor donor = new Donor();*/
        donation = new Donation();
        /*donation.setDonor(donor);*/
    }
    /*@Then("^There is a Donor$")
    public void checkDonor(){
        boolean check = donation.getDonor().equals(null);
        assertThat(String.valueOf(check), false);
    }*/
}
