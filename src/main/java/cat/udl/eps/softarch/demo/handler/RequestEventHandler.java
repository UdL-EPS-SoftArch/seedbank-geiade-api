package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.*;

public class RequestEventHandler {
    final Logger logger = LoggerFactory.getLogger(User.class);

    final RequestRepository requestRepository;

    public RequestEventHandler(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @HandleBeforeCreate
    public void handleRequestPreCreate(User user) {
        logger.info("Before creating: {}", user.toString());
    }

}
