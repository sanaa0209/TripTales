package com.unimib.triptales.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.unimib.triptales.model.Diary;
import com.unimib.triptales.model.Goal;
import com.unimib.triptales.model.Stage;
import com.unimib.triptales.model.Task;

import java.util.List;

public class CompleteDiary {
    @Embedded
    public Diary diary;

    @Relation(parentColumn = "id", entityColumn = "diaryId")
    public List<Stage> stages;  // Relazione con le tappe

    @Relation(parentColumn = "id", entityColumn = "diaryId")
    public List<Goal> goals;    // Relazione con gli obiettivi

    @Relation(parentColumn = "id", entityColumn = "diaryId")
    public List<Task> tasks;  // Relazione con le attività

    /*@Relation(parentColumn = "id", entityColumn = "diaryId")
    public List<Expense> expenses;    // Relazione con le spese*/
}
