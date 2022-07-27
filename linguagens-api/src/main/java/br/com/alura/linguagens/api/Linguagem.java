package br.com.alura.linguagens.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "principaisLinguaguens")
public class Linguagem {

    @Id
    private String id;
    private String title;
    private String image;
    private int votes;

    public Linguagem(String title, String image, int votes) {
        this.title = title;
        this.image = image;
        this.votes = votes;
    }

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getImage() {
        return image;
    }
    public int getVotes() {
        return votes;
    }

    public void setVotes(int ranking) {
        this.votes = ranking;
    }
}