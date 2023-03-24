package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.repository.DonationRepository;
import cat.udl.eps.softarch.demo.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteDonationStepDefs {
    public static String newResourceUri;
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private DonationRepository donationRepository;
}
