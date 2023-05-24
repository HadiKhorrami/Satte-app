package com.example.satte.db.helper;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public interface ExecutionProjectInspectionHelperConfig {

    RuntimeExceptionDao<com.example.satte.db.tables.ExecutionProjectInspection, Long> getExecutionProjectInspectionRuntimeExceptionDao();

    Dao<com.example.satte.db.tables.ExecutionProjectInspection, Long> getExecutionProjectInspectionDao();
}
