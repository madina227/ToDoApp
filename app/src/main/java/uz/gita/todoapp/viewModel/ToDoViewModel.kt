package uz.gita.todomockexam.viewModel

import androidx.lifecycle.LiveData
import uz.gita.todoapp.entity.ToDoEntity


interface ToDoViewModel {
    val infoScreen: LiveData<Int>
    val addScreen: LiveData<Unit>
    val editScreen: LiveData<Int>

    val allToDoes: LiveData<List<ToDoEntity>>

    fun triggerInfoScreen(id: Int)
    fun triggerAddScreen()
    fun triggerEditScreen(id: Int)
    fun insert(toDoEntity: ToDoEntity)
    fun delete(toDoEntity: ToDoEntity)
    fun update(toDoEntity: ToDoEntity)
    fun getItem(id: Int): ToDoEntity

    fun getAllTodos(state: Int)

}