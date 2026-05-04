package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.dto.ProfessorDto;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.ValidationException;

public final class RegistrationValidator {

    private RegistrationValidator() {
    }

    public static void validateUser(UserDto userDto, String confirmPassword)
            throws ValidationException {

        UserValidator validator = new UserValidator(userDto);

        if (!validator.isUserValid()) {
            throw new ValidationException("Datos del usuario invalidos");
        }

        if (!userDto.getPassword().equals(confirmPassword)) {
            throw new ValidationException("Las contrasenas no coinciden");
        }
    }

    public static void validateCoordinator(CoordinatorDto coordinatorDto)
            throws ValidationException {

        if (coordinatorDto == null
                || coordinatorDto.getPersonnelNumber() <= 0
                || isBlank(coordinatorDto.getName())
                || isBlank(coordinatorDto.getState())
                || coordinatorDto.getEntryDate() == null) {
            throw new ValidationException("Datos del coordinador invalidos");
        }

        coordinatorDto.setName(coordinatorDto.getName().trim());
        coordinatorDto.setState(coordinatorDto.getState().trim());
    }

    public static void validateProfessor(ProfessorDto professorDto)
            throws ValidationException {

        if (professorDto == null
                || professorDto.getStaffNumber() <= 0
                || isBlank(professorDto.getName())
                || isBlank(professorDto.getShift())) {
            throw new ValidationException("Datos del profesor invalidos");
        }

        professorDto.setName(professorDto.getName().trim());
        professorDto.setShift(professorDto.getShift().trim());
    }

    public static void validateIntern(InternDto internDto)
            throws ValidationException {

        if (internDto == null
                || isBlank(internDto.getStudentId())
                || internDto.getAge() <= 0
                || isBlank(internDto.getName())
                || isBlank(internDto.getGender())
                || isBlank(internDto.getMajor())) {
            throw new ValidationException("Datos del practicante invalidos");
        }

        internDto.setStudentId(internDto.getStudentId().trim());
        internDto.setName(internDto.getName().trim());
        internDto.setGender(internDto.getGender().trim());
        internDto.setMajor(internDto.getMajor().trim());

        if (isBlank(internDto.getIndigenousLanguage())) {
            internDto.setIndigenousLanguage(null);
        } else {
            internDto.setIndigenousLanguage(internDto.getIndigenousLanguage().trim());
        }
    }

    private static boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}
