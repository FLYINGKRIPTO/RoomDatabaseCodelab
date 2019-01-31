package com.example.roomdatabasecodelab;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {
    //A Repository is a class that abstracts access to multiple data sources.
    // The Repository is not part of the Architecture Components libraries,
    // but is a suggested best practice for code separation and architecture.
    // A Repository class handles data operations.
    // It provides a clean API to the rest of the app for app data.
      private WordDao mWordDao;
      //Add member variables for the DAO and the list of words.
      private LiveData<List<Word>> mAllWords;

      //Add a constructor that gets a handle to the database and initializes the member variables.
      WordRepository(Application application){
          WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
          mWordDao  = db.wordDao();
          mAllWords  = mWordDao.getAllWords();
      }
      //Add a wrapper for getAllWords(). Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    LiveData<List<Word>> getmAllWords() {
          return mAllWords;
    }

    //Add a wrapper for the insert() method.
    //You must call this on a non-UI thread or your app will crash.
    // Room ensures that you don't do any long-running operations on the main thread,
    // blocking the UI.
    public void insert (Word word) {
          new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word,Void,Void> {
          private WordDao mAsyncTaskDao;

          insertAsyncTask(WordDao wordDao) {
              mAsyncTaskDao = wordDao;
          }

        @Override
        protected Void doInBackground(final Word... words) {
              mAsyncTaskDao.insert(words[0]);
              return null;
        }
    }

}
