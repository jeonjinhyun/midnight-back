package com.ohgiraffers.midnightback.service;

import com.ohgiraffers.midnightback.dto.ExternalApiResponseDto;
import com.ohgiraffers.midnightback.entity.Result;
import com.ohgiraffers.midnightback.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public void saveResult(ExternalApiResponseDto responseDto) {
        Result result = new Result();
        result.setText(responseDto.getText());
        result.setImg(responseDto.getImg());
        resultRepository.save(result);
    }
}