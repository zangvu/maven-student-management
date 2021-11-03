package com.giangvt.school;

public class Student {
    private String id;
    private String name;
    private float maths;
    private float physics;
    private float english;

    public Student() {
    }

    public Student(String id, String name, float maths, float physics, float english) {
        this.id = id;
        this.name = name;
        this.maths = maths;
        this.physics = physics;
        this.english = english;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMaths() {
        return maths;
    }

    public void setMaths(float maths) {
        this.maths = maths;
    }

    public float getPhysics() {
        return physics;
    }

    public void setPhysics(float physics) {
        this.physics = physics;
    }

    public float getEnglish() {
        return english;
    }

    public void setEnglish(float english) {
        this.english = english;
    }

    public float getGPA() {
        return (this.maths + this.physics + this.english) / 3;
    }
    public void printProfile() {
        System.out.printf("|%8s|%-10s|%5.1f|%7.1f|%7.1f|%4.1f|\n", id, name, maths, physics, english, this.getGPA());
    }
}
