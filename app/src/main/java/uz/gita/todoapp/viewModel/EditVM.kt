package uz.gita.todoapp.viewModel

import androidx.lifecycle.LiveData
import uz.gita.todoapp.entity.ToDoEntity

interface EditVM {
    val setUpdateLiveData: LiveData<Unit>

    fun update(toDoEntity: ToDoEntity)

    fun getItem(id: Int): ToDoEntity
}