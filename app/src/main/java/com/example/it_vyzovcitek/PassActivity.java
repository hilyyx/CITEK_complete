package com.example.it_vyzovcitek;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.it_vyzovcitek.bottomnav.profile.ProfileFragment;

public class PassActivity extends AppCompatActivity {

    public EditText passwordInput;
    public TextView resultText;

    public ImageButton checkButton, back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);

        passwordInput = findViewById(R.id.editTextText);
        back = findViewById(R.id.back);
        resultText = findViewById(R.id.textView1);
        checkButton = findViewById(R.id.check_button);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordInput.getText().toString();
                if (!password.isEmpty()) {
                    int score = countBalls(password);
                    String strength = getStrengthDescription(score);
                    resultText.setText("Надежность пароля: " + strength);
                } else {
                    resultText.setText("Пожалуйста, введите пароль!");
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PassActivity.this, MainActivity.class));
            }
        });
    }

    private int countBalls(String password) {
        int balls = 0;
        balls += password.length() >= 8 ? 10 : 0; // более 8 знаков
        balls += password.chars()                // 2 буквы
                .filter(i -> "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".contains("" + (char) i)).count() > 1 ? 10 : 0;
        balls += password.chars()                // 2 цифры
                .filter(i -> "0123456789".contains("" + (char) i)).count() > 1 ? 10 : 0;
        balls += password.chars()                // одна или больше больших букв
                .anyMatch(i -> "ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains("" + (char) i)) ? 10 : 0;
        balls += password.chars()                // одна или больше маленьких букв
                .anyMatch(i -> "abcdefghijklmnopqrstuvwxyz".contains("" + (char) i)) ? 10 : 0;
        balls += password.chars()                // содержатся спецсимволы (дополнить список символов по желанию)
                .anyMatch(i -> "!@#$%^&*[]".contains("" + (char) i)) ? 10 : 0;
        return balls;
    }

    private String getStrengthDescription(int score) {
        if (score == 10) {
            return "Очень легкий";
        } else if (score == 20) {
            return "Легкий";
        } else if (score == 30) {
            return "Средний";
        } else if (score == 40) {
            return "Сложный";
        } else if (score == 50) {
            return "Очень сложный";
        } else if (score == 60) {
            return "Идеальный";
        } else {
            return "Неизвестно";
        }
    }
}
