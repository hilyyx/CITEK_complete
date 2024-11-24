package com.example.it_vyzovcitek.bottomnav.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.it_vyzovcitek.R;
import com.example.it_vyzovcitek.databinding.FragmentQuizBinding;
import com.example.it_vyzovcitek.mainQUIZ;

public class QuizFragment extends Fragment {
    private FragmentQuizBinding binding;
    private ImageButton nextButton;

    private CheckBox low12, bow12;
    private int age;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        low12 = view.findViewById(R.id.low12);
        bow12 = view.findViewById(R.id.bow12);
        nextButton = view.findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCheckBox = "";
                if (low12.isChecked() && bow12.isChecked()) {
                    Toast.makeText(getContext(), "Выберите только один вариант!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (low12.isChecked()) {
                    selectedCheckBox = "low12";
                } else if (bow12.isChecked()) {
                    selectedCheckBox = "bow12";
                } else if (!low12.isChecked() && !bow12.isChecked()){
                    Toast.makeText(getContext(), "Выберите один из вариантов", Toast.LENGTH_SHORT).show();
                    return; // Don't proceed if nothing is selected
                }
                Intent intent = new Intent(getActivity(), mainQUIZ.class);
                intent.putExtra("selectedCheckBox", selectedCheckBox);
                startActivity(intent);
            }
        });

        return view;
    }
}
