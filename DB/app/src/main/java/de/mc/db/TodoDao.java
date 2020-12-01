package de.mc.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class TodoDao {

    @Insert
    abstract long insert(Todo todo);
    @Update abstract void update(Todo todo);
    @Delete abstract void delete(Todo todo);

    @Query("DELETE FROM todo")
    abstract int deleteAll();

    @Query("SELECT * FROM todo WHERE id=:todoid")
    abstract Todo getById(long todoid);

    @Query("SELECT * FROM todo ORDER BY deadline ASC")
    abstract List<Todo> getAll();

}
