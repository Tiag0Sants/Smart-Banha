package com.example.prototipo2;

public class HelperClass {
    String nome, email, senha;

    public HelperClass(String nome, String email, String senha) {
    }

    public String getName() {
        return nome;
    }
    public void setName(String name) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return senha;
    }
    public void setPassword(String password) {
        this.senha = senha;
    }
    public HelperClass(String name, String email, String username, String password) {
        this.nome = name;
        this.email = email;
        this.senha = password;
    }
    public HelperClass() {
    }
}
