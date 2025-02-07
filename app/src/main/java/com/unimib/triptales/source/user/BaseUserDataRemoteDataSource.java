package com.unimib.triptales.source.user;

import android.net.Uri;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.unimib.triptales.repository.user.UserResponseCallback;

public abstract class BaseUserDataRemoteDataSource {
    protected UserResponseCallback userResponseCallback;

    public void setUserResponseCallback(UserResponseCallback userResponseCallback) {
        this.userResponseCallback = userResponseCallback;
    }

    public abstract void saveUser(FirebaseUser user, String imageUrl, OnSuccessListener<Void> successListener, OnFailureListener failureListener);
    public abstract void uploadProfilePicture(Uri imageUri, String userId, OnSuccessListener<Uri> successListener, OnFailureListener failureListener);
}
