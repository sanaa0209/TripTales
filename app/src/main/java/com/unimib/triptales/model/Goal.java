package com.unimib.triptales.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;
import java.util.UUID;

@Entity(foreignKeys = @ForeignKey(entity = Diary.class, parentColumns = "id", childColumns = "diaryId", onDelete = CASCADE))
public class Goal {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "diaryId")
    private String diaryId;

    @ColumnInfo(name = "goal_name")
    private String name;

    @ColumnInfo(name = "goal_description")
    private String description;

    @ColumnInfo(name = "goal_isSelected")
    private boolean goal_isSelected;

    @ColumnInfo(name = "goal_isChecked")
    private boolean goal_isChecked;

    @ColumnInfo(name = "goal_timestamp")
    private long timestamp;

    public Goal(){}

    public Goal(String name, String description, boolean goal_isSelected, boolean goal_isChecked,
                String diaryId, long timestamp) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.goal_isSelected = goal_isSelected;
        this.goal_isChecked = goal_isChecked;
        this.diaryId = diaryId;
        this.timestamp = timestamp;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getDiaryId() { return diaryId; }

    public void setDiaryId(String diaryId) { this.diaryId = diaryId; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isGoal_isSelected() {
        return goal_isSelected;
    }

    public void setGoal_isSelected(boolean goal_isSelected) {
        this.goal_isSelected = goal_isSelected;
    }

    public boolean isGoal_isChecked() {
        return goal_isChecked;
    }

    public void setGoal_isChecked(boolean goal_isChecked) {
        this.goal_isChecked = goal_isChecked;
    }

    public long getTimestamp() { return timestamp; }

    public void setTimestamp(long timestamp) { this.timestamp = timestamp;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Goal goal = (Goal) obj;
        return id.equals(goal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
