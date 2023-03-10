package com.example.foodwar.user_management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.foodwar.MainActivity;
import com.example.foodwar.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpTabFragment extends Fragment {
   EditText email;
   EditText pass;
   EditText mobile_num,cf_pass;

    private FirebaseAuth mAuth;

    Button signupButton;

    float v=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle saveInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.password);

        cf_pass = root.findViewById(R.id.cf_password);
        signupButton = root.findViewById(R.id.btn_signup);



        email.setTranslationX(800);
        pass.setTranslationX(800);

        cf_pass.setTranslationX(800);
        signupButton.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);

        cf_pass.setAlpha(v);
        signupButton.setAlpha(v);


        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();

        cf_pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signupButton.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        mAuth = FirebaseAuth.getInstance(); // Initialize FirebaseAuth instance

        // Disable the signup button initially
        signupButton.setEnabled(false);

        // Add a TextWatcher to check for errors and enable/disable the signup button
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean emailValid = isValidEmail(email.getText().toString().trim());
                boolean passwordValid = isValidPassword(pass.getText().toString().trim());
                boolean confirmPasswordValid = isValidConfirmPassword(pass.getText().toString().trim(), cf_pass.getText().toString().trim());

                if (emailValid && passwordValid && confirmPasswordValid) {
                    signupButton.setEnabled(true);
                } else {
                    signupButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        email.addTextChangedListener(textWatcher);
        pass.addTextChangedListener(textWatcher);
        cf_pass.addTextChangedListener(textWatcher);

//event signup for button sign up
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stremail = email.getText().toString().trim();
                String strpassword = pass.getText().toString().trim();
                // show a loading dialog while signing in
                ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Signing up. Please wait...", true);
                mAuth.createUserWithEmailAndPassword(stremail, strpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign up successful
                                    Toast.makeText(getActivity(), "Sign up successful", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
                                    HashMap<String, String> userInfo = new HashMap<>();
                                    userInfo.put("email", user.getEmail());
                                    userInfo.put("role", "user");
                                    userRef.setValue(userInfo);

                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                } else {
                                    // Sign up failed
                                    String errorMessage = task.getException().getMessage();
                                    Log.e("SignupTabFragment", "Sign up failed: " + errorMessage);
                                    Toast.makeText(getActivity(), "Sign up failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
 

                return root;


    }

    private boolean isValidEmail(String stremail) {
        if (TextUtils.isEmpty(stremail)) {
            email.setError("Email is required");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(stremail).matches()) {
            email.setError("Invalid email");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean isValidPassword(String strpassword) {
        if (TextUtils.isEmpty(strpassword)) {
            pass.setError("Password is required");
            return false;
        } else if (strpassword.length() < 6) {
            pass.setError("Password must be at least 6 characters");
            return false;
        } else {
            pass.setError(null);
            return true;
        }
    }

    private boolean isValidConfirmPassword(String strpassword, String strconfirmPassword) {
        if (TextUtils.isEmpty(strconfirmPassword)) {
            cf_pass.setError("Confirm password is required");
            return false;
        }

        if (!strconfirmPassword.equals(strpassword)) {
            cf_pass.setError("Passwords do not match");
            return false;
        }

        return true;

    }


}
