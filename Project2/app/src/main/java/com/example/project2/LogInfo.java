package com.example.project2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.project2.databinding.ActivityLogInfoBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogInfo extends AppCompatActivity {
    private ActivityLogInfoBinding binding;
    private LibraryDatabase db;
    private ListView mListView;
    private ArrayAdapter<String> mArrayAdapter;
    private List<String> typeList;
    private List<String> userList;

    private List<String> transactionList;
    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LibraryDatabase.getInstance(this);
        mListView = binding.transactions;
        typeList = db.transaction().getType();
        userList = db.transaction().getUser();
        transactionList = new ArrayList<>(typeList.size());
        String transaction;
        for (int i = 0; i < typeList.size(); i++) {
            transaction = "Transaction type: " + typeList.get(i) + "\nCustomer's username: " + userList.get(i);
            transactionList.add(transaction);
        }
        mArrayAdapter = new ArrayAdapter<>(this, R.layout.item_transactction, R.id.trans_type, transactionList);
        mListView.setAdapter(mArrayAdapter);

        binding.cont.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogFragment = new NewBookDialog();
                        dialogFragment.show(getSupportFragmentManager(), "");
                    }

                }
        );
    }
}
