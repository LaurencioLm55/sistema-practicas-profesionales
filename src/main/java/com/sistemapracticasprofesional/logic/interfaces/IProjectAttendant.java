package com.sistemapracticasprofesional.logic.interfaces;

import com.sistemapracticasprofesional.logic.dto.ProjectAttendantDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;

public interface IProjectAttendant {

    boolean insertProjectAttendant(ProjectAttendantDto projectAttendantDto) throws DaoException;

}
