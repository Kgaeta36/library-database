package com.example.project2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.project2.databinding.DialogLoginBinding;


public class LoginDialog  extends DialogFragment {


    private LibraryDatabase db;
    private DialogLoginBinding binding;
    private  DialogFragment dialogFragment;
    private boolean error = false;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogLoginBinding.inflate(LayoutInflater.from(getContext()));
        db = LibraryDatabase.getInstance(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setView(binding.getRoot())
                .setTitle("Login!")
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String user = String.valueOf(binding.userIn.getText());
                        String pass = String.valueOf(binding.passIn.getText());
                        Account acc = db.account().userVerify(user);
                        Account lib = db.account().librarianVerify();

                        if (acc != null && acc.getPassword().equals(pass) && !lib.getUsername().equals(user)){
                              Bundle args = new Bundle();
                              args.putCharSequence("title", getArguments().getCharSequence("book"));
                              args.putInt("reserve", getArguments().getInt("reserve"));
                              args.putCharSequence("user", user);
                              Intent i = new Intent(getContext(), Confirmation.class);
                              i.putExtras(args);
                              startActivity(i);
                        }else {
                           if (error){
                               Toast.makeText(getContext(), "ERROR: Unable to login!", Toast.LENGTH_SHORT).show();
                               Intent in = new Intent(getContext(), MainMenu.class);
                               startActivity(in);
                           }
                           error = true;
                            Toast.makeText(getContext(), "OOPS! Invalid Login information! Try Again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return builder.create();
    }
}
