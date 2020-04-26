package com.eli.nationalid.room.database;

import androidx.room.Room;

import android.content.Context;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    private AppDatabase appDatabase;

    private DatabaseClient(Context mCtx) {


        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "vuejs").allowMainThreadQueries().build();

    }

    public static synchronized DatabaseClient getmInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
