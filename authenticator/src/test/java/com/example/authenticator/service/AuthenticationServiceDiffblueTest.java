package com.example.authenticator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.authenticator.common.TokenType;
import com.example.authenticator.dto.AuthenticationRequest;
import com.example.authenticator.dto.AuthenticationResponse;
import com.example.authenticator.dto.RegisterRequest;
import com.example.authenticator.dto.ResetPasswordDto;
import com.example.authenticator.dto.UserDto;
import com.example.authenticator.model.ConfirmationToken;
import com.example.authenticator.model.ResetPsw;
import com.example.authenticator.model.Role;
import com.example.authenticator.model.Token;
import com.example.authenticator.model.User;
import com.example.authenticator.repository.ConfirmationTokenRepository;
import com.example.authenticator.repository.ResetPswRepository;
import com.example.authenticator.repository.TokenRepository;
import com.example.authenticator.repository.UsersRepository;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletMapping;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationService.class})
@ExtendWith(SpringExtension.class)
class AuthenticationServiceDiffblueTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private ConfirmationTokenRepository confirmationTokenRepository;

    @MockBean
    private EmailSenderService emailSenderService;

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private ResetPswRepository resetPswRepository;

    @MockBean
    private TokenRepository tokenRepository;

    @MockBean
    private UsersRepository usersRepository;

    /**
     * Method under test: {@link AuthenticationService#register(RegisterRequest)}
     */
    @Test
    void testRegister() {
        // Arrange
        when(usersRepository.existsByEmailAndDeleted(Mockito.<String>any(), anyBoolean())).thenReturn(true);

        // Act
        ResponseEntity<?> actualRegisterResult = authenticationService.register(new RegisterRequest());

        // Assert
        verify(usersRepository).existsByEmailAndDeleted(isNull(), eq(false));
        assertEquals("Error: Email is already in use!", actualRegisterResult.getBody());
        assertEquals(400, actualRegisterResult.getStatusCodeValue());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link AuthenticationService#confirmEmail(String)}
     */
    @Test
    void testConfirmEmail() {
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
        User user2 = mock(User.class);
        when(user2.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(user2).setAttempts(Mockito.<Integer>any());
        doNothing().when(user2).setAuthorities(Mockito.<Collection<GrantedAuthority>>any());
        doNothing().when(user2).setCreationDate(Mockito.<Date>any());
        doNothing().when(user2).setDeleted(anyBoolean());
        doNothing().when(user2).setEmail(Mockito.<String>any());
        doNothing().when(user2).setEnabled(anyBoolean());
        doNothing().when(user2).setFirstName(Mockito.<String>any());
        doNothing().when(user2).setLastName(Mockito.<String>any());
        doNothing().when(user2).setPassword(Mockito.<String>any());
        doNothing().when(user2).setRole(Mockito.<List<Role>>any());
        doNothing().when(user2).setStatus(anyBoolean());
        doNothing().when(user2).setTokens(Mockito.<List<Token>>any());
        doNothing().when(user2).setUpdateDate(Mockito.<Date>any());
        doNothing().when(user2).setUserId(Mockito.<Integer>any());
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
        ConfirmationToken confirmationToken = mock(ConfirmationToken.class);
        when(confirmationToken.getUser()).thenReturn(user2);
        doNothing().when(confirmationToken).setConfirmationToken(Mockito.<String>any());
        doNothing().when(confirmationToken).setCreatedDate(Mockito.<Date>any());
        doNothing().when(confirmationToken).setTokenId(Mockito.<Long>any());
        doNothing().when(confirmationToken).setUser(Mockito.<User>any());
        confirmationToken.setConfirmationToken("ABC123");
        confirmationToken
                .setCreatedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        confirmationToken.setTokenId(1L);
        confirmationToken.setUser(user);
        when(confirmationTokenRepository.findByConfirmationToken(Mockito.<String>any())).thenReturn(confirmationToken);

        User user3 = new User();
        user3.setAttempts(1);
        user3.setAuthorities(new ArrayList<>());
        user3.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user3.setDeleted(true);
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setFirstName("Jane");
        user3.setLastName("Doe");
        user3.setPassword("iloveyou");
        user3.setRole(new ArrayList<>());
        user3.setStatus(true);
        user3.setTokens(new ArrayList<>());
        user3.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user3.setUserId(1);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user3);

        User user4 = new User();
        user4.setAttempts(1);
        user4.setAuthorities(new ArrayList<>());
        user4.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user4.setDeleted(true);
        user4.setEmail("jane.doe@example.org");
        user4.setEnabled(true);
        user4.setFirstName("Jane");
        user4.setLastName("Doe");
        user4.setPassword("iloveyou");
        user4.setRole(new ArrayList<>());
        user4.setStatus(true);
        user4.setTokens(new ArrayList<>());
        user4.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user4.setUserId(1);
        when(usersRepository.save(Mockito.<User>any())).thenReturn(user4);
        when(usersRepository.findAllByEmail(Mockito.<String>any())).thenReturn(userList);

        // Act
        ResponseEntity<?> actualConfirmEmailResult = authenticationService.confirmEmail("ABC123");

        // Assert
        verify(confirmationToken).getUser();
        verify(confirmationToken).setConfirmationToken(eq("ABC123"));
        verify(confirmationToken).setCreatedDate(isA(Date.class));
        verify(confirmationToken).setTokenId(eq(1L));
        verify(confirmationToken).setUser(isA(User.class));
        verify(user2).getEmail();
        verify(user2).setAttempts(eq(1));
        verify(user2).setAuthorities(isA(Collection.class));
        verify(user2).setCreationDate(isA(Date.class));
        verify(user2).setDeleted(eq(true));
        verify(user2).setEmail(eq("jane.doe@example.org"));
        verify(user2).setEnabled(eq(true));
        verify(user2).setFirstName(eq("Jane"));
        verify(user2).setLastName(eq("Doe"));
        verify(user2).setPassword(eq("iloveyou"));
        verify(user2).setRole(isA(List.class));
        verify(user2).setStatus(eq(true));
        verify(user2).setTokens(isA(List.class));
        verify(user2).setUpdateDate(isA(Date.class));
        verify(user2).setUserId(eq(1));
        verify(confirmationTokenRepository).findByConfirmationToken(eq("ABC123"));
        verify(usersRepository).findAllByEmail(eq("jane.doe@example.org"));
        verify(usersRepository).save(isA(User.class));
        assertEquals("Email verified successfully!", actualConfirmEmailResult.getBody());
        assertEquals(200, actualConfirmEmailResult.getStatusCodeValue());
        assertTrue(actualConfirmEmailResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate() throws AuthenticationException {
        // Arrange
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

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
        Optional<User> ofResult = Optional.of(user);

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
        when(usersRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(usersRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setAttempts(1);
        user3.setAuthorities(new ArrayList<>());
        user3.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user3.setDeleted(true);
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setFirstName("Jane");
        user3.setLastName("Doe");
        user3.setPassword("iloveyou");
        user3.setRole(new ArrayList<>());
        user3.setStatus(true);
        user3.setTokens(new ArrayList<>());
        user3.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user3.setUserId(1);

        Token token = new Token();
        token.setExpired(true);
        token.setId(1);
        token.setRevoked(true);
        token.setToken("ABC123");
        token.setTokenType(TokenType.BEARER);
        token.setUser(user3);
        when(tokenRepository.save(Mockito.<Token>any())).thenReturn(token);
        when(tokenRepository.findAllValidTokensByUser(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        when(jwtService.generateRefreshToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");

        // Act
        ResponseEntity<?> actualAuthenticateResult = authenticationService
                .authenticate(new AuthenticationRequest("jane.doe@example.org", "iloveyou"));

        // Assert
        verify(tokenRepository).findAllValidTokensByUser(eq(1));
        verify(usersRepository).findByEmail(eq("jane.doe@example.org"));
        verify(jwtService).generateRefreshToken(isA(UserDetails.class));
        verify(jwtService).generateToken(isA(UserDetails.class));
        verify(tokenRepository).save(isA(Token.class));
        verify(usersRepository).save(isA(User.class));
        verify(authenticationManager).authenticate(isA(Authentication.class));
        assertEquals("ABC123", ((AuthenticationResponse) actualAuthenticateResult.getBody()).getAccessToken());
        assertEquals("ABC123", ((AuthenticationResponse) actualAuthenticateResult.getBody()).getRefreshToken());
        assertEquals(200, actualAuthenticateResult.getStatusCodeValue());
        assertTrue(actualAuthenticateResult.hasBody());
        assertTrue(actualAuthenticateResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate2() throws AuthenticationException {
        // Arrange
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        User user = mock(User.class);
        when(user.getUserId()).thenReturn(1);
        when(user.isEnabled()).thenReturn(true);
        when(user.isStatus()).thenReturn(true);
        doNothing().when(user).setAttempts(Mockito.<Integer>any());
        doNothing().when(user).setAuthorities(Mockito.<Collection<GrantedAuthority>>any());
        doNothing().when(user).setCreationDate(Mockito.<Date>any());
        doNothing().when(user).setDeleted(anyBoolean());
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setEnabled(anyBoolean());
        doNothing().when(user).setFirstName(Mockito.<String>any());
        doNothing().when(user).setLastName(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<List<Role>>any());
        doNothing().when(user).setStatus(anyBoolean());
        doNothing().when(user).setTokens(Mockito.<List<Token>>any());
        doNothing().when(user).setUpdateDate(Mockito.<Date>any());
        doNothing().when(user).setUserId(Mockito.<Integer>any());
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
        Optional<User> ofResult = Optional.of(user);

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
        when(usersRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(usersRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setAttempts(1);
        user3.setAuthorities(new ArrayList<>());
        user3.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user3.setDeleted(true);
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setFirstName("Jane");
        user3.setLastName("Doe");
        user3.setPassword("iloveyou");
        user3.setRole(new ArrayList<>());
        user3.setStatus(true);
        user3.setTokens(new ArrayList<>());
        user3.setUpdateDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user3.setUserId(1);

        Token token = new Token();
        token.setExpired(true);
        token.setId(1);
        token.setRevoked(true);
        token.setToken("ABC123");
        token.setTokenType(TokenType.BEARER);
        token.setUser(user3);
        when(tokenRepository.save(Mockito.<Token>any())).thenReturn(token);
        when(tokenRepository.findAllValidTokensByUser(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        when(jwtService.generateRefreshToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");

        // Act
        ResponseEntity<?> actualAuthenticateResult = authenticationService
                .authenticate(new AuthenticationRequest("jane.doe@example.org", "iloveyou"));

        // Assert
        verify(user).getUserId();
        verify(user).isEnabled();
        verify(user).isStatus();
        verify(user, atLeast(1)).setAttempts(Mockito.<Integer>any());
        verify(user).setAuthorities(isA(Collection.class));
        verify(user).setCreationDate(isA(Date.class));
        verify(user).setDeleted(eq(true));
        verify(user).setEmail(eq("jane.doe@example.org"));
        verify(user).setEnabled(eq(true));
        verify(user).setFirstName(eq("Jane"));
        verify(user).setLastName(eq("Doe"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(isA(List.class));
        verify(user).setStatus(eq(true));
        verify(user).setTokens(isA(List.class));
        verify(user).setUpdateDate(isA(Date.class));
        verify(user).setUserId(eq(1));
        verify(tokenRepository).findAllValidTokensByUser(eq(1));
        verify(usersRepository).findByEmail(eq("jane.doe@example.org"));
        verify(jwtService).generateRefreshToken(isA(UserDetails.class));
        verify(jwtService).generateToken(isA(UserDetails.class));
        verify(tokenRepository).save(isA(Token.class));
        verify(usersRepository).save(isA(User.class));
        verify(authenticationManager).authenticate(isA(Authentication.class));
        assertEquals("ABC123", ((AuthenticationResponse) actualAuthenticateResult.getBody()).getAccessToken());
        assertEquals("ABC123", ((AuthenticationResponse) actualAuthenticateResult.getBody()).getRefreshToken());
        assertEquals(200, actualAuthenticateResult.getStatusCodeValue());
        assertTrue(actualAuthenticateResult.hasBody());
        assertTrue(actualAuthenticateResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate3() {
        // Arrange
        User user = mock(User.class);
        when(user.isEnabled()).thenReturn(false);
        when(user.isStatus()).thenReturn(true);
        when(user.getAttempts()).thenReturn(1);
        doNothing().when(user).setAttempts(Mockito.<Integer>any());
        doNothing().when(user).setAuthorities(Mockito.<Collection<GrantedAuthority>>any());
        doNothing().when(user).setCreationDate(Mockito.<Date>any());
        doNothing().when(user).setDeleted(anyBoolean());
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setEnabled(anyBoolean());
        doNothing().when(user).setFirstName(Mockito.<String>any());
        doNothing().when(user).setLastName(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<List<Role>>any());
        doNothing().when(user).setStatus(anyBoolean());
        doNothing().when(user).setTokens(Mockito.<List<Token>>any());
        doNothing().when(user).setUpdateDate(Mockito.<Date>any());
        doNothing().when(user).setUserId(Mockito.<Integer>any());
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
        Optional<User> ofResult = Optional.of(user);
        when(usersRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("jane.doe@example.org")
                .password("iloveyou")
                .build();

        // Act
        ResponseEntity<?> actualAuthenticateResult = authenticationService.authenticate(request);

        // Assert
        verify(user).getAttempts();
        verify(user).isEnabled();
        verify(user).isStatus();
        verify(user).setAttempts(eq(1));
        verify(user).setAuthorities(isA(Collection.class));
        verify(user).setCreationDate(isA(Date.class));
        verify(user).setDeleted(eq(true));
        verify(user).setEmail(eq("jane.doe@example.org"));
        verify(user).setEnabled(eq(true));
        verify(user).setFirstName(eq("Jane"));
        verify(user).setLastName(eq("Doe"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(isA(List.class));
        verify(user).setStatus(eq(true));
        verify(user).setTokens(isA(List.class));
        verify(user).setUpdateDate(isA(Date.class));
        verify(user).setUserId(eq(1));
        verify(usersRepository).findByEmail(eq("jane.doe@example.org"));
        assertEquals("User is disabled", actualAuthenticateResult.getBody());
        assertEquals(403, actualAuthenticateResult.getStatusCodeValue());
        assertTrue(actualAuthenticateResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate4() {
        // Arrange
        User user = mock(User.class);
        when(user.isStatus()).thenReturn(false);
        when(user.getAttempts()).thenReturn(1);
        doNothing().when(user).setAttempts(Mockito.<Integer>any());
        doNothing().when(user).setAuthorities(Mockito.<Collection<GrantedAuthority>>any());
        doNothing().when(user).setCreationDate(Mockito.<Date>any());
        doNothing().when(user).setDeleted(anyBoolean());
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setEnabled(anyBoolean());
        doNothing().when(user).setFirstName(Mockito.<String>any());
        doNothing().when(user).setLastName(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<List<Role>>any());
        doNothing().when(user).setStatus(anyBoolean());
        doNothing().when(user).setTokens(Mockito.<List<Token>>any());
        doNothing().when(user).setUpdateDate(Mockito.<Date>any());
        doNothing().when(user).setUserId(Mockito.<Integer>any());
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
        Optional<User> ofResult = Optional.of(user);
        when(usersRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("jane.doe@example.org")
                .password("iloveyou")
                .build();

        // Act
        ResponseEntity<?> actualAuthenticateResult = authenticationService.authenticate(request);

        // Assert
        verify(user).getAttempts();
        verify(user).isStatus();
        verify(user).setAttempts(eq(1));
        verify(user).setAuthorities(isA(Collection.class));
        verify(user).setCreationDate(isA(Date.class));
        verify(user).setDeleted(eq(true));
        verify(user).setEmail(eq("jane.doe@example.org"));
        verify(user).setEnabled(eq(true));
        verify(user).setFirstName(eq("Jane"));
        verify(user).setLastName(eq("Doe"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(isA(List.class));
        verify(user).setStatus(eq(true));
        verify(user).setTokens(isA(List.class));
        verify(user).setUpdateDate(isA(Date.class));
        verify(user).setUserId(eq(1));
        verify(usersRepository).findByEmail(eq("jane.doe@example.org"));
        assertEquals("User is disabled", actualAuthenticateResult.getBody());
        assertEquals(403, actualAuthenticateResult.getStatusCodeValue());
        assertTrue(actualAuthenticateResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link AuthenticationService#updateUser(RegisterRequest, Integer)}
     */
    @Test
    void testUpdateUser() {
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
        Optional<User> ofResult = Optional.of(user);

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
        when(usersRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(usersRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        authenticationService.updateUser(new RegisterRequest(), 1);

        // Assert
        verify(usersRepository).findById(eq(1));
        verify(usersRepository).save(isA(User.class));
    }

    /**
     * Method under test:
     * {@link AuthenticationService#updateUser(RegisterRequest, Integer)}
     */
    @Test
    void testUpdateUser2() {
        // Arrange
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getFirstName()).thenReturn("Jane");
        when(user.getLastName()).thenReturn("Doe");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn(new ArrayList<>());
        doNothing().when(user).setAttempts(Mockito.<Integer>any());
        doNothing().when(user).setAuthorities(Mockito.<Collection<GrantedAuthority>>any());
        doNothing().when(user).setCreationDate(Mockito.<Date>any());
        doNothing().when(user).setDeleted(anyBoolean());
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setEnabled(anyBoolean());
        doNothing().when(user).setFirstName(Mockito.<String>any());
        doNothing().when(user).setLastName(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<List<Role>>any());
        doNothing().when(user).setStatus(anyBoolean());
        doNothing().when(user).setTokens(Mockito.<List<Token>>any());
        doNothing().when(user).setUpdateDate(Mockito.<Date>any());
        doNothing().when(user).setUserId(Mockito.<Integer>any());
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
        Optional<User> ofResult = Optional.of(user);

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
        when(usersRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(usersRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        authenticationService.updateUser(new RegisterRequest(), 1);

        // Assert
        verify(user).getEmail();
        verify(user).getFirstName();
        verify(user).getLastName();
        verify(user).getPassword();
        verify(user).getRole();
        verify(user).setAttempts(eq(1));
        verify(user).setAuthorities(isA(Collection.class));
        verify(user).setCreationDate(isA(Date.class));
        verify(user).setDeleted(eq(true));
        verify(user).setEmail(eq("jane.doe@example.org"));
        verify(user).setEnabled(eq(true));
        verify(user).setFirstName(eq("Jane"));
        verify(user).setLastName(eq("Doe"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(isA(List.class));
        verify(user).setStatus(eq(true));
        verify(user).setTokens(isA(List.class));
        verify(user).setUpdateDate(isA(Date.class));
        verify(user).setUserId(eq(1));
        verify(usersRepository).findById(eq(1));
        verify(usersRepository).save(isA(User.class));
    }

    /**
     * Method under test:
     * {@link AuthenticationService#updateUser(RegisterRequest, Integer)}
     */
    @Test
    void testUpdateUser3() {
        // Arrange
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

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
        Optional<User> ofResult = Optional.of(user);

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
        when(usersRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(usersRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        RegisterRequest.RegisterRequestBuilder passwordResult = RegisterRequest.builder()
                .email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest request = passwordResult.role(new ArrayList<>()).build();

        // Act
        authenticationService.updateUser(request, 1);

        // Assert
        verify(usersRepository).findById(eq(1));
        verify(usersRepository).save(isA(User.class));
        verify(passwordEncoder).encode(isA(CharSequence.class));
    }

    /**
     * Method under test: {@link AuthenticationService#deleteUser(Integer)}
     */
    @Test
    void testDeleteUser() {
        // Arrange
        doNothing().when(usersRepository).deleteById(Mockito.<Integer>any());

        // Act
        authenticationService.deleteUser(1);

        // Assert that nothing has changed
        verify(usersRepository).deleteById(eq(1));
    }

    /**
     * Method under test: {@link AuthenticationService#getUser(Integer)}
     */
    @Test
    void testGetUser() {
        // Arrange
        User user = new User();
        user.setAttempts(1);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        user.setAuthorities(authorities);
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
        when(usersRepository.findByUserId(Mockito.<Integer>any())).thenReturn(user);

        // Act
        UserDto actualUser = authenticationService.getUser(1);

        // Assert
        verify(usersRepository).findByUserId(eq(1));
        assertEquals("Doe", actualUser.getLastName());
        assertEquals("Jane", actualUser.getFirstName());
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals(authorities, actualUser.getRole());
    }

    /**
     * Method under test: {@link AuthenticationService#getUser(Integer)}
     */
    @Test
    void testGetUser2() {
        // Arrange
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getFirstName()).thenReturn("Jane");
        when(user.getLastName()).thenReturn("Doe");
        when(user.getRole()).thenReturn(new ArrayList<>());
        doNothing().when(user).setAttempts(Mockito.<Integer>any());
        doNothing().when(user).setAuthorities(Mockito.<Collection<GrantedAuthority>>any());
        doNothing().when(user).setCreationDate(Mockito.<Date>any());
        doNothing().when(user).setDeleted(anyBoolean());
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setEnabled(anyBoolean());
        doNothing().when(user).setFirstName(Mockito.<String>any());
        doNothing().when(user).setLastName(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<List<Role>>any());
        doNothing().when(user).setStatus(anyBoolean());
        doNothing().when(user).setTokens(Mockito.<List<Token>>any());
        doNothing().when(user).setUpdateDate(Mockito.<Date>any());
        doNothing().when(user).setUserId(Mockito.<Integer>any());
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
        when(usersRepository.findByUserId(Mockito.<Integer>any())).thenReturn(user);

        // Act
        UserDto actualUser = authenticationService.getUser(1);

        // Assert
        verify(user).getEmail();
        verify(user).getFirstName();
        verify(user).getLastName();
        verify(user).getRole();
        verify(user).setAttempts(eq(1));
        verify(user).setAuthorities(isA(Collection.class));
        verify(user).setCreationDate(isA(Date.class));
        verify(user).setDeleted(eq(true));
        verify(user).setEmail(eq("jane.doe@example.org"));
        verify(user).setEnabled(eq(true));
        verify(user).setFirstName(eq("Jane"));
        verify(user).setLastName(eq("Doe"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(isA(List.class));
        verify(user).setStatus(eq(true));
        verify(user).setTokens(isA(List.class));
        verify(user).setUpdateDate(isA(Date.class));
        verify(user).setUserId(eq(1));
        verify(usersRepository).findByUserId(eq(1));
        assertEquals("Doe", actualUser.getLastName());
        assertEquals("Jane", actualUser.getFirstName());
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertTrue(actualUser.getRole().isEmpty());
    }

    /**
     * Method under test: {@link AuthenticationService#findAll(boolean)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAll() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.hibernate.Session.enableFilter(String)" because "session" is null
        //       at com.example.authenticator.service.AuthenticationService.findAll(AuthenticationService.java:164)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        UsersRepository repository = mock(UsersRepository.class);
        TokenRepository tokenRepository = mock(TokenRepository.class);
        ResetPswRepository resetPswRepository = mock(ResetPswRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtService jwtService = new JwtService();

        // Act
        (new AuthenticationService(repository, tokenRepository, resetPswRepository, passwordEncoder, jwtService,
                new ProviderManager(new ArrayList<>()))).findAll(true).iterator();
    }

    /**
     * Method under test: {@link AuthenticationService#triggerMail(String, String)}
     */
    @Test
    void testTriggerMail() throws MessagingException {
        // Arrange
        doNothing().when(emailSenderService)
                .sendSimpleEmail(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());

        // Act
        authenticationService.triggerMail("Mail", "ABC123");

        // Assert
        verify(emailSenderService).sendSimpleEmail(eq("Mail"), eq("Reset password"), eq(
                "For reset the password, please click on the link ABC123 \n If you not request a reset password, ignore this email"));
    }

    /**
     * Method under test: {@link AuthenticationService#forgotPassword(String)}
     */
    @Test
    void testForgotPassword() throws MessagingException {
        // Arrange
        doNothing().when(emailSenderService)
                .sendSimpleEmail(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());

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
        when(usersRepository.findAllByEmail(Mockito.<String>any())).thenReturn(userList);
        when(jwtService.generateTokenResetPsw(Mockito.<UserDetails>any())).thenReturn("ABC123");

        // Act
        authenticationService.forgotPassword("jane.doe@example.org");

        // Assert
        verify(usersRepository).findAllByEmail(eq("jane.doe@example.org"));
        verify(emailSenderService).sendSimpleEmail(eq("jane.doe@example.org"), eq("Reset password"), eq(
                "For reset the password, please click on the link http://localhost:8222/api/resetPassword?token=ABC123 \n If you not request a reset password, ignore this email"));
        verify(jwtService).generateTokenResetPsw(isA(UserDetails.class));
    }

    /**
     * Method under test:
     * {@link AuthenticationService#resetPassword(ResetPasswordDto, String)}
     */
    @Test
    void testResetPassword() {
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
        when(usersRepository.findAllByEmail(Mockito.<String>any())).thenReturn(userList);

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
        when(resetPswRepository.findResetPswByResetToken(Mockito.<String>any())).thenReturn(resetPsw);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");

        // Act
        ResponseEntity<?> actualResetPasswordResult = authenticationService.resetPassword(null, "ABC123");

        // Assert
        verify(resetPswRepository).findResetPswByResetToken(eq("ABC123"));
        verify(usersRepository).findAllByEmail(eq("janedoe"));
        verify(jwtService).extractUsername(eq("ABC123"));
        assertEquals(403, actualResetPasswordResult.getStatusCodeValue());
        assertTrue(actualResetPasswordResult.hasBody());
        assertTrue(actualResetPasswordResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link AuthenticationService#resetPassword(ResetPasswordDto, String)}
     */
    @Test
    void testResetPassword2() {
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
        when(usersRepository.findAllByEmail(Mockito.<String>any())).thenReturn(userList);

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
        when(resetPswRepository.findResetPswByResetToken(Mockito.<String>any())).thenReturn(resetPsw);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");

        // Act
        ResponseEntity<?> actualResetPasswordResult = authenticationService.resetPassword(null, "ABC123");

        // Assert
        verify(resetPsw).isExpired();
        verify(resetPsw).setExpireAt(isA(LocalDateTime.class));
        verify(resetPsw).setExpired(eq(true));
        verify(resetPsw).setIdReset(eq(1L));
        verify(resetPsw).setResetToken(eq("ABC123"));
        verify(resetPsw).setTimeStamp(isA(Timestamp.class));
        verify(resetPsw).setUser(isA(User.class));
        verify(resetPswRepository).findResetPswByResetToken(eq("ABC123"));
        verify(usersRepository).findAllByEmail(eq("janedoe"));
        verify(jwtService).extractUsername(eq("ABC123"));
        assertEquals("Token expired", actualResetPasswordResult.getBody());
        assertEquals(403, actualResetPasswordResult.getStatusCodeValue());
        assertTrue(actualResetPasswordResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link AuthenticationService#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken() throws IOException {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();

        // Act
        authenticationService.refreshToken(request, response);

        // Assert that nothing has changed
        HttpServletResponse response2 = response.getResponse();
        assertTrue(response2 instanceof ResponseFacade);
        assertTrue(request.getInputStream() instanceof DelegatingServletInputStream);
        assertTrue(request.getHttpServletMapping() instanceof MockHttpServletMapping);
        assertTrue(request.getSession() instanceof MockHttpSession);
        assertEquals("", request.getContextPath());
        assertEquals("", request.getMethod());
        assertEquals("", request.getRequestURI());
        assertEquals("", request.getServletPath());
        assertEquals("HTTP/1.1", request.getProtocol());
        assertEquals("http", request.getScheme());
        assertEquals("localhost", request.getLocalName());
        assertEquals("localhost", request.getRemoteHost());
        assertEquals("localhost", request.getServerName());
        assertEquals(80, request.getLocalPort());
        assertEquals(80, request.getRemotePort());
        assertEquals(80, request.getServerPort());
        assertEquals(DispatcherType.REQUEST, request.getDispatcherType());
        assertFalse(request.isAsyncStarted());
        assertFalse(request.isAsyncSupported());
        assertFalse(request.isRequestedSessionIdFromURL());
        assertTrue(request.isActive());
        assertTrue(request.isRequestedSessionIdFromCookie());
        assertTrue(request.isRequestedSessionIdValid());
        ServletOutputStream expectedOutputStream = response.getOutputStream();
        assertSame(expectedOutputStream, response2.getOutputStream());
    }

    /**
     * Method under test:
     * {@link AuthenticationService#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken2() throws IOException {
        // Arrange
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("https://example.org/example");

        // Act
        authenticationService.refreshToken(request, new Response());

        // Assert
        verify(request).getHeader(eq("Authorization"));
    }

    /**
     * Method under test:
     * {@link AuthenticationService#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken3() throws IOException {
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
        Optional<User> ofResult = Optional.of(user);
        when(usersRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(jwtService.isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(true);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");

        // Act
        authenticationService.refreshToken(request, new MockHttpServletResponse());

        // Assert
        verify(usersRepository).findByEmail(eq("janedoe"));
        verify(jwtService).extractUsername(eq(""));
        verify(jwtService).generateToken(isA(UserDetails.class));
        verify(jwtService).isTokenValid(eq(""), isA(UserDetails.class));
        verify(request).getHeader(eq("Authorization"));
    }

    /**
     * Method under test:
     * {@link AuthenticationService#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken4() throws IOException {
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
        Optional<User> ofResult = Optional.of(user);
        when(usersRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        when(jwtService.isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(false);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");

        // Act
        authenticationService.refreshToken(request, mock(HttpServletResponse.class));

        // Assert
        verify(usersRepository).findByEmail(eq("janedoe"));
        verify(jwtService).extractUsername(eq(""));
        verify(jwtService).isTokenValid(eq(""), isA(UserDetails.class));
        verify(request).getHeader(eq("Authorization"));
    }
}
