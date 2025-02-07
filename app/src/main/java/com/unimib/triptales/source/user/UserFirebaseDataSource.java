package com.unimib.triptales.source.user;

import android.net.Uri;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UserFirebaseDataSource extends BaseUserDataRemoteDataSource {
    private final DatabaseReference databaseReference;

    public UserFirebaseDataSource(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    public void saveUserPicture(FirebaseUser user, String imageUrl, OnSuccessListener<Void> successListener, OnFailureListener failureListener) {
        String userId = user.getUid();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("email", user.getEmail());
        userMap.put("imageUrl", imageUrl);

        databaseReference.child(userId).setValue(userMap)
                .addOnSuccessListener(successListener)
                .addOnFailureListener(failureListener);
    }
}
