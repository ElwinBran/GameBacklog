package com.github.elwinbran.gamebacklog;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "backlogEntry")
public class BacklogEntry implements Parcelable
{
    public final static Parcelable.Creator<BacklogEntry> CREATOR =
            new Parcelable.Creator<BacklogEntry>() {
        public BacklogEntry createFromParcel(Parcel in) {
            return new BacklogEntry(in);
        }

        public BacklogEntry[] newArray(int size) {
            return new BacklogEntry[size];
        }
    };

    @PrimaryKey
    @NonNull
    private String gameTitle;

    @ColumnInfo(name = "platform")
    private String platform;

    @ColumnInfo(name = "notes")
    private String notes;

    @ColumnInfo(name = "added")
    @TypeConverters(Converters.class)
    private Date added;

    @ColumnInfo(name = "status")
    @TypeConverters(Converters.class)
    private GameStatus status;

    public BacklogEntry()
    {
        this.gameTitle = "";
    }

    public BacklogEntry(Parcel in)
    {
        this();
        this.setGameTitle(in.readString());
        this.setPlatform(in.readString());
        this.setNotes(in.readString());
        this.setAdded(Converters.fromTimestamp(in.readLong()));
        this.setStatus(Converters.fromInteger(in.readInt()));
    }

    public BacklogEntry(String gameTitle, String platform, String notes, Date added, GameStatus status)
    {
        this();
        this.setGameTitle(gameTitle);
        this.setPlatform(platform);
        this.setNotes(notes);
        this.setAdded(added);
        this.setStatus(status);
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(this.getGameTitle());
        parcel.writeString(this.getPlatform());
        parcel.writeString(this.getNotes());
        parcel.writeLong(Converters.dateToTimestamp(this.getAdded()));
        parcel.writeInt(Converters.statusToInteger(this.getStatus()));
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}
