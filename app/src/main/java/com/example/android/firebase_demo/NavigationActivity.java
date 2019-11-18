package com.example.android.firebase_demo;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NavigationActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    WelcomeScreen welcomeScreen = new WelcomeScreen();
    SettingsScreen settingsScreen = new SettingsScreen();

    final FragmentManager fragmentManager = getSupportFragmentManager();

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        welcomeScreen = new WelcomeScreen();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.navigationView, welcomeScreen);
        transaction.addToBackStack(null);
        transaction.commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.welcome_menu):

                        if (!welcomeScreen.isAdded()) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.navigationView, welcomeScreen).commit();

                        }

                        break;

                    case (R.id.settings_menu):

                        if (!settingsScreen.isAdded()) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.navigationView, settingsScreen).commit();

                        }

                        break;

                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {

        user.reload();

        finish();
        super.onBackPressed();


    }

}
