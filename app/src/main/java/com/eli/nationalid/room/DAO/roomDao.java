package com.eli.nationalid.room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.eli.nationalid.room.entities.DataListEntity;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface roomDao {

    @Query("SELECT * FROM data_list ")
    LiveData<List<DataListEntity>> getdataLists();

    @Insert(onConflict = REPLACE)
    void insertDataList(DataListEntity dataListEntity);

    @Delete
    void deleteDataList(DataListEntity dataListEntity);

    @Update
    void updateDataList(DataListEntity dataListEntity);


}
