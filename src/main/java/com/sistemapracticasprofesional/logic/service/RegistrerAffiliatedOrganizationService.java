package com.sistemapracticasprofesional.logic.service;

import com.sistemapracticasprofesional.logic.dao.AffiliatedOrganizationDao;
import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.validators.AffiliatedOrganizationValidator;

public class RegistrerAffiliatedOrganizationService {

    private AffiliatedOrganizationDao affiliatedOrganizationDao = new AffiliatedOrganizationDao();
    private AffiliatedOrganizationValidator affiliatedOrganizationValidator ;

    
    public boolean RegistrerAffiliatedOrganization (AffiliatedOrganizationDto affiliatedOrganizationDto)
    throws BusinessLogicException {

        boolean result = false;

        affiliatedOrganizationValidator = new AffiliatedOrganizationValidator(affiliatedOrganizationDto);

        if ( affiliatedOrganizationValidator.isAffiliatedOrganizationValid() ) {

            result = affiliatedOrganizationDao.insertOrganization(affiliatedOrganizationDto);

        }else{
            
            throw new BusinessLogicException("No se cumple con el formato requerido");

        }

        return result;

    }

}
