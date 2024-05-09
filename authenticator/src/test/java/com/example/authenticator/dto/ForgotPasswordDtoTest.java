package com.example.authenticator.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {ForgotPasswordDto.class})
@ExtendWith(SpringExtension.class)
class ForgotPasswordDtoTest {
    @Autowired
    private ForgotPasswordDto forgotPasswordDto;

    /**
     * Method under test: {@link ForgotPasswordDto#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new ForgotPasswordDto()).canEqual("Other"));
    }

    /**
     * Method under test: {@link ForgotPasswordDto#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        ForgotPasswordDto forgotPasswordDto2 = new ForgotPasswordDto();

        ForgotPasswordDto forgotPasswordDto3 = new ForgotPasswordDto();
        forgotPasswordDto3.setMail("Mail");

        // Act and Assert
        assertTrue(forgotPasswordDto2.canEqual(forgotPasswordDto3));
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link ForgotPasswordDto#equals(Object)}
     *   <li>{@link ForgotPasswordDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
        // Arrange
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
        forgotPasswordDto.setMail("Mail");

        ForgotPasswordDto forgotPasswordDto2 = new ForgotPasswordDto();
        forgotPasswordDto2.setMail("Mail");

        // Act and Assert
        assertEquals(forgotPasswordDto, forgotPasswordDto2);
        int expectedHashCodeResult = forgotPasswordDto.hashCode();
        assertEquals(expectedHashCodeResult, forgotPasswordDto2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link ForgotPasswordDto#equals(Object)}
     *   <li>{@link ForgotPasswordDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
        // Arrange
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
        forgotPasswordDto.setMail(null);

        ForgotPasswordDto forgotPasswordDto2 = new ForgotPasswordDto();
        forgotPasswordDto2.setMail(null);

        // Act and Assert
        assertEquals(forgotPasswordDto, forgotPasswordDto2);
        int expectedHashCodeResult = forgotPasswordDto.hashCode();
        assertEquals(expectedHashCodeResult, forgotPasswordDto2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link ForgotPasswordDto#equals(Object)}
     *   <li>{@link ForgotPasswordDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
        // Arrange
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
        forgotPasswordDto.setMail("Mail");

        // Act and Assert
        assertEquals(forgotPasswordDto, forgotPasswordDto);
        int expectedHashCodeResult = forgotPasswordDto.hashCode();
        assertEquals(expectedHashCodeResult, forgotPasswordDto.hashCode());
    }

    /**
     * Method under test: {@link ForgotPasswordDto#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
        // Arrange
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
        forgotPasswordDto.setMail(null);

        ForgotPasswordDto forgotPasswordDto2 = new ForgotPasswordDto();
        forgotPasswordDto2.setMail("Mail");

        // Act and Assert
        assertNotEquals(forgotPasswordDto, forgotPasswordDto2);
    }

    /**
     * Method under test: {@link ForgotPasswordDto#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
        // Arrange
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
        forgotPasswordDto.setMail("com.example.authenticator.dto.ForgotPasswordDto");

        ForgotPasswordDto forgotPasswordDto2 = new ForgotPasswordDto();
        forgotPasswordDto2.setMail("Mail");

        // Act and Assert
        assertNotEquals(forgotPasswordDto, forgotPasswordDto2);
    }

    /**
     * Method under test: {@link ForgotPasswordDto#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsNull_thenReturnNotEqual() {
        // Arrange
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
        forgotPasswordDto.setMail("Mail");

        // Act and Assert
        assertNotEquals(forgotPasswordDto, null);
    }

    /**
     * Method under test: {@link ForgotPasswordDto#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
        // Arrange
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
        forgotPasswordDto.setMail("Mail");

        // Act and Assert
        assertNotEquals(forgotPasswordDto, "Different type to ForgotPasswordDto");
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>default or parameterless constructor of {@link ForgotPasswordDto}
     *   <li>{@link ForgotPasswordDto#setMail(String)}
     *   <li>{@link ForgotPasswordDto#toString()}
     *   <li>{@link ForgotPasswordDto#getMail()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ForgotPasswordDto actualForgotPasswordDto = new ForgotPasswordDto();
        actualForgotPasswordDto.setMail("Mail");
        String actualToStringResult = actualForgotPasswordDto.toString();

        // Assert that nothing has changed
        assertEquals("ForgotPasswordDto(mail=Mail)", actualToStringResult);
        assertEquals("Mail", actualForgotPasswordDto.getMail());
    }
}
