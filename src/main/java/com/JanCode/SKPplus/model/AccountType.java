package com.JanCode.SKPplus.model;

public enum AccountType {
    USER("User") ,
    ADMIN("Admin"),
    KSIEGOWOSC("Księgowość"),
    DIAGNOSTYKA("Diagnostyka");
    public final String roleName;
    AccountType(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
