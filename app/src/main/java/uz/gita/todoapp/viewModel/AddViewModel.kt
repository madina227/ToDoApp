package uz.gita.todoapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.todoapp.entity.ToDoEntity
import uz.gita.todoapp.repository.Repository
import uz.gita.todomockexam.data.repository.impl.RepositoryImpl

class AddViewModel : AddVM, ViewModel() {
    val repository: Repository = RepositoryImpl()

    override val titleInputLiveData = MutableLiveData<String>()

    override val descriptionInputLiveData = MutableLiveData<String>()

    override val dataInputLiveData = MutableLiveData<String>()

    override val setSaveLivedata = MutableLiveData<Unit>()

    override fun insert(toDoEntity: ToDoEntity) {
        repository.insert(toDoEntity)
        Log.d("TTT", "insert: Auf")
        setSaveLivedata.value=Unit
    }

    override fun save(title: String, description: String, date: String) {
            //
    }

    override fun getTitle(title: String) {
        titleInputLiveData.value = title
    }

    override fun getDescription(description: String) {
        descriptionInputLiveData.value = description
    }
}