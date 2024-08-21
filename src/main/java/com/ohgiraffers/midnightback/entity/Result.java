package com.ohgiraffers.midnightback.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;
    @Column(name = "choice_text")
    private String text;
    @Column(name = "choice_img")
    private byte[] img;

    public Result() {
    }

    public Result(String text, byte[] img) {
        this.text = text;
        this.img = img;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}