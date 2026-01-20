package com.workintech.s17d2.model;

public class Developer {
    private int id;
    private String name;
    private double salary;
    private Experience experience;

    public Developer(int id,String name, double salary,Experience experience ) {
        this.id = id;
        this.experience = experience;
        this.salary = salary;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Experience getExperience() {
        return experience;
    }
}
