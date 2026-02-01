package pe.edu.tecsup.tienda.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootTest
public class BCryptPasswordEncoderTest {

    @Test
    void testCreateBCryptPassword() {
        // Create an instance of BCryptPasswordEncoder
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Encode a password
        String rawPassword = "tecsup"; // Here
        String encodedPassword = passwordEncoder.encode(rawPassword);
        log.info("Raw Password: " + rawPassword);
        log.info("Encoded Password: " + encodedPassword);

        // Check if a provided password matches the encoded password
        String providedPassword = "tecsup"; // Here
        boolean passwordMatches
                = passwordEncoder.matches(providedPassword, encodedPassword);
        log.info("Password Matches: " + passwordMatches);
    }
}


