package com.example.authenticator.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationRequest.class})
@ExtendWith(SpringExtension.class)
class AuthenticationRequestTest {
    @Autowired
    private AuthenticationRequest authenticationRequest;

    /**
     * Method under test: {@link AuthenticationRequest#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse(authenticationRequest.canEqual("Other"));
        assertTrue(authenticationRequest.canEqual(authenticationRequest));
    }

    /**
     * Method under test: {@link AuthenticationRequest#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        when(authenticationRequestBuilder.email(Mockito.<String>any())).thenReturn(AuthenticationRequest.builder());
        AuthenticationRequest buildResult = authenticationRequestBuilder.email("jane.doe@example.org")
                .password("iloveyou")
                .build();

        // Act
        boolean actualCanEqualResult = authenticationRequest.canEqual(buildResult);

        // Assert
        verify(authenticationRequestBuilder).email(eq("jane.doe@example.org"));
        assertTrue(actualCanEqualResult);
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AuthenticationRequest#equals(Object)}
     *   <li>{@link AuthenticationRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
        // Arrange
        AuthenticationRequest buildResult = AuthenticationRequest.builder()
                .email("jane.doe@example.org")
                .password("iloveyou")
                .build();
        AuthenticationRequest buildResult2 = AuthenticationRequest.builder()
                .email("jane.doe@example.org")
                .password("iloveyou")
                .build();

        // Act and Assert
        assertEquals(buildResult, buildResult2);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AuthenticationRequest#equals(Object)}
     *   <li>{@link AuthenticationRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
        // Arrange
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        when(authenticationRequestBuilder.email(Mockito.<String>any())).thenReturn(AuthenticationRequest.builder());
        AuthenticationRequest buildResult = authenticationRequestBuilder.email("jane.doe@example.org")
                .password("iloveyou")
                .build();
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder2 = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        when(authenticationRequestBuilder2.email(Mockito.<String>any())).thenReturn(AuthenticationRequest.builder());
        AuthenticationRequest buildResult2 = authenticationRequestBuilder2.email("jane.doe@example.org")
                .password("iloveyou")
                .build();

        // Act and Assert
        assertEquals(buildResult, buildResult2);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AuthenticationRequest#equals(Object)}
     *   <li>{@link AuthenticationRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
        // Arrange
        AuthenticationRequest buildResult = AuthenticationRequest.builder()
                .email("jane.doe@example.org")
                .password("iloveyou")
                .build();

        // Act and Assert
        assertEquals(buildResult, buildResult);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult.hashCode());
    }

    /**
     * Method under test: {@link AuthenticationRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
        // Arrange
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        when(authenticationRequestBuilder.email(Mockito.<String>any())).thenReturn(AuthenticationRequest.builder());
        AuthenticationRequest buildResult = authenticationRequestBuilder.email("jane.doe@example.org")
                .password("iloveyou")
                .build();
        AuthenticationRequest buildResult2 = AuthenticationRequest.builder()
                .email("jane.doe@example.org")
                .password("iloveyou")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link AuthenticationRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
        // Arrange
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        when(authenticationRequestBuilder.password(Mockito.<String>any())).thenReturn(AuthenticationRequest.builder());
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder2 = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        when(authenticationRequestBuilder2.email(Mockito.<String>any())).thenReturn(authenticationRequestBuilder);
        AuthenticationRequest buildResult = authenticationRequestBuilder2.email("jane.doe@example.org")
                .password("iloveyou")
                .build();
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder3 = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        when(authenticationRequestBuilder3.email(Mockito.<String>any())).thenReturn(AuthenticationRequest.builder());
        AuthenticationRequest buildResult2 = authenticationRequestBuilder3.email("jane.doe@example.org")
                .password("iloveyou")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link AuthenticationRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
        // Arrange
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        AuthenticationRequest buildResult = AuthenticationRequest.builder()
                .email("jane.doe@example.org")
                .password("iloveyou")
                .build();
        when(authenticationRequestBuilder.build()).thenReturn(buildResult);
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder2 = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        when(authenticationRequestBuilder2.password(Mockito.<String>any())).thenReturn(authenticationRequestBuilder);
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder3 = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        when(authenticationRequestBuilder3.email(Mockito.<String>any())).thenReturn(authenticationRequestBuilder2);
        AuthenticationRequest buildResult2 = authenticationRequestBuilder3.email("jane.doe@example.org")
                .password("iloveyou")
                .build();
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder4 = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        when(authenticationRequestBuilder4.email(Mockito.<String>any())).thenReturn(AuthenticationRequest.builder());
        AuthenticationRequest buildResult3 = authenticationRequestBuilder4.email("jane.doe@example.org")
                .password("iloveyou")
                .build();

        // Act and Assert
        assertNotEquals(buildResult2, buildResult3);
    }

    /**
     * Method under test: {@link AuthenticationRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
        // Arrange
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        AuthenticationRequest buildResult = AuthenticationRequest.builder().email(null).password("Password").build();
        when(authenticationRequestBuilder.build()).thenReturn(buildResult);
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder2 = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        when(authenticationRequestBuilder2.password(Mockito.<String>any())).thenReturn(authenticationRequestBuilder);
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder3 = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        when(authenticationRequestBuilder3.email(Mockito.<String>any())).thenReturn(authenticationRequestBuilder2);
        AuthenticationRequest buildResult2 = authenticationRequestBuilder3.email("jane.doe@example.org")
                .password("iloveyou")
                .build();
        AuthenticationRequest.AuthenticationRequestBuilder authenticationRequestBuilder4 = mock(
                AuthenticationRequest.AuthenticationRequestBuilder.class);
        when(authenticationRequestBuilder4.email(Mockito.<String>any())).thenReturn(AuthenticationRequest.builder());
        AuthenticationRequest buildResult3 = authenticationRequestBuilder4.email("jane.doe@example.org")
                .password("iloveyou")
                .build();

        // Act and Assert
        assertNotEquals(buildResult2, buildResult3);
    }

    /**
     * Method under test: {@link AuthenticationRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsNull_thenReturnNotEqual() {
        // Arrange
        AuthenticationRequest buildResult = AuthenticationRequest.builder()
                .email("jane.doe@example.org")
                .password("iloveyou")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, null);
    }

    /**
     * Method under test: {@link AuthenticationRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
        // Arrange
        AuthenticationRequest buildResult = AuthenticationRequest.builder()
                .email("jane.doe@example.org")
                .password("iloveyou")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, "Different type to AuthenticationRequest");
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AuthenticationRequest#AuthenticationRequest()}
     *   <li>{@link AuthenticationRequest#setEmail(String)}
     *   <li>{@link AuthenticationRequest#setPassword(String)}
     *   <li>{@link AuthenticationRequest#toString()}
     *   <li>{@link AuthenticationRequest#getEmail()}
     *   <li>{@link AuthenticationRequest#getPassword()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        AuthenticationRequest actualAuthenticationRequest = new AuthenticationRequest();
        actualAuthenticationRequest.setEmail("jane.doe@example.org");
        actualAuthenticationRequest.setPassword("iloveyou");
        String actualToStringResult = actualAuthenticationRequest.toString();
        String actualEmail = actualAuthenticationRequest.getEmail();

        // Assert that nothing has changed
        assertEquals("AuthenticationRequest(email=jane.doe@example.org, password=iloveyou)", actualToStringResult);
        assertEquals("iloveyou", actualAuthenticationRequest.getPassword());
        assertEquals("jane.doe@example.org", actualEmail);
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AuthenticationRequest#AuthenticationRequest(String, String)}
     *   <li>{@link AuthenticationRequest#setEmail(String)}
     *   <li>{@link AuthenticationRequest#setPassword(String)}
     *   <li>{@link AuthenticationRequest#toString()}
     *   <li>{@link AuthenticationRequest#getEmail()}
     *   <li>{@link AuthenticationRequest#getPassword()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        AuthenticationRequest actualAuthenticationRequest = new AuthenticationRequest("jane.doe@example.org", "iloveyou");
        actualAuthenticationRequest.setEmail("jane.doe@example.org");
        actualAuthenticationRequest.setPassword("iloveyou");
        String actualToStringResult = actualAuthenticationRequest.toString();
        String actualEmail = actualAuthenticationRequest.getEmail();

        // Assert that nothing has changed
        assertEquals("AuthenticationRequest(email=jane.doe@example.org, password=iloveyou)", actualToStringResult);
        assertEquals("iloveyou", actualAuthenticationRequest.getPassword());
        assertEquals("jane.doe@example.org", actualEmail);
    }
}
