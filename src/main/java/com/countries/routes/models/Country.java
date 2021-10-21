package com.countries.routes.models;

/**
 * Country
 */
public class Country {
    private String cca3;

    private String[] borders;

    public Country() {
    }

    public String getCca3() {
        return this.cca3;
    }

    public void setCca3(String cca3) {
        this.cca3 = cca3;
    }

    public String[] getBorders() {
        return this.borders;
    }

    public void setBorders(String[] borders) {
        this.borders = borders;
    }

    @Override
    public String toString() {
        return "Country [code=" + cca3 + "]";
    }

}