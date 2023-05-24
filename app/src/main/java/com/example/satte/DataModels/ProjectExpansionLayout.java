package com.example.satte.DataModels;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class ProjectExpansionLayout {
    private long localprojectid;
    private long companyid;
private long groupid;
private long userid;
private String username;
private String createdate;
private String modifieddate;

/////////////// Pk Fields /////
private long projectid;
///////////////////////////////////////////////
private long parentprojectid;
 private long projectcode;
private String title;
private String description;
private long plancode;
private String planname;
///////////////////////////////////////////////
private String projecttype;
private String projectstatus;
private int projectexecutetype;
private int projectrow;
///////////////////////////////////////////////
private int executeplacecode;
private String fasl;
private String bahrebardarnationalcode;
private String bahrebardarpersonname;
private String masternationalcode;
private String masterpersonname;
///////////////////////////////////////////////
 private int endyear;
private int startyear;
///////////////////////////////////////////////
 private long targetid;
private long targetvalue;
private long sourceid;
private String sourcename;
private String cofogcode;
///////////////////////////////////////////////
 private double creditAllocation;
private double creditApproval;
private double creditExchanged;
private double projectcredit;
private double creditcurrentyear;
private double approvedlastyear;
private double alllocationlastyeartotal;
private double functionlastyeartotal;
private double paiduptodatepreviousyear;
private double creditrequired;
private int periodtime;
private double contractpercentage;
private int exchangestatus;
private int projectimplementationstatus;
///////////////////////////////////////////////////
private String tagsimatname;
private String targetname;
private String targetunit;
private String tagsimatcode;
private String geometry;
////////////////////////////////////////////////
 private double lat;
 private double lng;
 private String wkt;
 private String omur;
 /////////////////////////////////////////////////

    public long getLocalprojectid() {
        return localprojectid;
    }

    public void setLocalprojectid(long localprojectid) {
        this.localprojectid = localprojectid;
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

    public int getProjectexecutetype() {
        return projectexecutetype;
    }

    public void setProjectexecutetype(int projectexecutetype) {
        this.projectexecutetype = projectexecutetype;
    }

    public int getProjectrow() {
        return projectrow;
    }

    public void setProjectrow(int projectrow) {
        this.projectrow = projectrow;
    }

    public int getExecuteplacecode() {
        return executeplacecode;
    }

    public void setExecuteplacecode(int executeplacecode) {
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

    public long getTargetid() {
        return targetid;
    }

    public void setTargetid(long targetid) {
        this.targetid = targetid;
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

    public double getContractpercentage() {
        return contractpercentage;
    }

    public void setContractpercentage(double contractpercentage) {
        this.contractpercentage = contractpercentage;
    }

    public int getExchangestatus() {
        return exchangestatus;
    }

    public void setExchangestatus(int exchangestatus) {
        this.exchangestatus = exchangestatus;
    }

    public int getProjectimplementationstatus() {
        return projectimplementationstatus;
    }

    public void setProjectimplementationstatus(int projectimplementationstatus) {
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

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }

    public String getOmur() {
        return omur;
    }

    public void setOmur(String omur) {
        this.omur = omur;
    }
}

