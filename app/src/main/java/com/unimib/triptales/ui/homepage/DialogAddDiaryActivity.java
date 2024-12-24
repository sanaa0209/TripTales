package com.unimib.triptales.ui.homepage;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia;
import androidx.appcompat.app.AppCompatActivity;

import com.unimib.triptales.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogAddDiaryActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 100;

    private Button buttonChooseImage, buttonSave, buttonCancel;
    private ImageView imageViewSelected;
    private EditText inputStartDate, inputEndDate, inputDiaryName;

    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_add_diary);

        // Inizializza i componenti
        buttonChooseImage = findViewById(R.id.buttonChooseImage);
        imageViewSelected = findViewById(R.id.imageViewSelected);
        inputStartDate = findViewById(R.id.inputStartDate);
        inputEndDate = findViewById(R.id.inputEndDate);
        inputDiaryName = findViewById(R.id.inputDiaryName);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        // Registrazione del Photo Picker
        pickMedia = registerForActivityResult(new PickVisualMedia(), uri -> {
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: " + uri);
                imageViewSelected.setImageURI(uri);
                imageViewSelected.setVisibility(ImageView.VISIBLE);
            } else {
                Log.d("PhotoPicker", "No media selected");
                Toast.makeText(this, "Nessuna immagine selezionata", Toast.LENGTH_SHORT).show();
            }
        });

        // Configura il pulsante per scegliere un'immagine
        buttonChooseImage.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                // Per Android 13+ usa il Photo Picker
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            } else {
                // Metodo tradizionale per versioni precedenti
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        // Configura il pulsante di salvataggio
        buttonSave.setOnClickListener(v -> {
            if (isDateValid()) {
                saveDiary();
            } else {
                Toast.makeText(this, "Le date non sono valide", Toast.LENGTH_SHORT).show();
            }
        });

        // Configura il pulsante di cancellazione
        buttonCancel.setOnClickListener(v -> finish());
    }

    private boolean isDateValid() {
        String startDateStr = inputStartDate.getText().toString();
        String endDateStr = inputEndDate.getText().toString();

        if (TextUtils.isEmpty(startDateStr) || TextUtils.isEmpty(endDateStr)) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date startDate = sdf.parse(startDateStr);
            Date endDate = sdf.parse(endDateStr);
            return startDate != null && endDate != null && !startDate.after(endDate);
        } catch (Exception e) {
            return false;
        }
    }

    private void saveDiary() {
        String diaryName = inputDiaryName.getText().toString();
        String startDate = inputStartDate.getText().toString();
        String endDate = inputEndDate.getText().toString();

        // Controlla che i dati siano validi
        if (TextUtils.isEmpty(diaryName) || TextUtils.isEmpty(startDate) || TextUtils.isEmpty(endDate)) {
            Toast.makeText(this, "Tutti i campi sono obbligatori", Toast.LENGTH_SHORT).show();
            return;
        }

        // Prepara i dati da passare alla schermata principale
        Intent resultIntent = new Intent();
        resultIntent.putExtra("DIARY_NAME", diaryName);
        resultIntent.putExtra("START_DATE", startDate);
        resultIntent.putExtra("END_DATE", endDate);

        // Aggiungi l'URI dell'immagine se presente
        if (imageViewSelected.getDrawable() != null) {
            resultIntent.putExtra("IMAGE_URI", imageViewSelected.getTag().toString());
        }

        setResult(RESULT_OK, resultIntent);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                imageViewSelected.setImageURI(imageUri);
                imageViewSelected.setVisibility(ImageView.VISIBLE);
            }
        }
    }
}
