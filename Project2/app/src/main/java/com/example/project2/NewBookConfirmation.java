package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.ActivityNewBookConfirmationBinding;

public class NewBookConfirmation extends AppCompatActivity {
    private LibraryDatabase db;
    private ActivityNewBookConfirmationBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewBookConfirmationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LibraryDatabase.getInstance(this);
        Bundle info = getIntent().getExtras();
        String title = info.getString("title");
        String author = info.getString("author");
        String genre = info.getString("genre");

        binding.confirm.setText("Title: " + title + "\nAuthor: " + author +
                "\nGenre: " + genre);
        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.book().addBook(new Book(title, author, genre));
                Toast.makeText(NewBookConfirmation.this, "Book Added!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(NewBookConfirmation.this, MainMenu.class);
                startActivity(i);
            }
        });
    }
}
