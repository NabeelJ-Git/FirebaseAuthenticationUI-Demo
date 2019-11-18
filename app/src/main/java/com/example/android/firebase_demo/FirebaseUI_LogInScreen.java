package com.example.android.firebase_demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class FirebaseUI_LogInScreen extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (user != null) {
            // User is signed in
            Intent homeIntent = new Intent(this, NavigationActivity.class);
            startActivity(homeIntent);
            finish();

        } else {
            // No user is signed in
            showFirebaseUI();
        }
    }

    /**
     * This method is used to specify the list oof providers to show
     * the user. Then we set the providers and build the UI with
     * startActivityForResult.
     */
    public void showFirebaseUI() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.AnonymousBuilder().build());

        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.AppTheme)
                .setLogo(R.drawable.baseline_android_black_48)
                .setIsSmartLockEnabled(false)
                .build(), RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Intent homeIntent = new Intent(this, NavigationActivity.class);
                startActivity(homeIntent);
                finish();
                user.reload(); //This is used to refresh user information from Firebase to avoid certain NPE's

            } else {
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
