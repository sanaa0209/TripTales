package com.unimib.triptales.ui.homepage.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.unimib.triptales.model.Diary;
import com.unimib.triptales.repository.diary.DiaryRepository;

import java.util.List;

public class DiaryViewModel extends ViewModel {

    private final DiaryRepository diaryRepository;
    private final MutableLiveData<List<Diary>> diaries = new MutableLiveData<>();
    private final MutableLiveData<Boolean> operationStatus = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>(); // Aggiunta per messaggi di errore

    public DiaryViewModel(DiaryRepository repository) {
        this.diaryRepository = repository;
    }

    // LiveData per osservare l'elenco dei diari
    public LiveData<List<Diary>> getDiaries() {
        return diaries;
    }

    // LiveData per lo stato delle operazioni (successo o fallimento)
    public LiveData<Boolean> getOperationStatus() {
        return operationStatus;
    }

    // LiveData per gestire messaggi di errore
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    // Carica tutti i diari
    public void loadDiaries() {
        diaryRepository.getDiaries(result -> {
            if (result != null) {
                diaries.postValue(result);
            } else {
                errorMessage.postValue("Errore nel caricare i diari."); // Gestione errore
            }
        });
    }

    // Aggiungi un nuovo diario
    public void addDiary(Diary diary) {
        diaryRepository.addDiary(diary, success -> {
            operationStatus.postValue(success);
            if (success) loadDiaries();
            else errorMessage.postValue("Errore nell'aggiungere il diario.");
        });
    }

    // Aggiorna un diario esistente
    public void updateDiary(Diary diary) {
        diaryRepository.updateDiary(diary, success -> {
            operationStatus.postValue(success);
            if (success) loadDiaries();
            else errorMessage.postValue("Errore nell'aggiornare il diario.");
        });
    }

    // Elimina un diario
    public void deleteDiary(Diary diary) {
        diaryRepository.deleteDiary(diary, success -> {
            operationStatus.postValue(success);
            if (success) loadDiaries();
            else errorMessage.postValue("Errore nella cancellazione del diario.");
        });
    }

    // Elimina una lista di diari
    public void deleteDiaries(List<Diary> diariesToDelete) {
        diaryRepository.deleteDiaries(diariesToDelete, success -> {
            operationStatus.postValue(success);
            if (success) loadDiaries();
            else errorMessage.postValue("Errore nella cancellazione dei diari.");
        });
    }
}
