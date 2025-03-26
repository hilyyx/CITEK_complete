package com.example.it_vyzovcitek.bottomnav.games;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.it_vyzovcitek.ITQuiz;
import com.example.it_vyzovcitek.MainActivity;
import com.example.it_vyzovcitek.PassActivity;
import com.example.it_vyzovcitek.StartGame;
import com.example.it_vyzovcitek.databinding.FragmentGamesBinding;
import com.example.it_vyzovcitek.databinding.FragmentQuizBinding;

public class GamesFragment extends Fragment {
    protected FragmentGamesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGamesBinding.inflate(inflater, container, false);
        FragmentGamesBinding.inflate(inflater, container, false);
        binding.gameWifiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GamesFragment.this.getActivity(), MainActivity.class));
            }
        });
        binding.itquizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GamesFragment.this.getActivity(), StartGame.class));
            }
        });

        binding.gameWifiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GamesFragment.this.getActivity(), mainn.class));
            }
        });

        return binding.getRoot();
    }
}
