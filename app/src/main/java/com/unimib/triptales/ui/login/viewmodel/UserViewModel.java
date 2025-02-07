package com.unimib.triptales.ui.login.viewmodel;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.unimib.triptales.model.Result;
import com.unimib.triptales.model.User;
import com.unimib.triptales.repository.user.IUserRepository;

public class UserViewModel extends ViewModel {

    private static final String TAG = UserViewModel.class.getSimpleName();

    private final IUserRepository userRepository;
    private MutableLiveData<Result> userMutableLiveData;
    private boolean authenticationError;

    public UserViewModel(IUserRepository userRepository) {
        this.userRepository = userRepository;
        authenticationError = false;
    }

    public MutableLiveData<Result> getUserMutableLiveData(String email, String password, boolean isUserRegistered) {
        if (userMutableLiveData == null) {
            fetchUserData(email, password, isUserRegistered);
        }
        return userMutableLiveData;
    }

    public void fetchUserData(String email, String password, boolean isUserRegistered) {
        Log.d(TAG, "fetchUserData: Fetching user data");
        userMutableLiveData = userRepository.getUser(null, null, email, password, isUserRegistered);
    }

    public MutableLiveData<Result> signupUser(String name, String surname, String email, String password) {
        if (userMutableLiveData == null) {
            Log.d(TAG, "signupUser: Signing up user");
            userMutableLiveData = userRepository.signUp(name, surname, email, password);
        }
        return userMutableLiveData;
    }

    public MutableLiveData<Result> getGoogleUserMutableLiveData(String token) {
        Log.d(TAG, "getGoogleUserMutableLiveData: Signing in with Google token");
        return userRepository.signInWithGoogle(token);
    }

    public MutableLiveData<Result> signUpWithGoogle(String idToken) {
        if (userMutableLiveData == null) {
            Log.d(TAG, "signUpWithGoogle: Signing up with Google ID token");
            userMutableLiveData = userRepository.signUpWithGoogle(idToken);
        }
        return userMutableLiveData;
    }

    public User getLoggedUser() {
        return userRepository.getLoggedUser();
    }

    public MutableLiveData<Result> logout() {
        if (userMutableLiveData == null) {
            Log.d(TAG, "logout: Logging out user");
            userMutableLiveData = userRepository.logout();
        } else {
            userRepository.logout();
        }
        return userMutableLiveData;
    }

    public boolean isAuthenticationError() {
        return authenticationError;
    }

    public void setAuthenticationError(boolean authenticationError) {
        this.authenticationError = authenticationError;
    }

    public MutableLiveData<Result> resetPassword(String email) {
        Log.d(TAG, "resetPassword: Resetting password");
        return userRepository.resetPassword(email);
    }

    public void saveUserPicture(FirebaseUser user, String imageUrl, OnSuccessListener<Void> successListener, OnFailureListener failureListener) {
        userRepository.saveUserPicture(user, imageUrl, successListener, failureListener);
    }
}
