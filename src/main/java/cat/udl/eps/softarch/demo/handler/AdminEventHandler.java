package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Admin;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.AdminRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterLinkSave;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeLinkSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class AdminEventHandler {

    final Logger logger = LoggerFactory.getLogger(User.class);

    final AdminRepository adminRepository;

    public AdminEventHandler(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @HandleBeforeCreate
    public void handleUserPreCreate(Admin admin) {
        logger.info("Before creating: {}", admin.toString());
    }

    @HandleBeforeSave
    public void handleUserPreSave(Admin admin) {
        logger.info("Before updating: {}", admin.toString());
    }

    @HandleBeforeDelete
    public void handleUserPreDelete(Admin admin) {
        logger.info("Before deleting: {}", admin.toString());
    }

    @HandleBeforeLinkSave
    public void handleUserPreLinkSave(Admin admin, Object o) {
        logger.info("Before linking: {} to {}", admin.toString(), o.toString());
    }

    @HandleAfterCreate
    public void handleUserPostCreate(Admin admin) {
        logger.info("After creating: {}", admin.toString());
        admin.encodePassword();
        adminRepository.save(admin);
    }

    @HandleAfterSave
    public void handleUserPostSave(Admin admin) {
        logger.info("After updating: {}", admin.toString());
        if (admin.isPasswordReset()) {
            admin.encodePassword();
        }
        adminRepository.save(admin);
    }

    @HandleAfterDelete
    public void handleAdminPostDelete(Admin admin) {
        logger.info("After deleting: {}", admin.toString());
    }

    @HandleAfterLinkSave
    public void handleAdminPostLinkSave(Admin admin, Object o) {
        logger.info("After linking: {} to {}", admin.toString(), o.toString());
    }
}