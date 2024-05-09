package com.example.authenticator.dto;

import com.example.authenticator.common.RoleName;
import com.example.authenticator.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {UserDtoTest.class})
@ExtendWith(SpringExtension.class)
class UserDtoTest {

    @Test
    void testBuilder() {
        // Create a Role instance
        Role role = new Role();
        role.setRole(RoleName.ADMIN); // or RoleName.USER, etc.

        // Act
        UserDto userDto = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .role(Collections.singletonList(role))
                .build();

        // Assert
        assertNotNull(userDto);
        assertEquals("John", userDto.getFirstName());
        assertEquals("Doe", userDto.getLastName());
        assertEquals("john.doe@example.com", userDto.getEmail());
        assertEquals(Collections.singletonList(role), userDto.getRole());
    }

    @Test
    void testNoArgsConstructor() {
        // Act
        UserDto userDto = new UserDto();

        // Assert
        assertNotNull(userDto);
        assertNull(userDto.getFirstName());
        assertNull(userDto.getLastName());
        assertNull(userDto.getEmail());
        assertNull(userDto.getRole());
    }

    @Test
    void testAllArgsConstructor() {
        // Create a Role instance
        Role role = new Role();
        role.setRole(RoleName.ADMIN); // or RoleName.USER, etc.

        // Act
        UserDto userDto = new UserDto("John", "Doe", "john.doe@example.com", Collections.singletonList(role));

        // Assert
        assertNotNull(userDto);
        assertEquals("John", userDto.getFirstName());
        assertEquals("Doe", userDto.getLastName());
        assertEquals("john.doe@example.com", userDto.getEmail());
        assertEquals(Collections.singletonList(role), userDto.getRole());
    }

    @Test
    void testEqualsAndHashCode() {
        // Create a Role instance
        Role role = new Role();
        role.setRole(RoleName.ADMIN); // or RoleName.USER, etc.

        // Arrange
        UserDto userDto1 = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .role(Collections.singletonList(role))
                .build();

        UserDto userDto2 = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .role(Collections.singletonList(role))
                .build();

        // Act and Assert
        assertEquals(userDto1, userDto2);
        assertEquals(userDto1.hashCode(), userDto2.hashCode());
    }

    @Test
    void testToString() {
        // Create a Role instance
        Role role = new Role();
        role.setRole(RoleName.ADMIN); // or RoleName.USER, etc.

        // Act
        UserDto userDto = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .role(Collections.singletonList(role))
                .build();

        // Assert
        assertNotNull(userDto.toString());
    }


}
