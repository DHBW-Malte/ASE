import com.dummy.UserService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserServiceTest {
    private static class DummyUserService extends UserService {
        private UserRepository userRepositry;

        public DummyUserService(UserRepository userRepository, EmailService emailService) {
            super(userRepository, emailService);
        }
        
    }
}
