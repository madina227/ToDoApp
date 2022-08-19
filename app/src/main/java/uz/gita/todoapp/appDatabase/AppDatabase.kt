package uz.gita.todoapp.appDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.todoapp.entity.ToDoEntity
import uz.gita.todoapp.dao.ToDoDao

@Database(entities = [ToDoEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun toDoDao(): ToDoDao

    companion object {
        private var instance: AppDatabase? = null

        fun init(context: Context) {
            instance = Room.databaseBuilder(context, AppDatabase::class.java,"todo.db")
                .allowMainThreadQueries()
                .build()
        }
        fun getInstance() = instance!!
    }
}