package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.ActivityMainMenuBinding;


public class MainMenu extends AppCompatActivity{
    private ActivityMainMenuBinding binding;

    private LibraryDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LibraryDatabase.getInstance(this);


            //CREATE ACCOUNT
       binding.createAccountButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              Intent i = new Intent(MainMenu.this, CreateAccount.class);
              startActivity(i);
           }
       });

       // PLACE HOLD
       binding.placeHoldButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(MainMenu.this, PlaceHold.class);
               startActivity(i);           }
       });

       // MANAGE SYSTEM

       binding.manageSystemButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(MainMenu.this, ManageSystem.class);
               startActivity(i);           }
       });



    }
}
