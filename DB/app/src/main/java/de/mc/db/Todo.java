package de.mc.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "todo")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name="title")
    public String title;

    @ColumnInfo(name="content")
    public String content;

    @ColumnInfo(name = "deadline")
    public long deadline;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id &&
                deadline == todo.deadline &&
                Objects.equals(title, todo.title) &&
                Objects.equals(content, todo.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, deadline);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", deadline=" + deadline +
                '}';
    }

    public static Todo create(String title, String content, long deadline){
        Todo todo = new Todo();
        todo.title=title;
        todo.content=content;
        todo.deadline=deadline;
        return todo;
    }
}
