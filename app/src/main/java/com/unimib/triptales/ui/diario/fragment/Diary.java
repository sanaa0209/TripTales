package com.unimib.triptales.ui.diario.fragment;

import android.graphics.Bitmap;

public class Diary {
    private String name;
    private String startDate;
    private String endDate;
    private Bitmap image;

    // Costruttore
    public Diary(String name, String startDate, String endDate, Bitmap image) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
