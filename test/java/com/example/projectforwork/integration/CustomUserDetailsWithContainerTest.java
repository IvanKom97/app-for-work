package com.example.projectforwork.integration;
import com.example.projectforwork.config.Config;
import com.example.projectforwork.config.CustomUserDetailsService;
import com.example.projectforwork.repository.UserRepository;
import com.example.projectforwork.utils.AbstractTestContainer;
import com.example.projectforwork.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class CustomUserDetailsWithContainerTest extends AbstractTestContainer {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void addSomeone() {
        userRepository.save(TestUtils.user());
    }

    @Test
    public void findUserByUsername() {
        String username = "iva@mail.ru";
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        assertEquals(userDetails.getUsername(), username);
    }
}
