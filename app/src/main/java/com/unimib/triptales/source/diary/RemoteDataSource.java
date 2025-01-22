package com.unimib.triptales.source.diary;


public class RemoteDataSource {

    // Placeholder for remote API calls, e.g., Retrofit
    public void fetchDiaries(RemoteCallback callback) {
        // Implementa chiamate API qui
    }

    public interface RemoteCallback {
        void onResponse(Object response);
        void onFailure(Throwable t);
    }
}
