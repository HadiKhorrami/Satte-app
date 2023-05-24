package com.example.satte.db.util;

import android.content.Context;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.satte.db.service.ExecutionProjectInspectionLocalServiceUtil;
import com.example.satte.db.service.ProjectLocalServiceUtil;
import com.example.satte.db.service.UserLocalServiceUtil;
import com.example.satte.db.tables.ExecutionProjectInspection;
import com.example.satte.db.tables.Project;
import com.example.satte.db.tables.User;

import timber.log.Timber;

public class JsonInsertUtil {

    public static boolean insertProjectFromJSON(JSONArray result, Context context) {

        try {
            ProjectLocalServiceUtil projectLocalServiceUtil = new ProjectLocalServiceUtil(context);
            com.example.satte.db.service.ProjectLocalServiceUtil documentLocalServiceUtil = new com.example.satte.db.service.ProjectLocalServiceUtil(context);

            for (int j = 0; j < result.length(); j++) {
                List<Project> projectList = projectLocalServiceUtil.getProjects();
                Project project = new Project();
                project.setLocalprojectid(j);
                long projectId = result.getJSONObject(j).getLong("projectId");
                project.setProjectid(projectId);
                project.setAlllocationlastyeartotal(result.getJSONObject(j).has("allLocationLastYearTotal") ? result.getJSONObject(j).getDouble("allLocationLastYearTotal") : 0);
                project.setApprovedlastyear(result.getJSONObject(j).has("approvedLastYear") ? result.getJSONObject(j).getDouble("approvedLastYear") : 0);
                project.setBahrebardarnationalcode(result.getJSONObject(j).has("bahrebardarNationalCode") ? result.getJSONObject(j).get("bahrebardarNationalCode").toString() : "");
                project.setBahrebardarpersonname(result.getJSONObject(j).has("bahrebardarPersonName") ? result.getJSONObject(j).get("bahrebardarPersonName").toString() : "");
                project.setBarname(result.getJSONObject(j).has("barname") ? result.getJSONObject(j).get("barname").toString() : "");
                project.setCofogcode(result.getJSONObject(j).has("cofogCode") ? result.getJSONObject(j).get("cofogCode").toString() : "");
                project.setContractPercentage(result.getJSONObject(j).has("contractPercentage") ? result.getJSONObject(j).getDouble("contractPercentage") : 0);
                project.setCompanyid(result.getJSONObject(j).has("companyId") ? result.getJSONObject(j).getLong("companyId") : 0);
                project.setCreatedate(result.getJSONObject(j).has("createDate") ? result.getJSONObject(j).get("createDate").toString() : "");
                project.setCreditAllocation(result.getJSONObject(j).has("creditAllocation") ? result.getJSONObject(j).getDouble("creditAllocation") : 0);
                project.setCreditApproval(result.getJSONObject(j).has("creditApproval") ? result.getJSONObject(j).getDouble("creditApproval") : 0);
                project.setCreditcurrentyear(result.getJSONObject(j).has("creditCurrentYear") ? result.getJSONObject(j).getDouble("creditCurrentYear") : 0);
                project.setCreditExchanged(result.getJSONObject(j).has("creditExchanged") ? result.getJSONObject(j).getDouble("creditExchanged") : 0);
                project.setCreditrequired(result.getJSONObject(j).has("creditRequired") ? result.getJSONObject(j).getDouble("creditRequired") : 0);
                project.setDescription(result.getJSONObject(j).has("description") ? result.getJSONObject(j).get("description").toString() : "");
                project.setEndyear(result.getJSONObject(j).has("endYear") ? result.getJSONObject(j).getInt("endYear") : 0);
                project.setExchangestatus(result.getJSONObject(j).has("exchangeStatus") ? result.getJSONObject(j).get("exchangeStatus").toString() : "");
                project.setExecuteplacecode(result.getJSONObject(j).has("executePlaceCode") ? result.getJSONObject(j).get("executePlaceCode").toString() : "");
                project.setFasl(result.getJSONObject(j).has("fasl") ? result.getJSONObject(j).get("fasl").toString() : "");
                project.setFunctionlastyeartotal(result.getJSONObject(j).has("functionLastYearTotal") ? result.getJSONObject(j).getDouble("functionLastYearTotal") : 0);
                project.setGroupid(result.getJSONObject(j).has("groupId") ? result.getJSONObject(j).getLong("groupId") : 0);
                project.setLat(result.getJSONObject(j).has("lat") ? result.getJSONObject(j).getDouble("lat") : 0);
                project.setLng(result.getJSONObject(j).has("lng") ? result.getJSONObject(j).getDouble("lng") : 0);
                project.setMasternationalcode(result.getJSONObject(j).has("masterNationalCode") ? result.getJSONObject(j).get("masterNationalCode").toString() : "");
                project.setMasterpersonname(result.getJSONObject(j).has("masterPersonName") ? result.getJSONObject(j).get("masterPersonName").toString() : "");
                project.setModifieddate(result.getJSONObject(j).has("modifiedDate") ? result.getJSONObject(j).get("modifiedDate").toString() : "");
                project.setOmur(result.getJSONObject(j).has("omur") ? result.getJSONObject(j).get("omur").toString() : "");
                project.setPaiduptodatepreviousyear(result.getJSONObject(j).has("paidUpToDatePreviousYear") ? result.getJSONObject(j).getDouble("paidUpToDatePreviousYear") : 0);
                project.setParentprojectid(result.getJSONObject(j).has("parentProjectId") ? result.getJSONObject(j).getLong("parentProjectId") : 0);
                project.setPeriodtime(result.getJSONObject(j).has("periodTime") ? result.getJSONObject(j).getInt("periodTime") : 0);
                project.setPlancode(result.getJSONObject(j).has("planCode") ? result.getJSONObject(j).getLong("planCode") : 0);
                project.setPlanname(result.getJSONObject(j).has("planName") ? result.getJSONObject(j).get("planName").toString() : "");
                project.setProjectcode(result.getJSONObject(j).has("projectCode") ? result.getJSONObject(j).getLong("projectCode") : 0);
                project.setProjectcredit(result.getJSONObject(j).has("projectCredit") ? result.getJSONObject(j).getDouble("projectCredit") : 0);
                project.setProjectexecutetype(result.getJSONObject(j).has("projectExecuteType") ? result.getJSONObject(j).get("projectExecuteType").toString() : "");
                project.setProjectimplementationstatus(result.getJSONObject(j).has("projectImplementationStatus") ? result.getJSONObject(j).get("projectImplementationStatus").toString() : "");
                project.setProjectrow(result.getJSONObject(j).has("projectRow") ? result.getJSONObject(j).get("projectRow").toString() : "");
                project.setProjectstatus(result.getJSONObject(j).has("projectStatus") ? result.getJSONObject(j).get("projectStatus").toString() : "");
                project.setProjecttype(result.getJSONObject(j).has("projectType") ? result.getJSONObject(j).get("projectType").toString() : "");
                project.setSourceid(result.getJSONObject(j).has("sourceId") ? result.getJSONObject(j).getLong("sourceId") : 0);
                project.setSourcename(result.getJSONObject(j).has("sourceName") ? result.getJSONObject(j).get("sourceName").toString() : "");
                project.setStartyear(result.getJSONObject(j).has("startYear") ? result.getJSONObject(j).getInt("startYear") : 0);
                project.setTagsimatcode(result.getJSONObject(j).has("tagsimatCode") ? result.getJSONObject(j).get("tagsimatCode").toString() : "");
                project.setTagsimatname(result.getJSONObject(j).has("tagsimatName") ? result.getJSONObject(j).get("tagsimatName").toString() : "");
                project.setTargetname(result.getJSONObject(j).has("targetName") ? result.getJSONObject(j).get("targetName").toString() : "");
                project.setTargetunit(result.getJSONObject(j).has("targetUnit") ? result.getJSONObject(j).get("targetUnit").toString() : "");
                project.setTargetvalue(result.getJSONObject(j).has("targetValue") ? result.getJSONObject(j).getLong("targetValue") : 0);
                project.setTitle(result.getJSONObject(j).has("title") ? result.getJSONObject(j).get("title").toString() : "");
                project.setUserid(result.getJSONObject(j).has("userId") ? result.getJSONObject(j).getLong("userId") : 0);
                project.setUsername(result.getJSONObject(j).has("userName") ? result.getJSONObject(j).get("userName").toString() : "");
                project.setWkt(result.getJSONObject(j).has("wkt") ? result.getJSONObject(j).get("wkt").toString() : "");

                Project existed = projectLocalServiceUtil.getProjectById(projectId);

                if (existed != null) {
                    project.setProjectid(existed.getProjectid());
                    projectLocalServiceUtil.updateProject(project);
                } else
                    project.setProjectid(projectId);
                projectLocalServiceUtil.insertProject(project);
            }
            return true;
        } catch (JSONException e) {
            System.out.println("asdff" + e.getMessage());
            Timber.e(e.getMessage(), e.getMessage());
        }

        return false;
    }

