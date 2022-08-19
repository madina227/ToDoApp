package uz.gita.todoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.todoapp.entity.ToDoEntity
import uz.gita.todoapp.repository.Repository
import uz.gita.todomockexam.data.repository.impl.RepositoryImpl

class EditViewModel : EditVM, ViewModel() {
    private val repository: Repository = RepositoryImpl()

    override val setUpdateLiveData = MutableLiveData<Unit>()

    override fun update(toDoEntity: ToDoEntity) {
        repository.update(toDoEntity)
        setUpdateLiveData.value = Unit
    }

    override fun getItem(id: Int): ToDoEntity {
        return repository.getItem(id)
    }


}