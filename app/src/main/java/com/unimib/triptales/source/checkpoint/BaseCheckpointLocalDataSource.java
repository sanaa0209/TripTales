package com.unimib.triptales.source.checkpoint;

import com.unimib.triptales.model.Checkpoint;

import java.util.List;

public interface BaseCheckpointLocalDataSource {

    long insertCheckpoint(Checkpoint checkpoint);
    void updateCheckpoint(Checkpoint checkpoint);
    void updateAllCheckpoints(List<Checkpoint> checkpoints);
    void updateCheckpointName(int checkpointId, String newName);
    void updateCheckpointDate(int checkpointId, String newDate);
    void updateCheckpointImageUri(int checkpointId, String newImageUri);
    void updateCheckpointLatitude(int checkpointId, double newLatitude);
    void updateCheckpointLongitude(int checkpointId, double newLongitude);
    void updateCheckpointIsSelected(int checkpointId, boolean newIsSelected);
    void deleteCheckpoint(Checkpoint checkpoint);
    void deleteAllCheckpoints(List<Checkpoint> checkpoints);
    List<Checkpoint> getAllCheckpoints();
    List<Checkpoint> getSelectedCheckpoints();
    List<Checkpoint> searchCheckpointsByName(String query);

    public interface Callback<T> {
        void onResult(T result);
    }
}
