package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.exception.ForbiddenException;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RepositoryEventHandler
public class RequestEventHandler {
    final Logger logger = LoggerFactory.getLogger(Request.class);

    final RequestRepository requestRepository;

    public RequestEventHandler(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @HandleBeforeCreate
    public void handleRequestPreCreate(Request request) {
        logger.info("Before creating: {}", request.toString());
        if (request.getFulfilledBy() != null) {
            Optional<Request> request1 = requestRepository.findByFulfilledBy(request.getFulfilledBy());
            if (request1.isPresent()) {
                throw new ForbiddenException();
            }
        }
    }
}
