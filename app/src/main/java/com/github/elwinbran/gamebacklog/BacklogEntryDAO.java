package com.github.elwinbran.gamebacklog;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BacklogEntryDAO
{
    @Query("SELECT * FROM backlogEntry")
    public List<BacklogEntry> getAllEntries();

    @Insert
    public void insertEntry(BacklogEntry entry);

    @Delete
    public void deleteEntry(BacklogEntry entry);

    @Update
    public void updateEntry(BacklogEntry entry);
}
