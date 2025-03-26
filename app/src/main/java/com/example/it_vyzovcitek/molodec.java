package com.example.it_vyzovcitek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class molodec extends AppCompatActivity {
    private int scoree;
    private String res;
    private TextView molodeccc;
    private ImageButton close;
    private TextView krutaia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_molodec);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            krutaia = findViewById(R.id.krutoi);
            close = findViewById(R.id.close_screen);
            molodeccc = findViewById(R.id.molodecc);

            scoree = getIntent().getIntExtra("score", 0);
            res = getIntent().getStringExtra("lowbow");

            if (res.equals("low12")) {
                if (scoree <= 3) {
                    molodeccc.setText("Спасибо тебе за прохождение квеста! Тебе стоит потренироваться перед следующим прохождением");
                } else if (scoree > 4 & scoree <= 6) {
                    molodeccc.setText("Спасибо тебе за прохождение квеста! У тебя неплохой результат!");
                } else {
                    molodeccc.setText("Ты огромный молодец! Это прекрасный результат!");
                }
                krutaia.setText(String.valueOf(scoree) + " / 10");
            } else if (res.equals("bow12")) {
                if (scoree <= 5) {
                    molodeccc.setText("Спасибо тебе за прохождение квеста! Тебе стоит потренироваться перед следующим прохождением");
                } else if (scoree > 6 & scoree <= 10) {
                    molodeccc.setText("Спасибо тебе за прохождение квеста! У тебя неплохой результат!");
                } else {
                    molodeccc.setText("Ты огромный молодец! Это прекрасный результат!");
                }
                krutaia.setText(String.valueOf(scoree) + " / 16");
            }

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(molodec.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            return insets;
        });
    }
}