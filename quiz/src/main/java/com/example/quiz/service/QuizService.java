package com.example.quiz.service;

import com.example.quiz.model.Question;
import com.example.quiz.model.UserAnswer;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

@Service
public class QuizService {

    private static final Logger logger = Logger.getLogger(QuizService.class.getName());

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    // Start a new session
    public Long startNewSession() {
        return System.currentTimeMillis(); // Use timestamp as session ID
    }

    // Get a random question
    public Optional<Question> getRandomQuestion() {
        try {
            List<Question> questions = questionRepository.findAll();

            if (questions.isEmpty()) {
                logger.warning("No questions found in the database.");
                return Optional.empty();  // If no questions are in the database, return an empty Optional
            }

            Random random = new Random();
            Question randomQuestion = questions.get(random.nextInt(questions.size()));

            return Optional.of(randomQuestion);
        } catch (Exception e) {
            logger.severe("Error fetching random question: " + e.getMessage());
            throw new RuntimeException("Error fetching random question", e);  // Propagate the error up
        }
    }

    // Submit an answer for a question
    public UserAnswer submitAnswer(Long sessionId, Long questionId, String submittedAnswer) {
        try {
            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(submittedAnswer);

            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setSessionId(sessionId);
            userAnswer.setQuestionId(questionId);
            userAnswer.setSubmittedAnswer(submittedAnswer);
            userAnswer.setCorrect(isCorrect);

            return userAnswerRepository.save(userAnswer);
        } catch (RuntimeException e) {
            logger.severe("Error submitting answer for session " + sessionId + " and question " + questionId + ": " + e.getMessage());
            throw e;  // Rethrow exception after logging it
        } catch (Exception e) {
            logger.severe("Unexpected error while submitting answer: " + e.getMessage());
            throw new RuntimeException("Unexpected error while submitting answer", e);
        }
    }

    // Get a summary of the answers for a session
    public List<UserAnswer> getSessionSummary(Long sessionId) {
        try {
            return userAnswerRepository.findBySessionId(sessionId);
        } catch (Exception e) {
            logger.severe("Error fetching session summary for session " + sessionId + ": " + e.getMessage());
            throw new RuntimeException("Error fetching session summary", e);
        }
    }
}
