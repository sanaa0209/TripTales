package com.unimib.triptales.repository.diary;

import com.unimib.triptales.model.Diary;
import com.unimib.triptales.source.diary.BaseDiaryLocalDataSource;
import com.unimib.triptales.source.diary.BaseDiaryRemoteDataSource;

import java.util.Collections;
import java.util.List;




import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiaryRepository implements IDiaryRepository, DiaryResponseCallBack {

    private final BaseDiaryLocalDataSource localDataSource;
    private final Executor executor;

    // Costruttore per inizializzare il data source e l'esecutore
    public DiaryRepository(BaseDiaryLocalDataSource localDataSource, BaseDiaryRemoteDataSource diaryRemoteDataSource) {
        this.localDataSource = localDataSource;
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void onSuccessFromLocal(List<Diary> diaries) {
        // Metodo chiamato in caso di successo (può essere lasciato vuoto o usato per logging)
        System.out.println("Successo: Recuperati " + diaries.size() + " diari dal database locale.");
    }

    @Override
    public void onFailureFromLocal(Exception exception) {
        // Metodo chiamato in caso di errore (può essere lasciato vuoto o usato per logging)
        System.err.println("Errore dal database locale: " + exception.getMessage());
    }

    @Override
    public long insertDiary(Diary diary) {
        try {
            return localDataSource.insertDiary(diary);
        } catch (Exception e) {
            onFailureFromLocal(e);
            return -1; // Ritorna un valore negativo in caso di errore
        }
    }




    @Override
    public void updateDiaryIsSelected(int diaryId, boolean isSelected) {
        executor.execute(() -> {
            try {
                localDataSource.updateDiaryIsSelected(diaryId, isSelected);
            } catch (Exception e) {
                onFailureFromLocal(e);
            }
        });
    }

    @Override
    public void deleteDiary(Diary diary) {
        executor.execute(() -> {
            try {
                localDataSource.deleteDiary(diary);
            } catch (Exception e) {
                onFailureFromLocal(e);
            }
        });
    }

    @Override
    public void deleteAllDiaries(List<Diary> diaries) {
        executor.execute(() -> {
            try {
                localDataSource.deleteAllDiaries(diaries);
            } catch (Exception e) {
                onFailureFromLocal(e);
            }
        });
    }

    @Override
    public List<Diary> getAllDiaries() {
        executor.execute(() -> {
            try {
                List<Diary> diaries = localDataSource.getAllDiaries();
                onSuccessFromLocal(diaries);
            } catch (Exception e) {
                onFailureFromLocal(e);
            }
        });
        return null;
    }

    @Override
    public void getSelectedDiaries(DiaryResponseCallBack callBack) {
        executor.execute(() -> {
            try {
                List<Diary> diaries = localDataSource.getSelectedDiaries();
                callBack.onSuccessFromLocal(diaries);
            } catch (Exception e) {
                callBack.onFailureFromLocal(e);
            }
        });
    }

    @Override
    public void updateDiary(Diary diary) {
        executor.execute(() -> {
            try {
                localDataSource.updateDiary();
            } catch (Exception e) {
                onFailureFromLocal(e);
            }
        });
    }

    @Override
    public List<Diary> getAllDiariesByUserId(String userId) {
        try {
            return localDataSource.getAllDiariesByUserId(userId);
        } catch (Exception e) {
            onFailureFromLocal(e);
            return Collections.emptyList(); // Ritorna una lista vuota in caso di errore
        }
    }

}
