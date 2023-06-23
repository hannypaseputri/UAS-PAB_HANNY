package com.si6a.wisataindonesia.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.si6a.wisataindonesia.R;
import com.si6a.wisataindonesia.Utilities.Utilities;
import com.si6a.wisataindonesia.databinding.ActivityDetailBinding;
import com.si6a.wisataindonesia.model.TravelData;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TravelData data = Utilities.travelData;

        binding.ivFoto.setImageBitmap(Utilities.convertBase64ToBitmap(data.getImage()));
        binding.tvTitle.setText(data.getTitle());
        binding.tvDescription.setText(data.getDescription());
    }
}