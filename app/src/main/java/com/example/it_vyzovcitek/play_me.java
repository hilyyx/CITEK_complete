package com.example.it_vyzovcitek;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class play_me extends AppCompatActivity {

    public ImageButton vverx_btn, wifi, wifi_off, bluetooth, bluetooth_off, geo, geo_off, dist, dist_off, ubrat;
    public ImageView verx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play_me);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // Инициализация элементов
            verx = findViewById(R.id.verx);
            vverx_btn = findViewById(R.id.vverx);
            wifi = findViewById(R.id.wifi);
            wifi_off = findViewById(R.id.wifi_off);
            bluetooth = findViewById(R.id.bluetooth);
            bluetooth_off = findViewById(R.id.bluetooth_off);
            geo = findViewById(R.id.geo);
            geo_off = findViewById(R.id.geo_off);
            dist = findViewById(R.id.disturb);
            dist_off = findViewById(R.id.disturb_off);
            ubrat = findViewById(R.id.ubrat);

            // Первоначально скрываем элементы
            verx.setVisibility(View.GONE);
            wifi.setVisibility(View.GONE);
            bluetooth.setVisibility(View.GONE);
            geo.setVisibility(View.GONE);
            dist.setVisibility(View.GONE);
            ubrat.setVisibility(View.GONE);
            wifi_off.setVisibility(View.GONE);
            bluetooth_off.setVisibility(View.GONE);
            geo_off.setVisibility(View.GONE);
            dist_off.setVisibility(View.GONE);

            // Логика обработки нажатий на кнопки
            vverx_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verx.setVisibility(View.VISIBLE);
                    wifi.setVisibility(View.VISIBLE);
                    bluetooth.setVisibility(View.VISIBLE);
                    geo.setVisibility(View.VISIBLE);
                    dist.setVisibility(View.VISIBLE);
                    wifi_off.setVisibility(View.VISIBLE);
                    bluetooth_off.setVisibility(View.VISIBLE);
                    geo_off.setVisibility(View.VISIBLE);
                    dist_off.setVisibility(View.VISIBLE);
                    ubrat.setVisibility(View.VISIBLE);
                    vverx_btn.setVisibility(View.GONE);
                }
            });

            ubrat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verx.setVisibility(View.GONE);
                    wifi.setVisibility(View.GONE);
                    bluetooth.setVisibility(View.GONE);
                    geo.setVisibility(View.GONE);
                    dist.setVisibility(View.GONE);
                    wifi_off.setVisibility(View.GONE);
                    bluetooth_off.setVisibility(View.GONE);
                    geo_off.setVisibility(View.GONE);
                    dist_off.setVisibility(View.GONE);
                    ubrat.setVisibility(View.GONE);
                    vverx_btn.setVisibility(View.VISIBLE);
                }
            });

            // Логика для Wi-Fi кнопки
            wifi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wifi.setVisibility(View.GONE);
                    wifi_off.setVisibility(View.VISIBLE);
                    checkCompletion();
                }
            });

            wifi_off.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wifi.setVisibility(View.VISIBLE);
                    wifi_off.setVisibility(View.GONE);
                }
            });

            // Логика для Bluetooth кнопки
            bluetooth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bluetooth.setVisibility(View.GONE);
                    bluetooth_off.setVisibility(View.VISIBLE);
                    checkCompletion();
                }
            });

            bluetooth_off.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bluetooth.setVisibility(View.VISIBLE);
                    bluetooth_off.setVisibility(View.GONE);
                }
            });

            // Логика для Geo кнопки
            geo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    geo.setVisibility(View.GONE);
                    geo_off.setVisibility(View.VISIBLE);
                }
            });

            geo_off.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    geo.setVisibility(View.VISIBLE);
                    geo_off.setVisibility(View.GONE);
                }
            });

            // Логика для Disturb кнопки
            dist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dist.setVisibility(View.GONE);
                    dist_off.setVisibility(View.VISIBLE);
                }
            });

            dist_off.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dist.setVisibility(View.VISIBLE);
                    dist_off.setVisibility(View.GONE);
                }
            });

            return insets;
        });
    }

    // Метод для проверки выполнения задачи
    private void checkCompletion() {
        if (wifi.getVisibility() == View.GONE && bluetooth.getVisibility() == View.GONE) {
            // Если оба скрыты, показать диалог
            AlertDialog.Builder builder = new AlertDialog.Builder(play_me.this);
            builder.setTitle("Поздравляем!")
                    .setMessage("Задача выполнена! Ты молодец!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Переход на другую активность
                            Intent intent = new Intent(play_me.this, MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
    }
}
