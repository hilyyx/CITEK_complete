package com.example.it_vyzovcitek;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.it_vyzovcitek.bottomnav.games.GamesFragment;
import com.example.it_vyzovcitek.bottomnav.profile.ProfileFragment;
import com.example.it_vyzovcitek.bottomnav.quiz.QuizFragment;
import com.example.it_vyzovcitek.bottomnav.rate.RateFragment;
import com.example.it_vyzovcitek.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), new QuizFragment()).commit();
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        binding.bottomNav.setSelectedItemId(R.id.quiz);
        Map<Integer, Fragment> fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.profile, new ProfileFragment());
        fragmentMap.put(R.id.games, new GamesFragment());
        fragmentMap.put(R.id.quiz, new QuizFragment());
        fragmentMap.put(R.id.rate, new RateFragment());
        binding.bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = fragmentMap.get(item.getItemId());

            getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(),fragment).commit();
            return true;
        });
    }
}
