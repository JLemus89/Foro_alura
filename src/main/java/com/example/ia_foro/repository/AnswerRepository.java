package com.example.ia_foro.repository;

import com.example.ia_foro.model.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
