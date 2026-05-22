package com.sistemapracticasprofesional.logic.interfaces;

import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;

public interface IIntern {

    boolean insertIntern(InternDto intern) throws DaoException;

    boolean updateIntern(InternDto intern) throws DaoException;

    boolean deleteIntern(String studentId) throws DaoException;

    InternDto getInternByStudentId(String studentId) throws DaoException;

    List<InternDto> getAllInterns() throws DaoException;

    void assignProject(String studentId, int projectId) throws DaoException;

    List<InternDto> getInternsActive() throws DaoException;
    
}
