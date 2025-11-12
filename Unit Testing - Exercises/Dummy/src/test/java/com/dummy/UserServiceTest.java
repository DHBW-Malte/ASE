package com.dummy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.assertArg;

public class UserServiceTest {
    
    private static class DummyUserRepository implements  UserRepository {
        User user = new User("Liam", "Liam@handsome.com", 26);
        
        @Override
        public boolean existsByEmail(String email) {
            return true;
        };

        @Override
        public User save(User user) {
            return user;
        };

        @Override
        public User findByEmail(String email) {
            return user;
        };   
    }

    private static class DummyEmailService implements EmailService {
        @Override
        public void sendWelcomeEmail(String email, String name) {};
        @Override
        public void sendPasswordResetEmail(String email) {};
    }

    @Test
    void checkAccountStatus() {
        User user = new User("Liam", "Liam@handsome.com", 26);

        EmailService dummyEmailService = new DummyEmailService();
        UserRepository dummyUserRepo = new DummyUserRepository();

        UserService service = new UserService(dummyUserRepo, dummyEmailService);

        AccountStatus status = service.calculateAccountStatus(user);
        assertEquals(AccountStatus.MINOR, status);
    }
}
