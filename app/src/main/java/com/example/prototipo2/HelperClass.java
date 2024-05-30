package com.example.prototipo2;

import java.io.Serializable;

public class HelperClass implements Serializable {
    private String nome;
    private String email;
    private String senha;
    private String gender;
    private int age;
    private float weight;
    private float height;
    private String goal;
    private double imc;
    private double tmb;
    private String nivelDeAtividade;

    public HelperClass() {
        // Construtor vazio necessário para Firebase
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public double getTmb() {
        return tmb;
    }

    public void setTmb(double tmb) {
        this.tmb = tmb;
    }

    public String getNivelDeAtividade() {
        return nivelDeAtividade;
    }

    public void setNivelDeAtividade(String nivelDeAtividade) {
        this.nivelDeAtividade = nivelDeAtividade;
    }
}