package com.example.staterecycler;

import java.io.Serializable;

public class DistrictClass implements Serializable {
    private String name;
    private String active;
    private String deceased;
    private String confirmed;
    private String recovered;

    public DistrictClass(String name, String active, String deceased, String confirmed, String recovered) {
        this.name = name;
        this.active = active;
        this.deceased = deceased;
        this.confirmed = confirmed;
        this.recovered = recovered;
    }

    public DistrictClass() {
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDeceased() {
        return deceased;
    }

    public void setDeceased(String deceased) {
        this.deceased = deceased;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
