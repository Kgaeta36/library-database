package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.ActivityCreateAccountBinding;
import com.example.project2.databinding.ActivityMainMenuBinding;

import java.util.List;

public class CreateAccount extends AppCompatActivity {
    private ActivityCreateAccountBinding binding;
    private LibraryDatabase db;
    private boolean error = false;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LibraryDatabase.getInstance(this);



        binding.createAccountButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(CreateAccount.this, MainMenu.class);


                        String username = binding.userInput.getText().toString();
                        String password = binding.passInput.getText().toString();
                        //check to make sure nothings empty
                        if (isEmpty(username) || isEmpty(password)) {
                            if (error) {
                                Toast.makeText(CreateAccount.this, R.string.user, Toast.LENGTH_LONG).show();
                                startActivity(i);
                            }
                            error = true;
                            Toast.makeText(CreateAccount.this, R.string.userError, Toast.LENGTH_LONG).show();

                        // no copies >:(
                        } else if (userExists(username)) {
                            if (error) {
                                Toast.makeText(CreateAccount.this, R.string.user, Toast.LENGTH_LONG).show();
                                startActivity(i);
                            }
                            error = true;
                            Toast.makeText(CreateAccount.this, R.string.userDouble, Toast.LENGTH_LONG).show();

                            // good to go o7
                        } else {
                            addInfo(username, password);
                            Toast.makeText(CreateAccount.this, R.string.accountSuccess, Toast.LENGTH_LONG).show();
                            startActivity(i);
                        }


                    }



                });

    // go home
        binding.mainMenuBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(CreateAccount.this, MainMenu.class);
                        startActivity(i);
                    }
                }
        );

    }
    private boolean isEmpty(String input){
        return (input.length() == 0);
    }
    private boolean userExists (String user){
      List<String> usernames = db.account().sameUser();
        for (String username : usernames) {
            if (username.equals(user)){
                return true;
            }
        }
        return false;
    }
    private void addInfo(String user, String pass){
        Account acc = new Account(user, pass);
        Transaction tra = new Transaction("New Account", user);
        db.transaction().addTransaction(tra);
        db.account().addAccount(acc);

    }
}
