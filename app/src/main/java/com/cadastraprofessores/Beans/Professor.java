package com.cadastraprofessores.Beans;

import java.io.Serializable;

/*
* Classe que representa a entidade
* */
public class Professor implements Serializable {

    //Atributos
    private int id;
    private String nome;
    private String email;
    private String materia;
    private String telefone;

    /*
     *Construtor vazio
     * */
    public Professor() {

    }
    /*
     * Construtor
     * */
    public Professor(String nome, String materia, String email, String telefone, int id) {
        this.nome = nome;
        this.materia = materia;
        this.email = email;
        this.telefone = telefone;
        this.id = id;
    }

    //getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    /*
     * ToString
     * */
    @Override
    public String toString() {
        return "Professor{" +
                "nome='" + nome + '\'' +
                ", materia='" + materia + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", id=" + id +
                '}';
    }
}
