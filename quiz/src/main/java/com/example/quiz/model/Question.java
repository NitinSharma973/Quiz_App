package com.example.quiz.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String correctAnswer;
}
