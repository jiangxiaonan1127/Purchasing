package com.turing.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Enquire implements Serializable {
    private Long id;

    private String enquireNum;

    private String enquireName;

    private String company;

    private String linkman;

    private String address;

    private String phone;

    private String fax;

    private String zip;

    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String remark;
    //询价开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    //编号对照表对应的状态
    private String status;
    //询价书对应采购计划类型
    private String stockType;

    private static final long serialVersionUID = 1L;

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnquireNum() {
        return enquireNum;
    }

    public void setEnquireNum(String enquireNum) {
        this.enquireNum = enquireNum == null ? null : enquireNum.trim();
    }

    public String getEnquireName() {
        return enquireName;
    }

    public void setEnquireName(String enquireName) {
        this.enquireName = enquireName == null ? null : enquireName.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}