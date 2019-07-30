package com.lemubit.lemuel.cybraryshoppingapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**They are both instances of Context, but the application instance
// is tied to the lifecycle of the application, while the Activity
// instance is tied to the lifecycle of an Activity.**/
public class MainActivityViewModel extends AndroidViewModel {
    private ItemRepository itemRepository;
    private LiveData<List<DbTable>> items;
    private LiveData<Integer> itemCount;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        itemRepository= new ItemRepository(application.getApplicationContext());
        items= itemRepository.getItems();
        itemCount= itemRepository.getItemCount();

    }

    public void insertNewItem(DbTable dbTable)
    {
        itemRepository.addNewItem(dbTable);
    }

    public void deleteItem(DbTable dbTable)
    {
        itemRepository.deleteItem(dbTable);
    }

    public LiveData<Integer> getItemCount() {
        return itemCount;
    }

    public LiveData<List<DbTable>> getItems() {
        return items;
    }
}
