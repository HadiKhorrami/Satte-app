package com.example.satte.db.service;

import android.content.Context;

import com.example.satte.db.tables.ExecutionProjectInspection;
import com.example.satte.db.tables.Project;

import java.util.List;


public class ExecutionProjectInspectionLocalServiceUtil {

    private ExecutionProjectInspectionLocalService executionProjectInspectionLocalService;

    public ExecutionProjectInspectionLocalServiceUtil(Context context) {
        executionProjectInspectionLocalService = new ExecutionProjectInspectionLocalService(context, com.example.satte.db.helper.SatteDBHelperConfig.class);
    }

    public ExecutionProjectInspection insertExecutionProjectInspection(ExecutionProjectInspection executionProjectInspection) {
        return executionProjectInspectionLocalService.createExecutionProjectInspection(executionProjectInspection);
    }
    public boolean deleteExecutionProjectInspection(long executionProjectInspectionId) {
        return executionProjectInspectionLocalService.deleteExecutionProjectInspection(executionProjectInspectionId);
    }

    public ExecutionProjectInspection getExecutionProjectInspectionById(long executionProjectInspectionId) {
        return executionProjectInspectionLocalService.getExecutionProjectInspection(executionProjectInspectionId);
    }

    public List<ExecutionProjectInspection> getExecutionProjectInspectionByProjectCode(String projectcode,long projectCode) {
        return executionProjectInspectionLocalService.getExecutionProjectInspectionsByProjectCode(projectcode,projectCode);
    }
    public List<ExecutionProjectInspection> getExecutionProjectInspection() {

        return executionProjectInspectionLocalService.getExecutionProjectInspection();
    }
    public ExecutionProjectInspection updateExecutionProjectInspection(ExecutionProjectInspection executionProjectInspection) {
        return executionProjectInspectionLocalService.updateExecutionProjectInspection(executionProjectInspection);
    }
}
