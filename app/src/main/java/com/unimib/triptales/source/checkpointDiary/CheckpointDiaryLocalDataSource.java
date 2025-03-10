package com.unimib.triptales.source.checkpointDiary;

import com.unimib.triptales.database.CheckpointDiaryDao;
import com.unimib.triptales.model.CheckpointDiary;

import java.util.List;

public class CheckpointDiaryLocalDataSource extends BaseCheckpointDiaryLocalDataSource {
    private final CheckpointDiaryDao checkpointDiaryDao;
    private final String diaryId;

    public CheckpointDiaryLocalDataSource(CheckpointDiaryDao checkpointDiaryDao, String diaryId) {
        this.checkpointDiaryDao = checkpointDiaryDao;
        this.diaryId = diaryId;
    }

    @Override
    public List<CheckpointDiary> getCheckpointDiariesByDiaryId(String diaryId) {
        try {
            return checkpointDiaryDao.getCheckpointDiaryById(diaryId);
        } catch (Exception e) {
            checkpointDiaryCallback.onFailureFromLocal(e);
            return null;
        }
    }

    @Override
    public void getAllCheckpointDiaries() {
        try {
            List<CheckpointDiary> checkpoints = checkpointDiaryDao.getAllCheckpointDiaries();
            checkpointDiaryCallback.onSuccessFromLocal(checkpoints);
        } catch (Exception e) {
            checkpointDiaryCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public long insertCheckpointDiary(CheckpointDiary checkpointDiary) {
        try {
            return checkpointDiaryDao.insertCheckpointDiary(checkpointDiary);
        } catch (Exception e) {
            checkpointDiaryCallback.onFailureFromLocal(e);
            return -1;
        }
    }


    @Override
    public void deleteCheckpointDiary(CheckpointDiary checkpointDiary) {
        try {
            checkpointDiaryDao.deleteCheckpointDiary(checkpointDiary);
            checkpointDiaryCallback.onSuccessDeleteFromLocal();
        } catch (Exception e) {
            checkpointDiaryCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void updateCheckpointDiaryName(int checkpointId, String newName) {
        try {
            checkpointDiaryDao.updateCheckpointDiaryName(checkpointId, newName);
        } catch (Exception e) {
            checkpointDiaryCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void updateCheckpointDiaryDate(int checkpointId, String newDate) {
        try {
            checkpointDiaryDao.updateCheckpointDiaryDate(checkpointId, newDate);
        } catch (Exception e) {
            checkpointDiaryCallback.onFailureFromLocal(e);
        }
    }

    @Override
    public void updateCheckpointDiaryImageUri(int checkpointId, String newImageUri) {
        try {
            checkpointDiaryDao.updateCheckpointDiaryImageUri(checkpointId, newImageUri);
        } catch (Exception e) {
            checkpointDiaryCallback.onFailureFromLocal(e);
        }
    }
}