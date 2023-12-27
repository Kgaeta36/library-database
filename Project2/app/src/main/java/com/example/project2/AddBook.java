package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.project2.databinding.ActivityAddBookBinding;

public class AddBook extends AppCompatActivity {

   private ActivityAddBookBinding binding;
   private LibraryDatabase db;
   private DialogFragment dialogFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LibraryDatabase.getInstance(this);

        binding.addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.newTitle.getText().toString();
                String author = binding.newAuthor.getText().toString();
                String genre = binding.newGenre.getText().toString();
                if (isEmpty(genre) || isEmpty(title) || isEmpty(author)){
                   dialogFragment = new NewBookDialog();
                   dialogFragment.show(getSupportFragmentManager(),"");
                } else {
                    Bundle info = new Bundle();
                    info.putCharSequence("title", title);
                    info.putCharSequence("author", author);
                    info.putCharSequence("genre", genre);
                    Intent i = new Intent(AddBook.this, NewBookConfirmation.class);
                    i.putExtras(info);
                    startActivity(i);
                }
            }
        });
    }

    private boolean isEmpty(String input){
        return (input.length() == 0);
    }
}
