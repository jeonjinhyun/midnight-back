package com.ohgiraffers.midnightback.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_choice")
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private Long id;

    @Column(name = "choice_text")
    private String text;
    @Column(name = "choice_score")
    private int score;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Choice() {
    }

    public Choice(String text, int score) {
        this.text = text;
        this.score = score;
    }
}

