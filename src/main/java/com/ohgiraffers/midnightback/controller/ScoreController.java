package com.ohgiraffers.midnightback.controller;

import java.util.concurrent.CompletableFuture;

import com.ohgiraffers.midnightback.dto.ExternalApiRequestDto;
import com.ohgiraffers.midnightback.dto.ExternalApiResponseDto;
import com.ohgiraffers.midnightback.dto.ScoreRequestDto;
import com.ohgiraffers.midnightback.service.ExternalApiService;
import com.ohgiraffers.midnightback.service.ResultService;
import com.ohgiraffers.midnightback.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/result")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ExternalApiService externalApiService;

    @Autowired
    private ResultService resultService;

    @PostMapping
    public CompletableFuture<ResponseEntity<ExternalApiResponseDto>> processResult(@RequestBody ScoreRequestDto requestDto) {
        // 점수 계산
        int score = scoreService.calculateScore(requestDto);
        String result = scoreService.evaluateResult(score);

        // 외부 API에 요청 보낼 데이터 생성
        ExternalApiRequestDto externalRequest = new ExternalApiRequestDto();
        externalRequest.setText1(requestDto.getText1());
        externalRequest.setText2(requestDto.getText2());
        externalRequest.setText3(requestDto.getText3());
        externalRequest.setText4(requestDto.getText7());
        externalRequest.setText5(result);

        System.out.print(externalRequest);

        // 비동기 외부 API 요청 및 결과 반환
        return externalApiService.sendRequest(externalRequest)
                .thenApply(externalResponse -> {
                    // 결과 저장
                    resultService.saveResult(externalResponse);
                    // 클라이언트에게 응답 반환
                    return ResponseEntity.ok(externalResponse);
                });
    }
}
