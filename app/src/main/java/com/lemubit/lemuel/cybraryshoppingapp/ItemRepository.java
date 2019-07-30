package com.lemubit.lemuel.cybraryshoppingapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {
    private Context context;

    public ItemRepository(Context context) {
        this.context = context.getApplicationContext();
    }

    //enable Java 8 and replace with Lambda
    public void addNewItem(DbTable dbTable) {
        AsyncTask.execute(() -> DbDatabase.getInstance(context).dbDao().insertItems(dbTable));
    }

    /**
     * Room doesn't allow you to issue database queries on the main thread.
     * LiveData applies this rule by automatically running the query
     * asynchronously on a background thread
     **/
    public LiveData<List<DbTable>> getItems() {
        return DbDatabase.getInstance(context).dbDao().getAllItems();
    }

    public LiveData<Integer> getItemCount() {
        return DbDatabase.getInstance(context).dbDao().countItems();
    }

    public void deleteItem(DbTable dbTable) {
        AsyncTask.execute(() -> DbDatabase.getInstance(context).dbDao().deleteItem(dbTable));
    }


}
