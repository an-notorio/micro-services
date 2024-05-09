package com.example.authenticator.config;

import java.util.HashMap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SecurityConfiguration.class})
@ExtendWith(SpringExtension.class)
class SecurityConfigurationDiffblueTest {
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    /**
     * Method under test:
     * {@link SecurityConfiguration#securityFilterChain(HttpSecurity)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSecurityFilterChain() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       org.springframework.security.authentication.AuthenticationProvider
        //   See https://diff.blue/R027 to resolve this issue.

        // Arrange
        AuthenticationManagerBuilder authenticationBuilder = new AuthenticationManagerBuilder(null);

        // Act
        securityConfiguration.securityFilterChain(new HttpSecurity(null, authenticationBuilder, new HashMap<>()));
    }
}
