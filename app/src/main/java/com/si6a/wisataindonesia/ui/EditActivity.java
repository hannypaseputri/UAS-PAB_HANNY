package com.si6a.wisataindonesia.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.si6a.wisataindonesia.R;
import com.si6a.wisataindonesia.Utilities.Utilities;
import com.si6a.wisataindonesia.api.RetrofitClient;
import com.si6a.wisataindonesia.databinding.ActivityAddBinding;
import com.si6a.wisataindonesia.databinding.ActivityEditBinding;
import com.si6a.wisataindonesia.model.ResponseData;
import com.si6a.wisataindonesia.model.TravelData;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;
    private Uri imageUri = null;
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: " + uri);
                    imageUri = uri;
                    binding.ivFoto.setImageURI(uri);
                } else {
                    Log.d("PhotoPicker", "No media selected");
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TravelData data = Utilities.travelData;
        binding.ivFoto.setImageBitmap(Utilities.convertBase64ToBitmap(data.getImage()));
        binding.etTitle.setText(data.getTitle());
        binding.etDescription.setText(data.getDescription());

        binding.ivFoto.setOnClickListener(view -> pickPicture());

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.etTitle.getText().toString();
                String description = binding.etDescription.getText().toString();

                updateData(data.getId(), title, description, imageUri);
            }
        });
    }

    private void pickPicture() {
        pickMedia.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build());
    }

    private void updateData(String _id, String title, String description, Uri imageUri) {
        binding.btnUpdate.setEnabled(false);
        binding.progressBar.setVisibility(View.VISIBLE);

        String base64 = imageUri != null ? Utilities.convertImageToBase64(getApplicationContext(), imageUri) : Utilities.travelData.getImage();

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", title);
            jsonObject.put("description", description);
            jsonObject.put("image", base64);

            RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), jsonObject.toString());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    RetrofitClient.getInstance().updateTravel(_id, requestBody).enqueue(new Callback<ResponseData>() {
                        @Override
                        public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(EditActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            binding.btnUpdate.setEnabled(true);
                            binding.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<ResponseData> call, Throwable t) {
                            Toast.makeText(EditActivity.this, "Terjadi kesalahan pada server, silahkan coba lagi", Toast.LENGTH_LONG).show();
                            binding.btnUpdate.setEnabled(true);
                            binding.progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            });
        } catch (Exception e) {
            Log.e("Exception", "Exception: " + e.getMessage());
        }
    }
}