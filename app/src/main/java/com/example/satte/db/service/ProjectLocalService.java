package com.example.satte.db.service;

import android.content.Context;

import com.example.satte.db.tables.ExecutionProjectInspection;
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

import com.example.satte.db.helper.ProjectHelperConfig;
import com.example.satte.db.tables.Project;

class ProjectLocalService {

    private final Logger logger = LoggerFactory.getLogger(ProjectLocalService.class);
    private Dao<Project, Long> projectDao;

    ProjectLocalService(Context context, Class<? extends OrmLiteSqliteOpenHelper> clz) {

        ProjectHelperConfig dbHelperConfig = (ProjectHelperConfig) OpenHelperManager.getHelper(context, clz);
        projectDao = dbHelperConfig.getProjectDao();
    }

    Project createProject(Project project) {
        try {
            projectDao.create(project);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return project;
    }

    Project updateProject(Project project) {
        try {
            projectDao.update(project);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return project;
    }



    Project getProjectById(long projectId) {
        try {
            return projectDao.queryForId(projectId);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }



    List<Project> getProjects() {
        try {
            return projectDao.queryForAll();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    List<Project> getProjectsByState(String projectimplementationstatus,String status) {
        try {
            return projectDao.queryForEq(projectimplementationstatus,status);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    List<Project> getProjectsByProjectCode(String projectcode,String projectCode) {
        try {
            return projectDao.queryForEq(projectcode,projectCode);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    boolean deleteProject(long projectId) {
        try {
            projectDao.deleteById(projectId);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    boolean deleteProjectTable(Project project) {
        try {
            int i = projectDao.delete(project);
            return i == 1;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    Object createOrUpdateStatus(Project project) {
        try {
            return projectDao.createOrUpdate(project);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }



    List<Project> getServicesImages(long id) {

        List<Project> modelImages = Collections.emptyList();
        try {
            QueryBuilder<Project, Long> qb = projectDao.queryBuilder();
            Where<Project, Long> where = qb.where();
            where.and(where.eq("className", "MidwayServiceAreaType"), where.eq("classPK", id));
            modelImages = projectDao.query(qb.prepare());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return modelImages;
    }

    List<Project> getProjectsByFieldName(String fieldName) {

        List<Project> modelImages = Collections.emptyList();
        try {
            QueryBuilder<Project, Long> qb = projectDao.queryBuilder();
            Where<Project, Long> where = qb.where();
            where.eq("fieldName", fieldName);
            modelImages = projectDao.query(qb.prepare());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return modelImages;
    }

    List<Project> getServicesImages() {

        List<Project> modelImages = Collections.emptyList();
        try {
            QueryBuilder<Project, Long> qb = projectDao.queryBuilder();
            Where<Project, Long> where = qb.where();
            where.eq("className", "MidwayServiceAreaType");
            modelImages = projectDao.query(qb.prepare());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return modelImages;
    }

    int deleteZeroProjects(long areaId) {

        int num = 0;
        try {
            DeleteBuilder<Project, Long> qb = projectDao.deleteBuilder();
            Where<Project, Long> where = qb.where();
            where.and(where.or(where.eq("projectId", 0), where.isNull("projectId")) ,where.eq("classPK", areaId));
            num = projectDao.delete(qb.prepare());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return num;
    }
}
