package com.example.roomdatabasecodelab;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deteleAll();

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    //LiveData, a lifecycle library class for data observation,
    // solves this problem. Use a return value of type LiveData in your method
    // description, and Room generates all necessary code to
    // update the LiveData when the database is updated.

    //you create an Observer of the data in the onCreate() method
    // of MainActivity and override the observer's onChanged() method.
    // When the LiveData changes, the observer is notified and onChanged()
    // is executed. You will then update the cached data in the adapter,
    // and the adapter will update what the user sees.
}
