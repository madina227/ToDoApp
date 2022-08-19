package uz.gita.todoapp.viewModel

import androidx.lifecycle.LiveData
import uz.gita.todoapp.entity.ToDoEntity

interface AddVM {

    val titleInputLiveData: LiveData<String>

    val descriptionInputLiveData: LiveData<String>

    val dataInputLiveData: LiveData<String>

    val setSaveLivedata: LiveData<Unit>

    fun insert(toDoEntity: ToDoEntity)

    fun save(title: String, description: String, date:String)


    fun getTitle(title: String)

    fun getDescription(description: String)

}