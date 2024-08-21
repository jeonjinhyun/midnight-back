package com.ohgiraffers.midnightback.controller;

import java.util.Map;
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
    public CompletableFuture<ResponseEntity<ExternalApiResponseDto>> processResult(@RequestBody Map<String, String> requestMap) {

        System.out.println(requestMap);

        ScoreRequestDto requestDto = new ScoreRequestDto();
        // select1, select2 중 첫 번째로 발견되는 값을 text1에 할당
        requestDto.setText1(getFirstValue(requestMap, "select1", "select2"));

        // select2_1, select2_2, select2_3 중 첫 번째로 발견되는 값을 text2에 할당
        requestDto.setText2(getFirstValue(requestMap, "select2_1", "select2_2", "select2_3"));

        // select3_1, select3_2 중 첫 번째로 발견되는 값을 text3에 할당
        requestDto.setText3(getFirstValue(requestMap, "select3_1", "select3_2"));

        // Answer1_1 ~ Answer3_2 중 첫 번째로 발견되는 값을 text4에 할당
        requestDto.setText4(getFirstValue(requestMap, "Answer1_1", "Answer1_2", "Answer2_1", "Answer2_2", "Answer3_1", "Answer3_2"));

        // Answer1_3 ~ Answer3_4 중 첫 번째로 발견되는 값을 text5에 할당
        requestDto.setText5(getFirstValue(requestMap, "Answer1_3", "Answer1_4", "Answer2_3", "Answer2_4", "Answer3_3", "Answer3_4"));

        // Answer1_5 ~ Answer3_6 중 첫 번째로 발견되는 값을 text6에 할당
        requestDto.setText6(getFirstValue(requestMap, "Answer1_5", "Answer1_6", "Answer2_5", "Answer2_6", "Answer3_5", "Answer3_6"));

        System.out.println(requestDto);

        int score = scoreService.calculateScore(requestDto);
        String result = scoreService.evaluateResult(score);

        ExternalApiRequestDto externalRequest = new ExternalApiRequestDto();
        externalRequest.setText1(requestDto.getText1());
        externalRequest.setText2(requestDto.getText2());
        externalRequest.setText3(requestDto.getText3());
        externalRequest.setText4(result);

        return externalApiService.sendRequest(externalRequest)
                .thenApply(externalResponse -> {
                    resultService.saveResult(externalResponse);
                    return ResponseEntity.ok(externalResponse);
                });
    }
    public static boolean containsKey(Map<String, String> map, String key) {
        return map.containsKey(key);
    }

    private String getFirstValue(Map<String, String> map, String... keys) {
        for (String key : keys) {
            if (map.containsKey(key)) {
                return map.get(key);
            }
        }
        return null;  // 해당 키가 없을 경우 null 반환
    }
}
