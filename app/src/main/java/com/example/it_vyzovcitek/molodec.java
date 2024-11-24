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
            scoree = getIntent().getIntExtra("score", 0);
            res = getIntent().getStringExtra("lowbow");
            if (res.equals("low12")) {
                krutaia.setText(String.valueOf(scoree) + " / 10");
            } else if (res.equals("bow12")) {
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