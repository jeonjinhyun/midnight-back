package com.ohgiraffers.midnightback.repository;

import com.ohgiraffers.midnightback.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    Optional<Choice> findByText(String text);
}
