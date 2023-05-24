package com.example.satte.db.service;

import android.content.Context;

import com.example.satte.db.tables.ExecutionProjectInspection;
import com.example.satte.db.tables.Project;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.satte.db.helper.ExecutionProjectInspectionHelperConfig;

public class ExecutionProjectInspectionLocalService {

    private final Logger logger = LoggerFactory.getLogger(ExecutionProjectInspectionLocalService.class);
    private Dao<ExecutionProjectInspection, Long> executionProjectInspectionDao;

    ExecutionProjectInspectionLocalService(Context context, Class<? extends OrmLiteSqliteOpenHelper> clz) {

        ExecutionProjectInspectionHelperConfig dbHelperConfig = (ExecutionProjectInspectionHelperConfig) OpenHelperManager.getHelper(context, clz);
        executionProjectInspectionDao = dbHelperConfig.getExecutionProjectInspectionDao();
    }

    ExecutionProjectInspection createExecutionProjectInspection(ExecutionProjectInspection midwayServiceAreaInfo) {
        try {
            executionProjectInspectionDao.create(midwayServiceAreaInfo);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return midwayServiceAreaInfo;
    }
    ExecutionProjectInspection updateExecutionProjectInspection(ExecutionProjectInspection executionProjectInspection) {
        try {
            executionProjectInspectionDao.update(executionProjectInspection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return executionProjectInspection;
    }

    List<ExecutionProjectInspection> getExecutionProjectInspection() {
        try {
            return executionProjectInspectionDao.queryForAll();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    boolean deleteExecutionProjectInspection(long midwayServiceAreaInfoId) {
        try {
            int i = executionProjectInspectionDao.deleteById(midwayServiceAreaInfoId);
            return i == 1;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    boolean deleteExecutionProjectInspectionTable(long executionProjectInspectionId) {
        try {
            int i = executionProjectInspectionDao.deleteById(executionProjectInspectionId);
            return i == 1;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    ExecutionProjectInspection getExecutionProjectInspection(long executionProjectInspectionId) {
            try {
                return executionProjectInspectionDao.queryForId(executionProjectInspectionId);
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
            return null;
        }
    List<ExecutionProjectInspection> getExecutionProjectInspectionsByProjectCode(String projectcode, long projectCode) {
        try {
            return executionProjectInspectionDao.queryForEq(projectcode,projectCode);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

//
//    ExecutionProjectInspection getExecutionProjectInspectionByName(String name) {
//        List<ExecutionProjectInspection> midwayServiceAreaInfos = Collections.emptyList();
//        try {
//            QueryBuilder<ExecutionProjectInspection, Long> qb = midwayServiceAreaInfoDao.queryBuilder();
//            Where<ExecutionProjectInspection, Long> where = qb.where();
//            where.like("name", name);
//            midwayServiceAreaInfos = midwayServiceAreaInfoDao.query(qb.prepare());
//        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
//        }
//
//        if (midwayServiceAreaInfos != null && midwayServiceAreaInfos.size() > 0)
//            return midwayServiceAreaInfos.get(0);
//        else return null;
//    }
//
//    public ExecutionProjectInspection getMidwayServiceAreaInfoByServerId(long midwayServiceAreaInfoId) {
//        List<ExecutionProjectInspection> midwayServiceAreaInfos = Collections.emptyList();
//        try {
//            QueryBuilder<ExecutionProjectInspection, Long> qb = midwayServiceAreaInfoDao.queryBuilder();
//            Where<ExecutionProjectInspection, Long> where = qb.where();
//            where.eq("midwayServiceAreaInfoId", midwayServiceAreaInfoId);
//            midwayServiceAreaInfos = midwayServiceAreaInfoDao.query(qb.prepare());
//        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
//        }
//
//        if (midwayServiceAreaInfos != null && midwayServiceAreaInfos.size() > 0)
//            return midwayServiceAreaInfos.get(0);
//        else return null;
//    }
//



}
