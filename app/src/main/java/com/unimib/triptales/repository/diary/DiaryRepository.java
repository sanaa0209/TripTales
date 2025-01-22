package com.unimib.triptales.repository.diary;

import com.unimib.triptales.model.Diary;
import com.unimib.triptales.source.diary.LocalDataSource;
import com.unimib.triptales.source.diary.RemoteDataSource;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiaryRepository {

    private final LocalDataSource localDataSource;
    private final RemoteDataSource remoteDataSource;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public DiaryRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public void getDiaries(OnResultCallback<List<Diary>> callback) {
        executor.execute(() -> {
            List<Diary> diaries = localDataSource.getAllDiaries();
            callback.onResult(diaries);
        });
    }

    public void addDiary(Diary diary, OnResultCallback<Boolean> callback) {
        executor.execute(() -> {
            try {
                localDataSource.insertDiary(diary);
                callback.onResult(true);
            } catch (Exception e) {
                callback.onResult(false); // Indica che c'è stato un errore
            }
        });
    }

    public void updateDiary(Diary diary, OnResultCallback<Boolean> callback) {
        executor.execute(() -> {
            try {
                localDataSource.updateDiary(diary);
                callback.onResult(true);
            } catch (Exception e) {
                callback.onResult(false); // Indica che c'è stato un errore
            }
        });
    }

    public void deleteDiary(Diary diary, OnResultCallback<Boolean> callback) {
        executor.execute(() -> {
            try {
                localDataSource.deleteDiary(diary);
                callback.onResult(true);
            } catch (Exception e) {
                callback.onResult(false); // Indica che c'è stato un errore
            }
        });
    }

    public void deleteDiaries(List<Diary> diariesToDelete, OnResultCallback<Boolean> callback) {
        executor.execute(() -> {
            try {
                for (Diary diary : diariesToDelete) {
                    localDataSource.deleteDiary(diary);
                }
                callback.onResult(true);
            } catch (Exception e) {
                callback.onResult(false); // Indica che c'è stato un errore
            }
        });
    }

    public interface OnResultCallback<T> {
        void onResult(T result);
    }
}
