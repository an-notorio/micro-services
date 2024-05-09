package com.example.authenticator.config;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.example.authenticator.repository.UsersRepository;
import com.example.authenticator.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ApplicationConfig.class, AuthenticationConfiguration.class})
@ExtendWith(SpringExtension.class)
class ApplicationConfigDiffblueTest {
    @Autowired
    private ApplicationConfig applicationConfig;

    @MockBean
    private UsersRepository usersRepository;

    /**
     * Method under test: {@link ApplicationConfig#authenticationProvider()}
     */
    @Test
    void testAuthenticationProvider() {
        // Arrange, Act and Assert
        assertTrue(((DaoAuthenticationProvider) applicationConfig.authenticationProvider())
                .getUserCache() instanceof NullUserCache);
    }

    /**
     * Method under test:
     * {@link ApplicationConfig#authenticationManager(AuthenticationConfiguration)}
     */
    @Test
    void testAuthenticationManager() throws Exception {
        // Arrange, Act and Assert
        assertTrue(
                ((ProviderManager) applicationConfig.authenticationManager(new AuthenticationConfiguration())).getProviders()
                        .get(0) instanceof DaoAuthenticationProvider);
    }

    /**
     * Method under test:
     * {@link ApplicationConfig#authenticationManager(AuthenticationConfiguration)}
     */
    @Test
    void testAuthenticationManager2() throws Exception {
        // Arrange
        AuthenticationConfiguration config = new AuthenticationConfiguration();
        config.setApplicationContext(mock(AnnotationConfigApplicationContext.class));

        // Act and Assert
        assertTrue(((ProviderManager) applicationConfig.authenticationManager(config)).getProviders()
                .get(0) instanceof DaoAuthenticationProvider);
    }

    /**
     * Method under test: {@link ApplicationConfig#passwordEncoder()}
     */
    @Test
    void testPasswordEncoder() {
        // Arrange, Act and Assert
        assertTrue(applicationConfig.passwordEncoder() instanceof BCryptPasswordEncoder);
    }

    /**
     * Method under test: {@link ApplicationConfig#userDetailsService()}
     */
    @Test
    void testUserDetailsService() {
        // Arrange, Act and Assert
        assertTrue(applicationConfig.userDetailsService() instanceof UserDetailsServiceImpl);
    }
}
