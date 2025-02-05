package com.unimib.triptales.repository.goal;

import com.unimib.triptales.model.Goal;

import java.util.List;

public interface GoalResponseCallback {
    void onSuccessFromRemote();
    void onSuccessFromRemote(List<Goal> goals);
    void onFailureFromRemote(Exception exception);
    void onSuccessFromLocal();
    void onSuccessFromLocal(List<Goal> goals);
    void onSuccessSelectionFromLocal(List<Goal> goals);
    void onSuccessCheckedFromLocal(List<Goal> goals);
    void onFailureFromLocal(Exception exception);
}