    public static boolean insertExecutionProjectInspectionFromJSON(JSONArray result, Context context,String from) {

        ExecutionProjectInspectionLocalServiceUtil executionProjectInspectionLocalServiceUtil = new ExecutionProjectInspectionLocalServiceUtil(context);
        for (int j = 0; j < result.length(); j++) {
            try {
                com.example.satte.db.tables.ExecutionProjectInspection executionProjectInspection = new com.example.satte.db.tables.ExecutionProjectInspection();
                long executionProjectInspectionId = result.getJSONObject(j).getLong("executionProjectInspectionId");
                executionProjectInspection.setExecutionprojectinspectionid(executionProjectInspectionId);
                executionProjectInspection.setCompanyid(result.getJSONObject(j).getLong("companyId"));
                executionProjectInspection.setGroupid(result.getJSONObject(j).getLong("groupId"));
                executionProjectInspection.setUserid(result.getJSONObject(j).getLong("userId"));
                executionProjectInspection.setUsername(result.getJSONObject(j).get("userName").toString());
                executionProjectInspection.setCreatedate(result.getJSONObject(j).get("createDate").toString());
                executionProjectInspection.setModifieddate(result.getJSONObject(j).get("modifiedDate").toString());
                executionProjectInspection.setProjectcode(result.getJSONObject(j).getLong("projectCode"));
                executionProjectInspection.setRialprogress(result.getJSONObject(j).has("rialProgress") ? result.getJSONObject(j).getLong("rialProgress") : 0);
                executionProjectInspection.setPercentageprogressfirstyear(result.getJSONObject(j).has("percentageProgressFirstYear") ? result.getJSONObject(j).getDouble("percentageProgressFirstYear") : 0);
                executionProjectInspection.setPhysicalprogressinspectiontime(result.getJSONObject(j).has("physicalProgressInspectionTime") ? result.getJSONObject(j).getDouble("physicalProgressInspectionTime") : 0);
                executionProjectInspection.setExecutionstatus(result.getJSONObject(j).has("executionStatus") ? result.getJSONObject(j).get("executionStatus").toString() : "");
                executionProjectInspection.setExecutionstatusint(result.getJSONObject(j).has("executionStatusInt") ? result.getJSONObject(j).getInt("executionStatusInt") : 0);
                executionProjectInspection.setPerformancequality(result.getJSONObject(j).has("performanceQuality") ? result.getJSONObject(j).get("performanceQuality").toString() : "");
                executionProjectInspection.setPerformancequalityint(result.getJSONObject(j).has("performanceQualityInt") ? result.getJSONObject(j).getInt("performanceQualityInt") : 0);
                executionProjectInspection.setHowreferwork(result.getJSONObject(j).has("howReferWork") ? result.getJSONObject(j).get("howReferWork").toString() : "");
                executionProjectInspection.setHowreferworkint(result.getJSONObject(j).has("howReferWorkInt") ? result.getJSONObject(j).getInt("howReferWorkInt") : 0);
                executionProjectInspection.setContractratebasics(result.getJSONObject(j).has("contractRateBasics") ? result.getJSONObject(j).get("contractRateBasics").toString() : "");
                executionProjectInspection.setContractratebasicsint(result.getJSONObject(j).has("contractRateBasicsInt") ? result.getJSONObject(j).getInt("contractRateBasicsInt") : 0);
                executionProjectInspection.setTechnicalspecifications(result.getJSONObject(j).has("technicalSpecifications") ? result.getJSONObject(j).get("technicalSpecifications").toString() : "");
                executionProjectInspection.setTechnicalspecificationsint(result.getJSONObject(j).has("technicalSpecificationsInt") ? result.getJSONObject(j).getBoolean("technicalSpecificationsInt") : false);
                executionProjectInspection.setOperationvolume(result.getJSONObject(j).has("operationVolume") ? result.getJSONObject(j).get("operationVolume").toString() : "");
                executionProjectInspection.setOperationvolumeint(result.getJSONObject(j).has("operationVolumeInt") ? result.getJSONObject(j).getBoolean("operationVolumeInt") : false);
                executionProjectInspection.setPractical(result.getJSONObject(j).has("practical") ? result.getJSONObject(j).get("practical").toString() : "");
                executionProjectInspection.setPracticalint(result.getJSONObject(j).has("practicalInt") ? result.getJSONObject(j).getBoolean("practicalInt") : false);
                executionProjectInspection.setPhysicalprogresstype(result.getJSONObject(j).has("physicalProgressType") ? result.getJSONObject(j).get("physicalProgressType").toString() : "");
                executionProjectInspection.setPhysicalprogresstypeint(result.getJSONObject(j).has("physicalProgressTypeInt") ? result.getJSONObject(j).getInt("physicalProgressTypeInt") : 0);
                executionProjectInspection.setExpressingopinionsproviding(result.getJSONObject(j).has("expressingOpinionsProviding") ? result.getJSONObject(j).get("expressingOpinionsProviding").toString() : "");
                executionProjectInspection.setCausesdelay(result.getJSONObject(j).has("causesDelay") ? result.getJSONObject(j).get("causesDelay").toString() : "");
                executionProjectInspection.setGeometry(result.getJSONObject(j).has("geometry") ? result.getJSONObject(j).get("geometry").toString() : "");
                executionProjectInspection.setWkt(result.getJSONObject(j).has("wkt") ? result.getJSONObject(j).get("wkt").toString() : "");
                executionProjectInspection.setLat(result.getJSONObject(j).has("lat") ? result.getJSONObject(j).getDouble("lat") : 0);
                executionProjectInspection.setLng(result.getJSONObject(j).has("lng") ? result.getJSONObject(j).getDouble("lng") : 0);
                executionProjectInspection.setDocuments(result.getJSONObject(j).has("documents") ? result.getJSONObject(j).get("documents").toString() : "");

                if(result.getJSONObject(j).has("image") && from.equals("LoadData")){
                    String images = "";
                    JSONArray imageArray = result.getJSONObject(j).getJSONArray("image");
                    for(int i = 0;i<imageArray.length();i++){
                        images += imageArray.get(i) + "###";
                    }
                    executionProjectInspection.setImage(images);
                }else if(result.getJSONObject(j).has("image") && from.equals("AddReview")){
                    executionProjectInspection.setImage(result.getJSONObject(j).get("image").toString().substring(1,result.getJSONObject(j).get("image").toString().length()-1));
                }

                ExecutionProjectInspection existed = executionProjectInspectionLocalServiceUtil.getExecutionProjectInspectionById(executionProjectInspectionId);
                    if (existed != null) {
                        executionProjectInspection.setExecutionprojectinspectionid(existed.getExecutionprojectinspectionid());
                        executionProjectInspectionLocalServiceUtil.updateExecutionProjectInspection(executionProjectInspection);
                    } else
                        executionProjectInspectionLocalServiceUtil.insertExecutionProjectInspection(executionProjectInspection);


            } catch (JSONException e) {
                System.out.println(e.getMessage());
                Timber.e(e.getMessage(), e.getMessage());
            }
        }

        return true;
    }

