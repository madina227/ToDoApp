package uz.gita.todoapp.repository

import androidx.lifecycle.LiveData
import uz.gita.todoapp.entity.ToDoEntity

interface Repository {
    fun insert(toDoEntity: ToDoEntity)
    fun delete(toDoEntity: ToDoEntity)
    fun update(toDoEntity: ToDoEntity)
    fun getAll(state:Int): LiveData<List<ToDoEntity>>
    fun getItem(id:Int):ToDoEntity
}