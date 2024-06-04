package com.example.prototipo2;

import java.io.Serializable;

public class HelperClass implements Serializable {

    private String name;
    private String email;
    private String password;
    private int age;
    private float weight;
    private float height;
    private String gender;
    private String goal;
    private double tmb;
    private double imc;

    // Construtores, getters e setters

    public HelperClass(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters e setters para todos os campos
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public double getTmb() {
        return tmb;
    }

    public void setTmb(double tmb) {
        this.tmb = tmb;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }
}
