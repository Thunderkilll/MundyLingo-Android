package com.dev.thunderkilll.mundylingo.Models;

public class Langue {

    String title ;
    String image ;
    String score ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }



    public Langue(String title, String image, String score) {
        this.title = title;
        this.image = image;
        this.score = score;
    }

    public Langue() {
    }

    @Override
    public String toString() {
        return "Langue{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
