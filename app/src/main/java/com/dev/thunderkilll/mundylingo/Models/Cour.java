package com.dev.thunderkilll.mundylingo.Models;

public class Cour {
    private String id ;
    private String idLevel ;
    private String grammaire ;
    private String conjugaison ;
    private String orthographe ;
    private String langue;
 //TODO : getters and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(String idLevel) {
        this.idLevel = idLevel;
    }

    public String getGrammaire() {
        return grammaire;
    }

    public void setGrammaire(String grammaire) {
        this.grammaire = grammaire;
    }

    public String getConjugaison() {
        return conjugaison;
    }

    public void setConjugaison(String conjugaison) {
        this.conjugaison = conjugaison;
    }

    public String getOrthographe() {
        return orthographe;
    }

    public void setOrthographe(String orthographe) {
        this.orthographe = orthographe;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }
    //TODO : Constructeurs par default and parametrer




    public Cour(String id, String idLevel, String grammaire, String conjugaison, String orthographe, String langue) {
        this.id = id;
        this.idLevel = idLevel;
        this.grammaire = grammaire;
        this.conjugaison = conjugaison;
        this.orthographe = orthographe;
        this.langue = langue;
    }

    //constructeurs pour les grammaires kahaw
    public Cour(String id , String langue, String idLevel, String grammaire) {
        this.id = id;
        this.langue = langue;
        this.idLevel = idLevel;
        this.grammaire = grammaire;
    }
    public Cour(String id,   String grammaire, String conjugaison, String orthographe, String langue) {
        this.id = id;
        this.grammaire = grammaire;
        this.conjugaison = conjugaison;
        this.orthographe = orthographe;
        this.langue = langue;
    }


    //constructeur vide
    public Cour() {
    }
    //toString()


    @Override
    public String toString() {
        return "Cour{" +
                "id='" + id + '\'' +
                ", idLevel='" + idLevel + '\'' +
                ", grammaire='" + grammaire + '\'' +
                ", conjugaison='" + conjugaison + '\'' +
                ", orthographe='" + orthographe + '\'' +
                ", langue='" + langue + '\'' +
                '}';
    }
}
