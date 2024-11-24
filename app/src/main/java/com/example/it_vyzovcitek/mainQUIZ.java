package com.example.it_vyzovcitek;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class mainQUIZ extends AppCompatActivity {

    private TextView questionText;
    private ImageView image;
    private Button[] answerButtons = new Button[4];
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private String selectedCheckBox;
    private int filledCircles = 0;
    private int score;
    private int MAX_CIRCLES = 0;
    private LinearLayout row1, row2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);
        EdgeToEdge.enable(this);
        selectedCheckBox = getIntent().getStringExtra("selectedCheckBox");
        if (selectedCheckBox == null) {
            Log.d("YourTag", "selectedCheckBox is null");
        }

        // Инициализация UI компонентов
        questionText = findViewById(R.id.questionText);
        answerButtons[0] = findViewById(R.id.answerButton1);
        answerButtons[1] = findViewById(R.id.answerButton2);
        answerButtons[2] = findViewById(R.id.answerButton3);
        answerButtons[3] = findViewById(R.id.answerButton4);
        image = findViewById(R.id.imageView);
        LinearLayout circlesLayout = findViewById(R.id.circlesLayout1);
        row1 = new LinearLayout(this);
        row2 = new LinearLayout(this);
        row1.setOrientation(LinearLayout.HORIZONTAL);
        row2.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams circleParams = new LinearLayout.LayoutParams(110, 110);
        if (selectedCheckBox.equals("bow12")) {
            MAX_CIRCLES = 16;
            circleParams = new LinearLayout.LayoutParams(110, 110);
            circleParams.setMargins(8, 8, 8, 8);
            image.setImageResource(getResources().getIdentifier("level2", "drawable", getPackageName()));
        } else if (selectedCheckBox.equals("low12")) {
            MAX_CIRCLES = 10;
            circleParams = new LinearLayout.LayoutParams(130, 130);
            circleParams.setMargins(10, 10, 10, 10);
            image.setImageResource(getResources().getIdentifier("level1", "drawable", getPackageName()));
        }
        for (int i = 0; i < MAX_CIRCLES; i++) {
            FrameLayout circleContainer = new FrameLayout(this);
            ImageView circle = new ImageView(this);
            circle.setImageResource(R.drawable.circle_empty); // Пустой кружок
            circle.setLayoutParams(circleParams);
            TextView number = new TextView(this);
            number.setText(String.valueOf(i + 1)); // Отображение числа от 1 до MAX_CIRCLES
            number.setTextColor(Color.BLACK);
            number.setGravity(Gravity.CENTER);
            FrameLayout.LayoutParams textParams = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
            );
            textParams.gravity = Gravity.CENTER;
            number.setLayoutParams(textParams);
            circleContainer.addView(circle);
            circleContainer.addView(number);
            if (selectedCheckBox.equals("bow12")) {
                if (i < 8) {
                    row1.addView(circleContainer);
                } else {
                    row2.addView(circleContainer);
                }
            } else if (selectedCheckBox.equals("low12")){
                if (i < 5) {
                    row1.addView(circleContainer);
                } else {
                    row2.addView(circleContainer);
                }
            }

        }

        // Добавляем ряды в родительский LinearLayout
        circlesLayout.setOrientation(LinearLayout.VERTICAL);
        circlesLayout.addView(row1);
        circlesLayout.addView(row2);
        // Получаем перемешанный список вопросов
        QuestionRepository repository = new QuestionRepository(selectedCheckBox);
        questions = repository.getShuffledQuestions();

        loadQuestion();
    }

    private void updateCircle(boolean correct) {
        if (filledCircles < MAX_CIRCLES) {
            // Определяем в каком ряду находится текущий кружок
            LinearLayout currentRow = filledCircles < 8 ? row1 : row2;
            int indexInRow = filledCircles < 8 ? filledCircles : filledCircles - 8;

            FrameLayout circleContainer = (FrameLayout) currentRow.getChildAt(indexInRow);
            ImageView circle = (ImageView) circleContainer.getChildAt(0);
            if (correct) {
                circle.setImageResource(R.drawable.right);
            } else {
                circle.setImageResource(R.drawable.wrong);
            }
            filledCircles++;
        }
    }
    private void updateCircle1(boolean correct) {
        if (filledCircles < MAX_CIRCLES) {
            // Определяем в каком ряду находится текущий кружок
            LinearLayout currentRow = filledCircles < 5 ? row1 : row2;
            int indexInRow = filledCircles < 5 ? filledCircles : filledCircles - 5;

            FrameLayout circleContainer = (FrameLayout) currentRow.getChildAt(indexInRow);
            ImageView circle = (ImageView) circleContainer.getChildAt(0);
            if (correct) {
                circle.setImageResource(R.drawable.right);
            } else {
                circle.setImageResource(R.drawable.wrong);
            }
            filledCircles++;
        }
    }

    private void loadQuestion() {
        if (currentQuestionIndex < 10) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionText.setText(currentQuestion.getQuestion());

            // Получаем перемешанные ответы
            String[] shuffledAnswers = currentQuestion.getShuffledAnswers();

            // Назначаем тексты на кнопки
            for (int i = 0; i < 4; i++) {

                answerButtons[i].setText(shuffledAnswers[i]);

                final String selectedAnswer = shuffledAnswers[i];

                // Устанавливаем обработчик нажатия на ImageButton
                answerButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer(selectedAnswer, currentQuestion);
                    }
                });
            }
        } else {
            Toast.makeText(this, "Викторина окончена!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(mainQUIZ.this, molodec.class);
            i.putExtra("score", score);
            i.putExtra("lowbow", selectedCheckBox);
            startActivity(i);
        }
    }
    private void loadQuestion1() {
        if (currentQuestionIndex < 16) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionText.setText(currentQuestion.getQuestion());

            // Получаем перемешанные ответы
            String[] shuffledAnswers = currentQuestion.getShuffledAnswers();

            // Назначаем тексты на кнопки
            for (int i = 0; i < 4; i++) {

                answerButtons[i].setText(shuffledAnswers[i]);

                final String selectedAnswer = shuffledAnswers[i];

                // Устанавливаем обработчик нажатия на ImageButton
                answerButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer(selectedAnswer, currentQuestion);
                    }
                });
            }
        } else {
            Toast.makeText(this, "Викторина окончена!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(mainQUIZ.this, molodec.class);
            i.putExtra("score", score);
            i.putExtra("lowbow", selectedCheckBox);
            startActivity(i);
        }
    }

    private void checkAnswer(String selectedAnswer, Question currentQuestion) {
        boolean correct = currentQuestion.isCorrectAnswer(selectedAnswer);

        if (correct) {
            score++;
            Toast.makeText(this, "Правильный ответ!", Toast.LENGTH_SHORT).show();
            if (selectedCheckBox.equals("bow12")) {
                updateCircle(true);
            } else if (selectedCheckBox.equals("low12")){
                updateCircle1(true);
            }
        } else {
            Toast.makeText(this, "Неправильный ответ!", Toast.LENGTH_SHORT).show();
            if (selectedCheckBox.equals("bow12")) {
                updateCircle(false);
            } else if (selectedCheckBox.equals("low12")){
                updateCircle1(false);
            }
        }
        currentQuestionIndex++;
        // Показываем пояснение через диалоговое окно
        showExplanationDialog(currentQuestion.getExplanation());

        // Переход к следующему вопросу после закрытия диалога

    }

    private void showExplanationDialog(String explanation) {
        // Создаем диалоговое окно для пояснения
        new AlertDialog.Builder(this)
                .setTitle("Пояснение")
                .setMessage(explanation)
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // После закрытия диалога загружаем следующий вопрос
                        if (selectedCheckBox.equals("low12")) {
                            loadQuestion();
                        } else if (selectedCheckBox.equals("bow12")) {
                            loadQuestion1();
                        }
                    }
                })
                .setCancelable(false) // Окно нельзя закрыть без нажатия на кнопку
                .show();
    }

}