    public static boolean insertUserFromJSON(JSONArray result, Context context) {

        UserLocalServiceUtil userLocalServiceUtil = new UserLocalServiceUtil(context);
        for (int j = 0; j < result.length(); j++) {
            try {
                com.example.satte.db.tables.User user = new com.example.satte.db.tables.User();
                long userId = result.getJSONObject(j).getLong("userId");
                user.setUserid(userId);
                user.setEmailaddress(result.getJSONObject(j).get("emailAddress").toString());
                user.setFirstname(result.getJSONObject(j).get("firstName").toString());
                user.setLastname(result.getJSONObject(j).get("lastName").toString());
                user.setFullname(result.getJSONObject(j).get("fullName").toString().toString());
                user.setLockout(result.getJSONObject(j).getBoolean("lockout"));
                user.setMobilenumber(result.getJSONObject(j).getLong("mobileNumber"));
                user.setPassword(result.getJSONObject(j).get("password").toString());
                user.setPasswordencrypted(result.getJSONObject(j).getBoolean("passwordEncrypted"));
                user.setScreenname(result.getJSONObject(j).get("screenName").toString());
                user.setStatus(result.getJSONObject(j).getLong("status"));

                User existed = userLocalServiceUtil.getUserById(userId);
                if (existed != null) {
                    user.setUserid(existed.getUserid());
                    userLocalServiceUtil.updateUser(user);
                } else
                    userLocalServiceUtil.insertUser(user);
            } catch (JSONException e) {
                System.out.println(e.getMessage());
                Timber.e(e.getMessage(), e.getMessage());
            }
        }

        return true;
    }

    public static boolean deleteProjectDataBase(Context context) {
        ProjectLocalServiceUtil projectLocalServiceUtil = new ProjectLocalServiceUtil(context);
        List<Project> project = projectLocalServiceUtil.getProjects();
        for (int i = 0; i < project.size(); i++) {
            projectLocalServiceUtil.deleteProject(project.get(i).getProjectid());
        }
        return true;
    }

    public static boolean deleteExecutionProjectInspectionDataBase(Context context) {
        ExecutionProjectInspectionLocalServiceUtil executionProjectInspectionLocalServiceUtil = new ExecutionProjectInspectionLocalServiceUtil(context);
        List<ExecutionProjectInspection> executionProjectInspections = executionProjectInspectionLocalServiceUtil.getExecutionProjectInspection();
        for (int i = 0; i < executionProjectInspections.size(); i++) {
            executionProjectInspectionLocalServiceUtil.deleteExecutionProjectInspection(executionProjectInspections.get(i).getExecutionprojectinspectionid());
        }
        return true;
    }
}
