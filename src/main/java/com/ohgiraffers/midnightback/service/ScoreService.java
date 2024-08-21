package com.ohgiraffers.midnightback.service;

import com.ohgiraffers.midnightback.dto.ScoreRequestDto;
import com.ohgiraffers.midnightback.entity.Choice;
import com.ohgiraffers.midnightback.repository.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ChoiceRepository choiceRepository;

    public int calculateScore(ScoreRequestDto requestDto) {
        int score = 0;
        score += getScoreByText(requestDto.getText1());
//        score += getScoreByText(requestDto.getText2());
//        score += getScoreByText(requestDto.getText3());
        score += getScoreByText(requestDto.getText4());
        score += getScoreByText(requestDto.getText5());
        score += getScoreByText(requestDto.getText6());
        score += getScoreByText(requestDto.getText7());
        return score;
    }

    private int getScoreByText(String text) {
        Optional<Choice> choice = choiceRepository.findByText(text);
        return choice.map(Choice::getScore).orElse(0);
    }

    public String evaluateResult(int score) {
        if (score >= 65) {
            return "좋은";
        } else {
            return "나쁜";
        }
    }
}


