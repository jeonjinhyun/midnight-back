package com.ohgiraffers.midnightback.dto;

public class ExternalApiResponseDto {
    private String text;
    private byte[] img;

    public ExternalApiResponseDto() {
    }

    public ExternalApiResponseDto(String text, byte[] img) {
        this.text = text;
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}