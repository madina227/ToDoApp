package uz.gita.todomockexam.viewModel.impl

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.todoapp.entity.ToDoEntity
import uz.gita.todoapp.repository.Repository
import uz.gita.todomockexam.data.repository.impl.RepositoryImpl
import uz.gita.todomockexam.viewModel.ToDoViewModel

class ToDoViewModelImpl : ToDoViewModel, ViewModel() {
    private val repo: Repository = RepositoryImpl()

    override val infoScreen: MutableLiveData<Int> = MutableLiveData()
    override val addScreen: MutableLiveData<Unit> = MutableLiveData()
    override val editScreen = MutableLiveData<Int>()

    override val allToDoes: MediatorLiveData<List<ToDoEntity>> = MediatorLiveData()

//    init {
//        allToDoes.addSource(repo.getAll()) {
//            allToDoes.value = it
//        }
//    }

    override fun triggerInfoScreen(id: Int) {
        infoScreen.value = id
    }

    override fun triggerAddScreen() {
        addScreen.value = Unit
    }

    override fun triggerEditScreen(id: Int) {
        editScreen.value = id
    }

    override fun insert(toDoEntity: ToDoEntity) {
        repo.insert(toDoEntity)
    }

    override fun delete(toDoEntity: ToDoEntity) {
        repo.delete(toDoEntity)
    }

    override fun update(toDoEntity: ToDoEntity) {
        repo.update(toDoEntity)
    }

    override fun getItem(id: Int): ToDoEntity {
        return repo.getItem(id)
    }

    override fun getAllTodos(state: Int) {
        allToDoes.addSource(repo.getAll(state)) {
            allToDoes.value = it
        }
    }

    override fun deleteAll(list: List<ToDoEntity>) {
        repo.deleteAll(list)
    }
}