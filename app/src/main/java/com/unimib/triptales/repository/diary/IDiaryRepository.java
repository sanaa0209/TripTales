package com.unimib.triptales.repository.diary;

import com.unimib.triptales.model.Diary;

import java.util.List;


public interface IDiaryRepository {
    long insertDiary(Diary diary);

    void updateDiaryIsSelected(int diaryId, boolean isSelected);

    void deleteDiary(Diary diary);

    void deleteAllDiaries(List<Diary> diaries);

    List<Diary> getAllDiaries();

    void getSelectedDiaries(DiaryResponseCallBack callBack);

    void updateDiary(Diary diary);

    List<Diary> getAllDiariesByUserId(String userId);
}
