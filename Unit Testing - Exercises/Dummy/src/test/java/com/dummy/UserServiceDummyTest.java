package com.dummy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceDummyTest {

    // --- Dummies / fakes ----------------------------------------------

    static class InMemoryUserRepository implements UserRepository {
        private final Map<String, User> byEmail = new HashMap<>();

        @Override
        public boolean existsByEmail(String email) {
            return byEmail.containsKey(email);
        }

        @Override
        public User save(User user) {
            byEmail.put(user.getEmail(), user);
            return user;
        }

        @Override
        public User findByEmail(String email) {
            return byEmail.get(email);
        }
    }

    static class EmailServiceDummy implements EmailService {
        int welcomeCount = 0;
        String lastWelcomeEmail;
        String lastWelcomeName;

        int resetCount = 0;
        String lastResetEmail;

        @Override
        public void sendWelcomeEmail(String email, String name) {
            welcomeCount++;
            lastWelcomeEmail = email;
            lastWelcomeName = name;
        }

        @Override
        public void sendPasswordResetEmail(String email) {
            resetCount++;
            lastResetEmail = email;
        }
    }

    // --- Test fixtures -------------------------------------------------

    private InMemoryUserRepository userRepo;
    private EmailServiceDummy emailDummy;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepo = new InMemoryUserRepository();
        emailDummy = new EmailServiceDummy();
        userService = new UserService(userRepo, emailDummy);
    }

    // --- registerUser tests -------------------------------------------

    @Test
    void registerUser_success_savesAndSendsEmail() {
        User user = new User("Alice", "alice@example.com", 21);

        RegistrationResult result = userService.registerUser(user);

        assertTrue(result.isSuccess());
        assertEquals("User registered successfully", result.getMessage());
        assertTrue(userRepo.existsByEmail("alice@example.com"));
        assertEquals(1, emailDummy.welcomeCount);
        assertEquals("alice@example.com", emailDummy.lastWelcomeEmail);
        assertEquals("Alice", emailDummy.lastWelcomeName);
    }

    @Test
    void registerUser_nullUser_throws() {
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(null));
        assertEquals(0, emailDummy.welcomeCount);
    }

    @Test
    void registerUser_blankEmail_rejected() {
        RegistrationResult result = userService.registerUser(new User("A", "   ", 20));
        assertFalse(result.isSuccess());
        assertEquals("Email is required", result.getMessage());
        assertEquals(0, emailDummy.welcomeCount);
    }

    @Test
    void registerUser_invalidEmail_rejected() {
        RegistrationResult result = userService.registerUser(new User("A", "bad@", 20));
        assertFalse(result.isSuccess());
        assertEquals("Invalid email format", result.getMessage());
        assertEquals(0, emailDummy.welcomeCount);
    }

    @Test
    void registerUser_shortName_rejected() {
        RegistrationResult result = userService.registerUser(new User("A", "a@b.co", 20));
        assertFalse(result.isSuccess());
        assertEquals("Name must be at least 2 characters long", result.getMessage());
    }

    @Test
    void registerUser_underage_rejected() {
        RegistrationResult result = userService.registerUser(new User("Al", "a@b.co", 12));
        assertFalse(result.isSuccess());
        assertEquals("User must be at least 13 years old", result.getMessage());
    }

    @Test
    void registerUser_duplicateEmail_rejected() {
        // Seed existing user
        userRepo.save(new User("Existing", "dup@example.com", 30));

        RegistrationResult result = userService.registerUser(new User("New", "dup@example.com", 25));

        assertFalse(result.isSuccess());
        assertEquals("User with this email already exists", result.getMessage());
        assertEquals(0, emailDummy.welcomeCount);
    }

    // --- calculateAccountStatus tests ---------------------------------

    @Test
    void calculateAccountStatus_minor() {
        assertEquals(AccountStatus.MINOR,
                userService.calculateAccountStatus(new User("A", "a@b.co", 17)));
    }

    @Test
    void calculateAccountStatus_adult() {
        assertEquals(AccountStatus.ADULT,
                userService.calculateAccountStatus(new User("A", "a@b.co", 30)));
    }

    @Test
    void calculateAccountStatus_senior() {
        assertEquals(AccountStatus.SENIOR,
                userService.calculateAccountStatus(new User("A", "a@b.co", 70)));
    }

    @Test
    void calculateAccountStatus_invalid_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.calculateAccountStatus(new User("A", "a@b.co", -1)));
    }
}