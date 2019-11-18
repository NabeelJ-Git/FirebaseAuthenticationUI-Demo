package com.example.android.firebase_demo;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeScreen extends Fragment {

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public WelcomeScreen() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        user.reload();

        View rootView = inflater.inflate(R.layout.fragment_welcome_screen, container, false);

        TextView anonText = rootView.findViewById(R.id.anonymousText);
        TextView userName = rootView.findViewById(R.id.userName);
        TextView userEmail = rootView.findViewById(R.id.userEmail);
//        TextView userPhoneNumber = rootView.findViewById(R.id.userPhoneNumber);


        if(user.isAnonymous()){
            anonText.setText(getResources().getString(R.string.anon_user_text));
        }

        else if (!user.isAnonymous()) {
            userName.append(user.getDisplayName());
            userEmail.append(user.getEmail());
//            userPhoneNumber.append(user.getPhoneNumber()); //This code was causing some problems for me so I commented it out for now

        }

        return rootView;
    }

}


