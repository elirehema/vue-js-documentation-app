package com.eli.nationalid.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.eli.nationalid.room.DAO.roomDao;
import com.eli.nationalid.room.entities.DataListEntity;

@Database(entities = {DataListEntity.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract roomDao infoModelDao();
}