package com.example.cadastro;

public class Cadastro {

    public int id;

    public String nome, estados, sobrenome, idade;

    public Cadastro(){

    }

    public Cadastro(String nome, String sobrenome, String estados, String idade) {
        this.nome = nome;
        this.estados = estados;
        this.sobrenome = sobrenome;
        this.idade = idade;
    }

    @Override
    public String toString(){
        return "Nome(Name): " + nome
                + " | "
                + "sobrenome(Surname): " + sobrenome
                + " | "
                + "Idade(Age): " + idade
                + " | "
                + "Estado(States): " + estados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstados() {
        return estados;
    }

    public void setEstados(String estados) {
        this.estados = estados;
    }

    public String getSobrenome() { return sobrenome; }

    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getIdade() { return idade; }

    public void setIdade(String idade) { this.idade = idade; }
}
