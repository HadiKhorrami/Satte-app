package com.example.satte.db.tables;

import androidx.room.PrimaryKey;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "project")
public class Project {
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
    ///////////////////////////////////////////////
     ///// Pk Fields /////
    @DatabaseField
    private long localprojectid;

    @DatabaseField(id = true)
    private long projectid;

    @DatabaseField
    private long parentprojectid;
    ///////////////////////////////////////////////
    @DatabaseField
    private long projectcode;

    @DatabaseField
    private String title;

    @DatabaseField
    private String description;

    @DatabaseField
    private long plancode;

    @DatabaseField
    private String planname;
    ///////////////////////////////////////////////
    @DatabaseField
    private String projecttype;

    @DatabaseField
    private String projectstatus;

    @DatabaseField
    private String projectexecutetype;

    @DatabaseField
    private String projectrow;
    ///////////////////////////////////////////////
    @DatabaseField
    private String executeplacecode;

    @DatabaseField
    private String fasl;

    @DatabaseField
    private String bahrebardarnationalcode;

    @DatabaseField
    private String bahrebardarpersonname;

    @DatabaseField
    private String barname;

    @DatabaseField
    private String masternationalcode;

    @DatabaseField
    private String masterpersonname;
    ///////////////////////////////////////////////
    @DatabaseField
    private int endyear;

    @DatabaseField
    private int startyear;
    ///////////////////////////////////////////////
    @DatabaseField
    private long targetvalue;

    @DatabaseField
    private long sourceid;

    @DatabaseField
    private String sourcename;

    @DatabaseField
    private String cofogcode;
   ///////////////////////////////////////////////
   @DatabaseField
   private double creditAllocation;

    @DatabaseField
    private double creditApproval;

    @DatabaseField
    private double creditExchanged;

    @DatabaseField
    private double projectcredit;

    @DatabaseField
    private double creditcurrentyear;
    @DatabaseField

    private double approvedlastyear;

    @DatabaseField
    private double alllocationlastyeartotal;

    @DatabaseField
    private double functionlastyeartotal;

    @DatabaseField
    private double paiduptodatepreviousyear;

    @DatabaseField
    private double creditrequired;

    @DatabaseField
    private int periodtime;

    @DatabaseField
    private double contractPercentage;

    @DatabaseField
    private String exchangestatus;

    @DatabaseField
    private String projectimplementationstatus;
    ///////////////////////////////////////////////////
    @DatabaseField
    private String tagsimatname;

    @DatabaseField
    private String targetname;

    @DatabaseField
    private String targetunit;

    @DatabaseField
    private String tagsimatcode;

    @DatabaseField
    private String geometry;
    ////////////////////////////////////////////////
    @DatabaseField
    private String wkt;
    @DatabaseField
    private double lat;
    @DatabaseField
    private double lng;
    /////////////////////////////////////////////////
    @DatabaseField
    private String omur;

