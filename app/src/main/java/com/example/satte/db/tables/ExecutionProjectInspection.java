package com.example.satte.db.tables;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "executionprojectinspection")
public class ExecutionProjectInspection {
    @DatabaseField
    private long companyid;

    @DatabaseField
    private long groupid;

    @DatabaseField
    private long userid;

    @DatabaseField
    private String username;

    @DatabaseField
    private String createdate;

    @DatabaseField
    private String modifieddate;
   //////////////////////////////////////////////
    @DatabaseField(id = true)
    private long executionprojectinspectionid;
    ///////////////////////////////////////////////
    @DatabaseField
    private long projectcode;

    @DatabaseField
    private double rialprogress;

    @DatabaseField
    private double percentageprogressfirstyear;

    @DatabaseField
    private double physicalprogressinspectiontime;

    @DatabaseField
    private String executionstatus;

    @DatabaseField
    private int executionstatusint;

    @DatabaseField
    private String performancequality;

    @DatabaseField
    private int performancequalityint;

    @DatabaseField
    private String howreferwork;

    @DatabaseField
    private int howreferworkint;

    @DatabaseField
    private String contractratebasics;

    @DatabaseField
    private int contractratebasicsint;

    @DatabaseField
    private String technicalspecifications;

    @DatabaseField
    private boolean technicalspecificationsint;

    @DatabaseField
    private String operationvolume;

    @DatabaseField
    private boolean operationvolumeint;

    @DatabaseField
    private String practical;

    @DatabaseField
    private boolean practicalint;

    @DatabaseField
    private String physicalprogresstype;

    @DatabaseField
    private int physicalprogresstypeint;

    @DatabaseField
    private String expressingopinionsproviding;

    @DatabaseField
    private String causesdelay;

    @DatabaseField
    private String geometry;

    @DatabaseField
    private String wkt;

    @DatabaseField
    private double lat;

    @DatabaseField
    private double lng;

    @DatabaseField
    private String image;

    @DatabaseField
    private String documents;

    public ExecutionProjectInspection() {
    }

    public long getCompanyid() {
        return companyid;
    }

    public void setCompanyid(long companyid) {
        this.companyid = companyid;
    }

    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(String modifieddate) {
        this.modifieddate = modifieddate;
    }

    public long getExecutionprojectinspectionid() {
        return executionprojectinspectionid;
    }

    public void setExecutionprojectinspectionid(long executionprojectinspectionid) {
        this.executionprojectinspectionid = executionprojectinspectionid;
    }

    public long getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(long projectcode) {
        this.projectcode = projectcode;
    }

    public double getRialprogress() {
        return rialprogress;
    }

    public void setRialprogress(double rialprogress) {
        this.rialprogress = rialprogress;
    }

    public double getPercentageprogressfirstyear() {
        return percentageprogressfirstyear;
    }

    public void setPercentageprogressfirstyear(double percentageprogressfirstyear) {
        this.percentageprogressfirstyear = percentageprogressfirstyear;
    }

    public double getPhysicalprogressinspectiontime() {
        return physicalprogressinspectiontime;
    }

    public void setPhysicalprogressinspectiontime(double physicalprogressinspectiontime) {
        this.physicalprogressinspectiontime = physicalprogressinspectiontime;
    }

    public String getExecutionstatus() {
        return executionstatus;
    }

    public void setExecutionstatus(String executionstatus) {
        this.executionstatus = executionstatus;
    }

    public int getExecutionstatusint() {
        return executionstatusint;
    }

    public void setExecutionstatusint(int executionstatusint) {
        this.executionstatusint = executionstatusint;
    }

    public String getPerformancequality() {
        return performancequality;
    }

    public void setPerformancequality(String performancequality) {
        this.performancequality = performancequality;
    }

    public int getPerformancequalityint() {
        return performancequalityint;
    }

    public void setPerformancequalityint(int performancequalityint) {
        this.performancequalityint = performancequalityint;
    }

    public String getHowreferwork() {
        return howreferwork;
    }

    public void setHowreferwork(String howreferwork) {
        this.howreferwork = howreferwork;
    }

    public int getHowreferworkint() {
        return howreferworkint;
    }

    public void setHowreferworkint(int howreferworkint) {
        this.howreferworkint = howreferworkint;
    }

    public String getContractratebasics() {
        return contractratebasics;
    }

    public void setContractratebasics(String contractratebasics) {
        this.contractratebasics = contractratebasics;
    }

    public int getContractratebasicsint() {
        return contractratebasicsint;
    }

    public void setContractratebasicsint(int contractratebasicsint) {
        this.contractratebasicsint = contractratebasicsint;
    }

    public String getTechnicalspecifications() {
        return technicalspecifications;
    }

    public void setTechnicalspecifications(String technicalspecifications) {
        this.technicalspecifications = technicalspecifications;
    }

    public boolean getTechnicalspecificationsint() {
        return technicalspecificationsint;
    }

    public void setTechnicalspecificationsint(boolean technicalspecificationsint) {
        this.technicalspecificationsint = technicalspecificationsint;
    }

    public String getOperationvolume() {
        return operationvolume;
    }

    public void setOperationvolume(String operationvolume) {
        this.operationvolume = operationvolume;
    }

    public boolean getOperationvolumeint() {
        return operationvolumeint;
    }

    public void setOperationvolumeint(boolean operationvolumeint) {
        this.operationvolumeint = operationvolumeint;
    }

    public String getPractical() {
        return practical;
    }

    public void setPractical(String practical) {
        this.practical = practical;
    }

    public boolean getPracticalint() {
        return practicalint;
    }

    public void setPracticalint(boolean practicalint) {
        this.practicalint = practicalint;
    }

    public String getPhysicalprogresstype() {
        return physicalprogresstype;
    }

    public void setPhysicalprogresstype(String physicalprogresstype) {
        this.physicalprogresstype = physicalprogresstype;
    }

    public int getPhysicalprogresstypeint() {
        return physicalprogresstypeint;
    }

    public void setPhysicalprogresstypeint(int physicalprogresstypeint) {
        this.physicalprogresstypeint = physicalprogresstypeint;
    }

    public String getExpressingopinionsproviding() {
        return expressingopinionsproviding;
    }

    public void setExpressingopinionsproviding(String expressingopinionsproviding) {
        this.expressingopinionsproviding = expressingopinionsproviding;
    }

    public String getCausesdelay() {
        return causesdelay;
    }

    public void setCausesdelay(String causesdelay) {
        this.causesdelay = causesdelay;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }
}