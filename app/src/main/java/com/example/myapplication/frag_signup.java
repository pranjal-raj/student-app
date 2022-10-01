package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class frag_signup extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frag_signup, container, false);

        EditText username = view.findViewById(R.id.emailid);
        EditText password = view.findViewById(R.id.password);
        EditText repassword = view.findViewById(R.id.repass);
        Button signup = view.findViewById(R.id.registerbtn);
        DBHelper DB = new DBHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= username.getText().toString();
                String pass= password.getText().toString();
                String repass = repassword.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)) {
                    Toast.makeText(getActivity(), "All fields are required...", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false) {
                            Boolean insert = DB.insertdata(user, pass);
                            if (insert == true) {
                                Toast.makeText(getActivity(), "Registered successfully\nRedirecting to Login page...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getActivity(), "User already exists...", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                                Toast.makeText(getActivity(), "Passwords not matching...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        });



        return  view;
    }

}