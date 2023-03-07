package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.repository.DonorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class DonorEventHandler {

    final Logger logger = LoggerFactory.getLogger(Donor.class);

    final DonorRepository donorRepository;

    public DonorEventHandler(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @HandleBeforeCreate
    public void handleDonorPreCreate(Donor donor) {
        logger.info("Before creating: {}", donor.toString());
    }

    @HandleBeforeSave
    public void handleDonorPreSave(Donor donor) {
        logger.info("Before updating: {}", donor.toString());
    }

    @HandleBeforeDelete
    public void handleDonorPreDelete(Donor donor) {
        logger.info("Before deleting: {}", donor.toString());
    }

    @HandleBeforeLinkSave
    public void handleDonorPreLinkSave(Donor donor, Object o) {
        logger.info("Before linking: {} to {}", donor.toString(), o.toString());
    }

    @HandleAfterCreate
    public void handleDonorPostCreate(Donor donor) {
        logger.info("After creating: {}", donor.toString());
        donor.encodePassword();
        donorRepository.save(donor);
    }

    @HandleAfterSave
    public void handleDonorPostSave(Donor donor) {
        logger.info("After updating: {}", donor.toString());
        if (donor.isPasswordReset()) {
            donor.encodePassword();
        }
        donorRepository.save(donor);
    }

    @HandleAfterDelete
    public void handleDonorPostDelete(Donor donor) {
        logger.info("After deleting: {}", donor.toString());
    }

    @HandleAfterLinkSave
    public void handleDonorPostLinkSave(Donor donor, Object o) {
        logger.info("After linking: {} to {}", donor.toString(), o.toString());
    }
}
