package com.unimib.triptales.ui.homepage.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.unimib.triptales.R;
import com.unimib.triptales.ui.diario.fragment.Diary;

public class AddDiaryFragment extends Fragment {

    private TextInputEditText inputDiaryName;
    private TextInputEditText inputStartDate;
    private TextInputEditText inputEndDate;
    private Button buttonChooseImage;
    private Button buttonSave;
    private Button buttonCancel;

    private Bitmap selectedImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_dialog_add_diary, container, false);

        // Initialize UI components
        inputDiaryName = view.findViewById(R.id.inputDiaryName);
        inputStartDate = view.findViewById(R.id.inputStartDate);
        inputEndDate = view.findViewById(R.id.inputEndDate);
        buttonChooseImage = view.findViewById(R.id.buttonChooseImage);
        buttonSave = view.findViewById(R.id.buttonSave);
        buttonCancel = view.findViewById(R.id.buttonCancel);

        // Set up listeners
        buttonChooseImage.setOnClickListener(v -> chooseImage());
        buttonSave.setOnClickListener(v -> saveDiary());
        buttonCancel.setOnClickListener(v -> cancelCreation());

        return view;
    }

    private void chooseImage() {
        // Intent per scegliere un'immagine dalla galleria (puoi implementare una logica di selezione immagine)
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1); // 1 è il codice per la richiesta di selezione dell'immagine
    }

    private void saveDiary() {
        String diaryName = inputDiaryName.getText().toString().trim();
        String startDate = inputStartDate.getText().toString().trim();
        String endDate = inputEndDate.getText().toString().trim();

        if (diaryName.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            Toast.makeText(getContext(), "Per favore, compila tutti i campi.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crea un nuovo oggetto Diary con i dati inseriti
        Diary newDiary = new Diary(diaryName, startDate, endDate, selectedImage);

        // Logic to save the diary (e.g., save to database or update list)
        Toast.makeText(getContext(), "Diario salvato con successo!", Toast.LENGTH_SHORT).show();

        // Passa l'oggetto Diario al Fragment principale (HomeFragment o altro) se necessario
        // Puoi usare un'interfaccia o un ViewModel per comunicare con il fragment principale
        requireActivity().onBackPressed(); // Chiudi il fragment corrente
    }

    private void cancelCreation() {
        // Naviga indietro senza salvare
        requireActivity().onBackPressed(); // Naviga indietro
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK && data != null) {
            // Ottieni l'immagine scelta
            try {
                selectedImage = android.provider.MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                Toast.makeText(getContext(), "Immagine selezionata!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
