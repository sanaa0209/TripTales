package com.unimib.triptales.ui.diario.fragment;

import com.unimib.triptales.ui.diario.fragment.Diary;
import java.util.ArrayList;
import java.util.List;

public class DiaryRepository {
    private static List<Diary> diaryList = new ArrayList<>();

    public static List<Diary> getAllDiaries() {
        return new ArrayList<>(diaryList); // Restituisce una copia della lista
    }

    public static void addDiary(Diary diary) {
        diaryList.add(diary);
    }

    public static void removeDiary(Diary diary) {
        diaryList.remove(diary);
    }
}
