package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserValidatorTest {

    private UserDto userDto;
    private UserValidator validator;

    @BeforeEach
    public void setUp() {
        userDto = new UserDto();
        validator = new UserValidator(userDto);
    }

    @Test
    public void validPasswordWithMinimumSecurityRequirements() {
        userDto.setPassword("Password123");

        assertTrue(validator.isUserPasswordValid(userDto));
    }

    @Test
    public void invalidPasswordWithLessThanTenCharacters() {
        userDto.setPassword("Pass1234");

        assertFalse(validator.isUserPasswordValid(userDto));
    }

    @Test
    public void validUserWithAlphanumericUserNameAndSecurePassword() {
        userDto.setUserName("coord01");
        userDto.setPassword("Password123");

        assertTrue(validator.isUserValid());
    }
}
