package cat.udl.eps.softarch.demo.config;
import cat.udl.eps.softarch.demo.domain.*;
import cat.udl.eps.softarch.demo.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;

@Configuration
public class DBInitialization {
    @Value("${default-password}")
    String defaultPassword;
    @Value("${spring.profiles.active:}")
    private String activeProfiles;
    private final UserRepository userRepository;

    private final DonorRepository donorRepository;

    private final PropagatorRepository propagatorRepository;

    private final DonationRepository donationRepository;

    private final TakeRepository takeRepository;

    private final RequestRepository requestRepository;

    public DBInitialization(UserRepository userRepository, DonorRepository donorRepository, PropagatorRepository propagatorRepository,
                            DonationRepository donationRepository, TakeRepository takeRepository, RequestRepository requestRepository) {

        this.userRepository = userRepository;
        this.donorRepository = donorRepository;
        this.propagatorRepository = propagatorRepository;
        this.donationRepository = donationRepository;
        this.takeRepository = takeRepository;
        this.requestRepository = requestRepository;
    }

    @PostConstruct
    public void initializeDatabase() {

        // Default user
        if (!userRepository.existsById("demo")) {
            User user = new User();
            user.setEmail("demo@sample.app");
            user.setUsername("demo");
            user.setPassword(defaultPassword);
            user.encodePassword();
            userRepository.save(user);
        }

        // Default donor
        if (!donorRepository.existsById("userdonor")) {
            Donor donor = new Donor();
            donor.setEmail("userdonor@sample.app");
            donor.setUsername("userdonor");
            donor.setPassword(defaultPassword);
            donor.encodePassword();
            donorRepository.save(donor);
        }

        // Default propagator
        if (!propagatorRepository.existsById("propagator")) {
            Propagator propagator = new Propagator();
            propagator.setEmail("propagator@sample.app");
            propagator.setUsername("propagator");
            propagator.setPassword(defaultPassword);
            propagator.encodePassword();
            propagatorRepository.save(propagator);
        }

        if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!userRepository.existsById("test")) {
                User user = new User();
                user.setEmail("test@sample.app");
                user.setUsername("test");
                user.setPassword(defaultPassword);
                user.encodePassword();
                userRepository.save(user);
            }

            // Default donor
            if (!donorRepository.existsById("userdonor")) {
                Donor donor = new Donor();
                donor.setEmail("userdonor@sample.app");
                donor.setUsername("userdonor");
                donor.setPassword(defaultPassword);
                donor.encodePassword();
                donorRepository.save(donor);
            }

            //Default donation
            Donation donation = new Donation();
            donation.setAmount(89);
            donation.setWeight(new BigDecimal("11.34"));
            donation.setDate(ZonedDateTime.now());
            donation.setLocation("Lleida");
            donation.setBy(donorRepository.findById("userdonor").get());
            donationRepository.save(donation);

            //Default take
            Take take = new Take();
            take.setAmount(30);
            take.setWeight(new BigDecimal("8.31"));
            take.setDate(ZonedDateTime.now());
            take.setLocation("Els Alamús");
            take.setBy(propagatorRepository.findById("propagator").get());
            takeRepository.save(take);

            //Default request
            Request request = new Request();
            request.setAmount(3);
            request.setWeight(new BigDecimal(32.0));
            request.setDate(ZonedDateTime.now());
            request.setLocation("Granada");
            request.setBy(propagatorRepository.findById("propagator").get());
            requestRepository.save(request);

            // Default propagator
            if (!propagatorRepository.existsById("propagator")) {
                Propagator propagator = new Propagator();
                propagator.setEmail("propagator@sample.app");
                propagator.setUsername("propagator");
                propagator.setPassword(defaultPassword);
                propagator.encodePassword();
                propagatorRepository.save(propagator);
            }
        }
    }
}
