package com.JanCode.SKPplus.model;

public enum IconType {
    FILE("fa-file-alt","bg-primary") ,
    DONATE("fa-donate","bg-success"),
    EXCLAMATION_TRIANGLE("fa-exclamation-triangle","bg-warning");

    public final String labelName;
    public final String labelBack;
    IconType(String labelName,String labelBack) {
        this.labelName = labelName;
        this.labelBack = labelBack;
    }
}
