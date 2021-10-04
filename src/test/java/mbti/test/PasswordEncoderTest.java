package mbti.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Test
   @DisplayName("패스워드 암호화 테스트")
   void passwordEncode() {

      String rawPassword = "12345678";

      String encodedPassword = passwordEncoder.encode(rawPassword);

      assertAll(
            () -> assertNotEquals(rawPassword, encodedPassword),
            () -> assertTrue(passwordEncoder.matches(rawPassword, encodedPassword))
      );
   }
}