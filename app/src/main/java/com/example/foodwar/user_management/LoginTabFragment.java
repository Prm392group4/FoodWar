package com.example.foodwar.user_management;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.foodwar.MainActivity;
import com.example.foodwar.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginTabFragment extends Fragment {

    EditText email, pass;
    TextView forgetPass,swipe;
    Button login_button;
    float v =0;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.password);
        forgetPass = root.findViewById(R.id.forget_pass);
        swipe=root.findViewById(R.id.tv_signUp);
        login_button = root.findViewById(R.id.login_btn);


        email.setTranslationX(800);
        pass.setTranslationX(800);
        forgetPass.setTranslationX(800);
        swipe.setTranslationX(800);
        login_button.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        swipe.setAlpha(v);
        login_button.setAlpha(v);


        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        swipe.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        login_button.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();


        mAuth = FirebaseAuth.getInstance(); // Initialize FirebaseAuth instance

        //event signup for button sign up
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stremail = email.getText().toString().trim();
                String strpassword = pass.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(stremail, strpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Login successful
                                    String userId = mAuth.getCurrentUser().getUid();
                                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);

                                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String role = snapshot.child("role").getValue(String.class);

                                            if (role.equals("user")) {
                                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                                startActivity(intent);
                                                getActivity().finish();
                                            } else if (role.equals("admin")) {
                                                Intent intent = new Intent(getActivity(), AdminActivity.class);
                                                startActivity(intent);
                                                getActivity().finish();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            // Handle database error
                                        }
                                    });
                                } else {
                                    // Login failed
                                    String errorMessage = task.getException().getMessage();
                                    Log.e("LoginTabFragment", "Login failed: " + errorMessage);
                                    Toast.makeText(getActivity(), "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });




        return root;


    }


}