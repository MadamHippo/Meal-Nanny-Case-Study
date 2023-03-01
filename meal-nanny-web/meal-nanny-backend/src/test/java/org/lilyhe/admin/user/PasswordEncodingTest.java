package org.lilyhe.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncodingTest {
    @Test
    public void testEncodePassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPW = "password123";
        String encodedPW = passwordEncoder.encode(rawPW);
        System.out.println(encodedPW);
        boolean matchPW = passwordEncoder.matches(rawPW, encodedPW);
        assertThat(matchPW).isTrue();
    }
}
