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

@ContextConfiguration(classes = {AuthenticationResponse.class})
@ExtendWith(SpringExtension.class)
class AuthenticationResponseTest {
    @Autowired
    private AuthenticationResponse authenticationResponse;

    /**
     * Method under test: {@link AuthenticationResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse(authenticationResponse.canEqual("Other"));
        assertTrue(authenticationResponse.canEqual(authenticationResponse));
    }

    /**
     * Method under test: {@link AuthenticationResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        when(authenticationResponseBuilder.accessToken(Mockito.<String>any())).thenReturn(AuthenticationResponse.builder());
        AuthenticationResponse buildResult = authenticationResponseBuilder.accessToken("ABC123")
                .refreshToken("ABC123")
                .build();

        // Act
        boolean actualCanEqualResult = authenticationResponse.canEqual(buildResult);

        // Assert
        verify(authenticationResponseBuilder).accessToken(eq("ABC123"));
        assertTrue(actualCanEqualResult);
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AuthenticationResponse#equals(Object)}
     *   <li>{@link AuthenticationResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
        // Arrange
        AuthenticationResponse buildResult = AuthenticationResponse.builder()
                .accessToken("ABC123")
                .refreshToken("ABC123")
                .build();
        AuthenticationResponse buildResult2 = AuthenticationResponse.builder()
                .accessToken("ABC123")
                .refreshToken("ABC123")
                .build();

        // Act and Assert
        assertEquals(buildResult, buildResult2);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AuthenticationResponse#equals(Object)}
     *   <li>{@link AuthenticationResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
        // Arrange
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        when(authenticationResponseBuilder.accessToken(Mockito.<String>any())).thenReturn(AuthenticationResponse.builder());
        AuthenticationResponse buildResult = authenticationResponseBuilder.accessToken("ABC123")
                .refreshToken("ABC123")
                .build();
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder2 = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        when(authenticationResponseBuilder2.accessToken(Mockito.<String>any()))
                .thenReturn(AuthenticationResponse.builder());
        AuthenticationResponse buildResult2 = authenticationResponseBuilder2.accessToken("ABC123")
                .refreshToken("ABC123")
                .build();

        // Act and Assert
        assertEquals(buildResult, buildResult2);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AuthenticationResponse#equals(Object)}
     *   <li>{@link AuthenticationResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
        // Arrange
        AuthenticationResponse buildResult = AuthenticationResponse.builder()
                .accessToken("ABC123")
                .refreshToken("ABC123")
                .build();

        // Act and Assert
        assertEquals(buildResult, buildResult);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult.hashCode());
    }

    /**
     * Method under test: {@link AuthenticationResponse#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
        // Arrange
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        when(authenticationResponseBuilder.accessToken(Mockito.<String>any())).thenReturn(AuthenticationResponse.builder());
        AuthenticationResponse buildResult = authenticationResponseBuilder.accessToken("ABC123")
                .refreshToken("ABC123")
                .build();
        AuthenticationResponse buildResult2 = AuthenticationResponse.builder()
                .accessToken("ABC123")
                .refreshToken("ABC123")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link AuthenticationResponse#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
        // Arrange
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        when(authenticationResponseBuilder.refreshToken(Mockito.<String>any()))
                .thenReturn(AuthenticationResponse.builder());
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder2 = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        when(authenticationResponseBuilder2.accessToken(Mockito.<String>any())).thenReturn(authenticationResponseBuilder);
        AuthenticationResponse buildResult = authenticationResponseBuilder2.accessToken("ABC123")
                .refreshToken("ABC123")
                .build();
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder3 = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        when(authenticationResponseBuilder3.accessToken(Mockito.<String>any()))
                .thenReturn(AuthenticationResponse.builder());
        AuthenticationResponse buildResult2 = authenticationResponseBuilder3.accessToken("ABC123")
                .refreshToken("ABC123")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link AuthenticationResponse#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
        // Arrange
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        AuthenticationResponse buildResult = AuthenticationResponse.builder()
                .accessToken("ABC123")
                .refreshToken("ABC123")
                .build();
        when(authenticationResponseBuilder.build()).thenReturn(buildResult);
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder2 = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        when(authenticationResponseBuilder2.refreshToken(Mockito.<String>any())).thenReturn(authenticationResponseBuilder);
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder3 = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        when(authenticationResponseBuilder3.accessToken(Mockito.<String>any())).thenReturn(authenticationResponseBuilder2);
        AuthenticationResponse buildResult2 = authenticationResponseBuilder3.accessToken("ABC123")
                .refreshToken("ABC123")
                .build();
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder4 = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        when(authenticationResponseBuilder4.accessToken(Mockito.<String>any()))
                .thenReturn(AuthenticationResponse.builder());
        AuthenticationResponse buildResult3 = authenticationResponseBuilder4.accessToken("ABC123")
                .refreshToken("ABC123")
                .build();

        // Act and Assert
        assertNotEquals(buildResult2, buildResult3);
    }

    /**
     * Method under test: {@link AuthenticationResponse#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
        // Arrange
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        AuthenticationResponse buildResult = AuthenticationResponse.builder()
                .accessToken(null)
                .refreshToken("Refresh Token")
                .build();
        when(authenticationResponseBuilder.build()).thenReturn(buildResult);
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder2 = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        when(authenticationResponseBuilder2.refreshToken(Mockito.<String>any())).thenReturn(authenticationResponseBuilder);
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder3 = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        when(authenticationResponseBuilder3.accessToken(Mockito.<String>any())).thenReturn(authenticationResponseBuilder2);
        AuthenticationResponse buildResult2 = authenticationResponseBuilder3.accessToken("ABC123")
                .refreshToken("ABC123")
                .build();
        AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder4 = mock(
                AuthenticationResponse.AuthenticationResponseBuilder.class);
        when(authenticationResponseBuilder4.accessToken(Mockito.<String>any()))
                .thenReturn(AuthenticationResponse.builder());
        AuthenticationResponse buildResult3 = authenticationResponseBuilder4.accessToken("ABC123")
                .refreshToken("ABC123")
                .build();

        // Act and Assert
        assertNotEquals(buildResult2, buildResult3);
    }

    /**
     * Method under test: {@link AuthenticationResponse#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsNull_thenReturnNotEqual() {
        // Arrange
        AuthenticationResponse buildResult = AuthenticationResponse.builder()
                .accessToken("ABC123")
                .refreshToken("ABC123")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, null);
    }

    /**
     * Method under test: {@link AuthenticationResponse#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
        // Arrange
        AuthenticationResponse buildResult = AuthenticationResponse.builder()
                .accessToken("ABC123")
                .refreshToken("ABC123")
                .build();

        // Act and Assert
        assertNotEquals(buildResult, "Different type to AuthenticationResponse");
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AuthenticationResponse#AuthenticationResponse()}
     *   <li>{@link AuthenticationResponse#setAccessToken(String)}
     *   <li>{@link AuthenticationResponse#setRefreshToken(String)}
     *   <li>{@link AuthenticationResponse#toString()}
     *   <li>{@link AuthenticationResponse#getAccessToken()}
     *   <li>{@link AuthenticationResponse#getRefreshToken()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        AuthenticationResponse actualAuthenticationResponse = new AuthenticationResponse();
        actualAuthenticationResponse.setAccessToken("ABC123");
        actualAuthenticationResponse.setRefreshToken("ABC123");
        String actualToStringResult = actualAuthenticationResponse.toString();
        String actualAccessToken = actualAuthenticationResponse.getAccessToken();

        // Assert that nothing has changed
        assertEquals("ABC123", actualAccessToken);
        assertEquals("ABC123", actualAuthenticationResponse.getRefreshToken());
        assertEquals("AuthenticationResponse(accessToken=ABC123, refreshToken=ABC123)", actualToStringResult);
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AuthenticationResponse#AuthenticationResponse(String, String)}
     *   <li>{@link AuthenticationResponse#setAccessToken(String)}
     *   <li>{@link AuthenticationResponse#setRefreshToken(String)}
     *   <li>{@link AuthenticationResponse#toString()}
     *   <li>{@link AuthenticationResponse#getAccessToken()}
     *   <li>{@link AuthenticationResponse#getRefreshToken()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        AuthenticationResponse actualAuthenticationResponse = new AuthenticationResponse("ABC123", "ABC123");
        actualAuthenticationResponse.setAccessToken("ABC123");
        actualAuthenticationResponse.setRefreshToken("ABC123");
        String actualToStringResult = actualAuthenticationResponse.toString();
        String actualAccessToken = actualAuthenticationResponse.getAccessToken();

        // Assert that nothing has changed
        assertEquals("ABC123", actualAccessToken);
        assertEquals("ABC123", actualAuthenticationResponse.getRefreshToken());
        assertEquals("AuthenticationResponse(accessToken=ABC123, refreshToken=ABC123)", actualToStringResult);
    }
}
