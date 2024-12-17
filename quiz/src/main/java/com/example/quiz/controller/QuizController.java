package com.example.quiz.controller;

import com.example.quiz.model.Question;
import com.example.quiz.model.UserAnswer;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/start")
    public Long startNewSession() {
        return quizService.startNewSession();
    }

    @GetMapping("/question")
    public Optional<Question> getRandomQuestion() {
        return quizService.getRandomQuestion();
    }

    @PostMapping("/submit")
    public UserAnswer submitAnswer(@RequestBody UserAnswerDto userAnswerDto) {
        // Ensure you have a DTO that holds the sessionId, questionId, and answer.
        return quizService.submitAnswer(userAnswerDto.getSessionId(), userAnswerDto.getQuestionId(), userAnswerDto.getAnswer());
    }

    @GetMapping("/summary")
    public List<UserAnswer> getSessionSummary(@RequestParam Long sessionId) {
        return quizService.getSessionSummary(sessionId);
    }

    // DTO for receiving the submitted answer (this can be replaced with your actual DTO structure)
    public static class UserAnswerDto {
        private Long sessionId;
        private Long questionId;
        private String answer;

        // Getters and setters
        public Long getSessionId() {
            return sessionId;
        }

        public void setSessionId(Long sessionId) {
            this.sessionId = sessionId;
        }

        public Long getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Long questionId) {
            this.questionId = questionId;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
