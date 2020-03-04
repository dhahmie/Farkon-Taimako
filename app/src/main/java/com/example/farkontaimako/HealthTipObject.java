package com.example.farkontaimako;

public class HealthTipObject {

    String imgURL, tipTitle, tipSymptoms, tipPrevention, tipCure, expertsNumber;

    public HealthTipObject(String imgURL, String tipTitle, String tipSymptoms, String tipPrevention, String tipCure, String expertsNumber) {
        this.imgURL = imgURL;
        this.tipTitle = tipTitle;
        this.tipSymptoms = tipSymptoms;
        this.tipPrevention = tipPrevention;
        this.tipCure = tipCure;
        this.expertsNumber = expertsNumber;
    }

    public HealthTipObject() {
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getTipTitle() {
        return tipTitle;
    }

    public String getTipSymptoms() {
        return tipSymptoms;
    }

    public String getTipPrevention() {
        return tipPrevention;
    }

    public String getTipCure() {
        return tipCure;
    }

    public String getExpertsNumber() {
        return expertsNumber;
    }
}
