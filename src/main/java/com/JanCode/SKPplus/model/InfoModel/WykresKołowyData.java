package com.JanCode.SKPplus.model.InfoModel;

public class WykresKołowyData {
    private int osobowe;
    private int ciezarowe;
    private int inne;

    public WykresKołowyData(int osobowe, int ciezarowe, int inne) {
        this.osobowe = osobowe;
        this.ciezarowe = ciezarowe;
        this.inne = inne;
    }

    public WykresKołowyData() {
    }

    public int getOsobowe() {
        return osobowe;
    }

    public void setOsobowe(int osobowe) {
        this.osobowe = osobowe;
    }

    public int getCiezarowe() {
        return ciezarowe;
    }

    public void setCiezarowe(int ciezarowe) {
        this.ciezarowe = ciezarowe;
    }

    public int getInne() {
        return inne;
    }

    public void setInne(int inne) {
        this.inne = inne;
    }
}