    public Project() {
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

    public long getLocalprojectid() {
        return localprojectid;
    }

    public void setLocalprojectid(long localprojectid) {
        this.localprojectid = localprojectid;
    }

    public long getProjectid() {
        return projectid;
    }

    public void setProjectid(long projectid) {
        this.projectid = projectid;
    }

    public long getParentprojectid() {
        return parentprojectid;
    }

    public void setParentprojectid(long parentprojectid) {
        this.parentprojectid = parentprojectid;
    }

    public long getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(long projectcode) {
        this.projectcode = projectcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPlancode() {
        return plancode;
    }

    public void setPlancode(long plancode) {
        this.plancode = plancode;
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public String getProjectstatus() {
        return projectstatus;
    }

    public void setProjectstatus(String projectstatus) {
        this.projectstatus = projectstatus;
    }

    public String getProjectexecutetype() {
        return projectexecutetype;
    }

    public void setProjectexecutetype(String projectexecutetype) {
        this.projectexecutetype = projectexecutetype;
    }

    public String getProjectrow() {
        return projectrow;
    }

    public void setProjectrow(String projectrow) {
        this.projectrow = projectrow;
    }

    public String getExecuteplacecode() {
        return executeplacecode;
    }

    public void setExecuteplacecode(String executeplacecode) {
        this.executeplacecode = executeplacecode;
    }

    public String getFasl() {
        return fasl;
    }

    public void setFasl(String fasl) {
        this.fasl = fasl;
    }

    public String getBahrebardarnationalcode() {
        return bahrebardarnationalcode;
    }

    public void setBahrebardarnationalcode(String bahrebardarnationalcode) {
        this.bahrebardarnationalcode = bahrebardarnationalcode;
    }

    public String getBahrebardarpersonname() {
        return bahrebardarpersonname;
    }

    public void setBahrebardarpersonname(String bahrebardarpersonname) {
        this.bahrebardarpersonname = bahrebardarpersonname;
    }

    public String getBarname() {
        return barname;
    }

    public void setBarname(String barname) {
        this.barname = barname;
    }

    public String getMasternationalcode() {
        return masternationalcode;
    }

    public void setMasternationalcode(String masternationalcode) {
        this.masternationalcode = masternationalcode;
    }

    public String getMasterpersonname() {
        return masterpersonname;
    }

    public void setMasterpersonname(String masterpersonname) {
        this.masterpersonname = masterpersonname;
    }

    public int getEndyear() {
        return endyear;
    }

    public void setEndyear(int endyear) {
        this.endyear = endyear;
    }

    public int getStartyear() {
        return startyear;
    }

    public void setStartyear(int startyear) {
        this.startyear = startyear;
    }

    public long getTargetvalue() {
        return targetvalue;
    }

    public void setTargetvalue(long targetvalue) {
        this.targetvalue = targetvalue;
    }

    public long getSourceid() {
        return sourceid;
    }

    public void setSourceid(long sourceid) {
        this.sourceid = sourceid;
    }

    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public String getCofogcode() {
        return cofogcode;
    }

    public void setCofogcode(String cofogcode) {
        this.cofogcode = cofogcode;
    }

    public double getCreditAllocation() {
        return creditAllocation;
    }

    public void setCreditAllocation(double creditAllocation) {
        this.creditAllocation = creditAllocation;
    }

    public double getCreditApproval() {
        return creditApproval;
    }

    public void setCreditApproval(double creditApproval) {
        this.creditApproval = creditApproval;
    }

    public double getCreditExchanged() {
        return creditExchanged;
    }

    public void setCreditExchanged(double creditExchanged) {
        this.creditExchanged = creditExchanged;
    }

    public double getProjectcredit() {
        return projectcredit;
    }

    public void setProjectcredit(double projectcredit) {
        this.projectcredit = projectcredit;
    }

    public double getCreditcurrentyear() {
        return creditcurrentyear;
    }

    public void setCreditcurrentyear(double creditcurrentyear) {
        this.creditcurrentyear = creditcurrentyear;
    }

    public double getApprovedlastyear() {
        return approvedlastyear;
    }

    public void setApprovedlastyear(double approvedlastyear) {
        this.approvedlastyear = approvedlastyear;
    }

    public double getAlllocationlastyeartotal() {
        return alllocationlastyeartotal;
    }

    public void setAlllocationlastyeartotal(double alllocationlastyeartotal) {
        this.alllocationlastyeartotal = alllocationlastyeartotal;
    }

    public double getFunctionlastyeartotal() {
        return functionlastyeartotal;
    }

    public void setFunctionlastyeartotal(double functionlastyeartotal) {
        this.functionlastyeartotal = functionlastyeartotal;
    }

    public double getPaiduptodatepreviousyear() {
        return paiduptodatepreviousyear;
    }

    public void setPaiduptodatepreviousyear(double paiduptodatepreviousyear) {
        this.paiduptodatepreviousyear = paiduptodatepreviousyear;
    }

    public double getCreditrequired() {
        return creditrequired;
    }

    public void setCreditrequired(double creditrequired) {
        this.creditrequired = creditrequired;
    }

    public int getPeriodtime() {
        return periodtime;
    }

    public void setPeriodtime(int periodtime) {
        this.periodtime = periodtime;
    }

    public double getContractPercentage() {
        return contractPercentage;
    }

    public void setContractPercentage(double contractPercentage) {
        this.contractPercentage = contractPercentage;
    }

    public String getExchangestatus() {
        return exchangestatus;
    }

    public void setExchangestatus(String exchangestatus) {
        this.exchangestatus = exchangestatus;
    }

    public String getProjectimplementationstatus() {
        return projectimplementationstatus;
    }

    public void setProjectimplementationstatus(String projectimplementationstatus) {
        this.projectimplementationstatus = projectimplementationstatus;
    }

    public String getTagsimatname() {
        return tagsimatname;
    }

    public void setTagsimatname(String tagsimatname) {
        this.tagsimatname = tagsimatname;
    }

    public String getTargetname() {
        return targetname;
    }

    public void setTargetname(String targetname) {
        this.targetname = targetname;
    }

    public String getTargetunit() {
        return targetunit;
    }

    public void setTargetunit(String targetunit) {
        this.targetunit = targetunit;
    }

    public String getTagsimatcode() {
        return tagsimatcode;
    }

    public void setTagsimatcode(String tagsimatcode) {
        this.tagsimatcode = tagsimatcode;
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

    public String getOmur() {
        return omur;
    }

    public void setOmur(String omur) {
        this.omur = omur;
    }
}
