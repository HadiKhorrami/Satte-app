package com.example.satte.db.service;

import android.content.Context;

import java.util.List;

import  com.example.satte.db.tables.Project;

public class ProjectLocalServiceUtil {

    private ProjectLocalService projectLocalService;

    public ProjectLocalServiceUtil(Context context) {
        projectLocalService = new ProjectLocalService(context, com.example.satte.db.helper.SatteDBHelperConfig.class);
    }

    public Project insertProject(Project project) {
        return projectLocalService.createProject(project);
    }

    public Project updateProject(Project project) {
        return projectLocalService.updateProject(project);
    }

    public Project getProjectById(long projectId) {
        return projectLocalService.getProjectById(projectId);
    }

    public List<Project> getProjects() {

        return projectLocalService.getProjects();
    }

    public List<Project> getProjectsByState(String projectimplementationstatus,String status) {

        return projectLocalService.getProjectsByState(projectimplementationstatus,status);
    }

    public List<Project> getProjectsByProjectCode(String projectcode,String projectCode) {

        return projectLocalService.getProjectsByState(projectcode,projectCode);
    }
    public boolean deleteProject(long projectId) {
    return projectLocalService.deleteProject(projectId);
   }

    public Object createOrUpdate(Project project) {
        return projectLocalService.createOrUpdateStatus(project);
    }
}
