package uz.gita.todomockexam.data.repository.impl

import androidx.lifecycle.LiveData
import uz.gita.todoapp.entity.ToDoEntity
import uz.gita.todoapp.repository.Repository
import uz.gita.todoapp.appDatabase.AppDatabase

class RepositoryImpl : Repository {
    private val todoDao = AppDatabase.getInstance().toDoDao()
    override fun insert(toDoEntity: ToDoEntity) {
        todoDao.insert(toDoEntity)
    }

    override fun delete(toDoEntity: ToDoEntity) {
        todoDao.delete(toDoEntity)
    }

    override fun update(toDoEntity: ToDoEntity) {
        todoDao.update(toDoEntity)
    }

    override fun getAll(state:Int): LiveData<List<ToDoEntity>> {
        return todoDao.getToDoes(state)
    }

    override fun getItem(id: Int): ToDoEntity {
       return todoDao.getItem(id)
    }
}