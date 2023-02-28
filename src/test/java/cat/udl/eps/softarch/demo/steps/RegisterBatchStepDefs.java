/*package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Batch;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegisterBatchStepDefs {
    public static Batch batch;

    @When("^I register a new batch$")
    public void registration(){
        batch = new Batch();
    }
    @Then("^Amount is -1$")
    public void equalsAmount(){
        assertThat(batch.getAmount(), is(-1));
    }
}


Commented test, as it's not complete, will be done later
 */