package com.example.android.firebase_demo;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsScreen extends Fragment {

    private MaterialButton sign_out;
    private MaterialButton delete_account;
    private MaterialButton create_account;

    public SettingsScreen() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings_screen, container, false);

        sign_out = rootView.findViewById(R.id.sign_out);
        delete_account = rootView.findViewById(R.id.delete_account);
        create_account = rootView.findViewById(R.id.create_account);

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(requireContext()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Intent main_firebaseUI = new Intent(requireContext(), FirebaseUI_LogInScreen.class);
                        main_firebaseUI.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(main_firebaseUI);

                        Toast.makeText(requireContext(), "You have been signed out", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(requireContext(), "Failed to sign out, please try again later", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        //Remember this will not work unless the user has signed in recently. Read my Medium post for more details
        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AuthUI.getInstance().delete(requireContext()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(requireContext(), "Your account has been deleted", Toast.LENGTH_SHORT).show();

                        Intent main_firebaseUI = new Intent(requireContext(), FirebaseUI_LogInScreen.class);
                        main_firebaseUI.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(main_firebaseUI);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(requireContext(), "Failed to delete account, please try again later", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent anon_create_account = new Intent(requireContext(), FirebaseUI_AnonUpgradeScreen.class);
                startActivity(anon_create_account);
            }
        });

        return rootView;
    }

}
