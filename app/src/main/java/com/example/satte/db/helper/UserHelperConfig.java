package com.example.satte.db.helper;

import com.example.satte.db.tables.Project;
import com.example.satte.db.tables.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public interface UserHelperConfig {

    Dao<User, Long> getUserDao();

    RuntimeExceptionDao<User, Long> getUserRuntimeExceptionDao();
}
