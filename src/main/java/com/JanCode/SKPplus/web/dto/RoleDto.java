package com.JanCode.SKPplus.web.dto;

public enum RoleDto {
    /*USER("User","green") ,
    ADMIN("Admin","red"),
    KSIEGOWOSC("Księgowość","yellow"),
    DIAGNOSTYKA("Diagnostyka","blue");
    public final String roleName;
    public final String color;
    RoleDto(String roleName,String color) {
        this.roleName = roleName;
        this.color = color;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getColor() {
        return color;
    }

     */
    USER("User") ,
    ADMIN("Admin"),
    KSIEGOWOSC("Księgowość"),
    DIAGNOSTYKA("Diagnostyka");
    public final String roleName;
    RoleDto(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
