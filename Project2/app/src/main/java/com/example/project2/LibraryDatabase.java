package com.example.project2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.io.File;

@Database(entities = {Account.class, Book.class, Transaction.class}, version = 7, exportSchema = false)
public abstract class LibraryDatabase extends RoomDatabase {
    public abstract AccountDao account();
    public abstract TransactionDao transaction();
    public abstract BookDao book();
    private static LibraryDatabase sInstance;


    public static synchronized LibraryDatabase getInstance(Context context){
        if (sInstance == null){
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            LibraryDatabase.class, "library.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
            sInstance.populateInitialData();
        }
        return sInstance;
    }


    public void populateInitialData(){
        if (account().count() == 0 && book().count() == 0) {
            runInTransaction(() -> {
                account().addAccount(
                        new Account("!admin2", "!admin2"));
                account().addAccount(
                        new Account("hShuard", "m@thl3t3"));
                account().addAccount(
                        new Account("bMishra", "bioN@no"));
                account().addAccount(
                        new Account("shirleyBee", "Carmel2Chicago"));

                book().addBook(
                        new Book("A Heartbreaking Work of Staggering Genius",
                                "Dave Eggers", "Memoir"));
                book().addBook(
                        new Book("The IDA Pro Book", "Chris Eagle",
                                "Computer Science"));
                book().addBook(
                        new Book("Frankenstein", "Mary Shelly",
                                "Fiction"));
            });
        }

    }
}
