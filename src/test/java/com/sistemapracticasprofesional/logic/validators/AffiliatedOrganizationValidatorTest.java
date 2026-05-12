package com.sistemapracticasprofesional.logic.validators;

import org.junit.jupiter.api.Test;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;

public class AffiliatedOrganizationValidatorTest {

    private AffiliatedOrganizationValidator validator;

    @BeforeEach
    public void setup(){

        validator = new AffiliatedOrganizationValidator();

    }

    @Test
    public void validName(){

        boolean realResult = true;
        boolean expetedResult = validator.isTextAffiliatedOrganizationValid("Oracle de México, S.A. de C.V.");

    }

    @Test
    public void validAddress(){

        boolean expetedResult = true;
        boolean realResult = validator.isAddressAffiliatedOrganizationValid("C. Montes Urales 470, Lomas - Virreyes, Lomas de Chapultepec, Miguel Hidalgo");

        assertEquals(expetedResult, realResult);

    }

    @Test
    public void validPhoneNumber(){

        boolean expetedResult = true;
        boolean realResult = validator.isPhoneNumberAffiliatedOrganizationValid("5591783000");

        assertEquals(expetedResult, realResult);
    }

    @Test
    public void validEmail(){

        boolean expetedResult = true;
        boolean realResult = validator.isEmailAffiliatedOrganizationValid("NA");

        assertEquals(expetedResult, realResult);

    }
}
