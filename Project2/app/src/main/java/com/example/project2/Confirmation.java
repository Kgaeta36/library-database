package com.example.project2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.ActivityConfimationBinding;


public class Confirmation extends AppCompatActivity {
    private LibraryDatabase db;
    private ActivityConfimationBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfimationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db  = LibraryDatabase.getInstance(this);
        Bundle info = getIntent().getExtras();
        String title = info.getString("title");
        String user = info.getString("user");
        int reserve = info.getInt("reserve");

        binding.confirm.setText("Customer Username: " + user + "\nBook Title: "
                + title + "\nReservation Number: " + reserve);

        // Confirm and log that lil guy
        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book b = db.book().findByTitle(title);
                b.setAvailable(false);
               db.book().update(b);
               db.transaction().addTransaction(new Transaction("Place Hold", user));
                Toast.makeText(Confirmation.this, "Hold Successful!", Toast.LENGTH_SHORT).show();
               Intent i = new Intent(Confirmation.this, MainMenu.class);
                startActivity(i);

            }
        });

        // GO HOME
        binding.mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Confirmation.this, MainMenu.class);
                startActivity(i);
            }
        });

    }
}
