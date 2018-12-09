package com.dev.thunderkilll.mundylingo.Models;

public class Level {

    private String  id ;
    private String  diffeculte  ;
    private String  NumLVL ;
    private String   langue;
    private String  nbrQuestion ;

    //TODO : getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiffeculte() {
        return diffeculte;
    }

    public void setDiffeculte(String diffeculte) {
        this.diffeculte = diffeculte;
    }

    public String getNumLVL() {
        return NumLVL;
    }

    public void setNumLVL(String numLVL) {
        NumLVL = numLVL;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getNbrQuestion() {
        return nbrQuestion;
    }

    public void setNbrQuestion(String nbrQuestion) {
        this.nbrQuestion = nbrQuestion;
    }


    //TODO : constructeur vide et paramétrer


    public Level(String id, String diffeculte, String numLVL, String langue, String nbrQuestion) {
        this.id = id;
        this.diffeculte = diffeculte;
        NumLVL = numLVL;
        this.langue = langue;
        this.nbrQuestion = nbrQuestion;
    }

    public Level() {
    }

    @Override
    public String toString() {
        return "Level{" +
                "id='" + id + '\'' +
                ", diffeculte='" + diffeculte + '\'' +
                ", NumLVL='" + NumLVL + '\'' +
                ", langue='" + langue + '\'' +
                ", nbrQuestion='" + nbrQuestion + '\'' +
                '}';
    }
}
