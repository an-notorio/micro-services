package com.example.authenticator.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {ResetPasswordDto.class})
@ExtendWith(SpringExtension.class)
class ResetPasswordDtoTest {

    @Autowired
    private ResetPasswordDto resetPasswordDto;


    @Test
    void passwordGetterReturnsCorrectValue() {
        // Arrange
        String expectedPassword = "myPassword";
        resetPasswordDto.setPassword(expectedPassword);

        // Act
        String actualPassword = resetPasswordDto.getPassword();

        // Assert
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void setPasswordUpdatesThePasswordField() {
        // Arrange
        String newPassword = "newPassword";

        // Act
        resetPasswordDto.setPassword(newPassword);

        // Assert
        assertEquals(newPassword, resetPasswordDto.getPassword());
    }

    @Test
    void testEquals() {
        // Arrange
        ResetPasswordDto dto1 = new ResetPasswordDto();
        dto1.setPassword("password");
        ResetPasswordDto dto2 = new ResetPasswordDto();
        dto2.setPassword("password");

        // Act and Assert
        assertEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        // Arrange
        ResetPasswordDto dto1 = new ResetPasswordDto();
        dto1.setPassword("password");
        ResetPasswordDto dto2 = new ResetPasswordDto();
        dto2.setPassword("password");

        // Act and Assert
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        ResetPasswordDto dto = new ResetPasswordDto();
        dto.setPassword("password");

        // Act
        String toString = dto.toString();

        // Assert
        assertNotNull(toString);
        assertTrue(toString.contains("password"));
    }

    @Test
    void builderReturnsValidInstance() {
        // Act
        ResetPasswordDto dto = ResetPasswordDto.builder()
                .password("myPassword")
                .repeatPassword("myPassword")
                .build();

        // Assert
        assertNotNull(dto);
        assertEquals("myPassword", dto.getPassword());
        assertEquals("myPassword", dto.getRepeatPassword());
    }

}
