package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.project2.databinding.ActivityManageSystemBinding;

public class ManageSystem extends AppCompatActivity {
    private ActivityManageSystemBinding binding;
    private LibraryDatabase db;
    private DialogFragment dialogFragment;
    private boolean error = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManageSystemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LibraryDatabase.getInstance(this);

        binding.libLogBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText user = binding.userIn;
                        EditText pass = binding.passIn;

                        String username = user.getText().toString();
                        String password = pass.getText().toString();
                        Account admin = db.account().librarianVerify();
                        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)){
                            Intent i = new Intent(ManageSystem.this, LogInfo.class);
                            startActivity(i);
                        } else {
                            if (error){
                                Toast.makeText(ManageSystem.this, "ERROR: Unable to login!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ManageSystem.this, MainMenu.class);
                                startActivity(i);
                            }
                            error = true;
                            Toast.makeText(ManageSystem.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
        );
        // go home
        binding.mainMenuBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ManageSystem.this, MainMenu.class);
                        startActivity(i);
                    }
                }
        );
    }
}
