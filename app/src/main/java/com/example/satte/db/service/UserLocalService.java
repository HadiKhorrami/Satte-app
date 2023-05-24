package com.example.satte.db.service;

import android.content.Context;

import com.example.satte.db.helper.ProjectHelperConfig;
import com.example.satte.db.helper.UserHelperConfig;
import com.example.satte.db.tables.Project;
import com.example.satte.db.tables.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

class UserLocalService {

    private final Logger logger = LoggerFactory.getLogger(UserLocalService.class);
    private Dao<User, Long> userDao;

    UserLocalService(Context context, Class<? extends OrmLiteSqliteOpenHelper> clz) {

        UserHelperConfig dbHelperConfig = (UserHelperConfig) OpenHelperManager.getHelper(context, clz);
        userDao = dbHelperConfig.getUserDao();
    }

    User createUser(User user) {
        try {
            userDao.create(user);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return user;
    }

    User updateUser(User user) {
        try {
            userDao.update(user);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return user;
    }



    User getUserById(long userId) {
        try {
            return userDao.queryForId(userId);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }



    List<User> getUsers() {
        try {
            return userDao.queryForAll();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    List<User> getUsersByScreenName(String screenname,String username) {
        try {
            return userDao.queryForEq(screenname,username);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }



    boolean deleteUser(long userId) {
        try {
            userDao.deleteById(userId);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

  
}
