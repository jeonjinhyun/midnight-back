package com.ohgiraffers.midnightback.dto;

import org.springframework.web.multipart.MultipartFile;

public class ExternalApiResponseDto {
    private String text;
    private byte[] img;
    private MultipartFile multipartFile;

    public ExternalApiResponseDto() {
    }

    public ExternalApiResponseDto(String text, byte[] img) {
        this.text = text;
        this.img = img;
    }

    public String getText() {
        if (this.text != null) {
            return this.text.replace("\\", ""); // '\' 문자를 제거합니다.
        }
        return null;
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