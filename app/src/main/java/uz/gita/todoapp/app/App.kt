package uz.gita.todoapp.app

import android.app.Application
import uz.gita.todoapp.appDatabase.ShP
import uz.gita.todoapp.appDatabase.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ShP.init(this)
        AppDatabase.init(this)
    }
}