package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import com.example.project2.databinding.ActivityPlaceHoldBinding;

import java.util.List;

public class PlaceHold extends AppCompatActivity {
    private LibraryDatabase db;
    private ActivityPlaceHoldBinding binding;
    private List<String> genreList;



    private List<String> booksList;
    private ArrayAdapter<String> bookAdapter;
    private ArrayAdapter<String> genreAdapter;
     private DialogFragment dialogFragment;
     private Spinner genreSpinner;
     private Spinner bookSpinner;
     private static int reserve = 24601;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaceHoldBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LibraryDatabase.getInstance(this);


        //GENRE SPINNER
        genreList = db.book().getGenres();

        genreSpinner = binding.genreList;

        genreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genreList);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(genreAdapter);



        // set up book spinner! - DONE :)
        binding.genreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String genreSelected = (String) genreSpinner.getSelectedItem();

                if (genreSelected != null){
                    booksList = db.book().findByGenre(genreSelected);
                    // check for no books!
                    if (booksList.size() !=  0){
                        bookSpinner = binding.bookList;

                        bookAdapter = new ArrayAdapter<>(PlaceHold.this, android.R.layout.simple_spinner_item, booksList);
                        bookAdapter.setDropDownViewResource(android.R.layout.simple_gallery_item);
                        bookSpinner.setAdapter(bookAdapter);
                    } else {
                        Toast.makeText(PlaceHold.this, "SORRY! No available books in that genre!", Toast.LENGTH_LONG).show();
                        if (bookAdapter != null){
                            bookAdapter.clear();
                            bookSpinner.setAdapter(bookAdapter);
                        }

                    }

                }
            }
        });
        // place hold on book!
        binding.bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // no book picked?
                if (bookSpinner == null){
                    Toast.makeText(PlaceHold.this, "OPPS! Please pick a book to place on hold!", Toast.LENGTH_SHORT).show();
                }
                String bookSelected = (String) bookSpinner.getSelectedItem();
                // go to login screen
                dialogFragment = new LoginDialog();
                Bundle args = new Bundle();
                args.putCharSequence("book", bookSelected);
                args.putInt("reserve", reserve);
                reserve++;
                dialogFragment.setArguments(args);
                dialogFragment.show(getSupportFragmentManager(), "");

            }
        });


        // go home!
        binding.mainMenuBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(PlaceHold.this, MainMenu.class);
                        startActivity(i);
                    }
                }
        );

        }

    }


