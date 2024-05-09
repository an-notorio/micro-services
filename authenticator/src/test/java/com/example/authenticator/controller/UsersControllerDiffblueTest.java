package com.example.authenticator.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.authenticator.common.TokenType;
import com.example.authenticator.dto.AuthenticationRequest;
import com.example.authenticator.dto.ForgotPasswordDto;
import com.example.authenticator.dto.RegisterRequest;
import com.example.authenticator.dto.ResetPasswordDto;
import com.example.authenticator.model.ResetPsw;
import com.example.authenticator.model.Token;
import com.example.authenticator.model.User;
import com.example.authenticator.repository.ResetPswRepository;
import com.example.authenticator.repository.TokenRepository;
import com.example.authenticator.repository.UsersRepository;
import com.example.authenticator.service.AuthenticationService;
import com.example.authenticator.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UsersController.class})
@ExtendWith(SpringExtension.class)
class UsersControllerDiffblueTest {
    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    private UsersController usersController;

    /**
     * Method under test: {@link UsersController#register(RegisterRequest)}
     */
    @Test
    void testRegister() throws Exception {
        // Arrange
        Mockito.<ResponseEntity<?>>when(authenticationService.register(Mockito.<RegisterRequest>any())).thenReturn(null);

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("jane.doe@example.org");
        registerRequest.setFirstName("Jane");
        registerRequest.setLastName("Doe");
        registerRequest.setPassword("iloveyou");
        registerRequest.setRole(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(registerRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link UsersController#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate() throws Exception {
        // Arrange
        Mockito.<ResponseEntity<?>>when(authenticationService.authenticate(Mockito.<AuthenticationRequest>any()))
                .thenReturn(null);

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("jane.doe@example.org");
        authenticationRequest.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(authenticationRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link UsersController#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken() throws Exception {
        // Arrange
        doNothing().when(authenticationService)
                .refreshToken(Mockito.<HttpServletRequest>any(), Mockito.<HttpServletResponse>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/refresh-token");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link UsersController#delete(Integer, HttpServletRequest)}
     */
    @Test
    void testDelete() throws Exception {
        // Arrange
        doNothing().when(authenticationService).deleteUser(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/delete/{userId}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link UsersController#delete(Integer, HttpServletRequest)}
     */
    @Test
    void testDelete2() throws Exception {
        // Arrange
        doNothing().when(authenticationService).deleteUser(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/delete/{userId}", 1);
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link UsersController#forgotPassword(ForgotPasswordDto)}
     */
    @Test
    void testForgotPassword() throws Exception {
        // Arrange
        doNothing().when(authenticationService).forgotPassword(Mockito.<String>any());

        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
        forgotPasswordDto.setMail("Mail");
        String content = (new ObjectMapper()).writeValueAsString(forgotPasswordDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/forgotPassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link UsersController#resetPassword(ResetPasswordDto, String)}
     */
    @Test
    void testResetPassword() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AuthenticationService service = mock(AuthenticationService.class);
        Mockito.<ResponseEntity<?>>when(service.resetPassword(Mockito.<ResetPasswordDto>any(), Mockito.<String>any()))
                .thenReturn(null);

        // Act
        ResponseEntity<?> actualResetPasswordResult = (new UsersController(service)).resetPassword(null, "ABC123");

        // Assert
        verify(service).resetPassword(isNull(), eq("ABC123"));
        assertNull(actualResetPasswordResult);
    }

    /**
     * Method under test:
     * {@link UsersController#resetPassword(ResetPasswordDto, String)}
     */
    @Test
    void testResetPassword2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        User user = new User();
        user.setAttempts(1);
        user.setAuthorities(new ArrayList<>());
        user.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(true);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(new ArrayList<>());
        user.setStatus(true);
        user.setTokens(new ArrayList<>());
        user.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUserId(1);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        UsersRepository repository = mock(UsersRepository.class);
        when(repository.findAllByEmail(Mockito.<String>any())).thenReturn(userList);

        User user2 = new User();
        user2.setAttempts(1);
        user2.setAuthorities(new ArrayList<>());
        user2.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setDeleted(true);
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setRole(new ArrayList<>());
        user2.setStatus(true);
        user2.setTokens(new ArrayList<>());
        user2.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setUserId(1);

        ResetPsw resetPsw = new ResetPsw();
        resetPsw.setExpireAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        resetPsw.setExpired(true);
        resetPsw.setIdReset(1L);
        resetPsw.setResetToken("ABC123");
        resetPsw.setTimeStamp(mock(Timestamp.class));
        resetPsw.setUser(user2);
        ResetPswRepository resetPswRepository = mock(ResetPswRepository.class);
        when(resetPswRepository.findResetPswByResetToken(Mockito.<String>any())).thenReturn(resetPsw);
        JwtService jwtService = mock(JwtService.class);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        TokenRepository tokenRepository = mock(TokenRepository.class);

        // Act
        ResponseEntity<?> actualResetPasswordResult = (new UsersController(new AuthenticationService(repository,
                tokenRepository, resetPswRepository, new BCryptPasswordEncoder(), jwtService, authenticationManager)))
                .resetPassword(null, "ABC123");

        // Assert
        verify(resetPswRepository).findResetPswByResetToken(eq("ABC123"));
        verify(repository).findAllByEmail(eq("janedoe"));
        verify(jwtService).extractUsername(eq("ABC123"));
        assertEquals(403, actualResetPasswordResult.getStatusCodeValue());
        assertTrue(actualResetPasswordResult.hasBody());
        assertTrue(actualResetPasswordResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link UsersController#resetPassword(ResetPasswordDto, String)}
     */
    @Test
    void testResetPassword3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        User user = new User();
        user.setAttempts(1);
        user.setAuthorities(new ArrayList<>());
        user.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(true);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(new ArrayList<>());
        user.setStatus(true);
        user.setTokens(new ArrayList<>());
        user.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUserId(1);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        UsersRepository repository = mock(UsersRepository.class);
        when(repository.findAllByEmail(Mockito.<String>any())).thenReturn(userList);

        User user2 = new User();
        user2.setAttempts(1);
        user2.setAuthorities(new ArrayList<>());
        user2.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setDeleted(true);
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setRole(new ArrayList<>());
        user2.setStatus(true);
        user2.setTokens(new ArrayList<>());
        user2.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setUserId(1);
        ResetPsw resetPsw = mock(ResetPsw.class);
        when(resetPsw.isExpired()).thenReturn(true);
        doNothing().when(resetPsw).setExpireAt(Mockito.<LocalDateTime>any());
        doNothing().when(resetPsw).setExpired(anyBoolean());
        doNothing().when(resetPsw).setIdReset(Mockito.<Long>any());
        doNothing().when(resetPsw).setResetToken(Mockito.<String>any());
        doNothing().when(resetPsw).setTimeStamp(Mockito.<Timestamp>any());
        doNothing().when(resetPsw).setUser(Mockito.<User>any());
        resetPsw.setExpireAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        resetPsw.setExpired(true);
        resetPsw.setIdReset(1L);
        resetPsw.setResetToken("ABC123");
        resetPsw.setTimeStamp(mock(Timestamp.class));
        resetPsw.setUser(user2);
        ResetPswRepository resetPswRepository = mock(ResetPswRepository.class);
        when(resetPswRepository.findResetPswByResetToken(Mockito.<String>any())).thenReturn(resetPsw);
        JwtService jwtService = mock(JwtService.class);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        TokenRepository tokenRepository = mock(TokenRepository.class);

        // Act
        ResponseEntity<?> actualResetPasswordResult = (new UsersController(new AuthenticationService(repository,
                tokenRepository, resetPswRepository, new BCryptPasswordEncoder(), jwtService, authenticationManager)))
                .resetPassword(null, "ABC123");

        // Assert
        verify(resetPsw).isExpired();
        verify(resetPsw).setExpireAt(isA(LocalDateTime.class));
        verify(resetPsw).setExpired(eq(true));
        verify(resetPsw).setIdReset(eq(1L));
        verify(resetPsw).setResetToken(eq("ABC123"));
        verify(resetPsw).setTimeStamp(isA(Timestamp.class));
        verify(resetPsw).setUser(isA(User.class));
        verify(resetPswRepository).findResetPswByResetToken(eq("ABC123"));
        verify(repository).findAllByEmail(eq("janedoe"));
        verify(jwtService).extractUsername(eq("ABC123"));
        assertEquals("Token expired", actualResetPasswordResult.getBody());
        assertEquals(403, actualResetPasswordResult.getStatusCodeValue());
        assertTrue(actualResetPasswordResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link UsersController#confirmUserAccount(String)}
     */
    @Test
    void testConfirmUserAccount() throws Exception {
        // Arrange
        Mockito.<ResponseEntity<?>>when(authenticationService.confirmEmail(Mockito.<String>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/confirm-account")
                .param("token", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link UsersController#getUsersFalse()}
     */
    @Test
    void testGetUsersFalse() throws Exception {
        // Arrange
        when(authenticationService.findAll(anyBoolean())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getUsersFalse");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UsersController#getUsersFalse()}
     */
    @Test
    void testGetUsersFalse2() throws Exception {
        // Arrange
        User user = new User();
        user.setAttempts(1);
        user.setAuthorities(new ArrayList<>());
        user.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(true);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(new ArrayList<>());
        user.setStatus(true);
        user.setTokens(new ArrayList<>());
        user.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUserId(1);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(authenticationService.findAll(anyBoolean())).thenReturn(userList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getUsersFalse");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"userId\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou"
                                        + "\",\"status\":true,\"attempts\":1,\"creationDate\":0,\"updateDate\":0,\"role\":[],\"deleted\":true,\"tokens\":[],"
                                        + "\"authorities\":[],\"enabled\":true,\"username\":\"jane.doe@example.org\",\"credentialsNonExpired\":true,"
                                        + "\"accountNonExpired\":true,\"accountNonLocked\":true}]"));
    }

    /**
     * Method under test: {@link UsersController#getUsersFalse()}
     */
    @Test
    void testGetUsersFalse3() throws Exception {
        // Arrange
        User user = new User();
        user.setAttempts(1);
        user.setAuthorities(new ArrayList<>());
        user.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(true);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(new ArrayList<>());
        user.setStatus(true);
        user.setTokens(new ArrayList<>());
        user.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUserId(1);

        User user2 = new User();
        user2.setAttempts(3);
        user2.setAuthorities(new ArrayList<>());
        user2.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setDeleted(false);
        user2.setEmail("john.smith@example.org");
        user2.setEnabled(false);
        user2.setFirstName("John");
        user2.setLastName("Smith");
        user2.setPassword("Password");
        user2.setRole(new ArrayList<>());
        user2.setStatus(false);
        user2.setTokens(new ArrayList<>());
        user2.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setUserId(2);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user);
        when(authenticationService.findAll(anyBoolean())).thenReturn(userList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getUsersFalse");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"userId\":2,\"firstName\":\"John\",\"lastName\":\"Smith\",\"email\":\"john.smith@example.org\",\"password\":\"Password"
                                        + "\",\"status\":false,\"attempts\":3,\"creationDate\":0,\"updateDate\":0,\"role\":[],\"deleted\":false,\"tokens\":[],"
                                        + "\"authorities\":[],\"enabled\":false,\"username\":\"john.smith@example.org\",\"credentialsNonExpired\":true,"
                                        + "\"accountNonExpired\":true,\"accountNonLocked\":false},{\"userId\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\","
                                        + "\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"status\":true,\"attempts\":1,\"creationDate\":0,"
                                        + "\"updateDate\":0,\"role\":[],\"deleted\":true,\"tokens\":[],\"authorities\":[],\"enabled\":true,\"username\":\"jane"
                                        + ".doe@example.org\",\"credentialsNonExpired\":true,\"accountNonExpired\":true,\"accountNonLocked\":true}]"));
    }

    /**
     * Method under test: {@link UsersController#getUsersFalse()}
     */
    @Test
    void testGetUsersFalse4() throws Exception {
        // Arrange
        User user = new User();
        user.setAttempts(1);
        user.setAuthorities(new ArrayList<>());
        user.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(true);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(new ArrayList<>());
        user.setStatus(true);
        user.setTokens(new ArrayList<>());
        user.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUserId(1);

        Token token = new Token();
        token.setExpired(true);
        token.setId(1);
        token.setRevoked(true);
        token.setToken("ABC123");
        token.setTokenType(TokenType.BEARER);
        token.setUser(user);

        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(token);

        User user2 = new User();
        user2.setAttempts(1);
        user2.setAuthorities(new ArrayList<>());
        user2.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setDeleted(true);
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setRole(new ArrayList<>());
        user2.setStatus(true);
        user2.setTokens(tokens);
        user2.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setUserId(1);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        when(authenticationService.findAll(anyBoolean())).thenReturn(userList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getUsersFalse");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"userId\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou"
                                        + "\",\"status\":true,\"attempts\":1,\"creationDate\":0,\"updateDate\":0,\"role\":[],\"deleted\":true,\"tokens\":[{\"id"
                                        + "\":1,\"token\":\"ABC123\",\"tokenType\":\"BEARER\",\"expired\":true,\"revoked\":true,\"user\":{\"userId\":1,\"firstName"
                                        + "\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"status\":true,\"attempts"
                                        + "\":1,\"creationDate\":0,\"updateDate\":0,\"role\":[],\"deleted\":true,\"tokens\":[],\"authorities\":[],\"enabled\""
                                        + ":true,\"username\":\"jane.doe@example.org\",\"credentialsNonExpired\":true,\"accountNonExpired\":true,"
                                        + "\"accountNonLocked\":true}}],\"authorities\":[],\"enabled\":true,\"username\":\"jane.doe@example.org\","
                                        + "\"credentialsNonExpired\":true,\"accountNonExpired\":true,\"accountNonLocked\":true}]"));
    }

    /**
     * Method under test: {@link UsersController#getUsersTrue()}
     */
    @Test
    void testGetUsersTrue() throws Exception {
        // Arrange
        when(authenticationService.findAll(anyBoolean())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getUsersTrue");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UsersController#getUsersTrue()}
     */
    @Test
    void testGetUsersTrue2() throws Exception {
        // Arrange
        User user = new User();
        user.setAttempts(1);
        user.setAuthorities(new ArrayList<>());
        user.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(true);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(new ArrayList<>());
        user.setStatus(true);
        user.setTokens(new ArrayList<>());
        user.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUserId(1);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(authenticationService.findAll(anyBoolean())).thenReturn(userList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getUsersTrue");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"userId\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou"
                                        + "\",\"status\":true,\"attempts\":1,\"creationDate\":0,\"updateDate\":0,\"role\":[],\"deleted\":true,\"tokens\":[],"
                                        + "\"authorities\":[],\"enabled\":true,\"username\":\"jane.doe@example.org\",\"credentialsNonExpired\":true,"
                                        + "\"accountNonExpired\":true,\"accountNonLocked\":true}]"));
    }

    /**
     * Method under test: {@link UsersController#getUsersTrue()}
     */
    @Test
    void testGetUsersTrue3() throws Exception {
        // Arrange
        User user = new User();
        user.setAttempts(1);
        user.setAuthorities(new ArrayList<>());
        user.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(true);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(new ArrayList<>());
        user.setStatus(true);
        user.setTokens(new ArrayList<>());
        user.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUserId(1);

        User user2 = new User();
        user2.setAttempts(3);
        user2.setAuthorities(new ArrayList<>());
        user2.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setDeleted(false);
        user2.setEmail("john.smith@example.org");
        user2.setEnabled(false);
        user2.setFirstName("John");
        user2.setLastName("Smith");
        user2.setPassword("Password");
        user2.setRole(new ArrayList<>());
        user2.setStatus(false);
        user2.setTokens(new ArrayList<>());
        user2.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setUserId(2);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user);
        when(authenticationService.findAll(anyBoolean())).thenReturn(userList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getUsersTrue");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"userId\":2,\"firstName\":\"John\",\"lastName\":\"Smith\",\"email\":\"john.smith@example.org\",\"password\":\"Password"
                                        + "\",\"status\":false,\"attempts\":3,\"creationDate\":0,\"updateDate\":0,\"role\":[],\"deleted\":false,\"tokens\":[],"
                                        + "\"authorities\":[],\"enabled\":false,\"username\":\"john.smith@example.org\",\"credentialsNonExpired\":true,"
                                        + "\"accountNonExpired\":true,\"accountNonLocked\":false},{\"userId\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\","
                                        + "\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"status\":true,\"attempts\":1,\"creationDate\":0,"
                                        + "\"updateDate\":0,\"role\":[],\"deleted\":true,\"tokens\":[],\"authorities\":[],\"enabled\":true,\"username\":\"jane"
                                        + ".doe@example.org\",\"credentialsNonExpired\":true,\"accountNonExpired\":true,\"accountNonLocked\":true}]"));
    }

    /**
     * Method under test: {@link UsersController#getUsersTrue()}
     */
    @Test
    void testGetUsersTrue4() throws Exception {
        // Arrange
        User user = new User();
        user.setAttempts(1);
        user.setAuthorities(new ArrayList<>());
        user.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setDeleted(true);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(new ArrayList<>());
        user.setStatus(true);
        user.setTokens(new ArrayList<>());
        user.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUserId(1);

        Token token = new Token();
        token.setExpired(true);
        token.setId(1);
        token.setRevoked(true);
        token.setToken("ABC123");
        token.setTokenType(TokenType.BEARER);
        token.setUser(user);

        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(token);

        User user2 = new User();
        user2.setAttempts(1);
        user2.setAuthorities(new ArrayList<>());
        user2.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setDeleted(true);
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setRole(new ArrayList<>());
        user2.setStatus(true);
        user2.setTokens(tokens);
        user2.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setUserId(1);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        when(authenticationService.findAll(anyBoolean())).thenReturn(userList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getUsersTrue");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"userId\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou"
                                        + "\",\"status\":true,\"attempts\":1,\"creationDate\":0,\"updateDate\":0,\"role\":[],\"deleted\":true,\"tokens\":[{\"id"
                                        + "\":1,\"token\":\"ABC123\",\"tokenType\":\"BEARER\",\"expired\":true,\"revoked\":true,\"user\":{\"userId\":1,\"firstName"
                                        + "\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"status\":true,\"attempts"
                                        + "\":1,\"creationDate\":0,\"updateDate\":0,\"role\":[],\"deleted\":true,\"tokens\":[],\"authorities\":[],\"enabled\""
                                        + ":true,\"username\":\"jane.doe@example.org\",\"credentialsNonExpired\":true,\"accountNonExpired\":true,"
                                        + "\"accountNonLocked\":true}}],\"authorities\":[],\"enabled\":true,\"username\":\"jane.doe@example.org\","
                                        + "\"credentialsNonExpired\":true,\"accountNonExpired\":true,\"accountNonLocked\":true}]"));
    }

    /**
     * Method under test: {@link UsersController#sendMail()}
     */
    @Test
    void testSendMail() throws Exception {
        // Arrange
        doNothing().when(authenticationService).triggerMail(Mockito.<String>any(), Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/mail");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link UsersController#update(RegisterRequest, Integer, HttpServletRequest)}
     */
    @Test
    void testUpdate() throws Exception {
        // Arrange
        doNothing().when(authenticationService).updateUser(Mockito.<RegisterRequest>any(), Mockito.<Integer>any());

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("jane.doe@example.org");
        registerRequest.setFirstName("Jane");
        registerRequest.setLastName("Doe");
        registerRequest.setPassword("iloveyou");
        registerRequest.setRole(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(registerRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/update/{userId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
