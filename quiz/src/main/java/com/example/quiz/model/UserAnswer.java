package com.example.quiz.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sessionId;

    private Long questionId;

    private String submittedAnswer;

    private boolean isCorrect;
}
