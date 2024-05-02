package com.itacademy.aqa.cucumber;

public class Menu {
    private String title;
    private Boolean isAvailable;

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSubMenuCount() {
        return subMenuCount;
    }

    public void setSubMenuCount(int subMenuCount) {
        this.subMenuCount = subMenuCount;
    }

    private int subMenuCount;
}
