package com.example.it_vyzovcitek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class APIActivity extends AppCompatActivity {

    // API-ключ VirusTotal
    public static final String VIRUSTOTAL_API_KEY = "1230f6a615ffb333caa26e51d3b026b1d94a5b4364f24579672786c0a9118a6e"; // Замените на свой API-ключ

    public EditText urlInput;
    public TextView resultText;
    public ImageButton checkButton, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiactivity);

        urlInput = findViewById(R.id.editTextText);
        resultText = findViewById(R.id.textView1);
        checkButton = findViewById(R.id.check_button);
        back = findViewById(R.id.back);


        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlInput.getText().toString();
                if (!url.isEmpty()) {
                    new SecurityCheckTask().execute(url);
                } else {
                    Toast.makeText(APIActivity.this, "Введите URL", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(APIActivity.this, MainActivity.class));
            }
        });
    }

    // Внутренний класс для выполнения проверки безопасности в фоновом потоке
    private class SecurityCheckTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String url = urls[0];
            try {
                return checkSecurity(url);
            } catch (IOException e) {
                Log.e("SecurityCheckTask", "Ошибка проверки безопасности: " + e.getMessage());
                return "Ошибка проверки безопасности";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            resultText.setText(result);
        }

        private String checkSecurity(String url) throws IOException {
            // Проверка протокола (HTTPS)
            if (url.startsWith("https://")) {
                return "Соединение безопасно (HTTPS).\n" + checkVirusTotal(url);
            } else {
                return "Соединение небезопасно (HTTP).\n" + checkVirusTotal(url);
            }
        }

        private String checkVirusTotal(String url) throws IOException {
            String encodedUrl = URLEncoder.encode(url, "UTF-8");
            String apiUrl = "https://www.virustotal.com/vtapi/v2/url/report?apikey=" + VIRUSTOTAL_API_KEY + "&resource=" + encodedUrl;

            URL vtUrl = new URL(apiUrl);
            URLConnection connection = vtUrl.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            try {
                JSONObject jsonObject = new JSONObject(response.toString());
                if (jsonObject.has("positives") && jsonObject.getInt("positives") > 0) {
                    return "ВНИМАНИЕ! URL является вредоносным!";
                } else {
                    return "Вредоносная активность не обнаружена!";
                }
            } catch (JSONException e) {
                Log.e("SecurityCheckTask", "Ошибка парсинга ответа VirusTotal: " + e.getMessage());
                return "Ошибка получения данных с VirusTotal.";
            }
        }
    }
}

