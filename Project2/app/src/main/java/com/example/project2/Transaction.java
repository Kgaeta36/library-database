package com.example.project2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactionBank")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "username")
    private String username;



    public Transaction(String type, String username) {
        this.type = type;
        this.username = username;
    }

    public int getId(){return this.id;}
    public void setId(int id){this.id = id;}
    public String getType(){return this.type;}



    public void setType(String type){this.type = type;}
    public String getUsername(){return this.username;}
    public void setUsername(String username){this.username = username;}
}