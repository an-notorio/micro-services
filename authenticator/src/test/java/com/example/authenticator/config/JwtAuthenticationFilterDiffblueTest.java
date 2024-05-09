package com.example.authenticator.config;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.authenticator.common.TokenType;
import com.example.authenticator.model.Token;
import com.example.authenticator.model.User;
import com.example.authenticator.repository.TokenRepository;
import com.example.authenticator.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = {JwtAuthenticationFilter.class})
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
class JwtAuthenticationFilterDiffblueTest {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private TokenRepository tokenRepository;

    @MockBean
    private UserDetailsService userDetailsService;

    /**
     * Method under test:
     * {@link JwtAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal() throws ServletException, IOException {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
    }

    /**
     * Method under test:
     * {@link JwtAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal2() throws ServletException, IOException {
        // Arrange
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getHeader(eq("Authorization"));
    }

    /**
     * Method under test:
     * {@link JwtAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal3() throws ServletException, IOException, UsernameNotFoundException {
        // Arrange
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(new User());
        when(jwtService.isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(true);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");

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
        Optional<Token> ofResult = Optional.of(token);
        when(tokenRepository.findByToken(Mockito.<String>any())).thenReturn(ofResult);
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(tokenRepository).findByToken(eq(""));
        verify(jwtService).extractUsername(eq(""));
        verify(jwtService).isTokenValid(eq(""), isA(UserDetails.class));
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getHeader(eq("Authorization"));
        verify(userDetailsService).loadUserByUsername(eq("janedoe"));
    }

    /**
     * Method under test:
     * {@link JwtAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal4() throws ServletException, IOException, UsernameNotFoundException {
        // Arrange
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(new User());
        when(jwtService.isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(false);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");

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
        Optional<Token> ofResult = Optional.of(token);
        when(tokenRepository.findByToken(Mockito.<String>any())).thenReturn(ofResult);
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(tokenRepository).findByToken(eq(""));
        verify(jwtService).extractUsername(eq(""));
        verify(jwtService).isTokenValid(eq(""), isA(UserDetails.class));
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getHeader(eq("Authorization"));
        verify(userDetailsService).loadUserByUsername(eq("janedoe"));
    }

    /**
     * Method under test:
     * {@link JwtAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal5() throws ServletException, IOException {
        // Arrange
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn(null);
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(jwtService).extractUsername(eq(""));
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getHeader(eq("Authorization"));
    }

    /**
     * Method under test:
     * {@link JwtAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal6() throws ServletException, IOException, UsernameNotFoundException {
        // Arrange
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(new User());
        when(jwtService.isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(true);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");

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
        Token token = mock(Token.class);
        when(token.isExpired()).thenReturn(false);
        when(token.isRevoked()).thenReturn(true);
        doNothing().when(token).setExpired(anyBoolean());
        doNothing().when(token).setId(Mockito.<Integer>any());
        doNothing().when(token).setRevoked(anyBoolean());
        doNothing().when(token).setToken(Mockito.<String>any());
        doNothing().when(token).setTokenType(Mockito.<TokenType>any());
        doNothing().when(token).setUser(Mockito.<User>any());
        token.setExpired(true);
        token.setId(1);
        token.setRevoked(true);
        token.setToken("ABC123");
        token.setTokenType(TokenType.BEARER);
        token.setUser(user);
        Optional<Token> ofResult = Optional.of(token);
        when(tokenRepository.findByToken(Mockito.<String>any())).thenReturn(ofResult);
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(token).isExpired();
        verify(token).isRevoked();
        verify(token).setExpired(eq(true));
        verify(token).setId(eq(1));
        verify(token).setRevoked(eq(true));
        verify(token).setToken(eq("ABC123"));
        verify(token).setTokenType(eq(TokenType.BEARER));
        verify(token).setUser(isA(User.class));
        verify(tokenRepository).findByToken(eq(""));
        verify(jwtService).extractUsername(eq(""));
        verify(jwtService).isTokenValid(eq(""), isA(UserDetails.class));
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getHeader(eq("Authorization"));
        verify(userDetailsService).loadUserByUsername(eq("janedoe"));
    }

    /**
     * Method under test:
     * {@link JwtAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal7() throws ServletException, IOException, UsernameNotFoundException {
        // Arrange
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(new User());
        when(jwtService.isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(true);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        Optional<Token> emptyResult = Optional.empty();
        when(tokenRepository.findByToken(Mockito.<String>any())).thenReturn(emptyResult);
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(tokenRepository).findByToken(eq(""));
        verify(jwtService).extractUsername(eq(""));
        verify(jwtService).isTokenValid(eq(""), isA(UserDetails.class));
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getHeader(eq("Authorization"));
        verify(userDetailsService).loadUserByUsername(eq("janedoe"));
    }
}
