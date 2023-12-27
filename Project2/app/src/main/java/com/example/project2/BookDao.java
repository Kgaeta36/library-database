package com.example.project2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void addBook(Book book);
    @Query("SELECT COUNT(*) FROM bookBank")
    int count();
    @Query("SELECT * FROM bookBank")
    List<Book> getAll();
    @Query("SELECT DISTINCT genre FROM bookBank ")
    List<String> getGenres();
    @Query("SELECT title FROM bookBank WHERE genre = :genre AND available = 1")
    List<String> findByGenre(String genre);
    @Query("SELECT * FROM bookBank WHERE title = :title")
    Book findByTitle(String title);
    @Update
    void update(Book book);
    @Delete
    void delete(Book book);
}
