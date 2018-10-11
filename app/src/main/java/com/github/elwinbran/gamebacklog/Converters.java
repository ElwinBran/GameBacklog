package com.github.elwinbran.gamebacklog;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class Converters
{
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static GameStatus fromInteger(Integer value){
        return GameStatus.values()[value];
    }

    @TypeConverter
    public static Integer statusToInteger(GameStatus status){
        return status.ordinal();
    }
}
