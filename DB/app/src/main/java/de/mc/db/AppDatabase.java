package de.mc.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Todo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
    private static AppDatabase INSTANCE = null;
    private static AppDatabase createInstance(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "tododb")
                .enableMultiInstanceInvalidation()
                .fallbackToDestructiveMigration()
                .build();
    }
    public static synchronized AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = createInstance(context);
        }
        return INSTANCE;
    }
}
