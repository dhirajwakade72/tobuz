package com.tobuz.model;

import javax.persistence.Entity;

@Entity
public class NewsLetterSubscription extends BaseEntity {

    private String email;

    private Integer appUserId;

    private Integer roleId;

    private Integer cityId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
