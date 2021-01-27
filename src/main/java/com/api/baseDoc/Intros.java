package com.api.baseDoc;

/**
 * @author yibo
 */

public class Intros {
    private String name;
    private String des;
    private String isRequired;

    public String getName() {
        return name;
    }

    public Intros setName(String name) {
        this.name = name;
        return this;
    }

    public String getDes() {
        return des;
    }

    public Intros setDes(String des) {
        this.des = des;
        return this;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public Intros setIsRequired(String isRequired) {
        this.isRequired = isRequired;
        return this;
    }
}
