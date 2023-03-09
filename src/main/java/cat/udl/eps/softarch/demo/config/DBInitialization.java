package cat.udl.eps.softarch.demo.config;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class DBInitialization {
    @Value("${default-password}")
    String defaultPassword;
    @Value("${spring.profiles.active:}")
    private String activeProfiles;
    private final UserRepository userRepository;
    private final DonorRepository donorRepository;

    public DBInitialization(UserRepository userRepository, DonorRepository donorRepository) {
    private final DonorRepository donorRepository;

    private final PropagatorRepository propagatorRepository;

    public DBInitialization(UserRepository userRepository, DonorRepository donorRepository, PropagatorRepository propagatorRepository) {

        this.userRepository = userRepository;
        this.donorRepository = donorRepository;
        this.donorRepository = donorRepository;
        this.propagatorRepository = propagatorRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        // Default donor
        if (!donorRepository.existsById("donor")) {
            Donor donor = new Donor();
            donor.setEmail("donor@sample.app");
            donor.setUsername("donor");
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
        }
    }
}
