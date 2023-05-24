package com.example.satte.db.helper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import com.example.satte.db.tables.Project;

public interface ProjectHelperConfig {

    Dao<Project, Long> getProjectDao();

    RuntimeExceptionDao<Project, Long> getProjectRuntimeExceptionDao();
}
