package com.example.roomdatabasecodelab;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    //Add a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash. Room ensures that you don't do any long-running operations on the main thread, blocking the UI.
    private WordRepository mRepository;
    //Add a private LiveData member variable to cache the list of words
    private LiveData<List<Word>> mAllWords;

    //Add a constructor that gets reference to the repository
    //and gets the list of words from the repository

    public WordViewModel (Application application){
        super(application);
        mRepository  = new WordRepository(application);
        mAllWords = mRepository.getmAllWords();
    }
    //Add a getter method for all the words
    //This completely hides the implementation from UI
    LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }

    //Create a Wrapper insert() method that calls the repository's insert()
    //method , in this way implementation of insert() is completely hidden from the UI

    public  void insert(Word word ){
        mRepository.insert(word);
    }

}
