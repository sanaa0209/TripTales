package com.unimib.triptales.source.diary;


import com.unimib.triptales.database.DiaryDao;
import com.unimib.triptales.model.Diary;

import java.util.List;

public class LocalDataSource {

    private final DiaryDao diaryDao;

    public LocalDataSource(DiaryDao diaryDao) {
        this.diaryDao = diaryDao;
    }

    public List<Diary> getAllDiaries() {
        return diaryDao.getAllDiaries();
    }

    public void insertDiary(Diary diary) {
        diaryDao.insert(diary);
    }

    public void updateDiary(Diary diary) {
        diaryDao.update(diary);
    }

    public void deleteDiary(Diary diary) {
        diaryDao.delete(diary);
    }
}
