package com.example.satte.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import com.example.satte.db.tables.Project;
import com.example.satte.db.tables.ExecutionProjectInspection;
import com.example.satte.db.tables.User;

public class SatteDBHelperConfig extends OrmLiteSqliteOpenHelper implements ProjectHelperConfig, ExecutionProjectInspectionHelperConfig,UserHelperConfig {

    private static final String DATABASE_NAME = "Satte.db";
    private static final int DATABASE_VERSION = 2;

    private final Logger logger = LoggerFactory.getLogger(com.example.satte.db.helper.SatteDBHelperConfig.class);

    private Dao<Project, Long> projectDao;
    private Dao<ExecutionProjectInspection, Long> executionProjectinSpectionDao;
    private Dao<User, Long> userDao;

    private RuntimeExceptionDao<Project, Long> projectRuntimeDao;
    private RuntimeExceptionDao<ExecutionProjectInspection, Long> executionProjectInspectionsRuntimeDao;
    private RuntimeExceptionDao<User, Long> userRuntimeDao;

    public SatteDBHelperConfig(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, com.example.satte.db.helper.SatteDBHelperConfig.class.getClassLoader().getResourceAsStream("META-INF/ormlite_config.txt"));
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Project.class);
            TableUtils.createTableIfNotExists(connectionSource, ExecutionProjectInspection.class);
            TableUtils.createTableIfNotExists(connectionSource, User.class);

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldversion, int newversion) {
        try {
            /*TableUtils.dropTable(connectionSource, Document.class, true);
            TableUtils.dropTable(connectionSource, MidwayServiceAreaInfo.class, true);
            TableUtils.dropTable(connectionSource, MidwayServiceAreaType.class, true);
            TableUtils.dropTable(connectionSource, MidwayServiceAreaServiceDetail.class, true);
            TableUtils.dropTable(connectionSource, MidwayServiceAreaRating.class, true);

            onCreate(sqLiteDatabase, connectionSource);*/

            if (oldversion < 2) {
                // we added the age column in version 2
                projectDao.executeRaw("ALTER TABLE `midwayserviceareainfo` ADD COLUMN trackingCode STRING;");
                projectDao.executeRaw("ALTER TABLE `midwayserviceareainfo` ADD COLUMN investorName STRING;");
                projectDao.executeRaw("ALTER TABLE `midwayserviceareainfo` ADD COLUMN investorMobileNumber STRING;");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public Dao<Project, Long> getProjectDao() {
        if (projectDao == null) {
            try {
                projectDao = getDao(Project.class);
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return projectDao;
    }

    public RuntimeExceptionDao<Project, Long> getProjectRuntimeExceptionDao() {
        if (projectRuntimeDao == null) {
            projectRuntimeDao = getRuntimeExceptionDao(Project.class);
        }
        return projectRuntimeDao;
    }

    public Dao<ExecutionProjectInspection, Long> getExecutionProjectInspectionDao() {
        if (executionProjectinSpectionDao == null) {
            try {
                executionProjectinSpectionDao = getDao(ExecutionProjectInspection.class);
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return executionProjectinSpectionDao;
    }

    public RuntimeExceptionDao<ExecutionProjectInspection, Long> getExecutionProjectInspectionRuntimeExceptionDao() {
        if (executionProjectInspectionsRuntimeDao == null) {
            executionProjectInspectionsRuntimeDao = getRuntimeExceptionDao(ExecutionProjectInspection.class);
        }
        return executionProjectInspectionsRuntimeDao;
    }


    @Override
    public void close() {
        super.close();
        projectDao = null;
        executionProjectinSpectionDao = null;
    }

    @Override
    public Dao<User, Long> getUserDao() {
        if (userDao == null) {
            try {
                userDao = getDao(User.class);
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return userDao;
    }

    @Override
    public RuntimeExceptionDao<User, Long> getUserRuntimeExceptionDao() {
        if (userRuntimeDao == null) {
            userRuntimeDao = getRuntimeExceptionDao(User.class);
        }
        return userRuntimeDao;
    }
}
