package com.example.project2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountDao {
    @Insert
    void addAccount(Account account);

    @Query("SELECT COUNT(*) FROM accountBank")
    int count();
    @Query("SELECT username FROM accountBank")
    List<String> sameUser();
    @Query("SELECT * FROM accountBank WHERE id = 1")
    Account librarianVerify();
    @Query("SELECT * FROM accountBank WHERE username = :user")
    Account userVerify(String user);
    @Query("SELECT * FROM accountBank")
    List<Account> getAll();
    @Delete
    void delete(Account account);
}


