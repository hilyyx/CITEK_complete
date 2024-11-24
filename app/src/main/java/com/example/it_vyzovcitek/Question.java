package com.example.it_vyzovcitek;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {
    private String question;
    private String[] answers;
    private int correctAnswerIndex;
    private String explanation; // Новое поле для пояснения

    public Question(String question, String[] answers, int correctAnswerIndex, String explanation) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        this.explanation = explanation;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getShuffledAnswers() {
        // Перемешиваем ответы и возвращаем их
        List<String> shuffledAnswers = Arrays.asList(answers.clone());
        Collections.shuffle(shuffledAnswers);
        return shuffledAnswers.toArray(new String[0]);
    }

    public boolean isCorrectAnswer(String selectedAnswer) {
        return selectedAnswer.equals(answers[correctAnswerIndex]);
    }

    public String getExplanation() {
        return explanation;
    }
}
