package com.sistemapracticasprofesional.logic.interfaces;
import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;
public interface IAffiliatedOrganization {
    
    boolean insertOrganization(AffiliatedOrganizationDto affiliatedOrganization) throws DaoException;

    boolean updateAffiliatedOrganization(AffiliatedOrganizationDto affiliatedOrganization, int idAffiliatedOrganization) throws DaoException;
    
    AffiliatedOrganizationDto getAffiliatedOrganization(int idAffilitedOrganization) throws DaoException;
    
    List<AffiliatedOrganizationDto> getListAffiliatedOrganiztionActiveState(String data) throws DaoException;

}
