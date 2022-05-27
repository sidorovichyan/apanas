package com.example.apanas.entity;

import java.util.Objects;

public class Triangle {

    private Integer firstStoron;

    private Integer secondStoron;

    private Integer thidStoron;

    public Triangle()
    {
        this.firstStoron = 1;
        this.secondStoron = 1;
        this.thidStoron = 1;
    }

    public Triangle(Integer firstStoron, Integer secondStoron, Integer thidStoron) {
        this.firstStoron = firstStoron;
        this.secondStoron = secondStoron;
        this.thidStoron = thidStoron;
    }

    public Integer getFirstStoron() {
        return firstStoron;
    }

    public void setFirstStoron(Integer firstStoron) {
        this.firstStoron = firstStoron;
    }

    public Integer getSecondStoron() {
        return secondStoron;
    }

    public void setSecondStoron(Integer secondStoron) {
        this.secondStoron = secondStoron;
    }

    public Integer getThidStoron() {
        return thidStoron;
    }

    public void setThidStoron(Integer thidStoron) {
        this.thidStoron = thidStoron;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "firstStoron=" + firstStoron +
                ", secondStoron=" + secondStoron +
                ", thidStoron=" + thidStoron +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(getFirstStoron(), triangle.getFirstStoron()) && Objects.equals(getSecondStoron(), triangle.getSecondStoron()) && Objects.equals(getThidStoron(), triangle.getThidStoron());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstStoron(), getSecondStoron(), getThidStoron());
    }
}
