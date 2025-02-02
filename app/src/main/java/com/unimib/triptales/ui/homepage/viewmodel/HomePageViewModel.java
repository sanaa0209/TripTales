package com.unimib.triptales.ui.homepage.viewmodel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.unimib.triptales.model.Diary;
import com.unimib.triptales.repository.diary.IDiaryRepository;
import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.LiveData;

public class HomePageViewModel extends ViewModel {

    private final IDiaryRepository diaryRepository;

    private final MutableLiveData<List<Diary>> diariesLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Diary>> selectedDiariesLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public HomePageViewModel(IDiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    // LiveData for the list of diaries
    public LiveData<List<Diary>> getDiariesLiveData() {
        return diariesLiveData;
    }

    // LiveData for the selected diaries
    public LiveData<List<Diary>> getSelectedDiariesLiveData() {
        return selectedDiariesLiveData;
    }

    // LiveData for loading state
    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    // LiveData for error messages
    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    // Method to fetch all diaries from the repository
    public void fetchDiaries(String userId) {
        loadingLiveData.setValue(true);
        try {
            List<Diary> diaries = diaryRepository.getAllDiariesByUserId(userId);
            diariesLiveData.postValue(diaries);
            loadingLiveData.postValue(true);
        } catch (Exception e) {
            loadingLiveData.postValue(false);
            errorLiveData.postValue("Errore durante il recupero dei diari: " + e.getMessage());
        }
    }

    // Method to save a new diary
    public void saveDiary(String diaryName, String startDate, String endDate, String country, String imageUri, String userId) {
        loadingLiveData.setValue(true);
        new Thread(() -> {
            try {
                Diary newDiary = new Diary(0, userId, diaryName, startDate, endDate, imageUri, null, country);
                long diaryId = diaryRepository.insertDiary(newDiary);

                if (diaryId > 0) {
                    // Aggiorna la lista dei diari dopo l'inserimento
                    List<Diary> diaries = diariesLiveData.getValue();
                    if (diaries == null) {
                        diaries = new ArrayList<>();
                    }
                    newDiary.setId((int) diaryId); // Imposta l'ID generato
                    diaries.add(newDiary);
                    diariesLiveData.postValue(diaries);
                }

                loadingLiveData.postValue(false);
                errorLiveData.postValue("Diario aggiunto con successo!");
                fetchDiaries(userId);
            } catch (Exception e) {
                loadingLiveData.postValue(false);
                errorLiveData.postValue("Errore durante il salvataggio del diario: " + e.getMessage());
            }
        }).start();


    }


    // Method to modify an existing diary
    public void modifyDiary(Diary diary) {
        loadingLiveData.setValue(true);
        try {
            diaryRepository.updateDiary(diary);

            List<Diary> currentDiaries = diariesLiveData.getValue();
            if (currentDiaries != null) {
                for (int i = 0; i < currentDiaries.size(); i++) {
                    if (currentDiaries.get(i).getId() == diary.getId()) {
                        currentDiaries.set(i, diary);
                        break;
                    }
                }
                diariesLiveData.postValue(currentDiaries);
            }
            loadingLiveData.postValue(false);
        } catch (Exception e) {
            loadingLiveData.postValue(false);
            errorLiveData.postValue("Errore durante la modifica del diario: " + e.getMessage());
        }
    }

    // Method to delete selected diaries
    public void deleteSelectedDiaries(List<Diary> diariesToDelete) {
        loadingLiveData.setValue(true);
        try {
            for (Diary diary : diariesToDelete) {
                diaryRepository.deleteDiary(diary);
            }

            List<Diary> currentDiaries = diariesLiveData.getValue();
            if (currentDiaries != null) {
                currentDiaries.removeAll(diariesToDelete);
                diariesLiveData.postValue(currentDiaries);
            }

            selectedDiariesLiveData.postValue(new ArrayList<>());
            loadingLiveData.postValue(false);
        } catch (Exception e) {
            loadingLiveData.postValue(false);
            errorLiveData.postValue("Errore durante l'eliminazione dei diari: " + e.getMessage());
        }
    }

    // Method to select a diary
    public void selectDiary(Diary diary) {
        List<Diary> selectedDiaries = selectedDiariesLiveData.getValue();
        if (selectedDiaries == null) {
            selectedDiaries = new ArrayList<>();
        }

        if (selectedDiaries.contains(diary)) {
            selectedDiaries.remove(diary);
        } else {
            selectedDiaries.add(diary);
        }

        selectedDiariesLiveData.postValue(selectedDiaries);
    }
}
