package com.example.martinzou.appgather_rebuild.entity;

import java.io.Serializable;

/**
 * Created by 10630 on 2018/4/3.
 */


//应用分类标签
public class Classify implements Serializable {
    private String className;
    private String type;
    private boolean isSelected=false;
    //构造函数
    public Classify(){}

    public Classify(String classname, String type) {
        this.className = classname;
        this.type = type;
    }

    public Classify(String classname, String type, boolean isSelected) {
        this.className = classname;
        this.type = type;
        this.isSelected = isSelected;
    }

    public String getClassname() {
        return className;
    }

    public void setClassname(String classname) {
        this.className = classname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "className='" + className + '\'' +
                ", type=" + type +
                '}';
    }
}
