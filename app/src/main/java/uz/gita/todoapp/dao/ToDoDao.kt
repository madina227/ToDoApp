package uz.gita.todoapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.gita.todoapp.entity.ToDoEntity

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo WHERE state=:state AND is_removed=0")
    fun getToDoes(state: Int): LiveData<List<ToDoEntity>>

    @Query("SELECT * FROM todo WHERE id=:id")
    fun getItem(id: Int): ToDoEntity

    @Insert
    fun insert(toDoEntity: ToDoEntity)

    @Update
    fun update(toDoEntity: ToDoEntity)

    @Delete
    fun delete(toDoEntity: ToDoEntity)

    @Delete
    fun deleteAll(list: List<ToDoEntity>)
}