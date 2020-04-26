package com.eli.nationalid.room.model;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eli.nationalid.room.database.AppDatabase;
import com.eli.nationalid.room.database.DatabaseClient;
import com.eli.nationalid.room.entities.DataListEntity;

import java.util.List;

public class infoListViewModel extends AndroidViewModel {

    Context context = this.getApplication();

    private final LiveData<List<DataListEntity>> itemInfoList;


    private AppDatabase appDatabase;

    public infoListViewModel(Application application) {
        super(application);
        appDatabase = DatabaseClient.getmInstance(context).getAppDatabase();
        itemInfoList = appDatabase.infoModelDao().getdataLists();
    }

    public LiveData<List<DataListEntity>> getItemInfoList() {
        return itemInfoList;
    }
}
