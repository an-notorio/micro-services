package com.example.authenticator.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.authenticator.model.Role;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RegisterRequestTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link RegisterRequest#equals(Object)}
     *   <li>{@link RegisterRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder passwordResult = RegisterRequest.builder()
                .email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder passwordResult2 = RegisterRequest.builder()
                .email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertEquals(buildResult, buildResult2);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link RegisterRequest#equals(Object)}
     *   <li>{@link RegisterRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder.lastName(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder2 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder2.firstName(Mockito.<String>any())).thenReturn(registerRequestBuilder);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder3 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder3.email(Mockito.<String>any())).thenReturn(registerRequestBuilder2);
        RegisterRequest.RegisterRequestBuilder passwordResult = registerRequestBuilder3.email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder4 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder4.email(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder passwordResult2 = registerRequestBuilder4.email("jane.doe@example.org")
                .firstName(null)
                .lastName(null)
                .password("iloveyou");
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertEquals(buildResult, buildResult2);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link RegisterRequest#equals(Object)}
     *   <li>{@link RegisterRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual3() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder.password(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder2 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder2.lastName(Mockito.<String>any())).thenReturn(registerRequestBuilder);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder3 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder3.firstName(Mockito.<String>any())).thenReturn(registerRequestBuilder2);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder4 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder4.email(Mockito.<String>any())).thenReturn(registerRequestBuilder3);
        RegisterRequest.RegisterRequestBuilder passwordResult = registerRequestBuilder4.email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder5 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder5.email(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder passwordResult2 = registerRequestBuilder5.email("jane.doe@example.org")
                .firstName(null)
                .lastName(null)
                .password(null);
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertEquals(buildResult, buildResult2);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link RegisterRequest#equals(Object)}
     *   <li>{@link RegisterRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder passwordResult = RegisterRequest.builder()
                .email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();

        // Act and Assert
        assertEquals(buildResult, buildResult);
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult.hashCode());
    }

    /**
     * Method under test: {@link RegisterRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder.email(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder passwordResult = registerRequestBuilder.email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder passwordResult2 = RegisterRequest.builder()
                .email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegisterRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder.firstName(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder2 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder2.email(Mockito.<String>any())).thenReturn(registerRequestBuilder);
        RegisterRequest.RegisterRequestBuilder passwordResult = registerRequestBuilder2.email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder passwordResult2 = RegisterRequest.builder()
                .email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegisterRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder.firstName(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder2 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder2.email(Mockito.<String>any())).thenReturn(registerRequestBuilder);
        RegisterRequest.RegisterRequestBuilder passwordResult = registerRequestBuilder2.email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder passwordResult2 = RegisterRequest.builder()
                .email("jane.doe@example.org")
                .firstName(null)
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegisterRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder.lastName(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder2 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder2.firstName(Mockito.<String>any())).thenReturn(registerRequestBuilder);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder3 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder3.email(Mockito.<String>any())).thenReturn(registerRequestBuilder2);
        RegisterRequest.RegisterRequestBuilder passwordResult = registerRequestBuilder3.email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder passwordResult2 = RegisterRequest.builder()
                .email("jane.doe@example.org")
                .firstName(null)
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegisterRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder.lastName(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder2 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder2.firstName(Mockito.<String>any())).thenReturn(registerRequestBuilder);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder3 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder3.email(Mockito.<String>any())).thenReturn(registerRequestBuilder2);
        RegisterRequest.RegisterRequestBuilder passwordResult = registerRequestBuilder3.email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder passwordResult2 = RegisterRequest.builder()
                .email("jane.doe@example.org")
                .firstName(null)
                .lastName(null)
                .password("iloveyou");
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegisterRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder builderResult = RegisterRequest.builder();
        builderResult.firstName("Jane");
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder.lastName(Mockito.<String>any())).thenReturn(builderResult);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder2 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder2.firstName(Mockito.<String>any())).thenReturn(registerRequestBuilder);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder3 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder3.email(Mockito.<String>any())).thenReturn(registerRequestBuilder2);
        RegisterRequest.RegisterRequestBuilder passwordResult = registerRequestBuilder3.email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder passwordResult2 = RegisterRequest.builder()
                .email("jane.doe@example.org")
                .firstName(null)
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegisterRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder.password(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder2 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder2.lastName(Mockito.<String>any())).thenReturn(registerRequestBuilder);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder3 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder3.firstName(Mockito.<String>any())).thenReturn(registerRequestBuilder2);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder4 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder4.email(Mockito.<String>any())).thenReturn(registerRequestBuilder3);
        RegisterRequest.RegisterRequestBuilder passwordResult = registerRequestBuilder4.email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder5 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder5.email(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder passwordResult2 = registerRequestBuilder5.email("jane.doe@example.org")
                .firstName(null)
                .lastName(null)
                .password("iloveyou");
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegisterRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual8() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder builderResult = RegisterRequest.builder();
        builderResult.lastName("Doe");
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder.password(Mockito.<String>any())).thenReturn(builderResult);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder2 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder2.lastName(Mockito.<String>any())).thenReturn(registerRequestBuilder);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder3 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder3.firstName(Mockito.<String>any())).thenReturn(registerRequestBuilder2);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder4 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder4.email(Mockito.<String>any())).thenReturn(registerRequestBuilder3);
        RegisterRequest.RegisterRequestBuilder passwordResult = registerRequestBuilder4.email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder5 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder5.email(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder passwordResult2 = registerRequestBuilder5.email("jane.doe@example.org")
                .firstName(null)
                .lastName(null)
                .password("iloveyou");
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegisterRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual9() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder builderResult = RegisterRequest.builder();
        builderResult.email("jane.doe@example.org");
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder.password(Mockito.<String>any())).thenReturn(builderResult);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder2 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder2.lastName(Mockito.<String>any())).thenReturn(registerRequestBuilder);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder3 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder3.firstName(Mockito.<String>any())).thenReturn(registerRequestBuilder2);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder4 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder4.email(Mockito.<String>any())).thenReturn(registerRequestBuilder3);
        RegisterRequest.RegisterRequestBuilder passwordResult = registerRequestBuilder4.email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder5 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder5.email(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder passwordResult2 = registerRequestBuilder5.email("jane.doe@example.org")
                .firstName(null)
                .lastName(null)
                .password("iloveyou");
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegisterRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual10() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder.role(Mockito.<List<Role>>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder2 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder2.password(Mockito.<String>any())).thenReturn(registerRequestBuilder);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder3 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder3.lastName(Mockito.<String>any())).thenReturn(registerRequestBuilder2);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder4 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder4.firstName(Mockito.<String>any())).thenReturn(registerRequestBuilder3);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder5 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder5.email(Mockito.<String>any())).thenReturn(registerRequestBuilder4);
        RegisterRequest.RegisterRequestBuilder passwordResult = registerRequestBuilder5.email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder6 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder6.email(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder passwordResult2 = registerRequestBuilder6.email("jane.doe@example.org")
                .firstName(null)
                .lastName(null)
                .password(null);
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegisterRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual11() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder builderResult = RegisterRequest.builder();
        builderResult.password("iloveyou");
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder.role(Mockito.<List<Role>>any())).thenReturn(builderResult);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder2 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder2.password(Mockito.<String>any())).thenReturn(registerRequestBuilder);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder3 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder3.lastName(Mockito.<String>any())).thenReturn(registerRequestBuilder2);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder4 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder4.firstName(Mockito.<String>any())).thenReturn(registerRequestBuilder3);
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder5 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder5.email(Mockito.<String>any())).thenReturn(registerRequestBuilder4);
        RegisterRequest.RegisterRequestBuilder passwordResult = registerRequestBuilder5.email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();
        RegisterRequest.RegisterRequestBuilder registerRequestBuilder6 = mock(RegisterRequest.RegisterRequestBuilder.class);
        when(registerRequestBuilder6.email(Mockito.<String>any())).thenReturn(RegisterRequest.builder());
        RegisterRequest.RegisterRequestBuilder passwordResult2 = registerRequestBuilder6.email("jane.doe@example.org")
                .firstName(null)
                .lastName(null)
                .password(null);
        RegisterRequest buildResult2 = passwordResult2.role(new ArrayList<>()).build();

        // Act and Assert
        assertNotEquals(buildResult, buildResult2);
    }

    /**
     * Method under test: {@link RegisterRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsNull_thenReturnNotEqual() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder passwordResult = RegisterRequest.builder()
                .email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();

        // Act and Assert
        assertNotEquals(buildResult, null);
    }

    /**
     * Method under test: {@link RegisterRequest#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
        // Arrange
        RegisterRequest.RegisterRequestBuilder passwordResult = RegisterRequest.builder()
                .email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe")
                .password("iloveyou");
        RegisterRequest buildResult = passwordResult.role(new ArrayList<>()).build();

        // Act and Assert
        assertNotEquals(buildResult, "Different type to RegisterRequest");
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link RegisterRequest#RegisterRequest()}
     *   <li>{@link RegisterRequest#setEmail(String)}
     *   <li>{@link RegisterRequest#setFirstName(String)}
     *   <li>{@link RegisterRequest#setLastName(String)}
     *   <li>{@link RegisterRequest#setPassword(String)}
     *   <li>{@link RegisterRequest#setRole(List)}
     *   <li>{@link RegisterRequest#toString()}
     *   <li>{@link RegisterRequest#getEmail()}
     *   <li>{@link RegisterRequest#getFirstName()}
     *   <li>{@link RegisterRequest#getLastName()}
     *   <li>{@link RegisterRequest#getPassword()}
     *   <li>{@link RegisterRequest#getRole()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        RegisterRequest actualRegisterRequest = new RegisterRequest();
        actualRegisterRequest.setEmail("jane.doe@example.org");
        actualRegisterRequest.setFirstName("Jane");
        actualRegisterRequest.setLastName("Doe");
        actualRegisterRequest.setPassword("iloveyou");
        ArrayList<Role> role = new ArrayList<>();
        actualRegisterRequest.setRole(role);
        String actualToStringResult = actualRegisterRequest.toString();
        String actualEmail = actualRegisterRequest.getEmail();
        String actualFirstName = actualRegisterRequest.getFirstName();
        String actualLastName = actualRegisterRequest.getLastName();
        String actualPassword = actualRegisterRequest.getPassword();

        // Assert that nothing has changed
        assertEquals("Doe", actualLastName);
        assertEquals("Jane", actualFirstName);
        assertEquals(
                "RegisterRequest(firstName=Jane, lastName=Doe, email=jane.doe@example.org, password=iloveyou," + " role=[])",
                actualToStringResult);
        assertEquals("iloveyou", actualPassword);
        assertEquals("jane.doe@example.org", actualEmail);
        assertSame(role, actualRegisterRequest.getRole());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>
     * {@link RegisterRequest#RegisterRequest(String, String, String, String, List)}
     *   <li>{@link RegisterRequest#setEmail(String)}
     *   <li>{@link RegisterRequest#setFirstName(String)}
     *   <li>{@link RegisterRequest#setLastName(String)}
     *   <li>{@link RegisterRequest#setPassword(String)}
     *   <li>{@link RegisterRequest#setRole(List)}
     *   <li>{@link RegisterRequest#toString()}
     *   <li>{@link RegisterRequest#getEmail()}
     *   <li>{@link RegisterRequest#getFirstName()}
     *   <li>{@link RegisterRequest#getLastName()}
     *   <li>{@link RegisterRequest#getPassword()}
     *   <li>{@link RegisterRequest#getRole()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<Role> role = new ArrayList<>();

        // Act
        RegisterRequest actualRegisterRequest = new RegisterRequest("Jane", "Doe", "jane.doe@example.org", "iloveyou",
                role);
        actualRegisterRequest.setEmail("jane.doe@example.org");
        actualRegisterRequest.setFirstName("Jane");
        actualRegisterRequest.setLastName("Doe");
        actualRegisterRequest.setPassword("iloveyou");
        ArrayList<Role> role2 = new ArrayList<>();
        actualRegisterRequest.setRole(role2);
        String actualToStringResult = actualRegisterRequest.toString();
        String actualEmail = actualRegisterRequest.getEmail();
        String actualFirstName = actualRegisterRequest.getFirstName();
        String actualLastName = actualRegisterRequest.getLastName();
        String actualPassword = actualRegisterRequest.getPassword();
        List<Role> actualRole = actualRegisterRequest.getRole();

        // Assert that nothing has changed
        assertEquals("Doe", actualLastName);
        assertEquals("Jane", actualFirstName);
        assertEquals(
                "RegisterRequest(firstName=Jane, lastName=Doe, email=jane.doe@example.org, password=iloveyou," + " role=[])",
                actualToStringResult);
        assertEquals("iloveyou", actualPassword);
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals(role, actualRole);
        assertSame(role2, actualRole);
    }
}
